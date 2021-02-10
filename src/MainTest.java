import Cryptography.CaeserCipher;
import Cryptography.MonoAlphabeticSubstitutionCipher;
import Cryptography.VigenereCipher;
import Solving.CaeserSolver;
import Solving.MonoAlphabeticSubstitutionSolver;

import java.util.ArrayList;

public class MainTest {

    public static void main(String[] args){
        VigenereCipher vigenereCipher = new VigenereCipher();
        vigenereCipher.setup("KS");
        //vigenereCipher.encryptCall("wearediscoveredsaveyourself");
        //System.out.println(vigenereCipher.getCipherText());

        vigenereCipher.decryptCall("Oaxkdwsf kldwxvov odoeofdsbq curgyd kl dzo Deadhydn Yiexscaee sf Wmxamz. Rw oftgiwn uvscksukd wmcam sxv zdkqov dzo nsgvax. Zyoonoj, rw pwvl kdswxsdwn sxv clbmqyvwn oslr lrw baqan Hbmckssx wnmmsdayf rw bwmwsnov dzojo. Zo svky whhojswxuov k kzwour vsxpammvli, s cdyo msnwxuo ax zsk chosuaxy gzojo zo’v zseko ly uyfcanwb orsd ly kkq xwhl. Sf vsdwb qosbk, Oaxkdwsf ggedn obadw ktymd lgg onofdk dzkl rsn s wsbcov oxpwml yf rac uravvrgyv. Yfo okk kf ofmgefdwb oslr s mgwhkkc sd sqw pafw, gzojo zo ekjfwvwn sd lrw sffacaldo xyjmwc lrsd lejxwn lrw xwovvw. Dzo gdzoj gsc sd sqw 12, gzof rw nacuynojov k tygu gp yogwwdji oramz rw bwkv ynoj kfn gfwb");
        System.out.println(vigenereCipher.getPlainText());


    }


}
