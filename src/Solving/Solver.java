package Solving;

import java.util.ArrayList;

public abstract class Solver {
    private String cipherText;
    private ArrayList<String> knownWords = new ArrayList<>();
    private ArrayList<String> possibleWords= new ArrayList<>();
    private ArrayList<String> possibleResults = new ArrayList<>();
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

    public void addKnownWord(String knownWord){
        this.knownWords.add(knownWord);
    }

    public ArrayList<String> getPossibleWords() {
        return possibleWords;
    }

    public void setPossibleWords(ArrayList<String> possibleWords) {
        this.possibleWords = possibleWords;
    }

    public void addPossibleWord(String possibleWord){
        this.possibleWords.add(possibleWord);
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
