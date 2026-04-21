import java.util.ArrayList;

// CLASS PERPUSTAKAAN
public class Perpustakaan {

    private ArrayList<Buku> daftarBuku;

    // Constructor
    public Perpustakaan() {
        daftarBuku = new ArrayList<>();
    }

    // Method untuk menambah buku
    public void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
    }

    // Method untuk menampilkan daftar buku + status
    public void tampilkanBuku() {
        System.out.println("\n===== Daftar Buku =====");
        for (Buku b : daftarBuku) {
            System.out.println(
                b + (b.isTersedia() ? " (Tersedia)" : " (Dipinjam)")
            );
        }
    }

    // Overloading Method 
    // Method cari buku berdasarkan ID
    public Buku cariBuku(String id) {
        for (Buku b : daftarBuku) {
            if (b.getIdBuku().equalsIgnoreCase(id)) return b;
        }
        return null;
    }

    // Method overload dengan filter buku tersedia
    public Buku cariBuku(String id, boolean hanyaTersedia) {
        for (Buku b : daftarBuku) {
            if (b.getIdBuku().equalsIgnoreCase(id)) {
                if (!hanyaTersedia || b.isTersedia()) return b;
            }
        }
        return null;
    }

    // Method untuk mengubah status buku
    public void setStatusBuku(String id, boolean status) {
        Buku b = cariBuku(id);
        if (b != null) b.setStatus(status);
    }
}