package Cryptography;

public abstract class Cryptor {
    private String plainText="";
    private String cipherText="";

    public void setParams(){}

    public String encryptCall(String plain){
        plainText = plain;
        toCaps();

        try {
            encrypt();
        } catch (Exception e) {
            cipherText = "Failure ";
            e.printStackTrace();
        }
        return cipherText;
    }

    public String decryptCall(String cipher){
        cipherText = cipher;
        toCaps();
        try {
            decrypt();
        } catch (Exception e) {
            cipherText = "Failure ";
            System.out.println("Error in decryption. Printing stack trace: ");
            System.out.println();
            e.printStackTrace();
        }
        return plainText;
    }

    protected abstract void encrypt() throws Exception;

    protected abstract void decrypt() throws Exception;

    private void toCaps(){
        plainText=plainText.toUpperCase();
        cipherText=cipherText.toUpperCase();
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }
}
