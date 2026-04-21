// CLASS BUKU DIPINJAM (CHILD CLASS)
public class BukuDipinjam extends Buku {

    // Atribut tambahan (tidak ada di parent)
    private int hariPinjam;
    private int lamaPinjam;

    // Constructor
    // Menggunakan super() untuk memanggil constructor parent (inheritance)
    public BukuDipinjam(String idBuku, String judul, int hariPinjam, int lamaPinjam) {
        super(idBuku, judul);
        this.hariPinjam = hariPinjam;
        this.lamaPinjam = lamaPinjam;
    }

    // Method untuk mendapatkan hari pinjam
    public int getHariPinjam() {
        return hariPinjam;
    }

    // Method untuk mendapatkan lama pinjam
    public int getLamaPinjam() {
        return lamaPinjam;
    }

    // Method untuk menghitung jumlah hari terlambat
    public int hitungHariTelat(int hariSekarang) {
    if (hariSekarang < hariPinjam) {
        return 0; // atau bisa throw error
    }

    int telat = hariSekarang - (hariPinjam + lamaPinjam);
    return Math.max(telat, 0);
    }

    // Method untuk menghitung denda (Rp 500/hari)
    public int hitungDenda(int hariSekarang) {
        return hitungHariTelat(hariSekarang) * 500;
    }

    // Override method (polymorphism)
    // Menambahkan informasi tambahan saat ditampilkan
    @Override
    public String toString() {
    return super.toString() +
            " | Hari pinjam: " + hariPinjam +
            " | Lama: " + lamaPinjam + " hari";
        }
}