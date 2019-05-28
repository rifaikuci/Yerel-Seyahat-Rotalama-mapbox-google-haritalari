package com.rifaikuci.yerelseyahatrotalama;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class detayAktivite extends AppCompatActivity {


    String yer ;
    Intent intent;
    TextView baslik;
    TextView detay;
    ImageView resim;
    ImageView volume;
    boolean sesDurumu=false;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay_aktivite);
        baslik=(TextView) findViewById(R.id.baslik);
        detay=(TextView) findViewById(R.id.detay);
        volume=(ImageView) findViewById(R.id.volume);
        resim=(ImageView) findViewById(R.id.resim);
        volume.setImageResource(R.drawable.ic_volume_off_black_24dp);
        if(Build.VERSION.SDK_INT>=19)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR)
                {
                    textToSpeech.setLanguage(new Locale("tr-TR"));
                }
            }
        });



        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sesDurumu==false)
                {
                    sesDurumu=true;
                    volume.setImageResource(R.drawable.ic_volume_up_black_24dp);

                    textToSpeech.speak(baslik.getText()+" "+detay.getText(),TextToSpeech.QUEUE_FLUSH,null);

                }
                else
                {
                    sesDurumu=false;
                    onPause();
                    volume.setImageResource(R.drawable.ic_volume_off_black_24dp);

                }
            }
        });


    intent=getIntent();
        yer =intent.getStringExtra("yer");

        if(yer.equalsIgnoreCase("ulucami"))
        {
            baslik.setText("Ulu Cami");
            resim.setImageResource(R.drawable.ulucami);
            detay.setText("Burdur şehir merkezinde, Pazar Mahallesinde bulunan Burdur Ulu Camisi, vakıf kayıtlarına göre Hamitoğullarından Dündar Bey tarafından 1300 yılında yaptırılmıştır. Sonraki yıllarda harap olan bu camiyi Çelik Mehmet Paşa 1749 yılında ikinci kez yaptırmıştır. Ancak bir deprem sonucu yıkılan cami l935 yılında Neo-Klasik üslupta yeniden yapılmıştır. \n" +
                    " \n" +
                    " Cami düzgün bir plan şeması göstermemektedir. Dikdörtgen planlı cami kesme taştan yapılmıştır. Son cemaat yeri caminin ibadet mekanından daha alçak olup önünde doğu, batı kenarlarında birer, kuzeyde de üç sivri kemerli açıklıkları bulunmaktadır. İlk yapılışında açık olan bu son cemaat yerleri camekanla kapatılmıştır \n" +
                    " \n" +
                    " İbadet mekanını üzeri ahşap bir tavan ile örtülmüştür. Üst örtüsü de oluklu kiremit çatılıdır. İç mekanda herhangi bir süsleme elemanına rastlanmamaktadır. Mihrap dikdörtgen bir niş içerisinde yuvarlak bir niş şeklindedir.Yanına basit bir minber eklenmiştir.Caminin ilk yapılışından kalan oyma tekniğinde yapılmış ahşap minberi Burdur Müzesindedir. Bu minberin oldukça zengin, birbiri içerisine girmiş bitkisel motifleri bulunmaktadır.Buna dayanılarak caminin ilk yapılışında kapı, pencere ve dolap kapaklarının da Osmanlı ağaç işçiliğinin en güzel örneklerini sergilendiği sanılmaktadır. \n" +
                    " \n" +
                    " Caminin kuzey doğu ve kuzey batı köşelerine kare kaideli yuvarlak gövdeli iki minare yerleştirilmiştir. Minarelerin şerefe altları klasik baklava ve stalaktitlidir. Kuzey, doğu ve batı yönlerinde üç giriş kapısı bulunan caminin ana duvarlarında iki sıra halinde Neo-Klasik üslubun özelliklerini yansıtan ince uzun,sivri kemerli pencereleri bulunmaktadır.");

        }

        if(yer.equalsIgnoreCase("hacilarhoyuk"))
        {
            baslik.setText("Hacılar Höyük");
            resim.setImageResource(R.drawable.hacilarhoyuk);
            detay.setText("Bölgede Tarih öncesi dönemlerin araştırıldığı ilk merkez olan Hacılar ören yeri, Hacılar Köyü’nün güneybatı bitişiğinde bulunmaktadır. Höyük denemeyecek kadar alçak ve yayvan bir ören yeri olan bu merkezin varlığı 1956’da saptanmış ve 1957 / 60 yılları arasında İngiliz bilim adamı James Mellaart’ın başkanlığındaki bir kurul tarafından kazılar yapılmıştır. Çalışmalar sonunda bu ören yerinde, insanoğlunun yiyecek üretimini, yani tarımı öğrenip uygulamaya başladığı, bunun sonucu olarak da sürekli yerleşik düzene geçildiği kabul edilen dönem olan Neolitik ’ten başlayarak (MÖ 8000 dolayları), Erken Kalkolitik ’in sonlarına kadar (MÖ 5700 - 5600), bazı kesintilerle devam eden 16 evreli bir yerleşme sürecinin var olduğu anlaşılmıştır.\n" +
                    "\n" +
                    "Hacılarda kazıların bitiminden çeyrek yüzyıl sonra, 1985 ve 1986 yıllarında bu yerleşmenin mezarlığını araştırmak ve burası ile ilgili bazı bilinmezlere yanıtlar bulmak amacıyla, Mellaart ekibinin çalıştığı yerlerin dışındaki alanlarda, giriş kısmında sözü edilen İstanbul Üniversitesi’ne bağlı bilim kurulu tarafından Refik DURU başkanlığında kısa süreli kazılar yapılmıştır.\n" +
                    "\n" +
                    "Bu iki aşamalı araştırmalarda elde edilmiş arkeolojik verilere göre, Hacılarda ana toprak üzerindeki ilk 7 yapı evresi, oldukça gelişmiş bir mimarlık geleneğine sahiptir. Bu dönemin sona ermesinden uzun bir süre geçtikten sonra, iskân yeniden başlamıştır. Dört ayrı yerleşme tabakası halinde olan bu yeni süreçte, önceleri basit kulübelerde oturanların, giderek olgun bir mimarlığa işaret eden, kerpiç duvarlı evler yapmaya başladıkları görülmektedir (Resim 1).\n" +
                    "\n" +
                    "Hacılardaki yerleşmenin bir sonraki aşaması olan Geç Neolitik ve Erken Kalkolitik’ olarak tanımlanan dönemlerde, mimarlık ve çömlekçilikte bazı önemli değişmeler gözlenmekle birlikte, daha eski dönemlerde yaşayanların soyundan olan toplulukların burada yaşamlarını sürdürdükleri anlaşılmaktadır. Bir kasaba niteliğindeki bu yeni yerleşmelerin sahiplerinin çok ustalık isteyen insan, özellikle hamile kadın (Ana Tanrıça) figürinleri yaptıkları ve olağanüstü estetik değerde boyalı kap kacak ürettikleri görülmektedir (Resim 2-3).\n" +
                    "\n" +
                    "Hacıların en geç yerleşmesinde, mimarlıkta ve hemen her konuda eskilerden farklı uygulamalar görülmektedir. Kapsamlı değişiklikler, Hacıların bu döneminin daha önce burada yaşamış halklardan farklı etnik ve kültürel kökenden gelen insanlar tarafından iskân edildiğine işaret etmektedir. Özellikle mimarlıkta değişim çok belirgindir.");
        }

        if(yer.equalsIgnoreCase("incirhan"))
        {
            baslik.setText("İncir  Han");
            resim.setImageResource(R.drawable.incirhan);
            detay.setText("Bucak ilçesinin 6 km batısında İncirdere köyü yakınında bulunmaktadır. Anadolu Selçuklu sultanlarından II. Gıyasettin Keyhüsrev Bin Keykubat tarafından 13. yy da yapılmıştır. Avlu kısmı tamamlanmamasına rağmen ahır bölümü hâlâ ayaktadır. Han'ın ilk bakışta dikkati çeken kısmı kitabeli taç kapısıdır. Dikdörtgen şeklindeki taç kapının ortasında istiridye kabuğu şeklinde kemerli esas giriş nişi dış cepheden iki yalancı sütunla desteklenmiştir. Han avlulu ve kapalı mekân olarak iki kısımdan oluşmaktadır.\n" +
                    "\n");
        }

        if(yer.equalsIgnoreCase("burdurmuzesi"))
        {
            baslik.setText("Burdur Müzesi");
            detay.setText("Burdur günümüzde Antalya, Muğla, Denizli, Afyon ve Isparta illeriyle çevrili olup, antikçağda İsauria, Lykaonia ile doğudan, Pamphylia ile güneyden, Likya ve Karia ile batıdan Firigya ve Galatia ilede kuzeyden çevrili Pisidia antik coğrafyasında bulunmaktadır.\n" +
                    "\n" +
                    "Burdur'un tarih öncesi (Prehistorik) geçmişi paleolitik çağlara kadar uzanmaktadır. Daha sonra sırasıyla Neolitik (8000 - 5500) Kalkolitik (5500 - 3200) çağlara ait somut buluntular Hacılar ve Kuruçay kazıları ile ortaya çıkmıştır.\n" +
                    "\n" +
                    "İşte böylesine zengin bir prehistorik ve klasik çağlar arkeolojisine sahip Burdurlular 1950 yılların ortasında bir müze oluşturma çabasına girmiş. 1957 - 1960 yılları arasında dört sezon arkeolojik kazılar yapılan Hacılar Höyük ile bütün dünyanın dikkatini üzerine çekmiştir. Bu girişimlerin sonucunda Burdur Müzesi 1963 yılında resmen kurulmuştur. 12 Haziran 1969 yılında Müzenin bulunduğu yerde Şeyh Mustafa veya Küçük Şeyh Bulgur zade Ağa Medresesinin Kütüphane olarak kullanılan Hicri 1239 tarihli Necip Efendi Kütüphanesi ve çevresinde yeni oluşturulan yapılarda Burdur Müzesi insanlığın ve bilim dünyasının hizmetine açılmıştır. Zengin bir arkeolojik potansiyele sahip olan ilimiz kısa bir zaman içerisinde Müzesini geliştirerek ülkemizdeki sayılı müzeler içerisinde belli bir yere gelmiş olmasına rağmen, yerleşim yeri ve sergileme imkanının yetersizliği görülmüş ve 1992 yılında müzenin batı kısmı kamulaştırılarak müzeye dahil edilen alan ile genişleyen müze günün gereksinimine uygun olarak yeni teşhir salonları ve eski eser depoları yapımına 9 Haziran 2001'de Kültür Bakanı Sayın İstemihan TALAY tarafından temeli atılarak başlanmış ve 6 yıl süren revizyon çalışmaları sonunda dönemin Kültür ve Turizm Bakanı Sayın Atila KOÇ tarafından  7 Temmuz 2006 tarihinde kapılarını ziyaretçi ve bilim dünyasına açmıştır.\n" +
                    "\n" +
                    " ");
            resim.setImageResource(R.drawable.burdurmuzesi);
        }

        if(yer.equalsIgnoreCase("dogatarihmuzesi"))
        {
            baslik.setText("Doğa Tarihi Müzesi");
            detay.setText("İlimiz Zafer Mahallesi’nde bulunan Rum Ortodoks Kilisesi 19. yüzyılın başlarında inşa edilmiştir. Kilisenin dar cepheleri doğu ve batı cephesidir. Doğu cephesinde,  ortasında büyük yanlarda birbirinin aynı iki küçük yarım oval üç apsis vardır. İç kısmı altılı iki sıra ağaç sütun dizisi ile üç nefe bölünmüştür. Orta nef daha geniştir.  Altışar adet ve iki sıra sıva ile kaplı sütunların kaideleri silindirik yivli taştır.\n" +
                    "\n" +
                    "Kilise, restore edilerek Doğa Tarihi Müzesi olarak ziyarete açılmıştır.\n" +
                    "\n" +
                    "Güney Fili (Mammuthus Meridionalis)\n" +
                    "\n" +
                    "Dünyada nadir bulunan, 10 milyon yıllık bir dev Güney filinin 3.30 metre uzunluğundaki savunma dişi ve kürek kemikleri Kemer ilçesi Elmacık köyünde gün yüzüne çıkarılmıştır. Doğa Tarihi Müzesi'nde sergilenmektedir.");
        resim.setImageResource(R.drawable.dogatarihimuzesi);
        }

        if(yer.equalsIgnoreCase("sagalassos"))
        {
            resim.setImageResource(R.drawable.sagalassos);
            baslik.setText("Sagalassos");
            detay.setText("İmparatorların Gözde Şehri Sagalassos\n" +
                    "\n" +
                    "Sagalassos, Ağlasun ilçesinin 7 km kuzeyinde ve Akdağ yamaçlarında denizden 1500-1700 metre yüksekliğinde kurulmuş bir kenttir. Sagalassos Pisidia bölgesinin Roma İmparatorluk Dönemi’nde en önemli şehirlerinden biridir. Şehirde bulunan yapıların büyük bir çoğunluğu Roma Dönemi’ne aittir. Sagalassos’un Batılılar tarafından ilk keşfi 1706 yılında Fransız gezgin Paul Lucas tarafından yapılmıştır.\n" +
                    "\n" +
                    "Bulutların arasındaki kente girişte hemen kuzeyde saray büyüklüğünde bir kent konağının avlusu ve salonları yer alır. Kentin alt kısmına büyük Roma İmparatorluk Hamamı hâkimdir. Anıtın soğukluk salonu, havuzları, ılıklık ve sıcaklık salonları korunmuş durumdadır. Hamamın alt katı, kentin Aşağı Agora'sına (meydanı) açılır. Aşağı Agora etrafında, agora çeşmesi ve daha üst kotta Apollo Klarios tapınağı ve Hadrian Çeşmesi kalıntıları yer alır. Yukarı kentte, Yukarı Agora etrafındaki pekçok anıt açığa çıkarılmış ve onarılarak ayağa kaldırılmıştır. Burada suları çağlayan muhteşem Antoninler Çeşmesi; meydana açılan iki anıtsal kemer, agoranın dört köşesinde yüksek onursal sütunlar, belediye binası, kent konseyi binası, kilise ve dans eden kızlar kabartmaları ile tanınan Heroon görülebilir. Doğuya doğru ilerlediğinizde, kentin antik kütüphanesi, hâlâ özgün kaynak suyu ile beslenen Helenistik çeşmesi ve daha ileride antik tiyatro tüm haşmeti ile sizi davet eder. Bir anda kendinizi Roma döneminde bulursunuz.\n" +
                    "\n" +
                    "Sagalassoslular M.Ö. 3. bin yılın sonlarında Batı ve Güney Anadolu’da yaşayan Luvi kabilelerinin bir kolu olan Pisidia halklarındandır. M.Ö. 333 yılında Büyük İskender, bu şehri ele geçirmiştir. Yazılı tarihi kaynaklarda, ilk defa M.Ö. 4. yüzyılın çeyreğinde Büyük İskender'in (M.Ö. 333-334) kenti zapt etmesiyle görülür. Sagalassos ‘un, Seleucid (Seleukos) ve Attalid (Attalos) hâkimiyetine girdiğini, M.Ö. 25 yılında Galatia kralı Amyntas’ın, ardından Augustus tarafından Roma İmparatorluğu topraklarına katıldığını görmekteyiz.\n" +
                    "\n" +
                    "Hadrian’ın 120’li yıllarda Sagalassos’u Pisidia imparatorluk kültünün resmi merkezi seçmesi üzerine başlayan çok daha büyük çaplı ekonomik büyümesi, bir yüzyıl sürerek imar gelişmesini başlatmıştır. M.S. 6. ve 7. yüzyılların başlarındaki iki deprem ve 6. yüzyılın ortasında Anadolu’daki veba salgını ve siyasi karışıklıklar, kentin sonunu getirmiştir. 7. yüzyıla kadar kentte yaşam küçülerek ve giderek daha kırsal bir karakter kazanarak sürmüştür. M.S. 11. yüzyıla kadar, eskiden Antoninus Pius tapınağının olduğu tepe üzerine sığınan halkıyla Sagalassos ' ta yaşam, küçük bir köy yerleşimi olarak devam etmiştir. M.S. 13. yüzyıl başlarında Selçukluların, Sagalassos İskender Tepesi üzerindeki son Bizans askeri kale yerleşimini ortadan kaldırmasıyla kentte yaşam sona ermiştir.\n" +
                    "\n" +
                    "En belirgin yapısı muhteşem Antoninler çeşmesidir. Şehir, İmparator Hadrian (M.S. 2. yy) döneminde ekonomik, siyasi ve sosyal anlamda en iyi dönemini yaşamıştır. Sagalassos Anadolu'nun en iyi korunagelmiş antik kentlerinden birisidir ve 2009 yılında UNESCO Dünya Mirası Geçici Ulusal Listesi'ne alınmıştır.\n" +
                    "\n" +
                    "       1989 yılından beri Kültür ve Turizm Bakanlığı adına, Belçika'nın Leuven Üniversitesi tarafından 2013'teki emekliliğine kadar Prof. Dr. Marc Waelkens tarafından yürütülen kazılar günümüzde Prof. Dr. Jeroen Poblome başkanlığında sürmektedir. Kazılar ve restorasyonlar sonucunda pek çok eser ve anıt ortaya çıkmıştır. 2007-2008 kazılarında ortaya çıkan İmparator Hadrian ve İmparator Marcus Aurelius'a ait devasa heykel parçaları, dünyada eşine az rastlanır kalitedeki işçilikleri ile görenleri hayran bırakır. Bu heykel başları ve bacak ve ayak kısımları ile Sagalassos'tan gelen pek çok eser bugün Burdur Müzesi'nde sergilenmektedir.");
        }

        if(yer.equalsIgnoreCase("kibyra"))
        {
            baslik.setText("Kibyra");
            resim.setImageResource(R.drawable.kibyra);
            detay.setText("Kahraman Savaşçıların ve Hızlı Atların Şehri\n" +
                    "\n" +
                    "Kibyra, Gölhisar ilçesinin hemen batı yamacındaki alçak tepeler üzerinde yer almaktadır. Kibyra antik kentinin, yerleşim alanı oldukça büyüktür. Kibyra birbirinden derin yarlarla ayrılan hâkim üç tepe üzerinde oturmaktadır. Yapılar; simetrik düzenlenmiş, tepeler teraslanarak göl ve ova manzarasına hâkim konumda ve hiçbir yapı bir diğerinin manzarasını kesmeyecek biçimde yerleştirilmişlerdir.\n" +
                    "\n" +
                    "Şehre girerken solda muhteşem anıtsal kapısıyla, Eski Anadolu’nun en görkemli ve günümüze sağlam korunmuş olarak ulaşan antik stadyumlarından olan, 11 bin izleyici kapasiteli Kibyra Stadion’u sizi karşılar. Burada gladyatörlerin mücadelelerini görür gibi olursunuz. İlerledikçe Bazilika, Yukarı ve Aşağı Agora, Roma Hamamı, Gymnasion, Tiyatro ve Odeion karşınıza çıkar. Diğer taraftan Anıt Mezar, Geç Roma Hamamı, yuvarlak kuleli giriş kapısı ve antik suyolları ilginizi çekecektir. 2006 yılında Burdur Müzesi başkanlığında başlatılan kazı çalışmaları, günümüzde T.C. Kültür ve Turizm Bakanlığı adına Mehmet Akif Ersoy Üniversitesi Öğretim Üyesi Yrd. Doç. Dr. Şükrü Özüdoğru başkanlığındaki ekip tarafından yürütülmektedir.\n" +
                    "\n" +
                    "Odeion (Müzik Evi) 3600 kişilik kapasitesiyle, halen ülkemizin sahip olduğu, antik çağlarda üzeri bir çatıyla kapatılmış en büyük yapısıdır ve iç bezemeleriyle en görkemli eserlerindendir. Orkestranın tam merkezinde kırmızı, yeşil ve beyaz mermerlerden yapılmış, yılanlardan oluşan saçları ve insanları taşa çeviren bakışlarıyla MEDUSA figürü, yapım tekniğiyle kendi türünde bilinen tekil örnektir. 2011 yılında Odeion’un ön kısmındaki Stoa’da  Anadolu'nun en sağlam ve büyük mozaik alanı olma özelliği taşıyan, yaklaşık 540 m2 alanı kapsayan sağlam durumda mozaik ortaya çıkarılmıştır. Yine Odeion’un önünde, Geç Roma Dönemi'ne ait (M.S. 6-7 yy.) Hamam yapısı, bölümleri, ısıtma ve su sitemleriyle görülebilir durumdadır. \n" +
                    "\n" +
                    "Kentin bugün görülebilen tüm mimari kalıntıları Roma İmparatorluk Dönemi'ne aittir. Hellenistik Dönem’de Kibyra ve yakın çevresinde konumlanmış antik kentlerden Boubon, Balboura ve Oinoanda'dan teşekkül, dörtlü “kent birliği (Tetrapolis)” (M. Ö. 2. - 1. yüzyıllarda) oluşmuştur. Söz konusu kentler birliği,  M.Ö. 82 yılında Romalı General Murena tarafından dağıtılıp, ortadan kaldırılmıştır. Bu tarihten sonra Kibyra Asia Eyaleti'ne ve diğer kentler Likya Birliği'ne dahil edilmiştir. Roma İmparatorluk Dönemi'nde ise, kendisine yaklaşık 25 kentin bağlı olduğu “Kibyra Conventusu” adı altında, Asia Eyalet Valisi'nin yargı merkezi olmuştur. M.S. 23 yılında meydana gelen büyük bir deprem sonucunda yerle bir olan kent, Roma İmparatoru Tiberius’un vergi affı getirmesiyle yeniden inşa edilebilmiştir. Kibyra özellikle M.S. 1. - 3. yüzyıllarda en parlak ve zengin dönemini yaşamıştır.\n" +
                    "\n" +
                    "Kibyra demircilik, dericilik ve at yetiştiriciliğinde oldukça ünlüdür. Buna çömlekçilik de eklenmelidir. Şehir halkı son derece savaşçı kimliğe sahiptir. Şehrin en çok dikkat çeken eserleri; görkemli Stadion’u, Odeion içindeki opus sectile Medusa döşemi ve önündeki, Türkiye'nin en sağlam ve büyük mozaik alanıdır. Kibyra'dan çıkarılan eserler Burdur Müzesi'nde ziyaretçilerin ilgisine sunulmaktadır.");
        }

        if(yer.equalsIgnoreCase("kremna"))
        {
            resim.setImageResource(R.drawable.kremna);
            baslik.setText("Kremna");
            detay.setText("Bucak İlçesi Çamlık Köyü sınırları içindedir. Aksu (Kastros)  vadisine hâkim bir tepe üzerinde Pisidia'lılarca kurulmuştur. Önemli bir Pisidia şehridir. Kremna ismi, Grekçe “uçurum” anlamına gelmektedir.\n" +
                    "\n" +
                    "Kentte ayakta kalabilen yapıların belli başlıları Roma Dönemine ait olanlardır. Şehrin etrafı iki metre genişliğinde, 7-8 metre yüksekliğinde surla çevrilidir. Kent ızgara planlı olarak kurulmuş örnek kentlerdendir. Kentte ortaya çıkarılan eserler Burdur  Müzesi Kremna bölümünde sergilenmektedir.");
        }

        if(yer.equalsIgnoreCase("bakibey"))
        {
            baslik.setText("Bakibey Konağı");
            detay.setText("Burdur merkez Değirmenler Mahallesi Divanbaba caddesindedir. 17.yy. Osmanlı sivil mimarisinin en güzel örneklerindendir. Kültür Bakanlığı tarafından kamulaştırıldıktan sonra 1988 yılında restorasyonu tamamlanmıştır. Bakibey Konağı, Koca Oda adıyla da bilinir. Bilinen en eski tapu kaydı 1830 yıllarında Reşit Bey üzerinedir. Ancak konağın Reşit Beyin dedesi Ahmet Paşa veya onun babası Çelik Mehmet Paşa zamanında yapılmış olması kuvvetle muhtemeldir.\n" +
                    "\n" +
                    "Konak, zemin katı pencere bitimine kadar devam eden taş temelin üzerinde ahşap ve kalın masif kerpiç duvarlardan oluşmuş iki katlı bir yapıdır. Alt katta ahır, ambar gibi odalar vardır. Üst kata taş merdivenle çıkılmaktadır. Üst katın bahçeye ve ara sokağa bakan geniş bir eyvanı vardır. Eyvanın tavanı çıtalarla süslüdür Çıtaların arası da yeşil, kırmızı toprak boyalarla süslenmiştir. Konağın beşik çatısı alaturka kiremitlerle örtülmüştür. Saçağın ahşap yüzeyleri de aynen eyvanın tavanı gibi yeşil, kırmızı toprak boyalarla süslü çıtalarla donatılmıştır. Direkler arasındaki boyalı süslü sivri kemerler, eli böğründeler, geniş ve boyalı çıkma çıtalı bu saçaklık mimariyi tamamlayan aksesuarı oluşturmaktadır. \n" +
                    "\n" +
                    "Eyvanın doğu kenarında selamlık, yani Başoda yer almaktadır. Konağın en göz alıcı odası Başodadır. Başoda kapısından başlayarak pencere, vitray pencereleri, dolap kapakları ve üstündeki nişleri, davlumbaz, pencere üzerinde dolaşan pervazlar, yüklük kapakları, dört tarafı çeviren koltuk silmeleri, tavan ve tavan göbekleri altın ve gümüş varakla ve kalem işi boyalarla süslüdür. Motifler bütünüyle devrin bitkisel süslemelerini yansıtırlar. Bütün bu altın ve gümüş kaplamalar, ahşap işçiliği ile kalem işi denilen boyalı süslemeleriyle ender rastlanan güzellikte bir başoda ortaya çıkarmıştır. Başodanın tabanı iki kademelidir. Cumbalı kısım döşemeden yükseltilmiştir.\n" +
                    "\n" +
                    "Başodadan sonra yan yana eyvana ve işten bir birine açılan iki küçük oda yer alır. Gerek malzeme ve gerekse süsleme yönünden sade olmakla birlikte altın-gümüş varak kaplamalı ve kalem işi olarak yapılan süslemeler göze çarpar. Bu odalardan biri ahşaptan, süslemeli davlumbazlıdır. Diğer ikinci küçük oda da ahşap tavan çıtalarla karelere bölünmüş ve pervazları kalem işi boyalı süslenmiştir. Orijinalinde evin devamında en az bir odanın daha olduğu düşünülmektedir. Ancak yıkılarak yok olmuştur.\n" +
                    "2003 yılında Kültür ve Turizm Bakanlığı ile Burdur Valiliği arasında yapılan bir protokol ile bakım ve teşhiri Valiliğimize devredilen konak yerli ve yabancı turistlerin hizmetine sunulmuştur.");
            resim.setImageResource(R.drawable.bakibey);
        }

        if(yer.equalsIgnoreCase("tasoda"))
        {
            baslik.setText("Taş Oda");
            detay.setText("Burdur Merkez Pazar mahallesindedir. 17.yy.dan kalma Osmanlı sivil mimari örneklerinden biridir. Kınalı Aşiretinden Emin Bey tarafından yaptırılmıştır.Kültür Bakanlığınca 1978 yılında restorasyon çalışmaları başlatılmış ve 1988 yılında da bitirilmiştir. Bina iki katlıdır. Birinci kat taş, ikinci kat kerpiç ve ahşap yapı malzemesi ile inşa edilmiştir. Özellikle Baş odanın doğu duvarı ve altındaki sivri kemerli iki yanı açık ahır kısmı kesme köfeki taşındandır. Ev, bahçenin batı kısmına yerleştirilmiştir. Birinci kata çıkışı sağlayan merdiven sahanlığının altı, aynı zamanda çeşmedir. Kesme taş bloklardan yapılan bu çeşme, bugün de kullanılmaktadır. Evin zemin katında sivri kemerli ahırdan başka, iki büyük ,bir de küçük oda vardır.\n" +
                    "\n" +
                    "Ahşap korkuluklu merdivenle önce ikinci kattaki sofaya çıkılır. Dikdörtgen biçimdeki sofanın güney ve batı cephesi boyunca odalar sıralanır. Kuzey kısmında ise bir köşkü bulunur. Bu sofa çıtalarla oluşturulmuş kafesler ile dışa kapatılmıştır. Sofanın çatı kısmı ahşap çıtalarla çakma tekniğinde yapılmış olup, çıtalar ve çıtalar arasındaki büyüklü küçüklü üçgenler; mavi, kırmızı ve yeşil renklerle boyanmıştır. Sofanın kuzey kısmında BAŞ ODA yer almaktadır.\n" +
                    "\n" +
                    "BAŞODA : Bol pencerelerle ışıklandırılmıştır. Ahşap yüklük, dolap, davlumbaz, tavan ve pencere pervazlarının kalem işi altın-gümüş varak kaplı süslemeleriyle yapının en göz alıcı odasıdır. Kuzey yönde tabandan yükseltilmiş seki odayı ikiye ayırdığı gibi, tavanı da ikiye bölmektedir. Bu ayırma, sofadaki gibi duvarlara bitişik yükselen, üzerleri kalem işi enine zikzak motiflerle süslü, alt ve üst kısımları kum saati biçimli-oymalı beş yüzlü sütün çelerdir. \n" +
                    "\n" +
                    "   Bu sütün çelerin aynısı tavana da yatay olarak yapılmıştır. Odanın girişinde yüklük boyunca zeminden alçaltılmış dar bir pabuçluk yer alır. Odanın ışıklandırılması iki yönden, iki sıralı pencerelerle sağlanmaktadır. Bunların içindeki vitray pencereler odaya ayrı bir güzellik vermektedir. Alt sıra pencerelerin dış kısımları demir lokmalı parmaklıklı, düz ahşap kepenklidir. İç kısımları ise, pervazlar kalem işi çiçek motifli ve pencere ve dolap aynalarında alçı kabartma ve altın varak kaplı harflerle Osmanlıca ve Farsça olarak yazılmış birer mısralık, konağı ve sahibini öven yazılar bulunmaktadır.\n" +
                    "Binanın, Başodadan başka sofaya açılan dört odası daha vardır. Bu odaların sofaya açılan ahşap kapaklı pencereleri, sofadan odalara ışık girmesini sağlamaktadır. Bitişiğindeki oda bir kapı ile Başodaya geçişlidir. Güney cephede alçı şerbetlikle, ahşap tavan işlemesiyle geleneksel Türk evi karakterini yansıtan ikinci bir Başoda yer alır.");
           resim.setImageResource(R.drawable.tasoda);
        }

        if(yer.equalsIgnoreCase("misirlilar"))
        {
            baslik.setText("Mısırlılar Evi");
            detay.setText("Burdur Merkez Oluklar altı Caddesinde yer almaktadır. Hinnaplı ev olarak adlandırılmıştır. Kültür Bakanlığı tarafından kamulaştırılmıştır. 19.yy. yapısıdır. İki katlı, taş temel üzerine bağdadi olarak yapılmış olup, çatısı alaturka kiremit ile örtülmüştür. Alt katta kışlık odalar ve kiler, üst katta ise ortadaki ince uzun sofaya açılan dört oda yer almaktadır. Tavanlar ahşap işlemelidir. Bol sayıda pencereler ışıklandırmayı sağlarlar ve ahşap kepenklidirler. Odalarda alçı şerbetlikler, ahşap yüklükler, ahşap tavan ve tabanlar ortak özelliklerdir. Başodanın tavan süslemeleri ve alçı şerbetliği diğerlerine göre daha özenlidir. Tavanda dairelerle oluşturulmuş, çiçek motifleriyle bezenmiş bir orta göbek ve bunu çevreleyen baklava dilimi motifleri ile süslü bir bordür yer almaktadır.");
           resim.setImageResource(R.drawable.misirlilar);
        }

        if(yer.equalsIgnoreCase("burdurgolu"))
        {
            baslik.setText("Burdur Gölü");
            detay.setText(" Burdur Gölü Söğüt Dağı ile Sulu dere Yayla dağ kütleleri arasında kuzeydoğu-güneybatı doğrultusunda uzanan oluk şeklindeki tektonik çöküntünün sularla dolması ile oluşmuştur. Gölün batı kesimi boyunca uzanan fay hattı nedeniyle bu kısımda kıyı çizgisi çok dardır. Bu dar bölgelerde göl birden derinleşir. Gölün güney ve kuzeyinde ise alüvyonların birikmesi ile sazlarla kaplı ve delta oluşumu başlamıştır. Kapalı bir havzada yer alan gölün akıntısı yoktur. Göl suyu oldukça tuzlu olup ülkemizin en derin göllerinden birisidir. Derinlik bazı bölgelerde 100 metreyi bulur. Göl su seviyesinin son yıllardaki aşırı düşüşüne gölü besleyen dere ve çaylar üzerinde yapılan barajlar ve son yıllardaki bölgede yaşanan aşırı kuraklığın neden olduğu sanılmaktadır. Göl üzerinde yapılan araştırmalara göre besin maddeleri yönünden çok zengin olmadığı belirtilmektedir. Buna karşılık gölün yüze yakın kuş türüne ve yaklaşık olarak 300 bine yakın su kuşuna ve özellikle Dünyada nesli tükenmekte olan \"dikkuyruk\" ördeklerinin % 70'ine ev sahipliği yapmaktadır. Endemik kuş türlerinin barınma alanı olan Burdur Gölü uluslararası öneme sahip bir sulak alandır. 85 kuş türü yaşar.");
           resim.setImageResource(R.drawable.burdurgolu);
        }

        if(yer.equalsIgnoreCase("golhisargolu"))
        {
            baslik.setText("Gölhisar Gölü");
            detay.setText("Gölhisar ilçesine bağlı Yamadı Köyü sınırlarında yer almaktadır. Tektonik bir çukurluktan oluşan Gölhisar Gölü 7 km karelik bir büyüklüğe sahiptir. Gölün suyu tatlıdır. Göl içinde sazan, tatlı su kefali ve yayın balığı yetişmektedir. Doğal bir güzelliğe sahip Gölhisar Gölü olta balıkçılığı için uygundur.");
           resim.setImageResource(R.drawable.golhisar);
        }

        if(yer.equalsIgnoreCase("saldagolu"))
        {
            baslik.setText("Salda Gölü");
            detay.setText("Yeşilova İlçe Merkezine 6 km. uzaklıktadır. Göl Antalya-Denizli Pamukkale Ana-tur güzergâhı üzerinde ve ilimizde yaz ve kış aylarında turizm hareketliğinin yoğun olarak yaşandığı ve tur otobüslerinin göl kenarında bulunan tesislerden yeme-içme ve kısa süreli dinlenme ihtiyaçlarını giderdikleri çok önemli bir konumda bulunmaktadır. Doğanbaba, Salda, Eşeler Dağları ve Kaya dibi Taşı önünde teşekkül etmiştir. Yapı itibariyle menşei tektoniktir. Denizden yüksekliği 1193 metredir. Tehlikeli bir bataklık sahası yoktur. Oldukça yuvarlak bir görünümü vardır. Suyu tatlıdır, içinde balık yaşar. Çok derin göllerden biridir. Balık yakalaması zordur. 47 km² lik bir sahayı kaplar. Güney cephesinde bulunan Sultan Pınar suyu burayı bir mesire yeri haline getirmiştir. Göl çevresinde tabii kumsallar mevcuttur. Gölden sonra kumsalları takiben ormanlar başlar. \n" +
                    "   Salda Gölü ve çevresi, 14.06.1989 tarihinde 1.derece Doğal Sit Alanı olarak tescil edilmiş ve koruma altına alınmış iken, bu karar Antalya Kültür ve Tabiat Varlıkları Kurulunun 28.07.1992 tarih ve 1501 sayılı yeni kararı ile tadil edilerek, Salda Gölü kıyısındaki bazı mahaller, 2.derece Doğal Sit Alanı olarak tescil edilmiştir.");
            resim.setImageResource(R.drawable.saldagolu);
        }

        if(yer.equalsIgnoreCase("insuyu"))
        {
            baslik.setText("İnsuyu Mağarası");
            detay.setText("Mağaralar\n" +
                    "İnsuyu Mağarası \n" +
                    "\n" +
                    "Burdur-Antalya karayolunun 13 kilometresinde yoldan 900 m. doğuda, Mandıra köyündedir. \n" +
                    "\n" +
                    "Özellikleri: Toplam 597 m. uzunluğunda yatay bir mağaradır. Türkiye'de turizme açılan ilk mağaralardan biridir. Kalker tortulanmalarından türlü şekil ve yapıda meydana gelen sarkıt ve dikitlerin teşekkül tarzları dikkate alınarak mağaranın binlerce yıl evvel teşekkül ettiği tahmin edilmektedir.\n" +
                    "\n" +
                    "Mağara içinde serin ve temiz bir hava cereyanı vardır. Bir kısım mağara sularının şeker ve mide hastalıklarına şifalı olduğuna inanılmaktadır. \n" +
                    "\n" +
                    "İnsuyu Mağarası, Kültür Bakanlığı, Gayrimenkul Eski Eserler ve Anıtlar Yüksek Kurulu’nun 9.7.1976 gün ve A 113 sayılı kararı ile I. derece Doğal Sit olarak tescil edilmiştir.\n" +
                    "\n");
            resim.setImageResource(R.drawable.insuyu);
        }




        if(yer.equalsIgnoreCase("burdur")) {
            baslik.setText("Tarih");
            resim.setImageResource(R.drawable.tarih);
            detay.setText("Günümüzde, Denizli, Afyon, Isparta, Antalya ve Muğla illeri ile çevrelenen Burdur, tarihi çağlarda, doğuda İşavria, Lykonia, güneyde Pamphylia, batıda Likya ve Karya, kuzeyde Frigya ve Galatia bölgeleri ile çevrili olan Pisidya bölgesi sınırları içinde yer almıştı.\n" +
                    "\n" +
                    "Burdur'un tarih öncesi (prehistorik) geçmişi Paleolitik Çağa uzanmaktadır. Yeşilova ilçesi Başkuyu köyünde bulunan kaya resimlerinden Paleolitik Çağ insanının Burdur'da yaşadığı kesinlikle kanıtlanmıştır. Paleolitik (700.000 - 15.000) ve Mezolitik (15.000 - 8.000) çağlara ait diğer buluntular, yörenin değişik yerlerinde yapılan araştırmalardan elde edilmiştir.\n" +
                    "\n" +
                    "Daha sonraki tarih öncesi dönemler olan Neolitik (8.000 - 5.500) ve Kalkolitik (5.500 - 3.200) çağlara ait somut buluntular, Burdur'da Hacılar Höyüğü ve Kuruçay Höyüğü'nde ortaya çıkarılmıştır. Hacılarda yapılan kazılarda, M.Ö. 7000 yıllarına tarihlenen \"Keramiksiz Neolitik\" evre üzerinde IX - IV katlar olarak belirtilen ve M.Ö. 5.400 yıllarına tarihlenen \"Geç Neolitik\" evreleri tespit edilmiştir.\n" +
                    "\n" +
                    "İnsanın yeryüzünde avcı-toplayıcılıktan yerleşik üretime geçerek belli bir yere bağlanması anlamına gelen büyük değişimin; hayvanları ehlileştirilmesi, köylerin kurulması, çanak çömlek yapımının öğrenilmesi gibi medeniyete uzanan gerekli adımların izlenebildiği en önemli arkeolojik merkezlerden biri Hacılardır. Yine Hacıların ana tanrıça figürinleri ile boyalı insan yüzlü çanak - çömlekleri dünya arkeolojisinde önemli bir yer tutmaktadır. Taş, kemik, ağaç ve pişmiş toprağın yanı sıra madenin de kullanılmaya başladığı Kalkolitik Çağ kalıntıları Burdur'da Hacılar, Kuruçay, Gebrem, Beyköy, Bucak, İstasyon Höyük gibi daha birçok höyüğün yüzey araştırmalarından anlaşılmaktadır.\n" +
                    "\n" +
                    "Eserlerin bakır, kurşun, kalay, gümüş, altın, tunç ve elektron gibi madenlerden yapılmaya başlandığı ilk Tunç Çağına (Yaklaşık 3.000 - 2.500) ait buluntulara Burdur'da Hacılar Büyük Höyük, Yazır, Yarıköy, Çamur, Hasanpaşa, Harmankaya, Alan, Beyköy gibi birçok höyükte rastlanmaktadır. Bu dönemde  kaplar elde yapılmıştır, maden görünümündedir. Çağın sonunda ise geometrik süslü ve boyalı çanak çömlek yapımına başlanmıştır.\n" +
                    "\n" +
                    "M.Ö. 2 bin yılın başlarında Burdur tarihi oldukça karanlıktır. M.Ö. 17. yüzyılda Hitit Çağı başladığında, Pisidya, Pamphylia ve Likya'da Arzava Krallığı hüküm sürmekteydi. Yarışlı Gölü civarı (Düğer) ve Uylupınar’da çıkan Frig eserleri daha sonra bu bölgede Friglerin yaşadığını kesin olarak ortaya koymuştur.\n" +
                    "\n" +
                    "M.Ö. 7. yüzyılda Pisidya, Frig devleti ile birlikte Lidya hakimiyetine girmiş ve M.Ö. 546 tarihinde Lidya Kralı Kroissos'un Pers Kralı Kyros'a yenilmesiyle birlikte bölge Pers hegemonyasına girmiştir. M.Ö. 334 yılında Büyük İskender Çanakkale'den Anadolu'ya girdikten sonra önüne çıkan Karya, Likya, Pamphylia kuvvetlerini ezerek Kestros (Aksu) Vadisi'nden Pisidya'ya girmiş, M.Ö. 333'te Sagalassos ve Kremna'yı da zaptetmiştir. İskender'in ölümünden sonra Pisidya önce Seleukoslar’a (M.Ö. 301) daha sonra da Bergama Krallığı'na bağlanmış (M.Ö. 228) ve Roma hakimiyetine girmiştir. Roma Çağı’nda Pisidya'nın her yerinde yoğun bir yerleşme vardır. Birçok yeni şehir kurulmuş, eski merkezler yeniden onarılmıştır. Bugün Burdur sınırları içinde bulunan harabelerin hemen hemen hepsinde bu çağa ait mimari kalıntılar görülmektedir. Kremna, Komama (Ürkütlü), Olbasa (Belenli) ve Sagalassos en önemlileridir.\n" +
                    "\n" +
                    "Roma İmparatorluğu'nun ikiye ayrılmasıyla Pisidya, Bizans İmparatorluğu’nun eline geçmiş ve bölgenin önemli merkezleri yavaş yavaş gerileyerek eski değerlerini kaybetmiştir. Bu sönük çağ, M.S. XI. yy. sonlarına doğru Türk hakimiyetinin başlamasına kadar devam etmiştir. 1071 - 1100 tarihlerinde Anadolu'ya gelen Türklerden Kınalı aşireti Pisidya'ya gelerek Burdur'a yerleşmiştir. Selçuklu Devleti'nin egemenlik alanına giren şehir 1075 - 1120 yıllarında sınır kenti olarak varlığını sürdürmüştür. Selçuklulardan sonra Hamitoğulları Beyliği'nin topraklarına katılan Burdur, I. Murat döneminde Hamitoğulları Beyliği’nden satın alınmıştır. O dönemde Tirkemiş kazası olarak anılan Burdur, Yıldırım'ın1391'deki seferi sonunda kesin olarak Osmanlı denetimi altına girmiştir. 1920'de bağımsız sancak olan Burdur, Cumhuriyetten sonra 1923'te il durumuna geçmiştir.\n" +
                    "\n" +
                    "Burdur İsmi (BURdaDUR)\n" +
                    "\n" +
                    "Burdur şehrini kuran Türkmen boylarından Kınalı aşireti mensupları burayı bulduklarında, bölgenin güzelliği karşısında \"Cennet buradadır. Burda dur!\" demişler ve \"Burda dur!\" sözü zamanla halk arasında \"Burdur\" haline dönüşmüştür.\n" +
                    "\n" +
                    "Eski Yunan Mitolojisinde kahraman Ulis (Aşil), tanrıların gazabına uğrar. Yunanistan'dan kovulur. Yolu Antalya yakınlarına düşer. Geceleri kutup yıldızına bakarak, kuzeye doğru ilerler. Karşısına bir göl çıkar ve o sırada gaibten bir ses ona Rumca ve eski Latince \"Ezostas! (Burada dur!)\" diye seslenir. Ulis burada durur ve burayı yurt tutar. Selçuklular Anadolu fetihleri sırasında Burdur'u fethederler. Köyün ismini \"Ezostas\" olarak öğrenirler. Rumca bilmedikleri için manasını sorarlar. \"Burada dur\" anlamına geldiğini öğrenirler. Buraya yerleşen Türkmen aşiretleri \"Burada dur\" kelimesini zamanla \"Burdur\" olarak telaffuz etmeye başlarlar ve bu kelime şehrin yeni ismi olur.");

        }


    }

    public void geri(View view) {
        Intent intent = new Intent(getApplicationContext(), yerlerinGosterimi.class);

        startActivity(intent);
    }

    public  void  onPause() {
        super.onPause();
        if (textToSpeech != null) {
            textToSpeech.stop();

        }
    }


}
