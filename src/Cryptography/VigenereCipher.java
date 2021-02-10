package Cryptography;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class VigenereCipher extends Cryptor{
    private String key;
    private ArrayList<CaeserCipher> caesers;

    public void setup(String key){
        this.key = key.toUpperCase();
        caesers = new ArrayList<>();

        for (int c : this.key.toCharArray()){
            int shift = c - 65; // Means that e.g. char of A gives us shift of 0; b->1; c->2;...
            CaeserCipher newCaeser = new CaeserCipher();
            newCaeser.setParams(shift);
            caesers.add(newCaeser);
        }
    }

    @Override
    public void encrypt(){
        StringBuilder ct = new StringBuilder();
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

            if (m.find()){
                Character mapped = caesers.get(index).shift(thisChar);
                ct.append(mapped);
                index++;
                if(index >= caesers.size()){
                    index = 0;
                }
            } else {
                ct.append(thisChar);
            }

        }

        setCipherText(ct.toString());

    }

    @Override
    protected void decrypt() throws Exception {
        StringBuilder pt = new StringBuilder();
        ArrayList<Character> chars = new ArrayList<>(
                getCipherText().chars()
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

            if (m.find()){
                Character mapped = caesers.get(index).antiShift(thisChar);
                pt.append(mapped);
                index++;
                if (index >= caesers.size()) {
                    index = 0;
                }
            } else {
                pt.append(thisChar);
            }
        }

        setPlainText(pt.toString());
    }

}
