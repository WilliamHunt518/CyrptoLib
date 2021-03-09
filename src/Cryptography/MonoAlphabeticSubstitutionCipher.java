package Cryptography;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MonoAlphabeticSubstitutionCipher extends Cryptor{
    private HashMap<Character, Character> alphabet;

    public void setParams(HashMap<Character, Character> alphabet){
        setAlphabet(alphabet);
    }

    @Override
    protected void encrypt() throws Exception {
        char[] plainCharArr = getPlainText().toCharArray();
        char[] cipherCharArr = new char[plainCharArr.length];
        for(int i=0;i<plainCharArr.length;i++){
            if(plainCharArr[i] > 64 && plainCharArr[i] < 133){
                cipherCharArr[i] = translateP2C(plainCharArr[i]);
            } else {
                cipherCharArr[i] = plainCharArr[i];
            }
        }
        setCipherText(String.valueOf(cipherCharArr));
    }

    @Override
    protected void decrypt() throws Exception {
        char[] cipherCharArr = getCipherText().toCharArray();
        char[] plainCharArr = new char[cipherCharArr.length];
        for(int i=0;i<cipherCharArr.length;i++){
            if(cipherCharArr[i] > 64 && cipherCharArr[i] < 133){
                plainCharArr[i] = translateC2P(cipherCharArr[i]);
            } else {
                plainCharArr[i] = cipherCharArr[i];
            }
        }
        setPlainText(String.valueOf(plainCharArr));
    }

    private char translateP2C(char plain) {
        return alphabet.get(plain);
    }

    private char translateC2P(char cipher) {

        Iterator it = getAlphabet().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            if(pair.getKey().equals(cipher)){
                return (char) pair.getValue();
            }

            //it.remove(); // avoids a ConcurrentModificationException [although no multithreading here so unreq'd]
        }
        return '#';
    }

    public HashMap<Character, Character> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(HashMap<Character, Character> inputAlphabet) {
        alphabet = new HashMap<>();
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

        for (Map.Entry<Character, Character> mapping : inputAlphabet.entrySet()){
            alphabet.put(mapping.getKey(), mapping.getValue());
        }
    }

    public void printAlphabet() {
        for (Map.Entry<Character, Character> mapping : alphabet.entrySet()){
            System.out.println(mapping.getKey() + " -> " + mapping.getValue());
        }


    }
}
