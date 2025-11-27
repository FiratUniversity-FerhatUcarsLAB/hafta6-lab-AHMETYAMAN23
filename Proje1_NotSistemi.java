import java.util.Scanner;

public class NotDegerlendirmeSistemi {

    // Ortalama hesaplama metodu
    public static double calculateAverage(int vize, int fin, int odev) {
        return vize * 0.30 + fin * 0.40 + odev * 0.30;
    }

    // Geçti mi kaldı mı? (boolean)
    public static boolean isPassingGrade(double ort) {
        return ort >= 50;
    }

    // Harf notu döndüren metot
    public static String getLetterGrade(double ort) {
        if (ort >= 90 && ort <= 100)
            return "A";
        else if (ort >= 80 && ort <= 89)
            return "B";
        else if (ort >= 70 && ort <= 79)
            return "C";
        else if (ort >= 60 && ort <= 69)
            return "D";
        else
            return "F";
    }

    // Onur listesine girebilir mi? (İç içe if + boolean mantık)
    public static boolean isHonorList(double ort, int v, int f, int o) {
        if (ort >= 85) {
            if (v >= 70 && f >= 70 && o >= 70) {
                return true;
            }
        }
        return false;
    }

    // Bütünleme hakkı var mı?
    public static boolean hasRetakeRight(double ort) {
        return (ort >= 40 && ort < 50);
    }

    // 40–50 arası ise bütünleme hakkı verir
    public static boolean hasRetakePermission(double ort) {
        return (ort >= 40 && ort < 50);
    }


    // Raporu yazdıran metot (printf)
    public static void printReport(String isim, int vize, int fin, int odev, double ort, String harf) {
        String durum = isPassingGrade(ort) ? "GECTI ✅" : "KALDI ❌";
        String butunleme = hasRetakeRight(ort) ? "VAR (Butunleme Hakki)" : "YOK";
        String onur = isHonorList(ort, vize, fin, odev) ? "ONUR LISTESI 🏆" : "YOK";


        System.out.println("\n--- OGRENCI RAPORU ---");

        System.out.printf("Adı: %s\n", isim);
        System.out.printf("Vize Notu: %d\n", vize);
        System.out.printf("Final Notu: %d\n", fin);
        System.out.printf("Odev Notu: %d\n", odev);
        System.out.printf("Ortalama: %.2f\n", ort);
        System.out.printf("Harf Notu: %s\n", harf);
        System.out.printf("Durum: %s\n", durum);
        System.out.printf("Onur Listesi: %s\n", onur);
        System.out.printf("Butunleme Hakkı: %s\n", butunleme);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ogrenci Adı: ");
        String isim = sc.nextLine();

        System.out.print("Vize Notu (0-100): ");
        int vize = sc.nextInt();

        System.out.print("Final Notu (0-100): ");
        int fin = sc.nextInt();

        System.out.print("Odev Notu (0-100): ");
        int odev = sc.nextInt();

        double ort = calculateAverage(vize, fin, odev);
        String harf = getLetterGrade(ort);

        printReport(isim, vize, fin, odev, ort, harf);

        sc.close();
    }
}
