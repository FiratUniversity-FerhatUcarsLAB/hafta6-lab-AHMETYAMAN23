import java.util.Scanner;

public class SiparisSistemi {

    // 1. Ana yemek fiyatı
    public static int getMainDishPrice(int secim) {
        int fiyat;
        switch(secim) {
            case 1: fiyat = 85; break;  // Izgara Tavuk
            case 2: fiyat = 120; break; // Adana Kebap
            case 3: fiyat = 110; break; // Levrek
            case 4: fiyat = 65; break;  // Mantı
            default: fiyat = 0;
        }
        return fiyat;
    }

    // 2. Başlangıç fiyatı
    public static int getAppetizerPrice(int secim) {
        int fiyat;
        switch(secim) {
            case 1: fiyat = 25; break; // Çorba
            case 2: fiyat = 45; break; // Humus
            case 3: fiyat = 55; break; // Sigara Böreği
            default: fiyat = 0;
        }
        return fiyat;
    }

    // 3. İçecek fiyatı (Happy Hour indirimi sadece burada)
    public static double calculateDrinkPrice(int secim, int saat) {
        double fiyat;
        switch(secim) {
            case 1: fiyat = 15; break;  // Kola
            case 2: fiyat = 12; break;  // Ayran
            case 3: fiyat = 35; break;  // Taze Meyve Suyu
            case 4: fiyat = 25; break;  // Limonata
            default: fiyat = 0;
        }

        if (isHappyHour(saat)) {
            fiyat = fiyat - (fiyat * 20 / 100); // %20 sadece içecekten düşüyor ✅
        }

        return fiyat;
    }

    // 4. Tatlı fiyatı
    public static int getDessertPrice(int secim) {
        int fiyat;
        switch(secim) {
            case 1: fiyat = 65; break; // Künefe
            case 2: fiyat = 55; break; // Baklava
            case 3: fiyat = 35; break; // Sütlaç
            default: fiyat = 0;
        }
        return fiyat;
    }

    // 5. Combo sipariş kontrolü
    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    // 6. Gün hafta sonu mu?
    public static boolean isWeekend(int gun) {
        return (gun == 6 || gun == 7);
    }

    // 7. Matine / Saat bazlı değil ama gün gereksinimi için hafta içi mi?
    public static boolean isWeekday(int gun) {
        return !(gun == 6 || gun == 7);
    }

    // 8. Happy Hour kontrolü (14:00–17:00)
    public static boolean isHappyHour(int saat) {
        return (saat >= 14 && saat <= 17);
    }

