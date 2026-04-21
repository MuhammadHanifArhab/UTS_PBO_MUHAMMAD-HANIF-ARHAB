// CLASS BUKU (PARENT CLASS)
public class Buku {

    // Encapsulation: atribut dibuat private agar tidak bisa diakses langsung
    private String idBuku;
    private String judul;
    private boolean tersedia;

    // Constructor: digunakan untuk inisialisasi object saat dibuat
    public Buku(String idBuku, String judul) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.tersedia = true; // default buku tersedia
    }

    // Getter: mengambil nilai atribut (encapsulation)
    public String getIdBuku() {
        return idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    // Setter: mengubah status buku
    public void setStatus(boolean status) {
        this.tersedia = status;
    }

    // Override method toString
    // Digunakan untuk menampilkan data buku
    @Override
    public String toString() {
        return idBuku + " - " + judul;
    }
}
