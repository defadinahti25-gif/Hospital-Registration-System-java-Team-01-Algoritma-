import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphRS {
    // Menggunakan Adjacency List
    private Map<String, List<String>> petaLokasi;

    public GraphRS() {
        petaLokasi = new HashMap<>();
    }

    // Menambah titik lokasi
    public void tambahLokasi(String lokasi) {
        petaLokasi.putIfAbsent(lokasi, new ArrayList<>());
    }

    // Menambah jalur (Edge) dua arah
    public void tambahJalur(String lokasi1, String lokasi2) {
        petaLokasi.get(lokasi1).add(lokasi2);
        petaLokasi.get(lokasi2).add(lokasi1);
    }

    // Cek tetangga lokasi
    // File: GraphRS.java

    // ... (kode sebelumnya)

    // Method cekJalur yang sudah dimodifikasi dengan ignoreCase
    public void cekJalur(String inputUser) {
        String lokasiDitemukan = null;

        // 1. Cari kunci asli di dalam Map yang cocok (abaikan besar/kecil huruf)
        for (String key : petaLokasi.keySet()) {
            if (key.equalsIgnoreCase(inputUser)) {
                lokasiDitemukan = key;
                break; // Ketemu! Hentikan loop
            }
        }

        // 2. Logika pengecekan
        if (lokasiDitemukan != null) {
            List<String> tujuan = petaLokasi.get(lokasiDitemukan);
            System.out.println("Dari [" + lokasiDitemukan + "] Anda bisa menuju ke: " + tujuan);
        } else {
            System.out.println("Lokasi '" + inputUser + "' tidak ditemukan. Coba periksa ejaan.");
        }
    }
}