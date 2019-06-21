import Cryptography.CaeserCipher;
import Solving.CaeserSolver;

import java.util.ArrayList;

public class MainTest {

    public static void main(String args[]){
        CaeserCipher caeserCipher = new CaeserCipher();
        caeserCipher.setParams(14);


        CaeserSolver caeserSolver = new CaeserSolver();
        ArrayList<String> known = new ArrayList<>();
        known.add("INTELLIGENT");
        caeserSolver.setKnownWords(known);
        System.out.println("Cracking...");
        System.out.println(caeserSolver.solveCall("WT O AOQVWBS WG SLDSQHSR HC PS WBTOZZWPZS, WH QOBBCH OZGC PS WBHSZZWUSBH"));
        System.out.println("Found using settings: ");
        System.out.println(caeserSolver.getBestSolutionConfig());

    }


}
