package Tools;


import Cryptography.VigenereCipher;

import java.util.*;
import java.util.stream.Collectors;

public class VigenereHelper {
    private VigenereCipher vigenereCipher;

    private String cipherText = "qhc jeqpaeb srxrrp hcoe pbccktjv hyoduxrc qrmgalp hyse yqtpxcrbd ree yqtcktgln mc gmsepkmcktq xnb oeqbapzhcos mke mc tfb myfn alnabrlp iq qhyq ilqeeoarbd afrarirp il jijftyoy mo cpftgzaj fndoaqqrsztsoe ympjfcyqimks aluja bc jajfcgluqiy kxngmujxtca dsoild tfb mykudxcrrrgkg nooabsq thgzh mctck tyheq mlyze yyrmxd flwcsep pilze reepb hyse zbel ko pbpmotca hyoduxrc qrmgalp il mryztgze wbt jftrie gp kllwl xbmrt flw qrcf x tpljyk wmrlb iomh lghe ykd flw fxrb qo gjpjbmckt mke gk ppxcrfcc lnc bxyjpjb iq aonxnr qrmgal qhgp cyk bc rsca tm zokmrmjiqb tfb sczupftw lf y jeykildfsi rcxl ulrja tyogcq wfflc xvmfdgkg bbtcztgln zv fskcrfolxl rbsrfne xs ublj xs roohxn bbtcztgln kbcfxngpmq  puae tpljyks axn zb uqbd rl eqqaziiqe a ffdbbn qfdc zhyknci il xn mqhcowgpe qfdc zhyknci rcpiqqalq dcpiek tffs roohxn bleq kor zhykgc qhc ioefc txlsb od xnw darb bsq ilptcxd aealdeq lnjv tfb pmtep mrmcijb od qwm darbs yk etxlsxtmo wfl iq kor xwyoe mc tfb tpljyk cyknmq arqaah tfb tpljyk dcpiek uqfne zokjol pibb cfxnlbl yqtyzkq qhc lwlbr mc tfb tpljyk hmtetbr axn spe ffs ikouiebde mc tfb tpljyk pmtep jobbl rl eqqaziiqe a ffdbbn qfdc zhyknci tfxt pblgxbjv lcxkq lur peaoer hewp tffs gp tfb ela od qhc jeqpaeb";
    private ArrayList<String> commonWords = new ArrayList<>();


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
        HashMap<Character, Integer> freq = new HashMap<>(26);
        String myCipherText=cipherText.replaceAll("\\s+","");
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

    static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // Function to find gcd of array of
    // numbers
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



    public static void main(String[] args) {
        VigenereHelper vigenereHelper = new VigenereHelper();
        vigenereHelper.printWordFreq();
        System.out.println();
        vigenereHelper.runFreqAnalysis();
        System.out.println();
        vigenereHelper.generateGCDTable();
    }

}
