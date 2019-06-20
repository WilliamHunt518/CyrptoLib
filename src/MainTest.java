import Cryptography.CaeserCipher;

public class MainTest {

    public static void main(String args[]){
        CaeserCipher caeserCipher = new CaeserCipher();
        caeserCipher.setParams(5);
        System.out.println("===============");
        String result = caeserCipher.encryptCall("HElLO WOrLd");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("result: "+result);

        System.out.println();
        System.out.println();
        System.out.println();
        caeserCipher = new CaeserCipher();
        caeserCipher.setParams(5);
        System.out.println("===============");
        result = caeserCipher.decryptCall("MJQQT BTWQI");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("result: "+result);

        result = caeserCipher.encryptCall("Hdsfgsdg");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("result: "+result);
    }


}
