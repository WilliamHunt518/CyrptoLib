package Tools;

import Cryptography.MonoAlphabeticSubstitutionCipher;

import java.util.*;

public class MASHelper {

    private MonoAlphabeticSubstitutionCipher MASCipher;
    private HashMap<Character, Character> alphabet = new HashMap<>();

    private String cipherText = "Ivy  Yuojiyuo  xdpuqc  uoguiyz  d  mnnk  Mnqujv  pyzusdq  jihzyoi,  Pde  Idqphz  in  snpy  in  ivyuk  vnhjy  xnk  Ivhkjzdc  ygyouow  pydqj.  Idqphz  aysdpy  do  uoxnkpdq  ihink  in  cnhow  Dqayki,  uoiknzhsuow  vup  in  vuwvyk  pdivypdiusj  doz  mvuqnjnmvc.  Noy  nx  ivy  annrj  Idqphz  jvdkyz  fuiv  Dqayki  fdj  d  svuqzkyo’j  jsuyosy annr uo fvusv ivy dhivnk updwuoyz kuzuow dqnowjuzy yqysikusuic ivdi fdj ikdgyquow uojuzy d iyqywkdmv  fuky.  Yuojiyuo  aywdo  in  fnozyk  fvdi  d  quwvi  aydp  fnhqz  qnnr  qury  ux  cnh  snhqz  kho  dqnowjuzy  ui  di  ivy  jdpy  jmyyz.  Ux  quwvi  fyky  d  fdgy,  ivyo  ivy  quwvi  aydp  jvnhqz  dmmydk  jidiunodkc,  qury d xknbyo fdgy. Cyi, uo kydquic, ivy quwvi aydp uj pnguow. Ivuj mdkdzne qyz vup in fkuiy vuj xukji \"jsuyoiuxus  mdmyk\"  di  dwy  16,  \"Ivy  Uogyjiuwdiuno  nx  ivy  Jidiy  nx  Dyivyk  uo  Pdwoyius  Xuyqzj.\"  Ivuj  lhyjiuno  nx  ivy  kyqdiugy  jmyyz  in  ivy  jidiunodkc  najykgyk  doz  ivy  najykgyk  pnguow  fuiv  ivy  quwvi  fdj  d  lhyjiuno ivdi fnhqz znpuodiy vuj ivuoruow xnk ivy oyei 10 cydkj";

    public void run(){
        MASCipher = new MonoAlphabeticSubstitutionCipher();

        alphabet.put('Y', 'e');
        alphabet.put('D', 'a');
        alphabet.put('N', 'o');
        alphabet.put('I', 't');
        alphabet.put('V', 'h');
        alphabet.put('U', 'i');
        alphabet.put('K', 'r');
        alphabet.put('X', 'f');
        alphabet.put('O', 'n');
        alphabet.put('J', 's');
        alphabet.put('M', 'p');
        alphabet.put('Q', 'l');
        alphabet.put('P', 'm');
        alphabet.put('C', 'y');
        alphabet.put('G', 'v');
        alphabet.put('Z', 'd');
        alphabet.put('S', 'c');
        alphabet.put('H', 'u');
        alphabet.put('W', 'g');
        alphabet.put('A', 'b');
        alphabet.put('E', 'x');
        alphabet.put('F', 'w');
        alphabet.put('R', 'k');
        alphabet.put('B', 'z');
        alphabet.put('L', 'q');


        MASCipher.setParams(alphabet);

        System.out.println(MASCipher.decryptCall(cipherText));
    }

    public void runFreqAnalysis(){
        HashMap<Character, Integer> freq = new HashMap<>(26);
        String myCipherText=cipherText.replaceAll("\\s+","");
        for(int i=0; i<myCipherText.length(); i++){
            Character c = myCipherText.charAt(i) ;
            Integer currentCount = freq.get(c);
            if (currentCount != null) {
                freq.put(c, new Integer(currentCount + 1));
            }
            else {
                freq.put(c, 1);
            }

        }

        Comparator<Map.Entry<Character, Integer>> valueComparator = new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2) {
                Integer v1 = e1.getValue();
                Integer v2 = e2.getValue();
                return v2.compareTo(v1);
            }
        };

        List<Map.Entry<Character, Integer>> listOfEntries = new ArrayList<Map.Entry<Character, Integer>>(freq.entrySet());

        // sorting HashMap by values using comparator
        Collections.sort(listOfEntries, valueComparator);


        System.out.println("here");
        for(Map.Entry<Character, Integer> mapping : listOfEntries){
            System.out.println(mapping.getKey() + " ==> " + mapping.getValue());
        }
    }

    public static void main(String[] args) {
        MASHelper MASHelper = new MASHelper();
        //subber.runFreqAnalysis();
        MASHelper.run();
    }


}
