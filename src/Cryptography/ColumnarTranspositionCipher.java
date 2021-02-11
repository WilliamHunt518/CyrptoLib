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

    private Integer[] calculateIndexOrders(String key){
        ArrayList<Integer> orders = new ArrayList<>();

        char[] keyAsIntArray = key.toCharArray();

        int[] intArray = IntStream.rangeClosed(64, 133).toArray();

        for(int charValue : intArray){
            for(int i = 0; i<key.length(); i++){
                int keyChar = key.toCharArray()[i];
                if (charValue == keyChar){
                    orders.add(i);
                    //System.out.println("FOUND, cv="+charValue+" ("+(char) charValue+") "+"kc="+keyChar+" ("+(char) keyChar+")");

                }
            }
        }
        //System.out.println("Orders: ");
        //System.out.println();
        //System.out.println(orders);
        return orders.toArray(new Integer[0]);
    }

    @Override
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

    @Override
    protected void decrypt() throws Exception {

    }

}
