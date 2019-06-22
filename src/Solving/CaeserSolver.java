package Solving;

import Cryptography.CaeserCipher;

import java.util.*;

public class CaeserSolver extends Solver{
    private int shift;
    private int bestSolutionConfig;

    @Override
    public String getSettingsDescription() {
        return null;
        //TODO return a relevant thing
    }

    @Override
    public String solveCall(String cipherText) {
        HashMap<Integer, String> possibles = new HashMap<>();
        String firstFound="";
        for(int i=0; i<26; i++){
            CaeserCipher caeserCipher = new CaeserCipher();
            caeserCipher.setParams(i);
            String plainText = caeserCipher.decryptCall(cipherText);
            //System.out.println("-With i="+i+" solution is: "+plainText);
            //System.out.println("---Checking possibility for words:");

            if(checkSolutionPossible(plainText)){
                if(firstFound.equals("")){
                    firstFound=plainText;
                }
                possibles.put(i, plainText);
            }
        }
        if(possibles.size()==0) {
            System.out.println("No sol'n found");
            return null;
        } else if(possibles.size()!=1) {
            System.out.println("Pruning");
            prune(possibles);
        } else {
            addPossibleResult(firstFound);
        }
        setBestSolutionConfig(getKeyFromVal(getPossibleResults().get(0), possibles));
        return getPossibleResults().get(0);

    }



    private Boolean checkSolutionPossible(String plainText) {
        for(String word : getKnownWords()){
            if(!plainText.contains(word)){
                return false;
            }
        }
        return true;
    }


    private ArrayList<String> prune(HashMap<Integer, String> possibles) {
        HashMap<String, Integer> rankedPossibilites = new HashMap<>();
        for (Map.Entry<Integer, String> possible : possibles.entrySet()) {
            int matches=0;
            for(String word : getPossibleWords()){
                if(possible.getValue().contains(word)){
                    matches++;
                }
            }
            rankedPossibilites.put(possible.getValue(), matches);
        }


        System.out.println("at prune : ");
        setPossibleResults(getTopN(rankedPossibilites));

        return getPossibleResults();

    }

    private ArrayList<String> getTopN(HashMap<String,Integer> rankedPossibilites) {
        Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                Integer v1 = e1.getValue();
                Integer v2 = e2.getValue();
                return v1.compareTo(v2);
            }
        };

        // Sort method needs a List, so let's first convert Set to List in Java
        List<Map.Entry<String, Integer>> listOfEntries = new ArrayList<Map.Entry<String, Integer>>(rankedPossibilites.entrySet());

        // sorting HashMap by values using comparator
        Collections.sort(listOfEntries, valueComparator);

        LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String, Integer>(listOfEntries.size());

        // copying entries from List to Map
        for(Map.Entry<String, Integer> entry : listOfEntries){
            sortedByValue.put(entry.getKey(), entry.getValue());
        }

        System.out.println("HashMap after sorting entries by values ");
        Set<Map.Entry<String, Integer>> entrySetSortedByValue = sortedByValue.entrySet();

        for(Map.Entry<String, Integer> mapping : entrySetSortedByValue){
            System.out.println(mapping.getKey() + " ==> " + mapping.getValue());
        }


        //SORTED
        ArrayList<String> topNPossibilities = new ArrayList<>();
        for(int i = 0; i < getPossiblilityDepth(); i++){
            topNPossibilities.add(listOfEntries.get(i).getKey());
        }

        return topNPossibilities;

    }

    private int getKeyFromVal(String val, HashMap<Integer, String> possibles) {
        for(Map.Entry<Integer, String> pair : possibles.entrySet()){
            if(pair.getValue().equals(val)){
                return pair.getKey();
            }
        }
        return -1000;
    }

    public int getBestSolutionConfig() {
        return bestSolutionConfig;
    }

    public void setBestSolutionConfig(int bestSolutionConfig) {
        this.bestSolutionConfig = bestSolutionConfig;
    }


}
