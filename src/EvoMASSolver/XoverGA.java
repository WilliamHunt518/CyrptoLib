package EvoMASSolver;

import Cryptography.MonoAlphabeticSubstitutionCipher;
import Solving.MonoAlphabeticSubstitutionSolver;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class XoverGA {
    private Population p;

    String[] possWords = {"AND", "THE", "BE", "TO", "OF", "A", "IN", "THAT", "HAVE", "HAS", "WITH", "I", "IT", "IS", "AN", "METHOD", "TEACHING", "HOWEVER", "TOGETHER"};  //some common english stopwords
    private String cipher = "Gvo wuhxfhhulm qogvlw lp gozxvumn, pzuirb xlqqlm um gvo vfqzmuguoh, uh horwlq fhow um omnumooiumn; vldoeoi, ug xzm yo z eoib fhopfr hfkkroqomg um roxgfio xrzhhoh. Um xllkoizgueo nilfkh qlhg lp gvo rozimumn lxxfih dugv hgfwomgh dlisumn glnogvoi um hqzrr nilfkh. Gvuh qogvlw vzh yoom fhow pli gvo omguio xlfiho li zh z hfkkroqomg um roxgfio xrzhhoh. Z eziuogb lp lgvoi qogvlwh hfxv zh kzmorh li woyzgoh xzm yo fhow gl hkzis hgfwomg umgoiohg zmw omxlfizno hgfwomg umelreoqomg.";
    char[] letters = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();

    //private String target = "METHINKS IT IS LIKE A WEASEL";

    public XoverGA(){
        p = new Population(100);

    }

    public void run(){
        int index = 0;
        boolean done = false;

        int bestFitness = 0;
        Individual bestSoln = new Individual();

        while (!done){

            //0. Debug
            if (index % 10000 == 0){
                System.out.println();
                System.out.println("On run: " + index + ", Sol'n = " + bestSoln + ", fit = "+ bestFitness);
                System.out.println("Alphabet = " + bestSoln.getAlphabet().toString());
                MonoAlphabeticSubstitutionCipher mas = new MonoAlphabeticSubstitutionCipher();
                mas.setParams(bestSoln.getAlphabet());
                mas.decryptCall(cipher);
                String plain = mas.getPlainText();
                System.out.println("Which makes: " + plain);
                System.out.println();
                System.out.println();
            }

            //1a. Select 2 individuals from the population
            Individual indA = p.pick();
            Individual indB = p.pick();
            while (indA == indB){
                //System.out.println("A");
                // For the unlikely event of picking the same thing twice (or more)
                indB = p.pick();
            }
            //2a. Select the BEST parent from the two
            Individual parent1;
            if (calculateFitness(indA) > calculateFitness(indB)){
                parent1 = indA;
            } else {
                parent1 = indB;
            }

            //1b. Select another 2 individuals from the population
            Individual indC = p.pick();
            Individual indD = p.pick();
            while (indC == indD || indC == indA || indC == indB || indD == indA || indD == indB){
                //System.out.println("B");
                // For the unlikely event of picking the same thing twice (or more)
                indC = p.pick();
                indD = p.pick();
            }
            //2b. Select the BEST parent from the two
            Individual parent2;
            if (calculateFitness(indC) > calculateFitness(indD)){
                parent2 = indC;
            } else {
                parent2 = indD;
            }

            //3. Create a child and mutate it
            Individual child = crossover(parent1, parent2);
            child = child.mutate();

            //4. Choose 2 new Individuals
            Individual indE = p.pick();
            Individual indF = p.pick();
            while (indE == indF || indE == indA || indE == indB || indE == indC || indE == indD ||  indF == indA || indF == indB || indF == indC || indF == indD){
                //System.out.println("C");
                // For the unlikely event of picking the same thing twice (or more)
                indE = p.pick();
                indF = p.pick();
            }

            //5. Select the WORST of these two
            if (calculateFitness(indE) > calculateFitness(indF)){
                p.replace(indF, child);
            } else {
                p.replace(indE, child);
            }

            int childFitness = calculateFitness(child);


            if (childFitness > bestFitness){
                bestFitness = childFitness;
                bestSoln = child;
            }

            if (childFitness == 60){
                done = true;
            }
            index++;

            /*if (index % 10000 == 0) {
                //p.printPopulation();
                System.out.println("On run: " + index + ", random sol'n:");
                System.out.println("Alphabet = " + child.getAlphabet().toString());
                System.out.println("fit = " + calculateFitness(child));
                MonoAlphabeticSubstitutionCipher mas = new MonoAlphabeticSubstitutionCipher();
                mas.setParams(child.getAlphabet());
                mas.decryptCall(cipher);
                String plain = mas.getPlainText();
                System.out.println("Which makes: " + plain);
                System.out.println();
            }
             */


        }
        System.out.println("After " + index + " runs:");
        System.out.println("    -> Solution = " + bestSoln);
        System.out.println("    -> Fitness = " + bestFitness);

        System.out.println();
        System.out.println();
        System.out.println("==========================================================================");
        System.out.println("==========================================================================");
        System.out.println("==========================================================================");
        System.out.println("On run: " + index + ", Sol'n = " + bestSoln + ", fit = "+ bestFitness);
        System.out.println("Alphabet = " + bestSoln.getAlphabet().toString());
        MonoAlphabeticSubstitutionCipher mas = new MonoAlphabeticSubstitutionCipher();
        mas.setParams(bestSoln.getAlphabet());
        mas.decryptCall(cipher);
        String plain = mas.getPlainText();
        System.out.println("Which makes: " + plain);
        System.out.println();
        System.out.println("==========================================================================");
        System.out.println("==========================================================================");
        System.out.println("==========================================================================");
        System.out.println();
        System.out.println();
    }

    private HashMap<Character, Character> tryAdd(HashMap<Character, Character> alphabet, char base, char map){
        if (!alphabet.containsKey(base) && !alphabet.containsValue(map)) {
            alphabet.put(base, map);
        }

        return alphabet;
    }


    private Individual crossover(Individual a, Individual b){
        Random random = new Random();

        HashMap<Character, Character> newAlphabet = new HashMap<>();

        // Should make 26 passes, each time trying to fit a pair at random. This means it will collide often and only
        // change about half the values probably
        for (int i=0; i<26; i++){
            int pos = random.nextInt(26);


            int roll = random.nextInt(2);  // 1 in 2 chance

            if(roll == 0){  // Makes a 1/L chance
                newAlphabet = tryAdd(newAlphabet, letters[pos], a.getAlphabet().get(letters[pos]));
            } else {
                newAlphabet = tryAdd(newAlphabet, letters[pos], b.getAlphabet().get(letters[pos]));
            }
        }

        // Fill in the remaining ones at random
        for(char l : letters){
            if (!newAlphabet.containsKey(l)){
                boolean done = false;
                char next = '-';
                while (!done){
                    next = Individual.genChar();

                    if (!newAlphabet.containsValue(next)){
                        done = true;
                    }
                }
                newAlphabet.put(l, next);
            }
        }

        Individual child = new Individual(newAlphabet);

        return child;



    }

    private int calculateFitness(Individual individual){
        MonoAlphabeticSubstitutionCipher mas = new MonoAlphabeticSubstitutionCipher();
        mas.setParams(individual.getAlphabet());
        mas.decryptCall(cipher);
        String plain = mas.getPlainText();

        String[] words = plain.split(" ");

        int fitness = 0;
        for (String w : words){
            for (String pw : possWords) {
                if (pw.toLowerCase().equals(w)){
                    //fitness++;
                    fitness += pw.length();  // value is proportionate to word length (complexity)
                }
            }
        }

        return fitness;

    }

}
