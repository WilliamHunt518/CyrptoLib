package EvoMASSolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Individual {
    private HashMap<Character, Character> alphabet = new HashMap<>();
    private int geneLength = 1182;
    private char[] letters = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();

    public Individual(){
        alphabet = generateRandomSolution();
    }

    public Individual(HashMap<Character, Character> alphabet){  // Informed constructor
        this.alphabet = alphabet;
    }

    public static Character genChar(){
        Random random = new Random();
        char thisChar = (char) (random.nextInt(26) + 97); // (0 to 25) + 65
        return thisChar;
    }

    private HashMap<Character, Character> generateRandomSolution() {
        HashMap<Character, Character> newAlphabet = new HashMap<Character, Character>();
        for (char l : letters){
            char nextChar = genChar();
            while (newAlphabet.containsValue(nextChar)){  // Makes sure we don't add twice
                nextChar = genChar();
            }

            newAlphabet.put(l, nextChar);

        }
        return newAlphabet;
    }

    public Individual mutate(){
        int mutations = 4;
        HashMap<Character, Character> newAlphabet = new HashMap<Character, Character>();
        for (Map.Entry<Character, Character> map : alphabet.entrySet()) {
            newAlphabet.put(map.getKey(), map.getValue());
        }

        for(int i = 0; i< mutations; i++) {
                        // For alphabet mutation, we take 2 random letters and swap their mappings
            Random random = new Random();
            int r1 = random.nextInt(26);
            int r2 = random.nextInt(26);
            while (r1 == r2) {  // Resolves clashes
                r2 = random.nextInt(26);
            }

            //System.out.println("alphabet before: " + newAlphabet.toString());
            char base1 = (char) (r1 + 65);
            char map1 = alphabet.get(base1);
            char base2 = (char) (r2 + 65);
            char map2 = alphabet.get(base2);

            newAlphabet.replace(base1, map2);
            newAlphabet.replace(base2, map1);

            //System.out.println("alphabet after:  " + newAlphabet.toString());
            //System.out.println();
        }

        Individual newIndividual = new Individual(newAlphabet);
        return newIndividual;
    }

    public HashMap<Character, Character> getAlphabet() {
        return alphabet;
    }

    public String toString(){
        return alphabet.toString();
    }

}
