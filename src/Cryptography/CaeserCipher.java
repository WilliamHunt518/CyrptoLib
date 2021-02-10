package Cryptography;

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
            if(plainCharArr[i] > 64 && plainCharArr[i] < 133){
                cipherCharArr[i] = shift(plainCharArr[i], shiftFactor);
            } else {
                cipherCharArr[i] = plainCharArr[i];
            }
        }
        setCipherText(String.valueOf(cipherCharArr));
    }

    @Override
    protected void decrypt() throws Exception{
        char[] cipherCharArr = getCipherText().toCharArray();
        char[] plainCharArr = new char[cipherCharArr.length];
        for(int i=0;i<cipherCharArr.length;i++){
            if(cipherCharArr[i] > 64 && cipherCharArr[i] < 133){
                plainCharArr[i] = shift(cipherCharArr[i], 0-shiftFactor);
            } else {
                plainCharArr[i] = cipherCharArr[i];
            }
        }
        setPlainText(String.valueOf(plainCharArr));

    }

    protected char shift(char c, int shiftFactor) {
        int newAscChar = (int) c + shiftFactor;
        if(newAscChar>90){
            newAscChar-=26;
        } else if(newAscChar<65){
            newAscChar+=26;
        }
        return (char) newAscChar;
    }

    protected char shift(char c) {
        return shift(c, shiftFactor);
    }

    protected char antiShift(char c){
        return shift(c, -shiftFactor);
    }

}
