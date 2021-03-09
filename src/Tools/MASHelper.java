package Tools;

import Cryptography.MonoAlphabeticSubstitutionCipher;

import java.util.*;

public class MASHelper {

    private MonoAlphabeticSubstitutionCipher MASCipher;
    private HashMap<Character, Character> alphabet = new HashMap<>();

    private String cipherText = "Gvo wuhxfhhulm qogvlw lp gozxvumn, pzuirb xlqqlm um gvo vfqzmuguoh, uh horwlq fhow um omnumooiumn; vldoeoi, ug xzm yo z eoib fhopfr hfkkroqomg um roxgfio xrzhhoh. Um xllkoizgueo nilfkh qlhg lp gvo rozimumn lxxfih dugv hgfwomgh dlisumn glnogvoi um hqzrr nilfkh. Gvuh qogvlw vzh yoom fhow pli gvo omguio xlfiho li zh z hfkkroqomg um roxgfio xrzhhoh. Z eziuogb lp lgvoi qogvlwh hfxv zh kzmorh li woyzgoh xzm yo fhow gl hkzis hgfwomg umgoiohg zmw omxlfizno hgfwomg umelreoqomg.";

    public void run(){
        MASCipher = new MonoAlphabeticSubstitutionCipher();

        alphabet.put('O', 'e');  // freq analysis

        alphabet.put('G', 't');  // freq analysis
        alphabet.put('V', 'h');  // follows for "the"

        //ASSUME alphabet.put('Z', 'i');  // standalone

        alphabet.put('Z', 'a');  // standalone// "_e i"  => "be I", "me I", "we I" => None of these make sense, so Z must be A

        alphabet.put('Y', 'b');  // "_e a"  => "be a"

        alphabet.put('M', 'n');  // "th__ _eth__ ha_ bee_" => "has been" or "had been" (it's unlikely anything else like "beef")

        // "th__", not "that" or "the_" => must be "this"
        alphabet.put('U', 'i');
        alphabet.put('H', 's');

        // "_ebates _an be" & "it _an be a"
        alphabet.put('X', 'c');
        alphabet.put('W', 'd');  // could be rebates, but unlikely

        // "disc_ssi_n" => "discussion"
        alphabet.put('F', 'u');
        alphabet.put('L', 'o');

        // "_ethod" => "method"
        alphabet.put('Q', 'm');

        // "oP teachin_" => "of teaching"
        alphabet.put('P', 'f');
        alphabet.put('N', 'g');

        // "fai___ common" => "fairly common"
        alphabet.put('I', 'r');
        alphabet.put('R', 'l');
        alphabet.put('B', 'y');

        // "_ery" => very
        alphabet.put('E', 'v');

        // "su__lement" => "Supplement
        alphabet.put('K', 'p');

        // "_ith"
        alphabet.put('D', 'w');

        // "wor_ing"
        alphabet.put('S', 'k');


        MASCipher.setParams(alphabet);
        System.out.println(MASCipher.decryptCall(cipherText));
        System.out.println();
        System.out.println();
        MASCipher.printAlphabet();
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
            System.out.println(mapping.getKey() + " appears " + mapping.getValue() + " time(s)");
        }
    }

    public static void main(String[] args) {
        MASHelper MASHelper = new MASHelper();
        MASHelper.runFreqAnalysis();
        MASHelper.run();
    }

}
