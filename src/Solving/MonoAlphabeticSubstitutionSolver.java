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

    String[] possWords = {"AND", "THE", "BE", "TO", "OF", "A", "IN", "THAT", "HAVE", "I", "IT", "IS", "AN"};  //some common english stopwords

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


}
