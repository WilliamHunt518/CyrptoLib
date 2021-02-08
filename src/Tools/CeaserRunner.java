package Tools;

import Cryptography.CaeserCipher;
import Solving.CaeserSolver;

public class CeaserRunner {
    private CaeserCipher caeserCipher;
    private CaeserSolver caeserSolver;

    void run(){
        caeserCipher = new CaeserCipher();
        caeserSolver = new CaeserSolver();

        String cipherText = "Qxphurxv   vwxglhv   kdyh   lghqwlilhg   wkh   xqfdqqb   lqyhuvh   uhodwlrqvkls   ehwzhhq   vprnlqj   dqg   Sdunlqvrq'v  glvhdvh.  Orqj-whup  vprnhuv  duh  vrphkrz  surwhfwhg  djdlqvw  Sdunlqvrq'v,  dqg  lw'v  qrw  ehfdxvh vprnhuv glh ri rwkhu wklqjv hduolhu. Wkh prvw uhfhqw, zhoo-frqgxfwhg vwxgb zdv sxeolvkhg lq  d  Pdufk  2010  lvvxh  ri  wkh  mrxuqdo  Qhxurorjb.  Idu  iurp  ghwhuplqlqj  d  fdxvh  iru  wkh  surwhfwlyh  hiihfw, wkhvh uhvhdufkhuv irxqg wkdw wkh qxpehu ri bhduv vshqw vprnlqj, pruh vr wkdq wkh qxpehu ri fljduhwwhv vprnhg gdlob, pdwwhuhg pruh iru d vwurqjhu surwhfwlyh hiihfw. Kduydug uhvhdufkhuv zhuh  dprqj  wkh  iluvw  wr  surylgh  frqylqflqj  hylghqfh  wkdw  vprnhuv  zhuh  ohvv  olnhob  wr  ghyhors  Sdunlqvrq'v.  Lq  d  vwxgb  sxeolvkhg  lq  Qhxurorjb  lq  Pdufk  2007,  wkhvh  uhvhdufkhuv  irxqg  wkh  surwhfwlyh  hiihfw  zdqhv  diwhu  vprnhuv  txlw.  Dqg  wkhb  frqfoxghg,  lq  wkhlu  vshfldo  vflhqwlilf  zdb,  wkdw wkhb glgq'w kdyh d foxh dv wr zkb";
        String[] possWords = {"AND", "THE", "BE", "TO", "OF", "A", "IN", "THAT", "HAVE", "I", "IT"};  //some common english stopwords

        for (String word : possWords){
            caeserSolver.addPossibleWord(word);
        }

        caeserSolver.setPossiblilityDepth(5);

        String pt = caeserSolver.partialSolveCall(cipherText);

        System.out.println("Result: " + pt);
        System.out.println();
        System.out.println("SHIFT = " + caeserSolver.getBestSolutionConfig());


        caeserSolver = new CaeserSolver();
    }

    public static void main(String[] args) {
        CeaserRunner cs = new CeaserRunner();
        cs.run();
    }
}
