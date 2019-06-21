package Solving;

import java.util.ArrayList;

public abstract class Solver {
    private String cipherText;
    private ArrayList<String> knownWords;
    private ArrayList<String> possibleWords;
    private ArrayList<String> possibleResults;
    private int possiblilityDepth;

    public abstract String getSettingsDescription();

    public abstract String solveCall(String cipherText);

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    public ArrayList<String> getKnownWords() {
        return knownWords;
    }

    public void setKnownWords(ArrayList<String> knownWords) {
        this.knownWords = knownWords;
    }

    public ArrayList<String> getPossibleWords() {
        return possibleWords;
    }

    public void setPossibleWords(ArrayList<String> possibleWords) {
        this.possibleWords = possibleWords;
    }

    public ArrayList<String> getPossibleResults() {
        return possibleResults;
    }

    public void setPossibleResults(ArrayList<String> possibleResults) {
        this.possibleResults = possibleResults;
    }

    public void addPossibleResult(String possibleResult){
        this.possibleResults.add(possibleResult);
    }

    public int getPossiblilityDepth() {
        return possiblilityDepth;
    }

    public void setPossiblilityDepth(int possiblilityDepth) {
        this.possiblilityDepth = possiblilityDepth;
    }

}
