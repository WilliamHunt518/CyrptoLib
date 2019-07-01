import Cryptography.CaeserCipher;
import Cryptography.MonoAlphabeticSubstitutionCipher;
import Solving.CaeserSolver;
import Solving.MonoAlphabeticSubstitutionSolver;

import java.util.ArrayList;

public class MainTest {

    public static void main(String args[]){
        MonoAlphabeticSubstitutionSolver mASSolver = new MonoAlphabeticSubstitutionSolver();
        mASSolver.solveCall("Hello world");

    }


}
