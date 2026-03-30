// Yahya AKTAN 2321021010

import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author akt
 */
public class Krallik {

    private int toplam_altin;
    private int toplam_yiyecek;
    private int halk_memnuniyeti;
    private Sehir[] sehirler;
    public int tur = 1;

    public Krallik() {

    }

    public void oyunuBaslat() {
        setToplam_altin(500);
        setToplam_yiyecek(300);

        sehirler = new Sehir[3];                                             // 3 şehirlik dizi oluşturur
        sehirler[0] = new Ticaret_Sehri("Limankent", "Ticaret", 0, 0);
        sehirler[1] = new Tarım_Sehri("Yesilova", "Tarim", 0, 0);
        sehirler[2] = new Sanayi_Sehri("Izmit", "Sanayi", 0, 0);
        halkMemnuniyeti();                                                   // Başlangıç halk memnuniyeti hesaplanır             
        Scanner scan = new Scanner(System.in);                              // Kullanıcıdan input almak için Scanner

        while (!oyunBittiMi()) {                // oyunBittiMi() fonksiyonundaki koşullara göre oyun oynanır.
            turuOyna(scan);
        }
        System.out.println("======== Oyun Bitti! ========");        //Oyun sonundaki bilgiler
        System.out.println("Altin: " + getToplam_altin() + " \nYiyecek: " + getToplam_yiyecek());
    }

    public void halkMemnuniyeti() {
        int toplamMemnuniyet = 0;
        for (int i = 0; i < sehirler.length; i++) {
            toplamMemnuniyet += sehirler[i].getSehir_halk_memnuniyet_seviyesi();    // Tüm şehirlerin memnuniyet toplanır,
        }
        int ortalamaMemnuniyet = toplamMemnuniyet / sehirler.length;                // Ortalaması alınır ve atanır                                                                                   
        setHalk_memnuniyeti(ortalamaMemnuniyet);
    }

    public void sehirler() {
        for (int i = 0; i < sehirler.length; i++) {                             // Sehirlerin ismini listeler
            System.out.println((i + 1) + " )" + sehirler[i].getSehir_adi());
        }

    }

    public void turuOyna(Scanner scan) {
        System.out.println("==== Orta Cag Kralligi Yonetim Oyunu ====");
        System.out.println("Yil: " + getTur());
        System.out.println("Altin: " + getToplam_altin());                          // Oyunun menüsündeki yazılar
        System.out.println("Yiyecek: " + getToplam_yiyecek());
        System.out.println("Halk memnuniyeti: " + getHalk_memnuniyeti());
        System.out.println("");
        System.out.println("Kralliktaki Bolgeler:");
        sehirler();                                                                 // Sehirleri ekrana listeler
        System.out.println("");
        System.out.println("Ne Yapmak Istersiniz?");
        System.out.println("1) Sehirleri goruntule");
        System.out.println("2) Belirli bir sehre yatirim yap");
        System.out.println("3) Vergi oranini ayarla");
        System.out.println("4) Turu bitir");
        System.out.println("============================================");
        System.out.println("Seciminiz nedir?");

        int secim_islemi = scan.nextInt();                                      // Kullanıcının menüdeki seçimleri için Scanner

        switch (secim_islemi) {
            case 1:
                sehirleriGoruntule();                                           // Sehirleri detaylıca gösterir
                System.out.println("");
                turuOyna(scan);                                                 // Aynı tura devam eder
                break;
            case 2:
                sehireYatırımYap(scan);                                         // Yatırım fonksiyonu çağrılır
                System.out.println("");
                turuOyna(scan);
                break;
            case 3:
                vergiyiAyarla(scan);                                            // Vergiyi ayarlar
                System.out.println("");
                turuOyna(scan);
                break;
            case 4:
                turSonuUretim();                                                // Tur sonu üretim yapılır
                halkMemnuniyeti();                                              // Halk memnuniyeti güncellenir
                tur++;                                                          // Sonraki tura geçer
                System.out.println("");
                return;

            default:
                System.out.println("Gecersiz islem");                           // Menü dışı bir şey seçilip seçilmediğini kontrol eder
                turuOyna(scan);
        }

    }

