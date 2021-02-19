package Tools;

import Cryptography.CaeserCipher;
import Solving.CaeserSolver;

public class CeaserRunner {
    private CaeserCipher caeserCipher;
    private CaeserSolver caeserSolver;

    void run(){
        caeserCipher = new CaeserCipher();
        caeserSolver = new CaeserSolver();

        String cipherText = "Gvo wuhxfhhulm qogvlw lp gozxvumn, pzuirb xlqqlm um gvo vfqzmuguoh, uh horwlq fhow um omnumooiumn; vldoeoi, ug xzm yo z eoib fhopfr hfkkroqomg um roxgfio xrzhhoh. Um xllkoizgueo nilfkh qlhg lp gvo rozimumn lxxfih dugv hgfwomgh dlisumn glnogvoi um hqzrr nilfkh. Gvuh qogvlw vzh yoom fhow pli gvo omguio xlfiho li zh z hfkkroqomg um roxgfio xrzhhoh. Z eziuogb lp lgvoi qogvlwh hfxv zh kzmorh li woyzgoh xzm yo fhow gl hkzis hgfwomg umgoiohg zmw omxlfizno hgfwomg umelreoqomg.";

        String[] possWords = {"AND", "THE", "BE", "TO", "OF", "A", "IN", "THAT", "HAVE", "I", "IT", "IS", "AN"};  //some common english stopwords

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
