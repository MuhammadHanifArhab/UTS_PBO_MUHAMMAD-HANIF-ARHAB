// CLASS ADMIN (CHILD dari User)
public class Admin extends User {

    public Admin(String nama, String password) {
        super(nama, password); // inheritance
    }

    // Method untuk tambah buku
    public void tambahBuku(Perpustakaan perpus, String id, String judul) {
        perpus.tambahBuku(new Buku(id, judul));
        System.out.println("Buku berhasil ditambahkan!");
    }

    // Method untuk ubah status buku (stok)
    public void ubahStatusBuku(Perpustakaan perpus, String id, boolean status) {
        perpus.setStatusBuku(id, status);
        System.out.println("Status buku berhasil diubah!");
    }
}