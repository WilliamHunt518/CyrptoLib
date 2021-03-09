package Tools;

import Cryptography.CaeserCipher;
import Solving.CaeserSolver;

public class CeaserRunner {
    private CaeserCipher caeserCipher;
    private CaeserSolver caeserSolver;

    void run(){
        caeserCipher = new CaeserCipher();
        caeserSolver = new CaeserSolver();

        String cipherText = "EFDAFCFBEFBB";
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
