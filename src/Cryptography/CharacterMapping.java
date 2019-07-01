package Cryptography;

import java.util.HashMap;

public class CharacterMapping {
    private HashMap<Character, Character> mapping = new HashMap<>();

    private HashMap<Character, Character> identityMapping(){
        HashMap<Character, Character> alphabet = new HashMap<>(26);
        alphabet.put('A', 'A');
        alphabet.put('B', 'B');
        alphabet.put('C', 'C');
        alphabet.put('D', 'D');
        alphabet.put('E', 'E');
        alphabet.put('F', 'F');
        alphabet.put('G', 'G');
        alphabet.put('H', 'H');
        alphabet.put('I', 'I');
        alphabet.put('J', 'J');
        alphabet.put('K', 'K');
        alphabet.put('L', 'L');
        alphabet.put('M', 'M');
        alphabet.put('N', 'N');
        alphabet.put('O', 'O');
        alphabet.put('P', 'P');
        alphabet.put('Q', 'Q');
        alphabet.put('R', 'R');
        alphabet.put('S', 'S');
        alphabet.put('T', 'T');
        alphabet.put('U', 'U');
        alphabet.put('V', 'V');
        alphabet.put('W', 'W');
        alphabet.put('X', 'X');
        alphabet.put('Y', 'Y');
        alphabet.put('Z', 'Z');

        return alphabet;
    }

    public HashMap<Character, Character> getMapping() {
        return mapping;
    }

    public void setMapping(HashMap<Character, Character> mapping) {
        this.mapping = mapping;
    }

    public void addPair(Character source, Character destination) {
        mapping.put(source, destination);
    }

    public static Character[] getStandardEnglishDistribution(){
        return new Character[] {'e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd', 'l', 'c', 'u', 'm', 'w', 'f', 'g', 'y', 'p', 'b', 'v', 'k', 'j', 'x', 'q', 'z'};
    }
}