    public void sehirleriGoruntule() {
        for (int i = 0; i < sehirler.length; i++) {                             // Döngüyle beraber her şehir için bilgiGoster metodunu çağırır.
            System.out.print((i + 1) + " )");
            sehirler[i].bilgiGoster();
        }
    }

    public void sehireYatırımYap(Scanner scan) {
        System.out.println("Hangi sehire yatirim yapmak istersiniz");
        sehirler();
        System.out.println("Sehir seciniz...");
        int index = scan.nextInt() - 1;                                         // Yapılacak yatırım için şehir seçilir indeks dönüşümü yapılır
        if (index >= 0 && index < sehirler.length) {                            // İndeks kontrolü yapılır
            System.out.println("Yatirim miktari: ");
            int miktar = scan.nextInt();
            if (this.toplam_altin >= miktar) {                                  // Yatırım miktarına göre altın hesaptan düşer
                this.toplam_altin -= miktar;
                sehirler[index].yatirimYap(miktar);
            } else {
                System.out.println("Yetersiz Altin!");                          // Yeterli altin kontrolü yapılır
            }
        } else {
            System.out.println("Gecersiz secim!");
        }

    }

    public void vergiyiAyarla(Scanner scan) {
        System.out.println("Hangi sehirin vergisini duzenlemek istersiniz");             // Yapılacak yatırım için şehir seçilir
        sehirler();
        System.out.println("Sehir seciniz...");
        int index = scan.nextInt() - 1;                                                 //  indeks dönüşümü yapılır
        Sehir sehir = sehirler[index];
        if (index >= 0 && index < sehirler.length) {                                    // indeks kontrolü
            System.out.println("Vergi artisi ne kadar olacak?");
            int artis = scan.nextInt();                                                 // Kulanıcıdan artis miktari alinir
            int yeni_vergi = sehir.getVergi_orani() + artis;                            // Eski vergiye eklenir
            sehir.setVergi_orani(yeni_vergi);                                           // Yeni vergi set edilir
            sehir.setSehir_halk_memnuniyet_seviyesi(sehir.getSehir_halk_memnuniyet_seviyesi() - artis);     // Vergi artacağı için memnuniyet düşürülür ve yeni değer atanır
            System.out.println("Yeni vergi: " + sehir.getVergi_orani());
        }
    }

    public void turSonuUretim() {
        for (int i = 0; i < sehirler.length; i++) {                                 // Döngüyle her şehrin tur sonu üretimi yapılır
            sehirler[i].turSonuUretimHesapla(this);
            this.toplam_altin += sehirler[i].getUretilen_altin();
            this.toplam_yiyecek += sehirler[i].getUretilen_yiyecek();
        }

    }

    public boolean oyunBittiMi() {
        if (tur >= 10) {                                                            // Altin , yiyecek , tur ve halk memnuniyetinin belirli koşullara karşılaştırılır ve bunlara göre                                                                               // oyunun bit
            System.out.println("Tur sayisi bitti.");                                // oyunun bitip bitmeyeceği kontrol edilir.
            return true;
        }
        if (toplam_altin <= 0) {
            System.out.println("Kralligin altini bitti.");
            return true;
        }
        if (toplam_yiyecek <= 0) {
            System.out.println("Kralligin yiyecegi bitti.");
            return true;
        }
        if (getHalk_memnuniyeti() == 0) {
            System.out.println("Halk memnuniyeti 0 ");
            return true;
        }
        return false;
    }

    // Getter setter metodlar
    public int getToplam_altin() {
        return toplam_altin;
    }

    public void setToplam_altin(int toplam_altin) {
        this.toplam_altin = toplam_altin;
    }

    public int getToplam_yiyecek() {
        return toplam_yiyecek;
    }

    public void setToplam_yiyecek(int toplam_yiyecek) {
        this.toplam_yiyecek = toplam_yiyecek;
    }

    public int getHalk_memnuniyeti() {
        return halk_memnuniyeti;
    }

    public void setHalk_memnuniyeti(int halk_memnuniyeti) {
        this.halk_memnuniyeti = halk_memnuniyeti;
    }

    public Sehir[] getSehirler() {
        return sehirler;
    }

    public void setSehirler(Sehir[] sehirler) {
        this.sehirler = sehirler;
    }

    public int getTur() {
        return tur;
    }

}
