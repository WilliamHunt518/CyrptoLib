package Cryptography;

import java.util.Arrays;

public class CaeserCipher extends Cryptor{
    private String plainText;
    private String cipherText;
    private int shiftFactor;

    public void setParams(int shift){
        shiftFactor=shift % 26;
    }

    @Override
    protected void encrypt() throws Exception{
        char[] plainCharArr = getPlainText().toCharArray();
        char[] cipherCharArr = new char[plainCharArr.length];
        for(int i=0;i<plainCharArr.length;i++){
            if(! (plainCharArr[i] == (int) ' ') ){  // if NOT ( that character == space as ASCII char )
                cipherCharArr[i] = shift(plainCharArr[i], shiftFactor);
            } else {
                cipherCharArr[i] = ' ';
            }
        }
        setCipherText(String.valueOf(cipherCharArr));
    }

    @Override
    protected void decrypt() throws Exception{
        char[] cipherCharArr = getCipherText().toCharArray();
        char[] plainCharArr = new char[cipherCharArr.length];
        for(int i=0;i<cipherCharArr.length;i++){
            if(! (cipherCharArr[i] == (int) ' ') ){  // if NOT ( that character == space as ASCII char )
                plainCharArr[i] = shift(cipherCharArr[i], 0-shiftFactor);
            } else {
                plainCharArr[i] = ' ';
            }
        }
        setPlainText(String.valueOf(plainCharArr));

    }

    private char shift(char c, int shiftFactor) {
        int ascChar = (int) c;
        int newAscChar = ascChar+shiftFactor;
        if(newAscChar>90){
            newAscChar-=26;
        } else if(newAscChar<65){
            newAscChar+=26;
        }
        return (char) newAscChar;
    }


}
