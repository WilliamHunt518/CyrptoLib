package Tools;


import Cryptography.VigenereCipher;

import java.util.*;
import java.util.stream.Collectors;


public class VigenereHelper {
    private VigenereCipher vigenereCipher;

    private String cipherText;
    private ArrayList<String> commonWords = new ArrayList<>();

    private ArrayList<String> possKeys;

    public VigenereHelper(String cipherText){
        this.cipherText = cipherText.replaceAll("[^a-zA-Z ]", "").toUpperCase();
    }


    public void printWordFreq(){
        HashMap<String, Integer> wordCounts = new HashMap<>();

        for (String word : cipherText.split(" ")){
            if(wordCounts.containsKey(word)){
                int currentCount = wordCounts.get(word);
                wordCounts.replace(word, currentCount + 1);
            } else {
                wordCounts.put(word, 1);
            }
        }

        Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                Integer v1 = e1.getValue();
                Integer v2 = e2.getValue();
                return v2.compareTo(v1);
            }
        };

        List<Map.Entry<String, Integer>> listOfEntries = new ArrayList<Map.Entry<String, Integer>>(wordCounts.entrySet());
        // sorting HashMap by values using comparator
        Collections.sort(listOfEntries, valueComparator);


        for(Map.Entry<String, Integer> mapping : listOfEntries){
            System.out.println(mapping.getKey() + " ==> " + mapping.getValue());

            if(mapping.getValue() >= 2){
                commonWords.add(mapping.getKey());
            }
        }
    }

    public void runFreqAnalysis(){
        runFreqAnalysis(cipherText);
    }

    public void runFreqAnalysis(String cipherTextArg){
        HashMap<Character, Integer> freq = new HashMap<>(26);
        String myCipherText=cipherTextArg.replaceAll("\\s+","");
        for(int i=0; i<myCipherText.length(); i++){
            Character c = myCipherText.charAt(i) ;
            Integer currentCount = freq.get(c);
            if (currentCount != null) {
                freq.put(c, new Integer(currentCount + 1));
            }
            else {
                freq.put(c, 1);
            }

        }

        Comparator<Map.Entry<Character, Integer>> valueComparator = new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2) {
                Integer v1 = e1.getValue();
                Integer v2 = e2.getValue();
                return v2.compareTo(v1);
            }
        };

        List<Map.Entry<Character, Integer>> listOfEntries = new ArrayList<Map.Entry<Character, Integer>>(freq.entrySet());

        // sorting HashMap by values using comparator
        Collections.sort(listOfEntries, valueComparator);


        System.out.println("here");
        for(Map.Entry<Character, Integer> mapping : listOfEntries){
            System.out.println(mapping.getKey() + " ==> " + mapping.getValue());
        }


    }

    public void generateGCDTable(){
        HashMap<String, Integer[]> GCDs = new HashMap<>();
        cipherText = cipherText+"  ";

        for(String word : commonWords){
            int spaceNum = 0;
            ArrayList<Integer> thisArr = new ArrayList<>();

            /*
            for(String textWord : cipherText.split(" ")){
                if(textWord.equals(word)){
                    thisArr.add((i + 1) - spaceNum);  // We don't 0 index for line number by convention
                }
                spaceNum++;
            }
             */
            boolean entryFlag = true;
            for(int i = 1; i < cipherText.length(); i++) {  // iterating through the chars for word start
                if (cipherText.toCharArray()[i - 1] == ' ' || entryFlag) {
                    if(entryFlag){
                        entryFlag=false;
                        i = 1;
                    } else {
                        spaceNum += 1;
                    }

                    StringBuilder sb = new StringBuilder();
                    int tempIndex = i;
                    boolean done = false;
                    while(!done){
                        if (cipherText.toCharArray()[tempIndex] == ' ') {
                            done = true;
                        } else {
                            sb.append(cipherText.toCharArray()[tempIndex]);
                        }
                        tempIndex++;
                    }

                    if (sb.toString().equals(word)) {
                        // found occurence
                        thisArr.add((i + 1) - spaceNum);  // We don't 0 index for line number by convention
                    }
                }
            }

            GCDs.put(word, (Integer[]) thisArr.toArray(new Integer[0]));


        }

        for(Map.Entry<String, Integer[]> mapping : GCDs.entrySet()) {
            System.out.println(mapping.getKey() + ":");
            int base = -1;
            ArrayList<Integer> diffs = new ArrayList<>();

            for (Integer record : mapping.getValue()) {
                System.out.println("==> " + record);
                if (base != -1) {
                    diffs.add(record - base);
                }
                base = record;
            }
            int thisGCD = findGCD(diffs);
            System.out.println("This GCD as per Recursive Euclid: " + thisGCD);
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    /***
     * Taken from an online source
     */
    static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    /***
     * Taken from an online source
     */
    static int findGCD(ArrayList<Integer> arr)
    {
        int result = 0;
        for (int element: arr){
            result = gcd(result, element);

            if(result == 1)
            {
                return 1;
            }
        }
        return result;
    }

    public void printSubFreqTables(int period){
        ArrayList<StringBuilder> stringBuilders = new ArrayList<>();
        String myCipherText=cipherText.replaceAll("\\s+","");

        ArrayList<Character> chars = new ArrayList<>(
                myCipherText.chars()
                        .mapToObj(e -> (char) e)
                        .collect(
                                Collectors.toList()
                        )
        );

        for (int i=0; i< period; i++){
            StringBuilder sb = new StringBuilder();
            stringBuilders.add(sb);
        }

        for (int i=0; i < myCipherText.length(); i++){
            char thisChar = chars.get(i);

            stringBuilders.get(i % period).append(thisChar);

        }

        for (int i=0; i< period; i++){
            System.out.println();
            System.out.println("i = "+ i);
            System.out.println();
            runFreqAnalysis(stringBuilders.get(i).toString());
        }


    }

    /*private int dictHeuristic(){

    }

    public int evaluateAttempt(String key){
        vigenereCipher.setup(key);
        String result = vigenereCipher.decryptCall(vigenereCipher.getPlainText());

        return dictHeuristic(result);
    }
    */

    /***
     * Taken from https://stackoverflow.com/questions/17192796/generate-all-combinations-from-multiple-lists
     */
    private void generatePermutations(List<List<Character>> lists, List<String> possKeys, int depth, String current) {
        if (depth == lists.size()) {
            possKeys.add(current);
            return;
        }

        for (int i = 0; i < lists.get(depth).size(); i++) {
            generatePermutations(lists, possKeys, depth + 1, current + lists.get(depth).get(i));
        }
    }

    private String mapKey(String key, char base){
        StringBuilder newKey = new StringBuilder();

        for (char c : key.toCharArray()){
            // e.g. We assume E (69) --> B (66)
            // ==> shift = (66 - 69) = -3
            // ==> thisChar = A - 3 = X

            int shift = c - base;  // ASCII of this char - base
            if (shift < 0){
                shift += 26;  // resets if negative
            }
            char thisChar = (char) (shift + 65);  // maps back to char
            newKey.append(thisChar);
        }
        return newKey.toString();
    }

    /***
     * We assume that E is used as base. This can be customized later
     */
    public void fuzzOptions(ArrayList<List<Character>> options){
        //options = new char[][]{new char[]{'B', 'K', 'X'}, new char[]{'T', 'E'}, new char[]{'C', 'Y'}};



        List<String> possKeys = new ArrayList<String>();


        generatePermutations(options, possKeys, 0, "");

        System.out.println(possKeys.toString());

        ArrayList<String> mappedKeys = new ArrayList<>();
        for (String key : possKeys){
            mappedKeys.add(mapKey(key, 'E'));
        }

        System.out.println(mappedKeys.toString());

        System.out.println();
        System.out.println();
        System.out.println();

        for (String key : mappedKeys){
            vigenereCipher = new VigenereCipher();
            vigenereCipher.setup(key);
            vigenereCipher.setCipherText(cipherText);
            String result = vigenereCipher.decryptCall(vigenereCipher.getCipherText());
            System.out.println("==========================================================================");
            System.out.println("For key: " + key);
            System.out.println(result);
            System.out.println();
        }




    }



    public static void main(String[] args) {
        VigenereHelper vigenereHelper = new VigenereHelper("Gvo wuhxfhhulm qogvlw lp gozxvumn, pzuirb xlqqlm um gvo vfqzmuguoh, uh horwlq fhow um omnumooiumn; vldoeoi, ug xzm yo z eoib fhopfr hfkkroqomg um roxgfio xrzhhoh. Um xllkoizgueo nilfkh qlhg lp gvo rozimumn lxxfih dugv hgfwomgh dlisumn glnogvoi um hqzrr nilfkh. Gvuh qogvlw vzh yoom fhow pli gvo omguio xlfiho li zh z hfkkroqomg um roxgfio xrzhhoh. Z eziuogb lp lgvoi qogvlwh hfxv zh kzmorh li woyzgoh xzm yo fhow gl hkzis hgfwomg umgoiohg zmw omxlfizno hgfwomg umelreoqomg.");
        vigenereHelper.printWordFreq();
        System.out.println();
        vigenereHelper.runFreqAnalysis();
        System.out.println();
        vigenereHelper.generateGCDTable();
        System.out.println();
        System.out.println();
        System.out.println();
        vigenereHelper.printSubFreqTables(3);
        System.out.println();
        System.out.println();
        ArrayList<List<Character>> options = new ArrayList<List<Character>>();
        options.add(Arrays.asList('O', 'G', 'M', 'L', 'H'));
        options.add(Arrays.asList('O', 'G', 'H', 'Z', 'U'));
        options.add(Arrays.asList('H', 'M', 'O', 'I', 'L'));
        vigenereHelper.fuzzOptions(options);

    }

}
