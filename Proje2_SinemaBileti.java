import java.util.Scanner;

public class SinemaBiletSistemi {

    // 1. Hafta sonu kontrolü
    public static boolean isWeekend(int gun) {
        return (gun == 6 || gun == 7);
    }

    // 2. Matine kontrolü
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3. Temel fiyat hesaplama
    public static int calculateBasePrice(int gun, int saat) {
        boolean haftaSonu = isWeekend(gun);
        boolean matine = isMatinee(saat);

        if (!haftaSonu) {
            if (matine) {
                return 45;
            } else {
                return 65;
            }
        } else {
            if (matine) {
                return 55;
            } else {
                return 85;
            }
        }
    }

    // 4. İndirim hesaplama
    public static double calculateDiscount(int yas, int meslek, int gun) {
        double indirim = 0;

        if (yas < 12) {
            return 25;
        }

        if (yas >= 65) {
            return 30;
        }

        switch (meslek) {
            case 1: // Öğrenci
                indirim = (gun >= 1 && gun <= 4) ? 20 : 15;
                break;
            case 2: // Öğretmen
                if (gun == 3) {
                    if (gun == 3) {
                        return 35;
                    }
                }
                break;
            case 3: // Diğer
                indirim = 0;
                break;
            default:
                indirim = 0;
        }

        return indirim;
    }

    // 5. Film formatı ekstra ücreti
    public static int getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: // 2D
                return 0;
            case 2: // 3D
                return 25;
            case 3: // IMAX
                return 35;
            case 4: // 4DX
                return 50;
            default:
                return 0;
        }
    }

    // 6. Final fiyat hesaplama
    public static double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru) {
        int basePrice = calculateBasePrice(gun, saat);
        int extra = getFormatExtra(filmTuru);
        double discount = calculateDiscount(yas, meslek, gun);

        double indirimTutar = (basePrice * discount) / 100;
        double sonuc = (basePrice - indirimTutar) + extra;

        return sonuc;
    }

    // 7. Bilet bilgisi oluşturma ve yazdırma (printf)
    public static void generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru, double finalFiyat) {
        String gunAdi = "";
        switch (gun) {
            case 1: gunAdi = "Pazartesi"; break;
            case 2: gunAdi = "Salı"; break;
            case 3: gunAdi = "Çarşamba"; break;
            case 4: gunAdi = "Perşembe"; break;
            case 5: gunAdi = "Cuma"; break;
            case 6: gunAdi = "Cumartesi"; break;
            case 7: gunAdi = "Pazar"; break;
        }

        String meslekAdi = "";
        switch (meslek) {
            case 1: meslekAdi = "Öğrenci"; break;
            case 2: meslekAdi = "Öğretmen"; break;
            case 3: meslekAdi = "Diğer"; break;
        }

        String formatAdi = "";
        switch (filmTuru) {
            case 1: formatAdi = "2D"; break;
            case 2: formatAdi = "3D"; break;
            case 3: formatAdi = "IMAX"; break;
            case 4: formatAdi = "4DX"; break;
        }

        System.out.println("\n--- Bilet Bilgisi ---");
        System.out.printf("Gün: %s\n", gunAdi);
        System.out.printf("Saat: %d:00\n", saat);
        System.out.printf("Yaş: %d\n", yas);
        System.out.printf("Meslek: %s\n", meslekAdi);
        System.out.printf("Film Formatı: %s\n", formatAdi);
        System.out.printf("Toplam Ücret: %.2f TL\n", finalFiyat);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Gün Seç (1=Pzt, 2=Sal, 3=Çar, 4=Per, 5=Cum, 6=Cmt, 7=Paz): ");
        int gun = sc.nextInt();

        System.out.print("Saat Gir (Örnek 11, 14, 19…): ");
        int saat = sc.nextInt();

        System.out.print("Yaş Gir: ");
        int yas = sc.nextInt();

        System.out.print("Meslek Seç (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
        int meslek = sc.nextInt();

        System.out.print("Film Türü Seç (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int filmTuru = sc.nextInt();

        double finalFiyat = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);

        generateTicketInfo(gun, saat, yas, meslek, filmTuru, finalFiyat);

        sc.close();
    }
}
