package EvoMASSolver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Population {
    private Random random = new Random();
    private int populationSize = 500;
    ArrayList<Individual> members = new ArrayList<>();

    public Population(int popSize){
        populationSize = popSize;
        for (int i = 0; i < populationSize; i++){
            members.add(new Individual());
        }
    }

    public void printPopulation(){
        for (Individual m : members){
            System.out.println(m.toString());
        }
    }

    public Iterator<Individual> getMembersIt(){
        return members.iterator();
    }

    public Individual pick(){
        int roll = random.nextInt(populationSize);
        return members.get(roll);
    }

    public void replace(Individual toKill, Individual toInsert){
        //System.out.println("Killing: " + toKill.toString());
        //System.out.println("Replacing: " + toInsert.toString());
        //System.out.println();

        members.set(members.indexOf(toKill), toInsert);  // Finds index of toKill and places toInsert. GC will then kill it
    }



}
