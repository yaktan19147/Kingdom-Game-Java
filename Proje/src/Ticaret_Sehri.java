/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author akt
 */
public class Ticaret_Sehri extends Sehir {

    public Ticaret_Sehri(String ad, String tip, int altin, int yiyecek) {
        super(ad, tip, altin, yiyecek);
    }

    @Override
    public void turSonuUretimHesapla(Krallik krallik) {
        setUretilen_altin((getUretim_kapasitesi() + getGelisim_seviyesi()) * 4 * (getVergi_orani() / 10));              // Şehirler üretim kapasitesi, gelişim seviyesive vergi oranına gör
        setUretilen_yiyecek((getUretim_kapasitesi() + getGelisim_seviyesi()) * 2 * (getVergi_orani() / 10));                  // belirli bir oranda altin ve yiyecek üretir
        if (baskinGerceklesiyorMu()) {
            int yagmalanma_miktari = 10;
            setUretilen_altin(getUretilen_altin() - yagmalanma_miktari);                         // Baskin gerçekleştiğinde üretilen ürünler yağmalanır
            setUretilen_yiyecek(getUretilen_yiyecek() - yagmalanma_miktari);
            System.out.println(getSehir_adi() + " sehirinde baskın gerceklestigi icin uretim azaldı.");
        }
        if (getUretilen_altin() < 0 || getUretilen_yiyecek() < 0) {
            setUretilen_altin(0);
            setUretilen_yiyecek(0);                         // Üretilen ürünlerin negatif sayıya dönüşmemesini engeller
        }
        System.out.println(getSehir_adi() + " sehrine " + getUretilen_altin() + " altin ve " + getUretilen_yiyecek() + " yiyecek uretildi."); // üretim sonrasi bilgiler

    }
}
