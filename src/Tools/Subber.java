package Tools;

import Cryptography.MonoAlphabeticSubstitutionCipher;

import java.util.HashMap;

public class Subber{

    private MonoAlphabeticSubstitutionCipher MASCipher;
    private HashMap<Character, Character> alphabet = new HashMap<>();

    private String cipherText = "Ivy  Yuojiyuo  xdpuqc  uoguiyz  d  mnnk  Mnqujv  pyzusdq  jihzyoi,  Pde  Idqphz  in  snpy  in  ivyuk  vnhjy  xnk  Ivhkjzdc  ygyouow  pydqj.  Idqphz  aysdpy  do  uoxnkpdq  ihink  in  cnhow  Dqayki,  uoiknzhsuow  vup  in  vuwvyk  pdivypdiusj  doz  mvuqnjnmvc.  Noy  nx  ivy  annrj  Idqphz  jvdkyz  fuiv  Dqayki  fdj  d  svuqzkyo’j  jsuyosy annr uo fvusv ivy dhivnk updwuoyz kuzuow dqnowjuzy yqysikusuic ivdi fdj ikdgyquow uojuzy d iyqywkdmv  fuky.  Yuojiyuo  aywdo  in  fnozyk  fvdi  d  quwvi  aydp  fnhqz  qnnr  qury  ux  cnh  snhqz  kho  dqnowjuzy  ui  di  ivy  jdpy  jmyyz.  Ux  quwvi  fyky  d  fdgy,  ivyo  ivy  quwvi  aydp  jvnhqz  dmmydk  jidiunodkc,  qury d xknbyo fdgy. Cyi, uo kydquic, ivy quwvi aydp uj pnguow. Ivuj mdkdzne qyz vup in fkuiy vuj xukji \"jsuyoiuxus  mdmyk\"  di  dwy  16,  \"Ivy  Uogyjiuwdiuno  nx  ivy  Jidiy  nx  Dyivyk  uo  Pdwoyius  Xuyqzj.\"  Ivuj  lhyjiuno  nx  ivy  kyqdiugy  jmyyz  in  ivy  jidiunodkc  najykgyk  doz  ivy  najykgyk  pnguow  fuiv  ivy  quwvi  fdj  d  lhyjiuno ivdi fnhqz znpuodiy vuj ivuoruow xnk ivy oyei 10 cydkj";

    public void run(){
        MASCipher = new MonoAlphabeticSubstitutionCipher();

        alphabet.put('Y', 'e');
        alphabet.put('I', 't');


        MASCipher.setParams(alphabet);

        System.out.println(MASCipher.decryptCall(cipherText));
    }

    public static void main(String[] args) {
        Subber subber = new Subber();
        subber.run();
    }


}
