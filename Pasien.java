public class Pasien {
    // Atribut data pasien
    int id;             
    String nama;        
    String keluhan;     
    String poliTujuan;  

    // Constructor
    public Pasien(int id, String nama, String keluhan, String poliTujuan) {
        this.id = id;
        this.nama = nama;
        this.keluhan = keluhan;
        this.poliTujuan = poliTujuan;
    }

    // Method untuk format tampilan text
    @Override
    public String toString() {
        return String.format("ID: %-4d | Nama: %-15s | Keluhan: %-15s | Poli: %s", 
                             id, nama, keluhan, poliTujuan);
    }
}