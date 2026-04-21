import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int hariSekarang = 1;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Perpustakaan perpus = new Perpustakaan();

        // DATA BUKU AWAL
        perpus.tambahBuku(new Buku("B1", "Laut Bercerita"));
        perpus.tambahBuku(new Buku("B2", "Bumi Manusia"));
        perpus.tambahBuku(new Buku("B3", "Sang Pemimpi"));
        perpus.tambahBuku(new Buku("B4", "Cantik itu Luka"));
        perpus.tambahBuku(new Buku("B5", "5 CM"));
        perpus.tambahBuku(new Buku("B6", "Negeri 5 Menara"));
        perpus.tambahBuku(new Buku("B7", "Perahu Kertas"));
        perpus.tambahBuku(new Buku("B8", "Laskar Pelangi"));
        perpus.tambahBuku(new Buku("B9", "The Alchemist"));
        perpus.tambahBuku(new Buku("B10", "The Little Prince"));

        // Admin
        Admin admin = new Admin("admin", "123");

        // Member
        ArrayList<Member> daftarUser = new ArrayList<>();

        int pilihRole;

        do {
            System.out.println("\n1. Login Admin");
            System.out.println("2. Login Member");
            System.out.println("3. Register Member");
            System.out.println("0. Keluar");
            pilihRole = input.nextInt();
            System.out.println("\n");

            if (pilihRole == 1) {
                input.nextLine();
                System.out.print("Nama: ");
                String nama = input.nextLine();
                System.out.print("Password: ");
                String pass = input.nextLine();

                if (admin.login(nama, pass)) {
                    // MENU ADMIN
                    int pilih;
                    do {
                        System.out.println("\n===== MENU ADMIN =====");
                        System.out.println("1. Tambah Buku");
                        System.out.println("2. Ubah Status Buku");
                        System.out.println("3. Lihat Buku");
                        System.out.println("0. Logout");
                        pilih = input.nextInt();

                        if (pilih == 1) {
                            input.nextLine();
                            System.out.print("Masukan ID Buku: ");
                            String id = input.nextLine();
                            System.out.print("Masukan Judul Buku: ");
                            String judul = input.nextLine();
                            admin.tambahBuku(perpus, id, judul);
                        } else if (pilih == 2) {
                            System.out.print("ID: ");
                            String id = input.next();
                            System.out.print("Status Ketersediaan Buku (true/false): ");
                            boolean status = input.nextBoolean();
                            admin.ubahStatusBuku(perpus, id, status);
                        } else if (pilih == 3) {
                            perpus.tampilkanBuku();
                        }

                    } while (pilih != 0);
                }
                

            } else if (pilihRole == 2) {
                input.nextLine();
                System.out.print("Masukan nim: ");
                String nim = input.nextLine();
                System.out.print("Masukan Password: ");
                String pass = input.nextLine();

                Member loginUser = null;
                for (Member a : daftarUser) {
                    if (a.login(nim, pass)) {
                        loginUser = a;
                        break;
                    }
                }

                if (loginUser != null) {
                    System.out.println("Login berhasil!");
                    System.out.println("Maksimal pinjam: " + loginUser.getMaxPinjam());
                    System.out.println("Sisa kuota: " + loginUser.getSisaKuota());
                    int pilih;
                    do {
                        System.out.println("\n===== MENU MEMBER =====");
                        System.out.println("1. Pinjam Buku");
                        System.out.println("2. Kembalikan Buku");
                        System.out.println("3. Lihat Buku Dipinjam");
                        System.out.println("4. Lihat Denda");
                        System.out.println("5. Lihat Riwayat");
                        System.out.println("0. Logout");
                        pilih = input.nextInt();
                        System.out.println("\n");

                        if (pilih == 1) {
                            perpus.tampilkanBuku();
                            System.out.print("ID buku yang ingin dipinjam: ");
                            String id = input.next();

                            Buku b = perpus.cariBuku(id, true);

                            if (b != null) {
                                System.out.print("Tanggal Hari pinjam: ");
                                int hari = input.nextInt();
                                System.out.print("Lama pinjam (hari): ");
                                int lama = input.nextInt();

                                if (loginUser.pinjamBuku(b, hari, lama)) {
                                    System.out.println("Berhasil pinjam buku!");
                                    System.out.println("Sisa kuota: " + loginUser.getSisaKuota());
                                } else {
                                    System.out.println("Gagal pinjam (kuota penuh / buku tidak tersedia)");
                                }
                            }
                        }

                        if (pilih == 2) {
                            System.out.println("Buku yang dipinjam: ");
                            loginUser.tampilkanBukuDipinjam();
                            System.out.print("ID buku yang ingin dikembalikan: ");
                            String id = input.next();

                            BukuDipinjam b = loginUser.ambilBukuDipinjam(id);

                            if (b != null) {
                                System.out.print("Tanggal Hari Ini: ");
                                hariSekarang = input.nextInt();

                                int denda = b.hitungDenda(hariSekarang);
                                if (denda > 0) {
                                    loginUser.tambahDenda(denda);
                                    System.out.println("Denda: " + denda);
                                }

                                loginUser.getDaftarPinjam().remove(b);
                                loginUser.pindahKeRiwayat(b);
                                perpus.setStatusBuku(id, true);
                            }
                        }

                        if (pilih == 3) {
                            System.out.println("Buku yang sedang dipinjam:");
                            loginUser.tampilkanBukuDipinjam();
                        }
                        if (pilih == 4) System.out.println("Total denda: " + loginUser.getTotalDenda());
                        if (pilih == 5) loginUser.tampilkanRiwayat();

                    } while (pilih != 0);
                }
                else{
                    System.out.println("Login gagal!");   
                }


            } else if (pilihRole == 3) {
                input.nextLine();
                System.out.print("Nama: ");
                String nama = input.nextLine();
                System.out.print("NIM: ");
                String nim = input.nextLine();
                System.out.print("Password: ");
                String pass = input.nextLine();

                daftarUser.add(new Member(nama, nim, pass));
                System.out.println("Registrasi berhasil!");
            }

        } while (pilihRole != 0);
    }
}