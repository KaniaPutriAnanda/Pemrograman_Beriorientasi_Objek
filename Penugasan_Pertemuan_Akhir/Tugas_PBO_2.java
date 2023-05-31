import java.util.Scanner;

public class Tugas_PBO_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("+-------------------------------------+");
        System.out.println("|    PROGRAM MENGHITUNG NILAI AKHIR   |");
        System.out.println("+-------------------------------------+");
        System.out.print("\n");

        // Input mata kuliah
        System.out.print("Masukkan nama mata kuliah : ");
        String mataKuliah = scanner.nextLine();

        // Input nilai kehadiran, UTS, dan UAS
        System.out.print("Masukkan nilai kehadiran  : ");
        int nilaiKehadiran = scanner.nextInt();
        
        System.out.print("Masukkan nilai UTS        : ");
        int nilaiUTS = scanner.nextInt();
        
        System.out.print("Masukkan nilai UAS        : ");
        int nilaiUAS = scanner.nextInt();

        ENter();
        System.out.print("\n");
        System.out.println("+-------------------------------------+");
        System.out.println("|              NILAI AKHIR            |");
        System.out.println("+-------------------------------------+");
        
        
        // Menghitung nilai akhir
        double nilaiAkhir = hitungNilaiAkhir(nilaiKehadiran, nilaiUTS, nilaiUAS);
        
        // Menampilkan nilai akhir dan mata kuliah
        System.out.println("Mata Kuliah : " + mataKuliah);
        System.out.println("Nilai Akhir : " + nilaiAkhir);
        
        // Menampilkan nilai grade
        String nilaiGrade = konversiKeGrade(nilaiAkhir);
        System.out.println("Grade       : " + nilaiGrade);
        scanner.close();
    }
    
    public static double hitungNilaiAkhir(int kehadiran, int uts, int uas) {
        double nilaiKehadiran = 0.0;
        
        if (kehadiran < 3) {
            nilaiKehadiran = 0.0;
        } else if (kehadiran >= 3 && kehadiran <= 4) {
            nilaiKehadiran = 60.0;
        } else if (kehadiran >= 5 && kehadiran <= 6) {
            nilaiKehadiran = 80.0;
        } else if (kehadiran > 7) {
            nilaiKehadiran = 100.0;
        }
        
        double nilaiAkhir = (nilaiKehadiran * 0.2) + (uts * 0.3) + (uas * 0.5);
        return nilaiAkhir;
    }
    
    public static String konversiKeGrade(double nilaiAkhir) {
        String grade;
        
        if (nilaiAkhir >= 85 && nilaiAkhir <= 100) {
            grade = "A";
        } else if (nilaiAkhir >= 70 && nilaiAkhir <= 84) {
            grade = "B";
        } else if (nilaiAkhir >= 60 && nilaiAkhir <= 69) {
            grade = "C";
        } else if (nilaiAkhir >= 50 && nilaiAkhir <= 59) {
            grade = "D";
        } else {
            grade = "E";
        }
        
        return grade;
    }

    static void ENter() {
        System.out.print("Tekan ENTER untuk melanjutkan...");
        Scanner enter = new Scanner(System.in);
        enter.nextLine();
        enter.close(); // Menutup objek Scanner setelah selesai membaca input
    }
}
