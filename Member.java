import java.util.ArrayList;

// CLASS MEMBER (CHILD dari User)
public class Member extends User {

    private String nim;
    private int maxPinjam;
    private int totalDenda;

    private ArrayList<BukuDipinjam> daftarPinjam;
    private ArrayList<BukuDipinjam> riwayat;

    // Constructor
    public Member(String nama, String nim, String password) {
        super(nim, password); // inheritance
        this.nim = nim;
        this.daftarPinjam = new ArrayList<>();
        this.riwayat = new ArrayList<>();
        this.totalDenda = 0;
        this.maxPinjam = hitungMaxPinjam();
    }

    private int hitungMaxPinjam() {
        int digit = Integer.parseInt(nim.substring(nim.length() - 1));
        if (digit <= 3) return 2;
        else if (digit <= 6) return 3;
        else return 5;
    }

    public int getSisaKuota() {
        return maxPinjam - daftarPinjam.size();
    }

    public boolean bisaPinjam() {
        return daftarPinjam.size() < maxPinjam;
    }

    public boolean pinjamBuku(Buku buku, int hari, int lama) {
        if (!bisaPinjam() || !buku.isTersedia()) return false;

        daftarPinjam.add(new BukuDipinjam(
                buku.getIdBuku(),
                buku.getJudul(),
                hari,
                lama
        ));

        buku.setStatus(false);
        return true;
    }

    public BukuDipinjam ambilBukuDipinjam(String id) {
        for (BukuDipinjam b : daftarPinjam) {
            if (b.getIdBuku().equalsIgnoreCase(id)) return b;
        }
        return null;
    }

    public void tambahDenda(int denda) {
        totalDenda += denda;
    }

    public int getTotalDenda() {
        return totalDenda;
    }

    public ArrayList<BukuDipinjam> getDaftarPinjam() {
        return daftarPinjam;
    }

    public void pindahKeRiwayat(BukuDipinjam b) {
        riwayat.add(b);
    }

    public void tampilkanBukuDipinjam() {
        if (daftarPinjam.isEmpty()) {
            System.out.println("Belum ada buku dipinjam.");
        } else {
            for (BukuDipinjam b : daftarPinjam) {
                System.out.println(b);
            }
        }
    }

    public void tampilkanRiwayat() {
        if (riwayat.isEmpty()) {
            System.out.println("Belum ada riwayat.");
        } else {
            for (BukuDipinjam b : riwayat) {
                System.out.println(b);
            }
        }
    }

    // Getter maksimal pinjam
    public int getMaxPinjam() {
    return maxPinjam;
}
}