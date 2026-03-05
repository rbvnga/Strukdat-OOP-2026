import java.util.Scanner;
//SISTEM MANAJEMEN PERPUSTAKAAN

public class App {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        Perpustakaan perpustakaan = new Perpustakaan();

        boolean mulai = true;
        while (mulai) {
            System.out.println("=== SISTEM PERPUSTAKAAN ===");
            System.out.println("1. Login sebagai Anggota");
            System.out.println("2. Login sebagai Petugas");
            System.out.println("0. Exit");
            System.out.print("Pilih: ");
            int pilihan = input.nextInt();
            input.nextLine(); // membersihkan enter

            System.out.print("Masukkan ID: ");
            String id = input.nextLine();

            System.out.print("Masukkan Nama: ");
            String nama = input.nextLine();

            User user;

            if (pilihan == 1) {
                user = new Anggota(id, nama);
            } else if (pilihan == 2) {
                user = new Petugas(id, nama);
            } else if (pilihan == 0) {
                mulai = false;
                continue;
            } else {
                System.out.println("Pilihan tidak valid");
                continue;
            }

            System.out.println("\nLogin berhasil");
            System.out.println("Selamat datang, " + nama);

            boolean jalan = true;

            while (jalan) {
                user.tampilkanMenu();
                System.out.print("pilih Menu: ");
                int menu = input.nextInt();
                input.nextLine();

                if (user instanceof Anggota) {
                    if (menu == 1) {
                        // menampilkan daftar buku
                        System.out.println("     LIST BUKU");
                        perpustakaan.tampilkanSemuaBuku();
                    } else if (menu == 2) {
                        // meminjam buku
                        System.out.print("Judul Buku: ");
                        String judul = input.nextLine();
                        System.out.print("Penulis Buku: ");
                        String penulis = input.nextLine();
                        perpustakaan.meminjamBuku(judul, penulis);
                    } else if (menu == 3) {
                        // mengembalikan buku
                        System.out.print("Judul Buku: ");
                        String judul = input.nextLine();
                        System.out.print("Penulis Buku: ");
                        String penulis = input.nextLine();
                        perpustakaan.kembalikanBuku(judul, penulis);
                    } else if (menu == 0) {
                        jalan = false;
                    } else {
                        System.out.println("Pilihan tidak valid");
                        continue;
                    }
                } else if (user instanceof Petugas) {
                    if (menu == 1) {
                        System.out.println("     LIST BUKU");
                        perpustakaan.tampilkanSemuaBuku();
                        // menampilkan daftar buku
                    } else if (menu == 2) {
                        // tambah stok buku
                        System.out.print("Judul Buku: ");
                        String judul = input.nextLine();
                        System.out.print("Penulis Buku: ");
                        String penulis = input.nextLine();
                        System.out.print("Tambah Stok: ");
                        int stokBaru = input.nextInt();
                        input.nextLine();
                        perpustakaan.tambahBuku(judul, penulis, stokBaru);
                    } else if (menu == 3) {
                        // menghapus stok buku
                        System.out.print("Judul Buku: ");
                        String judul = input.nextLine();
                        System.out.print("Penulis Buku: ");
                        String penulis = input.nextLine();
                        System.out.print("Hapus Stok: ");
                        int stokBaru = input.nextInt();
                        input.nextLine();
                        perpustakaan.hapusStok(judul, penulis, stokBaru);
                    } else if (menu == 0) {
                        jalan = false;
                    } else {
                        System.out.println("Pilihan tidak valid");
                        continue;
                    }
                }
            }
        }
        input.close();
    }
}

// ABSTRACTION
abstract class User {
    protected String id;
    protected String nama;

    // constructor
    public User(String idNew, String namaNew) {
        this.id = idNew;
        this.nama = namaNew;
    }

    // Setter ID
    public void setId(String idNew) {
        this.id = idNew;
    }

    // Getter ID
    public String getId() {
        return "ID Pengguna: " + id;
    }

    // Setter nama
    public void setNama(String namaNew) {
        this.nama = namaNew;
    }

    // Getter nama
    public String getNama() {
        return "Nama Pengguna: " + nama;
    }

    // Method Abstract
    public abstract void tampilkanMenu();
}

// INHERITANCE
class Anggota extends User {
    public Anggota(String id, String nama) {
        super(id, nama);
    }

    // POLYMORPHISM
    @Override
    public void tampilkanMenu() {
        System.out.println("\nMenu Anggota: ");
        System.out.println("1. Lihat Semua Buku");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Kembalikan Buku");
        System.out.println("0. Keluar");

    }
}

