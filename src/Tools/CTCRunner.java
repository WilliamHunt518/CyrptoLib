package Tools;

import Cryptography.ColumnarTranspositionCipher;
import Cryptography.MonoAlphabeticSubstitutionCipher;
import EvoMASSolver.Individual;

public class CTCRunner {

    public static void main(String[] args) {

        ColumnarTranspositionCipher ctc = new ColumnarTranspositionCipher();
        String ct = "rouwrtttitseidlasabirhotoouoetdnmhsreeihmeetswrcethoftittafrsoebfownsltntunee";

        char[] letters = "abcdefghijklmnopqrstuvwxyz=".toUpperCase().toCharArray();
        ctc.setup("VOID");

        for(char l1 : letters){
            for(char l2 : letters){
                for(char l3 : letters){
                    for(char l4 : letters) {
                        for(char l5 : letters) {
                            for(char l6 : letters) {
                                for(char l7 : letters) {
                                    String key = String.valueOf(l1) + String.valueOf(l2) + String.valueOf(l3) + String.valueOf(l4) + String.valueOf(l5) + String.valueOf(l6) + String.valueOf(l7);
                                    key = key.replaceAll("=", "");
                                    if (!ctc.checkKeyEquivalency(key)) {
                                        ctc.setup(key);
                                        String output = ctc.decryptCall(ct);
                                        int fitness = CTCRunner.calculateFitness(output);

                                        if (fitness > 10) {
                                            System.out.println("Poss found!");
                                            System.out.println("PT:  " + output);
                                            System.out.println("Fit: " + fitness);
                                            System.out.println("Key: " + key);
                                            System.out.println();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //ctc.setup();
    }

    private static int calculateFitness(String result){
        String[] possWords = {"MUCH", "WEBB", "WAS"};
        int fitness = 0;

        for(int i=0; i<result.length(); i++){  // iter through first chars
            for(String word : possWords){
                if(i + word.length() <= result.length()) {
                    if (result.substring(i, i + word.length()).equals(word)) {
                        fitness+=word.length();
                        //System.out.println("Found: " + word + "in " + result.substring(i, i + word.length()));
                    }
                }
            }
        }

        return fitness;

    }
}
