package Solving;

import Cryptography.CharacterMapping;
import Cryptography.MonoAlphabeticSubstitutionCipher;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MonoAlphabeticSubstitutionSolver extends Solver{
    private Character[] masterAlphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    //private
    private HashMap<Character, Character> bestSolutionConfig;

    @Override
    public String getSettingsDescription() {
        return null;
        //TODO ret'n relevant
    }

    @Override
    public String solveCall(String cipherText) {
        HashMap<CharacterMapping, String> possibles = new HashMap<>();
        HashMap<Character, Integer> frequencyDistribution = runFrequencyAnalysis(cipherText);
        for(char c : CharacterMapping.getStandardEnglishDistribution()) {

        }

        return null;



    }

    public HashMap<Character, Integer> runFrequencyAnalysis(String cipherText) {
        HashMap<Character, Integer> freq = new HashMap<>(26);
        cipherText=cipherText.replaceAll("\\s+","");
        for(int i=0; i<cipherText.length(); i++){
            Character c = cipherText.charAt(i) ;
            Integer currentCount = freq.get(c);
            if (currentCount != null) {
                freq.put(c, new Integer(currentCount + 1));
            }
            else {
                freq.put(c, 1);
            }

        }


        System.out.println("here");
        for (Map.Entry<Character, Integer> f : freq.entrySet()) {
            System.out.println(f.getKey()+" = "+f.getValue());
        }
        return freq;


    }



    /*




    @Override
    public String solveCall(String cipherText) {
        HashMap<HashMap<Character, Character>, String> possibles = new HashMap<>();
        String firstFound="";
        ArrayList<HashMap<Character, Character>> allMaps = generateAllMaps();


//cmt
        for(int i=0; i<26; i++){
            MonoAlphabeticSubstitutionCipher mAS = new MonoAlphabeticSubstitutionCipher();
            caeserCipher.setParams(i);
            String plainText = caeserCipher.decryptCall(cipherText);
            //System.out.println("-With i="+i+" solution is: "+plainText);
            //System.out.println("---Checking possibility for words:");

            if(checkSolutionPossible(plainText)){
                if(firstFound.equals("")){
                    firstFound=plainText;
                }
                possibles.put(i, plainText);
            }
        }
        if(possibles.size()==0) {
            System.out.println("No sol'n found");
            return null;
        } else if(possibles.size()!=1) {
            System.out.println("Pruning");
            prune(possibles);
        } else {
            addPossibleResult(firstFound);
        }
        setBestSolutionConfig(getKeyFromVal(getPossibleResults().get(0), possibles));
        return getPossibleResults().get(0);

//endcmt

        return null;

    }

    public void tempMain(){
        System.out.println(generateAllMaps());
    }

    private ArrayList<HashMap<Character,Character>> generateAllMaps() {
        Function<Integer, Integer> mapListSizeGetter = MonoAlphabeticSubstitutionSolver::factorial;
        ArrayList<HashMap<Character, Character>> allMaps = new ArrayList<>();                                            //(mapListSizeGetter.apply(masterAlphabet.length));
        for(Character c : masterAlphabet) {
            allMaps.add(new HashMap<>(c,c));
        }
        //System.out.println(allMaps.size());
        for(int i=0; i<masterAlphabet.length;i++) {
            allMaps=buildMaps(i, allMaps);
        }
        return allMaps;

    }


    private ArrayList<HashMap<Character, Character>> buildMaps(int deltaIndex, ArrayList<HashMap<Character, Character>> builder) {
        System.out.println("DBG - @ buildMaps call for delta="+deltaIndex+" size of builder: "+builder.size());
        //Function<Integer, Integer> mapListSizeGetter = MonoAlphabeticSubstitutionSolver::factorial;
        ArrayList<HashMap<Character, Character>> newBuilder = new ArrayList<>();
        for(HashMap<Character, Character> possibilityToChange : builder){
            for(char alpha : masterAlphabet){
                HashMap<Character, Character> duplicatedPossibility = (HashMap<Character, Character>) possibilityToChange.clone();
                duplicatedPossibility.put(masterAlphabet[deltaIndex], alpha);
                newBuilder.add(duplicatedPossibility);
            }
        }
        return newBuilder;

    }

    private static int factorial(int n){
        if(n==1){
            return 1;
        } else {
            return n*factorial(n-1);
        }
    }

    */


}
