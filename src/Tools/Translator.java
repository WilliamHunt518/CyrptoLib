package Tools;

import java.util.ArrayList;

public class Translator {
    private int shift=0;
    private int counter=0;
    private int trigger=4;  //means every 4

    public ArrayList<String> hexToDec(ArrayList<String> hexCodes){
        ArrayList<String> translatedCodes = new ArrayList<>();
        for (String code : hexCodes){
            if(counter % trigger == 0) {

                int translatedCode = Integer.parseInt(code, 16);
                //translatedCodes.add(String.valueOf(translatedCode));
                translatedCode = applyShift(translatedCode);


                String translatedChar = Character.toString((char) translatedCode);
                translatedCodes.add(String.valueOf(translatedChar));
            }
            counter++;
        }

        return translatedCodes;
    }

    public int applyShift(int translatedCode){
        translatedCode+=shift;
        if(translatedCode>127){
            translatedCode-=128;
        }
        return translatedCode;

    }

    private ArrayList<String> preProcess(String text){
        ArrayList<String> processed = new ArrayList<>();

        String[] split = text.split(" ");

        for (String s : split){
            try {
                processed.add(s.substring(0, 2));
                processed.add(s.substring(2, 4));
            } catch (Exception e) {
                System.out.println("Completed stream process");
            }
        }

        return processed;
    }

    public void convert(String text){
        ArrayList<String> translated = hexToDec(preProcess(text));
        System.out.println(translated.toString());
    }

    public void setShift(int arg){
        shift = arg;
    }


    public static void main(String[] args) {
        Translator t = new Translator();
        /*
        for(int i = 0; i< 128; i++){
            t.setShift(i);
            t.convert("1206 2e03 3f1d 7a22 2f1c 2900 3606 3406 " +
                    "764f 3b01 3e4f 091b 3b03 3301 7a18 3f1d " +
                    "3f4f 3b03 364f 3400 3706 340e 2e0a 3e4f " +
                    "3c00 284f 2e07 3f4f 1400 380a 364f 0a0a " +
                    "3b0c 3f4f 0a1d 3315 3f62 50");
        }
        */



        t.setShift(54);
        t.convert("1206 2e03 3f1d 7a22 2f1c 2900 3606 3406 " +
                            "764f 3b01 3e4f 091b 3b03 3301 7a18 3f1d " +
                            "3f4f 3b03 364f 3400 3706 340e 2e0a 3e4f " +
                            "3c00 284f 2e07 3f4f 1400 380a 364f 0a0a " +
                            "3b0c 3f4f 0a1d 3315 3f62 50");


    }


}
