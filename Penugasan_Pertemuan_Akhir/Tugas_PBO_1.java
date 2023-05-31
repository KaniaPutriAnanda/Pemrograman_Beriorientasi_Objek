public class Tugas_PBO_1 {
    public static void main(String[] args) {
        String awal = "JavaScript adalah bahasa pemrograman tingkat tinggi dan dinamis";
        
        tampilkanKeLayar(awal);
    }
    
    public static void tampilkanKeLayar(String stringAwal) {
        System.out.println("1. " + stringAwal.substring(0, stringAwal.indexOf("tingkat")));
        System.out.println("2. " + stringAwal.toLowerCase());
        System.out.println("3. " + stringAwal.toUpperCase());
        System.out.println("4. " + stringAwal.substring(stringAwal.indexOf("pemrograman"), stringAwal.indexOf("pemrograman") + 12));
        System.out.println("5. " + stringAwal.substring(stringAwal.indexOf("tinggi")));
    }
}
