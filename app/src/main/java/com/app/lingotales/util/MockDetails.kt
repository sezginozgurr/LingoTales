package com.app.lingotales.util

import com.app.lingotales.R
import com.app.lingotales.presentation.home.detail.DetailBlock

object MockDetails {

    private val data: Map<String, List<DetailBlock>> = mapOf(
        "Toprak ve Uçan Tohum" to listOf(
            DetailBlock.PageText(
                "Toprak, meraklı bir çocuktu. Cebinde hep bir büyüteç, çantasında küçük bir suluk taşırdı. Bir sabah penceresinin önüne, bembeyaz tüylü minik bir tohum düştü. Tohum, rüzgârla dans eden minicik bir paraşüt gibiydi.\n" +
                        "“Pıt!” diye fısırdadı tohum, sanki yere konarken çıkardığı sesi söylemek ister gibi. Toprak gülümsedi:\u2028“Adın Pıt olsun,” dedi. “Seni büyütmek isterim.”"
            ),
            DetailBlock.PageImage(
                R.drawable.toprak_ve_ucan_tohum_1,
                "“Adın Pıt olsun,” dedi. “Seni büyütmek isterim.”\n" +
                        "Toprak ile Pıt, güneşi gören bir yer aramak için yola çıktılar. Ormanda ilk karşılarına Karınca Usta çıktı. Karınca Usta, antenlerini sallayıp “Toprağı elinle ufala,” dedi. “Kolay kırılıyorsa iyidir. Ne çok ıslak, ne de çok sert olsun.” Toprak bir avuç toprak alıp ufaladı; tam kıvamındaydı.\n" +
                        "Sonra, yaprakların arasından Kaplumbağa Tırt çıktı. “Güneş güzeldir,” dedi ağır ağır, “ama öğle sıcağında biraz gölge de ister. Sabır, her tohumun dostudur.” Toprak başını salladı. “Sabır bende var,” dedi.\n" +
                        "Dalların arasından Serçe Pıtpıt kondu: “Su şarkı söylerse tohum uyanır!” diye cıvıldadı. “Az ama düzenli su, en güzel ninni.” Toprak, suluğunu salladı: “Şarkımız hazır.”\n" +
                        "Güneşi seven, rüzgârı çok almayan küçük bir açıklık buldular."
            ),

            DetailBlock.PageText(
                "Toprak, minik bir çukur açtı, Pıt’ı içine yerleştirdi. Üstünü sevgiyle kapattı, avucuyla toprağı usulca bastırdı. Suluğundan birkaç damla döktü. “Hoş geldin, Pıt,” dedi.\n" +
                        "Ertesi gün Toprak yine geldi. Toprağın üstüne bir çöp parçasıyla minicik bir tabela yaptı: “Burada güzel şeyler filizleniyor.” Yanına küçük bir taş koydu ki rüzgâr toprağı dağıtmasın. Her gün azıcık su verdi, otları nazikçe ayıkladı.\n" +
                        "Bir akşamüstü rüzgâr hışır hışır esti. İnce bir yaprak, Toprak’ın yaptığı minik sulama kanalını tıkadı. “Su şarkı söyleyemiyor,” dedi Toprak telaşla. Hemen Kaplumbağa Tırt’ı çağırdı. Tırt, “Sakin ol,” dedi. Karınca Usta da geldi; birlikte yaprağı çekip kenara aldılar. Serçe Pıtpıt, gagasıyla kuru otları taşıdı. Kanal yeniden açıldı, su hafifçe “şıp şıp” diye mırıldandı."
            ),
            DetailBlock.PageImage(R.drawable.toprak_ve_ucan_tohum_2, "Yol arkadaşlarıyla hazırlık"),

            DetailBlock.PageText(
                "oprak, Pıt’a fısıldadı: “Buradayım. Sen uykunu al, ben beklerim.” Günler geçti. Bulutlar gölgeler yaptı, güneş sarı çizgiler çizdi, rüzgâr yaprakları taradı. Sabırla beklediler.\n" +
                        "Bir sabah… Toprağın kahverengisi arasından iki minik yeşil kulak belirdi! Pıt uyanmıştı. Toprak sevinçle ellerini çırptı. “Günaydın!” dedi. Pıt, her gün biraz daha uzadı. İncecik gövdesi güçlendi, yaprakları çoğaldı.\n" +
                        "Köyün çocukları merakla geldiler: Defne, Aras ve Zeliş. Toprak onlara anlattı: “Toprağı kontrol ettik, suyu düzenli verdik, güneşi paylaştık, gölgeyi ayarladık.” Çocuklar sırayla küçük kovalarla su taşıdılar. Her biri, Pıt’ın yanında kendi adını taşıyan minicik bir çiçek ekti. Orada küçük bir “Çocuk Bahçesi” doğdu.\n" +
                        "Sonra Pıt sarı bir taç gibi çiçek açtı. Çiçek, güldükçe arılar uğradı, rüzgâr içinden geçip şarkı söyledi. Zamanla sarı taç, beyaz bir topa dönüştü; yüzlerce minicik paraşüt tohumu ışıkla parladı.\n" +
                        "“Hazırım,” diye fısırdadı Pıt. “Ben büyüdüm, şimdi sıra kardeşlerimde.” Toprak ve arkadaşları derin bir nefes alıp aynı anda üflediler. Beyaz tohumlar havalanıp gökyüzünde küçük yıldızlar gibi dağıldı. Kimi dere kenarına, kimi okul bahçesine, kimi de boş bir saksıya kondu."
            ),
            DetailBlock.PageImage(R.drawable.toprak_ve_ucan_tohum_3, "İlk filiz ve küçük mucize"),

            DetailBlock.PageText(
                "Karınca Usta, “Emeğin meyvesi çoğalmaktır,” dedi. Kaplumbağa Tırt, “Sabır gölge gibi gezinir, ama hep yanımızdadır,” diye eklendi. Serçe Pıtpıt cıvıldadı: “Su şarkı söylemeye devam ediyor!”\n" +
                        "Toprak, Pıt’ın yanında oturup gülümsedi: “En küçük tohum bile sevgiyle büyür. Birlikte olunca daha da güzel büyür.” Çocuk Bahçesi gün geçtikçe renklendi; her sabah yeni bir filiz “merhaba” dedi.\n" +
                        "Ve o günden sonra, köyde hiçbir tohum yalnız kalmadı. Çocuklar dinlemeyi, beklemeyi, paylaşmayı öğrendi. Rüzgâr estiğinde beyaz paraşütler gökyüzünde dans etti, Toprak ve arkadaşları onlara el salladı.\n" +
                        "Masal burada bitince, bahçede yeni bir gün başlıyordu. Çünkü küçük bir sevgi, koca bir dünyayı yeşertebiliyordu. \uD83C\uDF31✨"
            )
        ),

        // Örnek: diğer hikayeler eklenebilir
        "Nil ve Uçurtma Postası" to listOf(
            DetailBlock.PageText("Nil, Pof adını verdiği uçurtmasıyla sisli kasabada rüzgâr postanesi kurdu."),
            DetailBlock.PageImage(R.drawable.ic_launcher_background, "Nil ve Pof tepeye çıkıyor"),
            DetailBlock.PageText("Çocukların sesleri ve renkli kurdelelerle sis dağıldı; değirmen döndü."),
            DetailBlock.PageImage(R.drawable.ic_launcher_background, "Renkli kuyruğun dansı")
        ),

        "Duru ve Fısıltı Bahçesi" to listOf(
            DetailBlock.PageText("Duru, konuşan bitkileri dinledi; su, gölge ve arı dostluğu ile bahçe canlandı."),
            DetailBlock.PageImage(R.drawable.ic_launcher_background, "Fısıltı Bahçesi uyanıyor")
        )
    )

    fun blocksFor(storyTitle: String): List<DetailBlock> = data[storyTitle].orEmpty()
}
