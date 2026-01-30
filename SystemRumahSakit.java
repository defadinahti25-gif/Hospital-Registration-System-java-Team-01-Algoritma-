import java.util.*;

public class SystemRumahSakit {

    // ==== STRUKTUR DATA GLOBAL ===
    static LinkedList<Pasien> dbPasien = new LinkedList<>(); 
    static Stack<String> logAktivitas = new Stack<>();       
    static Queue<Pasien> antrianDokter = new LinkedList<>(); 
    
    static Scanner scanner = new Scanner(System.in);
    static NodeTree strukturOrganisasi;
    static GraphRS petaRS;

    public static void main(String[] args) {
        inisialisasiDataAwal(); 
        jalankanMenu();         
    }

    static void inisialisasiDataAwal() {
        // Setup Tree
        strukturOrganisasi = new NodeTree("Direktur RS");
        NodeTree medis = new NodeTree("Pelayanan Medis");
        NodeTree admin = new NodeTree("Administrasi");
        
        strukturOrganisasi.tambahSubBagian(medis);
        strukturOrganisasi.tambahSubBagian(admin);
        medis.tambahSubBagian(new NodeTree("Poli Umum"));
        medis.tambahSubBagian(new NodeTree("Poli Gigi"));
        medis.tambahSubBagian(new NodeTree("IGD"));
        admin.tambahSubBagian(new NodeTree("Pendaftaran"));
        admin.tambahSubBagian(new NodeTree("Kasir"));

        // Setup Graph
        petaRS = new GraphRS();
        petaRS.tambahLokasi("Lobby");
        petaRS.tambahLokasi("Poli Umum");
        petaRS.tambahLokasi("Apotek");
        petaRS.tambahLokasi("Kantin");
        
        petaRS.tambahJalur("Lobby", "Poli Umum");
        petaRS.tambahJalur("Poli Umum", "Apotek");
        petaRS.tambahJalur("Lobby", "Kantin");
    }

    static void jalankanMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=============================================");
            System.out.println("   SISTEM MANAJEMEN RUMAH SAKIT NUSA PUTRA");
            System.out.println("=============================================");
            System.out.println("1. Daftar Pasien Baru (Linked List)");
            System.out.println("2. Masukkan Pasien ke Antrian (Queue)");
            System.out.println("3. Panggil Pasien (Dequeue)");
            System.out.println("4. Cari Data Pasien (Search)");
            System.out.println("5. Tampilkan Semua Data (Sort)");
            System.out.println("6. Lihat Struktur Organisasi (Tree)");
            System.out.println("7. Cek Rute Lokasi (Graph)");
            System.out.println("8. Lihat Log Aktivitas (Stack)");
            System.out.println("0. Keluar");
            System.out.print("Pilihan Anda: ");

            try {
                int input = scanner.nextInt();
                scanner.nextLine(); 

                switch (input) {
                    case 1: menuDaftar(); break;
                    case 2: menuAntrian(); break;
                    case 3: menuPanggil(); break;
                    case 4: menuCari(); break;
                    case 5: menuTampil(); break;
                    case 6: 
                        System.out.println("\n--- STRUKTUR ORGANISASI ---");
                        strukturOrganisasi.printTree(""); 
                        break;
                    case 7: menuGraph(); break;
                    case 8: menuStack(); break;
                    case 0: isRunning = false; break;
                    default: System.out.println("Pilihan tidak valid.");
                }
            } catch (Exception e) {
                System.out.println("Error: Masukkan angka yang benar.");
                scanner.nextLine();
            }
        }
    }

    static void menuDaftar() {
        System.out.print("Masukkan ID Pasien (Angka): ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nama Pasien: ");
        String nama = scanner.nextLine();
        System.out.print("Keluhan: ");
        String kel = scanner.nextLine();
        System.out.print("Poli Tujuan: ");
        String poli = scanner.nextLine();

        Pasien pBaru = new Pasien(id, nama, kel, poli);
        dbPasien.add(pBaru);
        logAktivitas.push("Mendaftarkan pasien: " + nama);
        System.out.println(">> Sukses! Data pasien disimpan.");
    }

    static void menuAntrian() {
        System.out.print("Masukkan ID Pasien: ");
        int id = scanner.nextInt();
        Pasien ketemu = null;
        for (Pasien p : dbPasien) {
            if (p.id == id) ketemu = p;
        }
        if (ketemu != null) {
            antrianDokter.offer(ketemu);
            logAktivitas.push("Pasien masuk antrian: " + ketemu.nama);
            System.out.println(">> Pasien " + ketemu.nama + " masuk antrian.");
        } else {
            System.out.println(">> ID tidak ditemukan.");
        }
    }

    static void menuPanggil() {
        if (antrianDokter.isEmpty()) {
            System.out.println(">> Antrian kosong.");
        } else {
            Pasien p = antrianDokter.poll();
            System.out.println("Memanggil: " + p.nama);
            logAktivitas.push("Melayani pasien: " + p.nama);
        }
    }

    static void menuCari() {
        System.out.println("1. Cari Nama (Linear) | 2. Cari ID (Binary)");
        int pil = scanner.nextInt();
        scanner.nextLine();
        if (pil == 1) {
            System.out.print("Nama: ");
            ManajemenData.cariLinear(dbPasien, scanner.nextLine());
        } else if (pil == 2) {
            System.out.print("ID: ");
            ManajemenData.cariBinary(dbPasien, scanner.nextInt());
        }
    }

    static void menuTampil() {
        System.out.println("\nUrutkan ID? (y/n)");
        if (scanner.next().equalsIgnoreCase("y")) ManajemenData.urutkanByID(dbPasien);
        for (Pasien p : dbPasien) System.out.println(p);
    }

    static void menuGraph() {
        System.out.print("Posisi Anda \n-Lobby\n-Poli Umum\n-Apotek\n= ");
        String pos = scanner.nextLine().trim();
        petaRS.cekJalur(pos);
    }

    static void menuStack() {
        if (logAktivitas.isEmpty()) System.out.println("History kosong.");
        for (int i = logAktivitas.size() - 1; i >= 0; i--) {
            System.out.println((logAktivitas.size() - i) + ". " + logAktivitas.get(i));
        }
    }
}