    // 9. İndirim hesaplama (YAŞ + MESLEK + GÜN + COMBO + 200 TL üzeri)
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat, boolean haftaIci) {
        double indirim = 0;

        if (combo) {
            indirim = 15;
        } else {
            if (tutar > 200) {
                indirim = 10;
            }
        }

        if (ogrenci) {
            indirim += haftaIci ? 10 : 0;
        }

        switch (ogrenci ? 1 : 3) {
            case 1:
                if (haftaIci)
                    indirim += 10;
                break;
            case 2:
                if(gunCek(gun) == 3)
                    indirim += 35;
                break;
            default:
                indirim += 0;
        }

        return indirim;
    }

    // 10. Bahşiş önerisi (%10)
    public static double calculateServiceTip(double tutar) {
        return tutar * 10 / 100;
    }

    // 11. Gün adını çözme
    public static String getGunAdi(int gun) {
        String gunAdi;
        switch(gun) {
            case 1: gunAdi = "Pazartesi"; break;
            case 2: gunAdi = "Salı"; break;
            case 3: gunAdi = "Çarşamba"; break;
            case 4: gunAdi = "Perşembe"; break;
            case 5: gunAdi = "Cuma"; break;
            case 6: gunAdi = "Cumartesi"; break;
            case 7: gunAdi = "Pazar"; break;
            default: gunAdi = "Bilinmiyor";
        }
        return gunAdi;
    }

    // 12. Siparişin Combo olup olmadığını metot içinde onay mesajıyla döndürme
    public static String getComboMessage(boolean combo) {
        return combo ? "EVET (Combo - %15 indirim)" : "HAYIR";
    }

    // 13. Rastgele ikram indirimi (eğitsel Math.random kullanımı, toplam tutara ÇOK küçük dokunuş)
    public static double getRandomTreatDiscount() {
        return Math.random() < 0.5 ? 5 : 0; // %50 ihtimal 5 TL ikram indirimi ✅ (eğitsel kullanım)
    }

    // Kullanıcıdan meslek alıp boolean olarak gün kontrol metodu
    public static int gunCek(int g) { return g; } // switch'te gün referansı için yardımcı

    static int gun; // Öğretmen çarşamba kontrolü için global referans

    // 6. Final fiyat hesaplama (tüm metotlar bir araya)
    public static double calculateFinalPrice(
            int anaSecim, int basSecim, int iceSecim, int tatSecim,
            int yas, int meslek, int gun, int saat
    ) {

        int anaFiyat = getMainDishPrice(anaSecim);
        int basFiyat = getAppetizerPrice(basSecim);
        double iceFiyat = calculateDrinkPrice(iceSecim, saat);
        int tatFiyat = getDessertPrice(tatSecim);

        boolean anaVar = anaFiyat > 0;
        boolean icecekVar = iceFiyat > 0;
        boolean tatliVar = tatFiyat > 0;
        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);
        boolean haftaIci = (gun >= 1 && gun <= 5);

        double araToplam = anaFiyat + basFiyat + iceFiyat + tatFiyat;

        double ikram = getRandomTreatDiscount();
        araToplam -= ikram;

        double discountRate;
        switch(meslek) {
            case 1: discountRate = (gun >= 1 && gun <= 5) ? 10 : 5; break;
            case 2:
                if (gun == 3)
                    discountRate = 35;
                else
                    discountRate = 0;
                break;
            default: discountRate = 0;
        }

        if (combo) {
            discountRate += 15;
        }

        if (yas < 12) {
            discountRate += 25;
        } else {
            if (yas >= 65) {
                discountRate += 30;
            }
        }

        if (meslek == 2 && gun == 3) {
            discountRate += 35;
        }

        return surpriseLuckyDiscount(araToplam - (araToplam * discountRate / 100));
    }

    // Şanslı sürpriz kupon
    public static double surpriseLuckyDiscount(double fiyat) {
        return Math.random() < 0.3 ? fiyat - 10 : fiyat; // %30 ihtimal 10 TL kupon ✅
    }

    // 7. Bilet bilgisi yazdırma (printf ile zorunlu çıktı)
    public static void generateTicketInfo(
            int ana, int bas, int ice, int tat,
            int yas, int meslek, int gun, int saat
    ) {
        double finalFiyat = calculateFinalPrice(ana, bas, ice, tat, yas, meslek, gun, saat);
        double tip = calculateServiceTip(finalFiyat);
        double totalWithTip = finalFiyat + tip;

        System.out.println("\n--- SIPARIS RAPORU ---");
        System.out.printf("Gün: %s\n", getGunAdi(gun));
        System.out.printf("Saat: %d:00\n", saat);
        System.out.printf("Ana Yemek Ücreti: %d TL\n", getMainDishPrice(ana));
        System.out.printf("Başlangıç Ücreti: %d TL\n", getAppetizerPrice(bas));
        System.out.printf("İçecek Ücreti: %.2f TL\n", calculateDrinkPrice(ice, saat));
        System.out.printf("Tatlı Ücreti: %d TL\n", getDessertPrice(tat));
        System.out.printf("Ara Toplam: %.2f TL\n", (getMainDishPrice(ana) + getAppetizerPrice(bas) + calculateDrinkPrice(ice,saat) + getDessertPrice(tat)));
        System.out.printf("Combo Sipariş: %s\n", getComboMessage(isComboOrder(getMainDishPrice(ana)>0, calculateDrinkPrice(ice,saat)>0, getDessertPrice(tat)>0)));
        System.out.printf("İndirimli Tutar: %.2f TL\n", finalFiyat);
        System.out.printf("Bahşiş Önerisi (%%10): %.2f TL\n", tip);
        System.out.printf("Ödenecek Tutar (Bahşiş Dahil): %.2f TL\n", totalWithTip);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Gün Seç (1=Pzt ... 7=Paz): ");
        gun = sc.nextInt();

        System.out.print("Meslek Seç (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
        int meslek = sc.nextInt();

        System.out.print("Saat Gir (Örnek 15): ");
        int saat = sc.nextInt();

        System.out.print("Yaş Gir: ");
        int yas = sc.nextInt();

        System.out.println("\nAna Yemekler:\n1=Izgara Tavuk (85 TL)\n2=Adana Kebap (120 TL)\n3=Levrek (110 TL)\n4=Mantı (65 TL)");
        System.out.print("Seç: ");
        int ana = sc.nextInt();

        System.out.println("\nBaşlangıçlar:\n1=Çorba (25 TL)\n2=Humus (45 TL)\n3=Sigara Böreği (55 TL)");
        System.out.print("Seç: ");
        int bas = sc.nextInt();

        System.out.println("\nİçecekler:\n1=Kola (15 TL)\n2=Ayran (12 TL)\n3=Taze Meyve Suyu (35 TL)\n4=Limonata (25 TL)");
        System.out.print("Seç: ");
        int ice = sc.nextInt();

        System.out.println("\nTatlılar:\n1=Künefe (65 TL)\n2=Baklava (55 TL)\n3=Sütlaç (35 TL)");
        System.out.print("Seç: ");
        int tat = sc.nextInt();

        generateTicketInfo(ana, bas, ice, tat, yas, meslek, gun, saat);

        sc.close();
    }
}
