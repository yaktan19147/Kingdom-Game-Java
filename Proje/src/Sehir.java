/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author akt
 */
public abstract class Sehir {

    private String sehir_adi;
    private int uretim_kapasitesi;
    private int gelisim_seviyesi;
    private int ordu_seviyesi;
    private int guvenlik_seviyesi;
    private int sehir_halk_memnuniyet_seviyesi;
    private String sehir_turu;
    private int baskin_ihtimali;
    private int uretilen_altin;
    private int uretilen_yiyecek;
    private int vergi_orani;

    public Sehir(String ad, String tip, int altin, int yiyecek) {
        this.sehir_adi = ad;
        this.sehir_turu = tip;
        this.uretilen_altin = 0;
        this.uretilen_yiyecek = 0;
        this.uretim_kapasitesi = 10;
        this.gelisim_seviyesi = 1;
        this.ordu_seviyesi = 10;            // Şehirlerin özelliği için başlangıç değerleri
        this.guvenlik_seviyesi = 20;
        this.sehir_halk_memnuniyet_seviyesi = 50;
        this.baskin_ihtimali = 30;
        this.vergi_orani = 20;    // başlangıç olarak %20 vergi

    }

    public void yatirimYap(int altin_miktari) {
        int yatirim_miktari = altin_miktari / 100;   // Parametre olarak gelen altından her 100 altına yatirim miktarı hesaplanır ve gerekli yerlere yatırılır.
        if (yatirim_miktari > 0) {
            this.gelisim_seviyesi += yatirim_miktari;
            this.ordu_seviyesi += yatirim_miktari;
            this.guvenlik_seviyesi += yatirim_miktari;
            this.baskin_ihtimali -= yatirim_miktari;

            System.out.println(getSehir_adi() + " sehrine " + altin_miktari + " kadar altin yatirim yapildi.");
            System.out.println("Yeni gelisim seviyesi: " + getGelisim_seviyesi());                                    // Yatırım sonrası özellikler ekrana basılır
            System.out.println("Yeni ordu seviyesi: " + getOrdu_seviyesi());
            System.out.println("Yeni guvenlik seviyesi: " + getGuvenlik_seviyesi());
        } else {
            System.out.println("Yatırım yapmak için gereken altin miktari 100 altin ve katlaridir.");
        }
    }

    public boolean baskinGerceklesiyorMu() {
        int random_sayi = (int) (Math.random() * 100);      // [0,99] kapalı aralığında sayı üretilir ve bu sayi baskin ihtimli ile karşılaştırılır
        if (random_sayi < baskin_ihtimali) {                // Buna göre baskın gerçekleşir
            System.out.println("Baskin gerceklesiyor");
            System.out.println("============================================");
            this.uretim_kapasitesi -= 5;                    // Baskın gerçekleştinde bazı özelliklerin seviyesi düşer
            this.guvenlik_seviyesi -= 5;
            this.sehir_halk_memnuniyet_seviyesi -= 10;
            if (getUretim_kapasitesi() < 0 || getGuvenlik_seviyesi() < 0 || getSehir_halk_memnuniyet_seviyesi() < 0) {
                this.uretim_kapasitesi = 0;                                                                               // Seviyelerin negatife sayıya dönüşmeme kontrolü yapılır
                this.guvenlik_seviyesi = 0;
                this.sehir_halk_memnuniyet_seviyesi = 0;
            }
            System.out.println("Baskin gerceklesti.");
            System.out.println("Yeni uretim kapasitesi: " + getUretim_kapasitesi() // Baskın sonrasi özellikler ekrana basilir
                    + " Yeni guvenlik seviyesi: " + getGuvenlik_seviyesi()
                    + " Yeni halk memnuniyet seviyesi: " + getSehir_halk_memnuniyet_seviyesi());

            return true;
        } else {
            return false;
        }

    }

    public abstract void turSonuUretimHesapla(Krallik krallik);     // Alt sınıfların mutlaka emessi gereken metod

    public void bilgiGoster() {
        System.out.println("Sehir Adi: " + getSehir_adi());
        System.out.println("Sehir Tipi: " + getSehir_turu());
        System.out.println("Sehir Gelisim: " + getGelisim_seviyesi());                              // Şehirlerin bilgilerini yazan metod
        System.out.println("Sehir Uretim Kapasitesi: " + getUretim_kapasitesi());
        System.out.println("Halk memnuniyet seviyesi: " + getSehir_halk_memnuniyet_seviyesi());
        System.out.println("-----------------------------------------");

    }

    // Getter Setter metodlar
    public void setSehir_adi(String sehir_adi) {
        this.sehir_adi = sehir_adi;
    }

    public void setSehir_turu(String sehir_turu) {
        this.sehir_turu = sehir_turu;
    }

    public void setUretim_kapasitesi(int uretim_kapasitesi) {
        this.uretim_kapasitesi = uretim_kapasitesi;
    }

    public void setGelisim_seviyesi(int gelisim_seviyesi) {
        this.gelisim_seviyesi = gelisim_seviyesi;
    }

    public void setOrdu_seviyesi(int ordu_seviyesi) {
        this.ordu_seviyesi = ordu_seviyesi;
    }

    public void setGuvenlik_seviyesi(int guvenlik_seviyesi) {
        this.guvenlik_seviyesi = guvenlik_seviyesi;
    }

    public void setSehir_halk_memnuniyet_seviyesi(int sehir_halk_memnuniyet_seviyesi) {
        this.sehir_halk_memnuniyet_seviyesi = sehir_halk_memnuniyet_seviyesi;
    }

    public void setBaskin_ihtimali(int baskin_ihtimali) {
        this.baskin_ihtimali = baskin_ihtimali;
    }

    public int getUretilen_altin() {
        return uretilen_altin;
    }

    public int getVergi_orani() {
        return vergi_orani;
    }

    public void setVergi_orani(int vergi_orani) {
        this.vergi_orani = vergi_orani;
    }

    public void setUretilen_altin(int uretilen_altin) {
        this.uretilen_altin = uretilen_altin;
    }

    public int getUretilen_yiyecek() {
        return uretilen_yiyecek;
    }

    public void setUretilen_yiyecek(int uretilen_yiyecek) {
        this.uretilen_yiyecek = uretilen_yiyecek;
    }

    public String getSehir_adi() {
        return sehir_adi;
    }

    public String getSehir_turu() {
        return sehir_turu;
    }

    public int getUretim_kapasitesi() {
        return uretim_kapasitesi;
    }

    public int getGelisim_seviyesi() {
        return gelisim_seviyesi;
    }

    public int getOrdu_seviyesi() {
        return ordu_seviyesi;
    }

    public int getGuvenlik_seviyesi() {
        return guvenlik_seviyesi;
    }

    public int getSehir_halk_memnuniyet_seviyesi() {
        return sehir_halk_memnuniyet_seviyesi;
    }

    public int getBaskin_ihtimali() {
        return baskin_ihtimali;
    }
}