// INHERITANCE
class Petugas extends User {
    public Petugas(String id, String nama) {
        super(id, nama);
    }

    // POLYMORPHISM
    @Override
    public void tampilkanMenu() {
        System.out.println("\nMenu Petugas: ");
        System.out.println("1. Lihat Semua Buku");
        System.out.println("2. Tambah Buku");
        System.out.println("3. Hapus Buku");
        System.out.println("0. Keluar");
    }
}

class Buku {
    // ENCAPSULATION
    private String judul;
    private String penulis;
    private int stok;

    public Buku(String judulNew, String penulisNew, int stokNew) {
        this.judul = judulNew;
        this.penulis = penulisNew;
        this.stok = stokNew;
    }

    // Setter Judul
    public void setJudul(String judul) {
        this.judul = judul;
    }

    // Getter Judul
    public String getJudul() {
        return judul;
    }

    // Setter Penulis
    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    // Getter penulis
    public String getPenulis() {
        return penulis;
    }

    // Setter Stok Buku
    public void setStok(int stok) {
        this.stok = stok;
    }

    // Getter Stok Buku
    public int getStok() {
        return stok;
    }

}

class Perpustakaan {
    private Buku[] daftarBuku = new Buku[100];
    private int jumlahBuku = 0;

    public void tambahBuku(String judul, String penulis, int stokBaru) {

        for (int i = 0; i < jumlahBuku; i++) {
            if (daftarBuku[i].getJudul().equalsIgnoreCase(judul)
                    && daftarBuku[i].getPenulis().equalsIgnoreCase(penulis)) {
                int stokLama = daftarBuku[i].getStok();
                daftarBuku[i].setStok(stokLama + stokBaru);
                System.out.println("Stok buku berhasil ditambahkan!");
                return;
            }
        }

        if (jumlahBuku < daftarBuku.length) {
            daftarBuku[jumlahBuku] = new Buku(judul, penulis, stokBaru);
            jumlahBuku++;
            System.out.println("Buku berhasil ditambahakan");
        } else {
            System.out.println("Kapasistas penuh");
        }
    }

    public void hapusStok(String judul, String penulis, int stok) {
        if (jumlahBuku == 0) {
            System.out.println("Belum ada Buku");
        } else {
            for (int i = 0; i < jumlahBuku; i++) {
                if (daftarBuku[i].getJudul().equalsIgnoreCase(judul)
                        && daftarBuku[i].getPenulis().equalsIgnoreCase(penulis)) {
                    int stokLama = daftarBuku[i].getStok();
                    if (stokLama >= stok) {
                        daftarBuku[i].setStok(stokLama - stok);
                        System.out.println("Stok buku berhasil diperbarui");
                        return;
                    } else {
                        System.out.println("Stok tidak cukup");
                        return;
                    }
                }
            }
            System.out.println("Buku tidak ditemukan");
        }
    }

    public void tampilkanSemuaBuku() {
        if (jumlahBuku == 0) {
            System.out.println("Belum ada Buku");
        } else {
            for (int i = 0; i < jumlahBuku; i++) {
                System.out.println("Judul Buku [" + i + "]: " + daftarBuku[i].getJudul());
                System.out.println("Penulis       : " + daftarBuku[i].getPenulis());
                System.out.println("Stok          : " + daftarBuku[i].getStok());
                System.out.println("-------------------------");
            }
        }
    }

    public void meminjamBuku(String judul, String penulis) {
        if (jumlahBuku == 0) {
            System.out.println("Belum ada buku");
        } else {
            for (int i = 0; i < jumlahBuku; i++) {
                if (daftarBuku[i].getJudul().equalsIgnoreCase(judul)
                        && daftarBuku[i].getPenulis().equalsIgnoreCase(penulis)) {
                    if (daftarBuku[i].getStok() > 0) {
                        daftarBuku[i].setStok(daftarBuku[i].getStok() - 1);
                        System.out.println("Buku berhasil dipinjam");
                    } else {
                        System.out.println("Stok buku habis");
                    }

                }
            }
        }
    }

    public void kembalikanBuku(String judul, String penulis) {
        for (int i = 0; i < jumlahBuku; i++) {
            if (daftarBuku[i].getJudul().equalsIgnoreCase(judul)
                    && daftarBuku[i].getPenulis().equalsIgnoreCase(penulis)) {
                daftarBuku[i].setStok(daftarBuku[i].getStok() + 1);
                System.out.println("Stok buku berhasil diperbarui");
            }
        }
    }

}