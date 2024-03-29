import Cryptography.CaeserCipher;
import Cryptography.MonoAlphabeticSubstitutionCipher;
import Solving.CaeserSolver;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MasterTest {
    private String shortPlain = "HELLO WORLD";
    private String midPlain = "IF A MACHINE IS EXPECTED TO BE INFALLIBLE, IT CANNOT ALSO BE INTELLIGENT";
    private String longPlain = "BUT I MUST EXPLAIN TO YOU HOW ALL THIS MISTAKEN IDEA OF DENOUNCING PLEASURE AND PRAISING PAIN WAS BORN AND I WILL GIVE YOU A COMPLETE ACCOUNT OF THE SYSTEM, AND EXPOUND THE ACTUAL TEACHINGS OF THE GREAT EXPLORER OF THE TRUTH, THE MASTER-BUILDER OF HUMAN HAPPINESS. NO ONE REJECTS, DISLIKES, OR AVOIDS PLEASURE ITSELF, BECAUSE IT IS PLEASURE, BUT BECAUSE THOSE WHO DO NOT KNOW HOW TO PURSUE PLEASURE RATIONALLY ENCOUNTER CONSEQUENCES THAT ARE EXTREMELY PAINFUL. NOR AGAIN IS THERE ANYONE WHO LOVES OR PURSUES OR DESIRES TO OBTAIN PAIN OF ITSELF, BECAUSE IT IS PAIN, BUT OCCASIONALLY CIRCUMSTANCES OCCUR IN WHICH TOIL AND PAIN CAN PROCURE HIM SOME GREAT PLEASURE. TO TAKE A TRIVIAL EXAMPLE, WHICH OF US EVER UNDERTAKES LABORIOUS PHYSICAL EXERCISE, EXCEPT TO OBTAIN SOME ADVANTAGE FROM IT? BUT WHO HAS ANY RIGHT TO FIND FAULT WITH A MAN WHO CHOOSES TO ENJOY A PLEASURE THAT HAS NO ANNOYING CONSEQUENCES, OR ONE WHO AVOIDS A PAIN THAT PRODUCES NO RESULTANT PLEASURE? ON THE OTHER HAND, WE DENOUNCE WITH RIGHTEOUS INDIGNATION AND DISLIKE MEN WHO ARE SO BEGUILED AND DEMORALIZED BY THE CHARMS OF PLEASURE OF THE MOMENT, SO BLINDED BY DESIRE, THAT THEY CANNOT FORESEE THE PAIN AND TROUBLE THAT ARE BOUND TO ENSUE; AND EQUAL BLAME BELONGS TO THOSE WHO FAIL IN THEIR DUTY THROUGH WEAKNESS OF WILL, WHICH IS THE SAME AS SAYING THROUGH SHRINKING FROM TOIL AND PAIN. THESE CASES ARE PERFECTLY SIMPLE AND EASY TO DISTINGUISH. IN A FREE HOUR, WHEN OUR POWER OF CHOICE IS UNTRAMMELLED AND WHEN NOTHING PREVENTS OUR BEING ABLE TO DO WHAT WE LIKE BEST, EVERY PLEASURE IS TO BE WELCOMED AND EVERY PAIN AVOIDED. BUT IN CERTAIN CIRCUMSTANCES AND OWING TO THE CLAIMS OF DUTY OR THE OBLIGATIONS OF BUSINESS IT WILL FREQUENTLY OCCUR THAT PLEASURES HAVE TO BE REPUDIATED AND ANNOYANCES ACCEPTED. THE WISE MAN THEREFORE ALWAYS HOLDS IN THESE MATTERS TO THIS PRINCIPLE OF SELECTION: HE REJECTS PLEASURES TO SECURE OTHER GREATER PLEASURES, OR ELSE HE ENDURES PAINS TO AVOID WORSE PAINS";


    @Nested
    @DisplayName("Tests for Caeser cipher")
    class CaeserTests {
        private CaeserCipher caeserCipher;
        private CaeserSolver caeserSolver;
        private int shift = 14;
        private String shortCipher = "VSZZC KCFZR";
        private String midCipher = "WT O AOQVWBS WG SLDSQHSR HC PS WBTOZZWPZS, WH QOBBCH OZGC PS WBHSZZWUSBH";
        private String longCipher = "PIH W AIGH SLDZOWB HC MCI VCK OZZ HVWG AWGHOYSB WRSO CT RSBCIBQWBU DZSOGIFS OBR DFOWGWBU DOWB KOG PCFB OBR W KWZZ UWJS MCI O QCADZSHS OQQCIBH CT HVS GMGHSA, OBR SLDCIBR HVS OQHIOZ HSOQVWBUG CT HVS UFSOH SLDZCFSF CT HVS HFIHV, HVS AOGHSF-PIWZRSF CT VIAOB VODDWBSGG. BC CBS FSXSQHG, RWGZWYSG, CF OJCWRG DZSOGIFS WHGSZT, PSQOIGS WH WG DZSOGIFS, PIH PSQOIGS HVCGS KVC RC BCH YBCK VCK HC DIFGIS DZSOGIFS FOHWCBOZZM SBQCIBHSF QCBGSEISBQSG HVOH OFS SLHFSASZM DOWBTIZ. BCF OUOWB WG HVSFS OBMCBS KVC ZCJSG CF DIFGISG CF RSGWFSG HC CPHOWB DOWB CT WHGSZT, PSQOIGS WH WG DOWB, PIH CQQOGWCBOZZM QWFQIAGHOBQSG CQQIF WB KVWQV HCWZ OBR DOWB QOB DFCQIFS VWA GCAS UFSOH DZSOGIFS. HC HOYS O HFWJWOZ SLOADZS, KVWQV CT IG SJSF IBRSFHOYSG ZOPCFWCIG DVMGWQOZ SLSFQWGS, SLQSDH HC CPHOWB GCAS ORJOBHOUS TFCA WH? PIH KVC VOG OBM FWUVH HC TWBR TOIZH KWHV O AOB KVC QVCCGSG HC SBXCM O DZSOGIFS HVOH VOG BC OBBCMWBU QCBGSEISBQSG, CF CBS KVC OJCWRG O DOWB HVOH DFCRIQSG BC FSGIZHOBH DZSOGIFS? CB HVS CHVSF VOBR, KS RSBCIBQS KWHV FWUVHSCIG WBRWUBOHWCB OBR RWGZWYS ASB KVC OFS GC PSUIWZSR OBR RSACFOZWNSR PM HVS QVOFAG CT DZSOGIFS CT HVS ACASBH, GC PZWBRSR PM RSGWFS, HVOH HVSM QOBBCH TCFSGSS HVS DOWB OBR HFCIPZS HVOH OFS PCIBR HC SBGIS; OBR SEIOZ PZOAS PSZCBUG HC HVCGS KVC TOWZ WB HVSWF RIHM HVFCIUV KSOYBSGG CT KWZZ, KVWQV WG HVS GOAS OG GOMWBU HVFCIUV GVFWBYWBU TFCA HCWZ OBR DOWB. HVSGS QOGSG OFS DSFTSQHZM GWADZS OBR SOGM HC RWGHWBUIWGV. WB O TFSS VCIF, KVSB CIF DCKSF CT QVCWQS WG IBHFOAASZZSR OBR KVSB BCHVWBU DFSJSBHG CIF PSWBU OPZS HC RC KVOH KS ZWYS PSGH, SJSFM DZSOGIFS WG HC PS KSZQCASR OBR SJSFM DOWB OJCWRSR. PIH WB QSFHOWB QWFQIAGHOBQSG OBR CKWBU HC HVS QZOWAG CT RIHM CF HVS CPZWUOHWCBG CT PIGWBSGG WH KWZZ TFSEISBHZM CQQIF HVOH DZSOGIFSG VOJS HC PS FSDIRWOHSR OBR OBBCMOBQSG OQQSDHSR. HVS KWGS AOB HVSFSTCFS OZKOMG VCZRG WB HVSGS AOHHSFG HC HVWG DFWBQWDZS CT GSZSQHWCB: VS FSXSQHG DZSOGIFSG HC GSQIFS CHVSF UFSOHSF DZSOGIFSG, CF SZGS VS SBRIFSG DOWBG HC OJCWR KCFGS DOWBG";

        @BeforeEach
        void beforeEach(){
            caeserCipher = new CaeserCipher();
            caeserCipher.setParams(shift);

            caeserSolver = new CaeserSolver();
        }

        @Test
        void encryptShort() {
            Assertions.assertEquals(shortCipher, caeserCipher.encryptCall(shortPlain));
        }

        @Test
        void encryptMid() {
            Assertions.assertEquals(midCipher, caeserCipher.encryptCall(midPlain));
        }

        @Test
        void encryptLong() {
            Assertions.assertEquals(longCipher, caeserCipher.encryptCall(longPlain));
        }

        @Test
        void decryptShort() {
            Assertions.assertEquals(shortPlain, caeserCipher.decryptCall(shortCipher));
        }

        @Test
        void decryptMid() {
            Assertions.assertEquals(midPlain, caeserCipher.decryptCall(midCipher));
        }

        @Test
        void decryptLong() {
            Assertions.assertEquals(longPlain, caeserCipher.decryptCall(longCipher));
        }

        @Test
        void solveShort(){
            caeserSolver.addKnownWord("HELLO");
            Assertions.assertEquals(shortPlain, caeserSolver.solveCall(shortCipher));
            Assertions.assertEquals(shift, caeserSolver.getBestSolutionConfig());
        }

        @Test
        void solveMid(){
            caeserSolver.addKnownWord("INTELLIGENT");
            Assertions.assertEquals(midPlain, caeserSolver.solveCall(midCipher));
            Assertions.assertEquals(shift, caeserSolver.getBestSolutionConfig());
        }

        @Test
        void solveLong(){
            caeserSolver.addKnownWord("DENOUNCING");
            caeserSolver.addKnownWord("CIRCUMSTANCES");
            Assertions.assertEquals(longPlain, caeserSolver.solveCall(longCipher));
            Assertions.assertEquals(shift, caeserSolver.getBestSolutionConfig());
        }

        @Test
        void gracefulFailure(){
            try {
                caeserSolver.addKnownWord("####");
                Assertions.assertNotEquals(longPlain, caeserSolver.solveCall(longCipher));
            } catch (Exception e) {
                Assertions.assertTrue(true);
            }
        }
    }

    @Nested
    @DisplayName("Tests for MAS cipher")
    class MASTests {
        private MonoAlphabeticSubstitutionCipher mASCipher;
        private HashMap<Character, Character> alphabet = new HashMap<>();
        private String shortCipher = "WCVVT XTHVL";
        private String midCipher = "IZ Q AQFWIEC ID CYPCFSCL ST BC IEZQVVIBVC, IS FQEETS QVDT BC IESCVVIGCES";
        private String longCipher = "BRS I ARDS CYPVQIE ST MTR WTX QVV SWID AIDSQJCE ILCQ TZ LCETREFIEG PVCQDRHC QEL PHQIDIEG PQIE XQD BTHE QEL I XIVV GIKC MTR Q FTAPVCSC QFFTRES TZ SWC DMDSCA, QEL CYPTREL SWC QFSRQV SCQFWIEGD TZ SWC GHCQS CYPVTHCH TZ SWC SHRSW, SWC AQDSCH-BRIVLCH TZ WRAQE WQPPIECDD. ET TEC HCOCFSD, LIDVIJCD, TH QKTILD PVCQDRHC ISDCVZ, BCFQRDC IS ID PVCQDRHC, BRS BCFQRDC SWTDC XWT LT ETS JETX WTX ST PRHDRC PVCQDRHC HQSITEQVVM CEFTRESCH FTEDCURCEFCD SWQS QHC CYSHCACVM PQIEZRV. ETH QGQIE ID SWCHC QEMTEC XWT VTKCD TH PRHDRCD TH LCDIHCD ST TBSQIE PQIE TZ ISDCVZ, BCFQRDC IS ID PQIE, BRS TFFQDITEQVVM FIHFRADSQEFCD TFFRH IE XWIFW STIV QEL PQIE FQE PHTFRHC WIA DTAC GHCQS PVCQDRHC. ST SQJC Q SHIKIQV CYQAPVC, XWIFW TZ RD CKCH RELCHSQJCD VQBTHITRD PWMDIFQV CYCHFIDC, CYFCPS ST TBSQIE DTAC QLKQESQGC ZHTA IS? BRS XWT WQD QEM HIGWS ST ZIEL ZQRVS XISW Q AQE XWT FWTTDCD ST CEOTM Q PVCQDRHC SWQS WQD ET QEETMIEG FTEDCURCEFCD, TH TEC XWT QKTILD Q PQIE SWQS PHTLRFCD ET HCDRVSQES PVCQDRHC? TE SWC TSWCH WQEL, XC LCETREFC XISW HIGWSCTRD IELIGEQSITE QEL LIDVIJC ACE XWT QHC DT BCGRIVCL QEL LCATHQVINCL BM SWC FWQHAD TZ PVCQDRHC TZ SWC ATACES, DT BVIELCL BM LCDIHC, SWQS SWCM FQEETS ZTHCDCC SWC PQIE QEL SHTRBVC SWQS QHC BTREL ST CEDRC; QEL CURQV BVQAC BCVTEGD ST SWTDC XWT ZQIV IE SWCIH LRSM SWHTRGW XCQJECDD TZ XIVV, XWIFW ID SWC DQAC QD DQMIEG SWHTRGW DWHIEJIEG ZHTA STIV QEL PQIE. SWCDC FQDCD QHC PCHZCFSVM DIAPVC QEL CQDM ST LIDSIEGRIDW. IE Q ZHCC WTRH, XWCE TRH PTXCH TZ FWTIFC ID RESHQAACVVCL QEL XWCE ETSWIEG PHCKCESD TRH BCIEG QBVC ST LT XWQS XC VIJC BCDS, CKCHM PVCQDRHC ID ST BC XCVFTACL QEL CKCHM PQIE QKTILCL. BRS IE FCHSQIE FIHFRADSQEFCD QEL TXIEG ST SWC FVQIAD TZ LRSM TH SWC TBVIGQSITED TZ BRDIECDD IS XIVV ZHCURCESVM TFFRH SWQS PVCQDRHCD WQKC ST BC HCPRLIQSCL QEL QEETMQEFCD QFFCPSCL. SWC XIDC AQE SWCHCZTHC QVXQMD WTVLD IE SWCDC AQSSCHD ST SWID PHIEFIPVC TZ DCVCFSITE: WC HCOCFSD PVCQDRHCD ST DCFRHC TSWCH GHCQSCH PVCQDRHCD, TH CVDC WC CELRHCD PQIED ST QKTIL XTHDC PQIED";

        //TODO Try to get alphabet declaration into a beforeAll()

        @BeforeEach
        void beforeEach() {
            alphabet.put('A', 'Q');
            alphabet.put('B', 'B');
            alphabet.put('C', 'F');
            alphabet.put('D', 'L');
            alphabet.put('E', 'C');
            alphabet.put('F', 'Z');
            alphabet.put('G', 'G');
            alphabet.put('H', 'W');
            alphabet.put('I', 'I');
            alphabet.put('J', 'O');
            alphabet.put('K', 'J');
            alphabet.put('L', 'V');
            alphabet.put('M', 'A');
            alphabet.put('N', 'E');
            alphabet.put('O', 'T');
            alphabet.put('P', 'P');
            alphabet.put('Q', 'U');
            alphabet.put('R', 'H');
            alphabet.put('S', 'D');
            alphabet.put('T', 'S');
            alphabet.put('U', 'R');
            alphabet.put('V', 'K');
            alphabet.put('W', 'X');
            alphabet.put('X', 'Y');
            alphabet.put('Y', 'M');
            alphabet.put('Z', 'N');

            mASCipher = new MonoAlphabeticSubstitutionCipher();
            mASCipher.setParams(alphabet);
        }

        @Test
        void encryptShort() {
            Assertions.assertEquals(shortCipher, mASCipher.encryptCall(shortPlain));
        }

        @Test
        void encryptMid() {
            Assertions.assertEquals(midCipher, mASCipher.encryptCall(midPlain));
        }

        @Test
        void encryptLong() {
            Assertions.assertEquals(longCipher, mASCipher.encryptCall(longPlain));
        }

        @Test
        void decryptShort() {
            Assertions.assertEquals(shortPlain, mASCipher.decryptCall(shortCipher));
        }

        @Test
        void decryptMid() {
            Assertions.assertEquals(midPlain, mASCipher.decryptCall(midCipher));
        }

        @Test
        void decryptLong() {
            Assertions.assertEquals(longPlain, mASCipher.decryptCall(longCipher));
        }
    }

}
