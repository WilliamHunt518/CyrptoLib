import Cryptography.CaeserCipher;
import org.junit.jupiter.api.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MasterTest {
    private String shortPlain = "HELLO WORLD";
    private String midPlain = "If a machine is expected to be infallible, it cannot also be intelligent".toUpperCase();
    private String longPlain = "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure? On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains".toUpperCase();

    @Nested
    @DisplayName("Tests for Caeser cipher")
    class CaeserTests {
        private CaeserCipher caeserCipher;
        private int shift = 14;
        private String shortCipher = "VSZZC KCFZR";
        private String midCipher = "WT O AOQVWBS WG SLDSQHSR HC PS WBTOZZWPZS, WH QOBBCH OZGC PS WBHSZZWUSBH";
        private String longCipher = "PIH W AIGH SLDZOWB HC MCI VCK OZZ HVWG AWGHOYSB WRSO CT RSBCIBQWBU DZSOGIFS OBR DFOWGWBU DOWB KOG PCFB OBR W KWZZ UWJS MCI O QCADZSHS OQQCIBH CT HVS GMGHSA, OBR SLDCIBR HVS OQHIOZ HSOQVWBUG CT HVS UFSOH SLDZCFSF CT HVS HFIHV, HVS AOGHSF-PIWZRSF CT VIAOB VODDWBSGG. BC CBS FSXSQHG, RWGZWYSG, CF OJCWRG DZSOGIFS WHGSZT, PSQOIGS WH WG DZSOGIFS, PIH PSQOIGS HVCGS KVC RC BCH YBCK VCK HC DIFGIS DZSOGIFS FOHWCBOZZM SBQCIBHSF QCBGSEISBQSG HVOH OFS SLHFSASZM DOWBTIZ. BCF OUOWB WG HVSFS OBMCBS KVC ZCJSG CF DIFGISG CF RSGWFSG HC CPHOWB DOWB CT WHGSZT, PSQOIGS WH WG DOWB, PIH CQQOGWCBOZZM QWFQIAGHOBQSG CQQIF WB KVWQV HCWZ OBR DOWB QOB DFCQIFS VWA GCAS UFSOH DZSOGIFS. HC HOYS O HFWJWOZ SLOADZS, KVWQV CT IG SJSF IBRSFHOYSG ZOPCFWCIG DVMGWQOZ SLSFQWGS, SLQSDH HC CPHOWB GCAS ORJOBHOUS TFCA WH? PIH KVC VOG OBM FWUVH HC TWBR TOIZH KWHV O AOB KVC QVCCGSG HC SBXCM O DZSOGIFS HVOH VOG BC OBBCMWBU QCBGSEISBQSG, CF CBS KVC OJCWRG O DOWB HVOH DFCRIQSG BC FSGIZHOBH DZSOGIFS? CB HVS CHVSF VOBR, KS RSBCIBQS KWHV FWUVHSCIG WBRWUBOHWCB OBR RWGZWYS ASB KVC OFS GC PSUIWZSR OBR RSACFOZWNSR PM HVS QVOFAG CT DZSOGIFS CT HVS ACASBH, GC PZWBRSR PM RSGWFS, HVOH HVSM QOBBCH TCFSGSS HVS DOWB OBR HFCIPZS HVOH OFS PCIBR HC SBGIS; OBR SEIOZ PZOAS PSZCBUG HC HVCGS KVC TOWZ WB HVSWF RIHM HVFCIUV KSOYBSGG CT KWZZ, KVWQV WG HVS GOAS OG GOMWBU HVFCIUV GVFWBYWBU TFCA HCWZ OBR DOWB. HVSGS QOGSG OFS DSFTSQHZM GWADZS OBR SOGM HC RWGHWBUIWGV. WB O TFSS VCIF, KVSB CIF DCKSF CT QVCWQS WG IBHFOAASZZSR OBR KVSB BCHVWBU DFSJSBHG CIF PSWBU OPZS HC RC KVOH KS ZWYS PSGH, SJSFM DZSOGIFS WG HC PS KSZQCASR OBR SJSFM DOWB OJCWRSR. PIH WB QSFHOWB QWFQIAGHOBQSG OBR CKWBU HC HVS QZOWAG CT RIHM CF HVS CPZWUOHWCBG CT PIGWBSGG WH KWZZ TFSEISBHZM CQQIF HVOH DZSOGIFSG VOJS HC PS FSDIRWOHSR OBR OBBCMOBQSG OQQSDHSR. HVS KWGS AOB HVSFSTCFS OZKOMG VCZRG WB HVSGS AOHHSFG HC HVWG DFWBQWDZS CT GSZSQHWCB: VS FSXSQHG DZSOGIFSG HC GSQIFS CHVSF UFSOHSF DZSOGIFSG, CF SZGS VS SBRIFSG DOWBG HC OJCWR KCFGS DOWBG";

        @BeforeEach
        void beforeEach(){
            caeserCipher = new CaeserCipher();
            caeserCipher.setParams(shift);
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
    }
}
