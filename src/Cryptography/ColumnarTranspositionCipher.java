package Cryptography;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ColumnarTranspositionCipher extends Cryptor{
    private String key;

    public void setup(String key) {
        this.key = key;
    }

    public Boolean checkKeyEquivalency(String key){
        Integer[] argOrder = calculateIndexOrders(key);
        Integer[] thisOrder = calculateIndexOrders(this.key);
        return Arrays.equals(argOrder, thisOrder);
    }

    private Integer[] calculateIndexOrders(String key){
        int[] intArray = IntStream.rangeClosed(63, 133).toArray();
        int counter = 0;
        Integer[] orders = new Integer[key.length()];
        for(int charValue : intArray){  //go through each char from a onwards
            for(int i = 0; i<key.length(); i++){
                int keyChar = key.toCharArray()[i];  //check this val
                if (charValue == keyChar){
                    orders[i] = counter;
                    counter++;
                    System.out.println("FOUND, cv="+charValue+" ("+(char) charValue+") "+"kc="+keyChar+" ("+(char) keyChar+")");
                }
            }
        }
        return orders;
    }

    @Override
    //NOT fully working
    protected void encrypt() throws Exception {
        StringBuilder[] columns = new StringBuilder[key.length()];
        for (int i = 0; i<key.length(); i++){
            columns[i] = new StringBuilder();
        }
        ArrayList<Character> chars = new ArrayList<>(
                getPlainText().chars()
                        .mapToObj(e -> (char) e)
                        .collect(
                                Collectors.toList()
                        )
        );
        Iterator<Character> charIt = chars.iterator();
        int index = 0;
        while (charIt.hasNext()){
            Character thisChar = charIt.next();
            String substring = thisChar.toString();
            Pattern re = Pattern.compile("[a-zA-Z]+");
            Matcher m = re.matcher(substring);

            if (m.find()) {
                columns[index].append(thisChar);
                index++;
                if (index >= key.length()) {
                    index = 0;
                }
            }
        }
        columns = padColumns(columns);
        StringBuilder finalBuilder = new StringBuilder();
        Integer[] indexOrders = calculateIndexOrders(key);
        for(int sublistIndex : indexOrders){
            finalBuilder.append(columns[sublistIndex]);
        }
        setCipherText(finalBuilder.toString());
    }

    private StringBuilder[] padColumns(StringBuilder[] columns) {
        int maxLength = columns[0].length();  //The first column is always the longest (or joint-longest)
        for (StringBuilder sb : columns){
            if(sb.length()<maxLength){
                sb.append(getRandChar());
            }
        }
        return columns;
    }

    private char getRandChar() {
        int randomNum = ThreadLocalRandom.current().nextInt(65, 91);  //ASCII of a random char
        return (char)randomNum;
    }

    // Unused, generates first N characters for key length = N
    public String runFirstLines(String cipherText, int keyLen, int numLines){
        String letters = "abcdefghijklmnoprstuvwxyz";
        String key = letters.substring(0, keyLen);
        String tempKey = this.key;
        setup(key);
        String result = decryptCall(cipherText).substring(0, keyLen*numLines);
        this.key = tempKey;

        return result;
    }

    @Override
    protected void decrypt() throws Exception {
        int groupLen = (getCipherText().length() / key.length());  //floor div and add 1 (same as ceil div)

        StringBuilder[] columns = new StringBuilder[key.length()];
        for (int i = 0; i<key.length(); i++){
            columns[i] = new StringBuilder();
        }

        Integer[] indexOrders = calculateIndexOrders(key);
        StringBuilder finalBuilder = new StringBuilder();

        for(int groupIndex=0; groupIndex<groupLen ; groupIndex++) {
            for(int sublistIndex : indexOrders) {
                try {
                    finalBuilder.append(getCipherText().toCharArray()[groupIndex + ((sublistIndex) * groupLen)]);
                } catch (Exception e) {
                    System.out.println("PASSING");
                }
            }
        }
        setPlainText(finalBuilder.toString());
    }
}
