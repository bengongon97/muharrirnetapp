package com.example.menes.muharrirnetapp;

import com.example.menes.muharrirnetapp.PictureHandling.Embedded;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlogPost {

    @SerializedName("id")
    private Integer postId;
    @SerializedName("title")
    private PostTitle title;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("content")
    private PostContent content;
    @SerializedName("author")
    private String author; //neden sayı lan bu
    @SerializedName("categories") // bu da
    private List<String> categories;
    @SerializedName("excerpt")
    private PostExcerpt excerpt;
    @SerializedName("date")
    private String date;
    @SerializedName("featured_media")
    private Integer postPictureId;
    @SerializedName("status")
    private String status;
    @SerializedName("_embedded")
    private Embedded embedded;


    // Kept this minimal. Can (WILL*) be extended.
    public BlogPost(Embedded embedded, String status, Integer postPictureId, Integer postId, String date, PostTitle title, PostContent content, List<String> tags, String author, List<String> categories, PostExcerpt excerpt)
    {
        this.embedded = embedded;
        this.postId = postId;
        this.date = date;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.author = author;
        this.categories = categories;
        this.excerpt = excerpt;
        this.postPictureId = postPictureId;
        this.status = status;
    }

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    public Integer getPostPictureId() {
        return postPictureId;
    }

    public void setPostPictureId(Integer postPictureId) {
        this.postPictureId = postPictureId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public PostTitle getTitle() {
        return title;
    }

    public void setTitle(PostTitle title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public PostContent getContent() {
        return content;
    }

    public void setContent(PostContent content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public PostExcerpt getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(PostExcerpt excerpt) {
        this.excerpt = excerpt;
    }
}

/*
* Example body. The response comes as an array of json objects like the following
* {
        "id": 336,
        "date": "2018-08-26T01:33:26",
        "date_gmt": "2018-08-25T22:33:26",
        "guid": {
            "rendered": "http://muharrir.net/?p=336"
        },
        "modified": "2018-08-26T02:02:15",
        "modified_gmt": "2018-08-25T23:02:15",
        "slug": "su-tarafa-dogru",
        "status": "publish",
        "type": "post",
        "link": "http://muharrir.net/su-tarafa-dogru/",
        "title": {
            "rendered": "ŞU TARAFA DOĞRU"
        },
        "content": {
            "rendered": "<p>Uçağın kalkmasına daha bir saat vardı ve akşam namazı vakti girmişti. Namazımı kılıp ardından kızım için güzelce bir hediye alabilirdim. Hızlıca abdest alabileceğim bir yer aradım. Tuvaletten başka bir yer bulamadım. Buna canım çok sıkıldı. Çünkü kendimi bildim bileli tuvaletlerde abdest almak hoşuma gitmiyor. Bunun nedenini çok düşündüm. Sanırım bilinçaltımda yatan tuvaletin garip kokusu ve lavaboların temiz olmayışının görüntüsü beni hep engelliyordu. Başka çarem olmadığına kendimi ikna ettikten sonra kapıya yaklaştım. İçerden su sesi geliyordu. Yine düşünmeye başladım, bunu hep yapıyordum. Kimilerinin karar bile diye adlandıramayacağı birçok şey için hep düşünüyordum. Acaba beklemeli sonra rahat rahat mı almalı yoksa o vakti kızıma daha güzel bir hediye beğenmek için mi kullanmalıydım? Bu ikilemde bir süre kaldıktan sonra içeriye girdim. İki tane lavabo vardı. Bunlardan birisinde abdest almakta olan bir bey vardı. Alan dar olduğu için onu rahatsız etmek istemedim. Vakti iyi kullanmam gerektiğini tekrar hatırladıktan sonra abdest hazırlığına başladım. Bir yandan ceplerime peçete koyuyordum, paçalarımı sıvazlıyordum bir yandan da beyefendiyi gözlemliyordum. En fazla 35’li yaşlardaydı ya da 40’lı. Bilemiyorum… Zaten bu yaş tahmini işini de hiç beceremem. Üstünde açık renkli bir gömlek, altında kot pantolon vardı. Kolundaki saati ayakkabısıyla uyumluydu. Geriye doğru taranmış saçları gri tonlarındaydı, bu yaşını büyük gösteriyordu. Bunun aksine açık kahve tonundaki top sakalı ve yuvarlak çerçeveli gözlüğü onu genç gösteriyordu.</p>\n\n<p>Bir anda düşünmeye başladım. Muhtemelen aynı yere gidecektik çünkü bulunduğumuz ülkede eğer birisi açık tenliyse ve de namaz kılıyorsa yüksek ihtimalle Türkiye’ye dönüyordur. Bu düşünceler sadece bir girizgâhtı. Benim kafamı kurcalayan bu adamın niye burada olduğuydu. Aynı amaçla gelmiş olsak muhakkak bir yerlerde karşılaşırdık. Üstelik top sakalının olması beni hep oraya itiyordu. Vazgeç artık şu sınıflandırmalardan, dış görünüşüne bakarak insanları yargılama! Belki bir iş için gelmiştir. Hayır hayır, kesin öyle. Bu adam kesin SİTÖ’cü. Bunun başka bir ifadesi olamaz. Ben bunları düşünürken o da ayakkabılarını giyiyordu. Ben onu izliyordum ama aklım bunlarla meşguldü. Sonra bir anda önce boğuk sonradan netleşen bir sesle sanki düşünce dünyamdan irkilerek uyandım: “Hayırdır genç, ne işin var senin bu ülkede, yoksa sen bu Simleyici ekibinden misin?” dedi. Bu soruyla birlikte sarsılmıştım. Bu adam acaba benim zihnimi mi okuyordu? Bu düşündüklerimi de duydu mu acaba… Şaşırdığımı da pek fazla belli etmek istemiyordum ama ses tonum beni ele vermiş gibiydi: “Yok yok. Benim alakam olamaz. Ben buraya ÜKA aracılığıyla geldim.” dedim. Bu cevaptan sonra kendime gelmiş, ikili diyaloğumu daha düzgün sürdürmüştüm.</p>\n\n<p>&#8211; ÜKA mı, o nedir daha önce duymadım.</p>\n\n<p>&#8211; Ülkeleri Kalkındırma Ajansı.</p>\n\n<p>&#8211; Bu isim bana bir yerlerden tanıdık geliyor. Neyse ki onlardan değilsin. Eğer öyle olsaydı sana bir güzel nutuk atacaktım da neyse… Anlat bakalım genç neler yaptınız burada?</p>\n\n<p>Bu soruya cevap vermeli miydim, karşımdaki adam kimdi bilmiyordum. Bana önce sert çıkmış sonra bir anda yumuşayıvermişti. Ciddiyetimi bozmak istemedim, üstünkörü yaptığımız çalışmalardan bahsettim: “Birçok çalışmayı yürütüyoruz, gıda yardımları bunların başında geliyor. Bununla birlikte okul ve yetimhane inşası gibi birçok alanda hizmet ediyoruz. Başkanlığa bağlıyız.” dedim.</p>\n\n<p>Bu kadar kelam ettikten sonra benzer şekilde kendi işinden bahsetmesini bekledim fakat hiç oralı olmadı. Ben anlatırken dikkatle dinledi sanki biraz da huzursuz oldu. Gerçi hem uçağa az kalmıştı hem de daha namaz kılacaktık. Sanırım kızıma hediye alamayacaktım. Çünkü bu süreyi yakarak ben sordum bu sefer: “Ya siz? İş için geldiniz herhâlde, tüccar mısınız?” Gülümsedi. “Evet sayılır… Yani iş için geldim ama tüccar değilim. Müttehit Milletler ’de çalışıyorum, bu bölgenin raporlarını hazırlayıp düzenli bir şekilde genel merkeze sunuyorum. İsmim Dardin Colen.” dedi. Beklediğimden fazla bilgi vermesine, özgüvenine şaşırırken bir yandan da Türkçesine hayran kalmıştım. “Türkçeyi bu kadar iyi nasıl öğrendiniz Dardin Bey” dedim.</p>\n\n<p>“Benim işim bu, birden fazla dile hâkimim. Dünyanın birçok yerinde saha hizmetinde bulundum. Yaşım ilerleyince de beni bu bölgeye verdiler, herhalde buradan emekli olacağım.” dedi. Güzel bir senaryo dinliyorum gibi hissettim adamın söylediklerine inanmak istemiyordum. Kendimce birçok çelişki yakaladım. Mesela neden en başta bana Simleyici’lerden misin diye sormuştu, neden o kelimeyi seçmişti? Bu bir yem sorusu muydu yoksa onlara sempatisi mi vardı. Yoksa gerçekten MM’de çalışan örgütle bağlantısı olan birisi miydi? Bu kadar düşündüğümü belli etmek istemedim. Zaten abdesti de biteli epey olmuştu. Gömleğinin cebinden bir bez çıkardı, gözlüklerini silmeye başladı. Bu süre zarfında bir şeyler söylemeliyim diye düşündüm. Söylemesem içimde kalacaktı. Dayanamadım. Tam gözlüğünü takıp kapıya yönelirken atladım:</p>\n\n<p>“Bir şey sorabilir miyim?” dedim.</p>\n\n<p>“Tabi, buyur bakalım ama zor olmasın; malum vakit dar uçağa yetişeceğim.”</p>\n\n<p>“Aslında tam da soru değil, fazla vaktinizi almam. Şunu merak ediyorum: Neden bana ilk başta Simleyici ekibinden misin diye sordunuz? Bunun yerine SİTÖ’cü müsün sen diyebilirdiniz.”</p>\n\n<p>Tam cevap verecekken üsteledim:</p>\n\n<p>“Durun ben söyleyeyim, siz ya beni saf zanneden bir Simleyici Terör Örgütü adamısınız ve bana tuzak soru sorarak test etmek istediniz. Beklediğiniz cevabı alamayınca da bir şeyler uydurdunuz. Ya da gerçekten MM’de çalışıyorsunuz ve bu örgütle kol kola olduğunuz için örgüt olarak nitelendirmek zorunuza gidiyor. Anlayamıyorum, bir de gelmişsiniz burada namaz için hazırlık yapıyorsunuz.” Biraz olsun rahatlamıştım. En azından kızıma hediye alamayacağım için pişman olmayacaktım.</p>\n\n<p>Dardin Bey de sinirden kıpkırmızı kesilmişti. Ama belli etmemeye çalışıyordu. Derin bir nefes aldı. Sakinliğin tablosunu çizmek istercesine dişlerini gösteren hafif bir gülümseme ve kısık gözlerle: “Kıble şu tarafa doğru.” deyip gitti.</p>\n",
            "protected": false
        },
        "excerpt": {
            "rendered": "<p>Uçağın kalkmasına daha bir saat vardı ve akşam namazı vakti girmişti. Namazımı kılıp ardından kızım için güzelce bir hediye alabilirdim. Hızlıca abdest alabileceğim bir yer aradım. Tuvaletten başka bir yer bulamadım. Buna canım çok sıkıldı. Çünkü kendimi bildim bileli tuvaletlerde abdest almak hoşuma gitmiyor. Bunun nedenini çok düşündüm. Sanırım bilinçaltımda [&hellip;]</p>\n",
            "protected": false
        },
        "author": 3,
        "featured_media": 339,
        "comment_status": "open",
        "ping_status": "open",
        "sticky": false,
        "template": "",
        "format": "standard",
        "meta": [],
        "categories": [
            17,
            16
        ],
        "tags": [],
        "_links": {
            "self": [
                {
                    "href": "http://muharrir.net/wp-json/wp/v2/posts/336"
                }
            ],
            "collection": [
                {
                    "href": "http://muharrir.net/wp-json/wp/v2/posts"
                }
            ],
            "about": [
                {
                    "href": "http://muharrir.net/wp-json/wp/v2/types/post"
                }
            ],
            "author": [
                {
                    "embeddable": true,
                    "href": "http://muharrir.net/wp-json/wp/v2/users/3"
                }
            ],
            "replies": [
                {
                    "embeddable": true,
                    "href": "http://muharrir.net/wp-json/wp/v2/comments?post=336"
                }
            ],
            "version-history": [
                {
                    "count": 4,
                    "href": "http://muharrir.net/wp-json/wp/v2/posts/336/revisions"
                }
            ],
            "predecessor-version": [
                {
                    "id": 344,
                    "href": "http://muharrir.net/wp-json/wp/v2/posts/336/revisions/344"
                }
            ],
            "wp:featuredmedia": [
                {
                    "embeddable": true,
                    "href": "http://muharrir.net/wp-json/wp/v2/media/339"
                }
            ],
            "wp:attachment": [
                {
                    "href": "http://muharrir.net/wp-json/wp/v2/media?parent=336"
                }
            ],
            "wp:term": [
                {
                    "taxonomy": "category",
                    "embeddable": true,
                    "href": "http://muharrir.net/wp-json/wp/v2/categories?post=336"
                },
                {
                    "taxonomy": "post_tag",
                    "embeddable": true,
                    "href": "http://muharrir.net/wp-json/wp/v2/tags?post=336"
                }
            ],
            "curies": [
                {
                    "name": "wp",
                    "href": "https://api.w.org/{rel}",
                    "templated": true
                }
            ]
        }
    },
* */