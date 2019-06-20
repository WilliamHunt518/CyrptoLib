package Cryptography;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MonoAlphabeticSubstitutionCipher extends Cryptor{
    private HashMap<Character, Character> alphabet;

    public void setParams(HashMap<Character, Character> alphabet){
        setAlphabet(alphabet);
    }

    public void setIndividualParam(char source, char dest){ //source is plain, dest is cipher ( p -> c )
        alphabet.put(source, dest);
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

            if(pair.getValue().equals(cipher)){
                return (char) pair.getKey();
            }

            //it.remove(); // avoids a ConcurrentModificationException [although no multithreading here so unreq'd]
        }
        System.out.println();
        return '#';
    }

    public HashMap<Character, Character> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(HashMap<Character, Character> alphabet) {
        this.alphabet = alphabet;
    }
}
