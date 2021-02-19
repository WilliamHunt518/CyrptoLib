package EvoMASSolver;

public class Main {

    public static void main(String[] args) {
        //GeneticAlgorithm ga = new GeneticAlgorithm();
        XoverGA ga = new XoverGA();


        long startTime = System.nanoTime();
        ga.run();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);  //divide by 1000000 to get millisecond

        System.out.println("Took " + (duration / 1000000) + "ms");

    }
}
