import Cryptography.CaeserCipher;
import Cryptography.ColumnarTranspositionCipher;
import Cryptography.MonoAlphabeticSubstitutionCipher;
import Cryptography.VigenereCipher;
import Solving.CaeserSolver;
import Solving.MonoAlphabeticSubstitutionSolver;

import java.util.ArrayList;

public class MainTest {

    public static void main(String[] args){
        ColumnarTranspositionCipher columnarTranspositionCipher = new ColumnarTranspositionCipher();

        columnarTranspositionCipher.setup("fdbgcae");
        columnarTranspositionCipher.decryptCall("rouwrtttitseidlasabirhotoouoetdnmhsreeihmeetswrcethoftittafrsoebfownsltntunee");
        System.out.println(columnarTranspositionCipher.getCipherText());
    }
}
