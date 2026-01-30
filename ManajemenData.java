import java.util.LinkedList;

public class ManajemenData {
    
    // ALGORITMA SORTING: BUBBLE SORT (Sort by ID)
    public static void urutkanByID(LinkedList<Pasien> list) {
        int n = list.size();
        if (n == 0) return;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).id > list.get(j + 1).id) {
                    Pasien temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        System.out.println(">> Data berhasil diurutkan berdasarkan ID.");
    }

    // ALGORITMA SEARCHING: LINEAR SEARCH (Search by Nama)
    public static void cariLinear(LinkedList<Pasien> list, String namaDicari) {
        boolean ditemukan = false;
        System.out.println("\n--- Hasil Linear Search (Nama: " + namaDicari + ") ---");
        
        for (Pasien p : list) {
            if (p.nama.equalsIgnoreCase(namaDicari)) {
                System.out.println("DITEMUKAN: " + p);
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Pasien dengan nama '" + namaDicari + "' tidak ditemukan.");
    }

    // ALGORITMA SEARCHING: BINARY SEARCH (Search by ID)
    public static void cariBinary(LinkedList<Pasien> list, int idDicari) {
        urutkanByID(list); // Wajib urut dulu
        
        System.out.println("\n--- Hasil Binary Search (ID: " + idDicari + ") ---");
        int kiri = 0;
        int kanan = list.size() - 1;
        boolean ditemukan = false;

        while (kiri <= kanan) {
            int tengah = kiri + (kanan - kiri) / 2;
            Pasien pTengah = list.get(tengah);

            if (pTengah.id == idDicari) {
                System.out.println("DITEMUKAN: " + pTengah);
                ditemukan = true;
                break;
            }

            if (pTengah.id < idDicari) {
                kiri = tengah + 1;
            } else {
                kanan = tengah - 1;
            }
        }
        if (!ditemukan) System.out.println("Pasien ID " + idDicari + " tidak ditemukan.");
    }
}