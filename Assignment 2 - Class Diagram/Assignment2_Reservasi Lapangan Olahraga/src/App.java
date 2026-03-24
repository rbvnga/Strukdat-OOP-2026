public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("RESERVASI LAPANGAN OLAHRAGA YUKS^^");

        Admin admin1 = new Admin("A01", "Ajeng", "adminAjengCool@mail.com");
        admin1.login();
        TipeOlahraga badminton = new Badminton();
        TipeOlahraga basket = new Basket();
        TipeOlahraga voli = new Voli();
        Field lapangan1 = new Field("F01", badminton, 50000);
        Field lapangan2 = new Field("F02", basket, 35000);
        Field lapangan3 = new Field("F03", voli, 40000);
        admin1.tambahLapangan(lapangan1);
        admin1.tambahLapangan(lapangan2);
        admin1.tambahLapangan(lapangan3);
        System.out.println("\n");

        MetodePembayaran bayarEWallet = new EWallet();
        MetodePembayaran bayarCash = new Tunai();

        System.out.println("===Customer Pertama====");
        Customer c1 = new Customer("C01", "Budi", "budiRizz@mail.com");
        c1.login();
        Reservasi r1 = c1.buatReservasi("r1", lapangan1, "10-01-2026", "10:00", "12:00", bayarEWallet);
        r1.detailReservasi();
        System.out.println("\n");
        r1.prosesPembayaran();
        r1.detailReservasi();
        System.out.println("\n");

        System.out.println("===Customer Kedua====");
        Customer c2 = new Customer("C02", "Taeri", "MsTaeri@mail.com");
        c2.login();
        Reservasi r2 = c2.buatReservasi("r2", lapangan2, "01-01-2026", "14:00", "15:00", bayarCash);
        r2.prosesPembayaran();
        r2.detailReservasi();
        System.out.println("\n");
        Reservasi r3 = c2.buatReservasi("r2", lapangan3, "02-01-2026", "13:00", "16:00", bayarCash);
        r3.prosesPembayaran();
        r3.detailReservasi();

    }
}

abstract class User {
    protected String userId;
    protected String nama;
    protected String email;

    public User(String userId, String nama, String email) {
        this.userId = userId;
        this.nama = nama;
        this.email = email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    abstract void login();
}

class Customer extends User {
    // Customer bisa buat reservasi
    public Customer(String userId, String nama, String email) {
        super(userId, nama, email);
    }

    // Customer menghasilkan object Reservasi
    // Reservasi menyimpan referensi Customer
    public Reservasi buatReservasi(String reservasiId, Field lapangan, String tanggal, String waktuMulai,
            String waktuBerakhir, MetodePembayaran metodePembayaran) {
        Reservasi reservasi = new Reservasi(reservasiId, this, // object customer
                lapangan, tanggal, waktuMulai, waktuBerakhir, metodePembayaran);
        return reservasi;
    }

    @Override
    public void login() {
        System.out.println("(Customer) " + nama + " Login");
    }
}

class Admin extends User {
    public Admin(String userId, String nama, String email) {
        super(userId, nama, email);
    }

    // admin bisa menambah lapangan
    public void tambahLapangan(Field lapangan) {
        System.out.println("Lapangan ditambahkan: " + lapangan.getFieldInfo());
    }

    @Override
    public void login() {
        System.out.println("(Admin) " + nama + " Login");
    }
}

interface TipeOlahraga {
    String getNamaOlahraga();
}

class Badminton implements TipeOlahraga {
    @Override
    public String getNamaOlahraga() {
        return "Badminton";
    }
}

class Voli implements TipeOlahraga {
    @Override
    public String getNamaOlahraga() {
        return "Voli";
    }
}

class Basket implements TipeOlahraga {
    @Override
    public String getNamaOlahraga() {
        return "Basket";
    }
}

class Field {
    private String fieldId;
    private TipeOlahraga namaOlahraga;
    private int hargaPerJam;

    public Field(String fieldId, TipeOlahraga namaOlahraga, int hargaPerJam) {
        this.fieldId = fieldId;
        this.namaOlahraga = namaOlahraga;
        this.hargaPerJam = hargaPerJam;
    }

    public int getHargaPerJam() {
        return hargaPerJam;
    }

    public String getFieldInfo() {
        return fieldId + " " + namaOlahraga.getNamaOlahraga() + " - Rp" + hargaPerJam + " per jam";
    }
}

class Jadwal {
    private String tanggal;
    private String waktuMulai;
    private String waktuBerakhir;

    public Jadwal(String tanggal, String waktuMulai, String waktuBerakhir) {
        this.tanggal = tanggal;
        this.waktuMulai = waktuMulai;
        this.waktuBerakhir = waktuBerakhir;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setWaktuMulai(String waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public String getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuBerakhir(String waktuBerakhir) {
        this.waktuBerakhir = waktuBerakhir;
    }

    public String getWaktuBerakhir() {
        return waktuBerakhir;
    }

    // hitung durasi sewa lapangan para customer
    public int hitungDurasiJam() {
        int mulai = Integer.parseInt(waktuMulai.substring(0, 2));
        int selesai = Integer.parseInt(waktuBerakhir.substring(0, 2));
        return selesai - mulai;
    }

    // untuk menampilkan informasi jadwal berserta durasi seewanya
    public String infoJadwal() {
        return tanggal + " | Jam mulai: " + waktuMulai + " | Jam Berakhir: " + waktuBerakhir + " | Durasi: "
                + hitungDurasiJam() + " Jam";
    }
}

interface MetodePembayaran {
    void bayar(int harga);
}

class Tunai implements MetodePembayaran {
    @Override
    public void bayar(int harga) {
        System.out.println("Bayar Cash Sebesar Rp " + harga);
    }
}

class EWallet implements MetodePembayaran {
    @Override
    public void bayar(int harga) {
        System.out.println("Bayar melalui e-wallet sebesar Rp " + harga);

    }
}

class Pembayaran {
    private int harga;
    private String status = "UNPAID"; // status awal masih belum dibayar "UNPAID"

    private MetodePembayaran metodePembayaran; // Pembayaran memiliki Metode Pembayaran

    public Pembayaran(int harga, MetodePembayaran metodePembayaran) {
        this.harga = harga;
        this.metodePembayaran = metodePembayaran;
    }

    public void bayar() {
        metodePembayaran.bayar(harga);
        status = "PAID";
    }

    public String getStatus() {
        return status;
    }
}

class Reservasi {
    private String reservasiId;
    private Customer customer; // <Association>
    private Field lapangan;

    // Class Reservasi mempunyai Jadwal dan Pembayaran <Composition>
    private Jadwal jadwal;
    private Pembayaran pembayaran;

    public Reservasi(String reservasiId, Customer customer, Field lapangan, String tanggal, String waktuMulai,
            String waktuBerakhir, MetodePembayaran metodePembayaran) {
        this.reservasiId = reservasiId;
        this.customer = customer;
        this.lapangan = lapangan;

        // object Jadwal dibuat di dalam class Reservasi
        this.jadwal = new Jadwal(tanggal, waktuMulai, waktuBerakhir);

        // totalHarga dihitung berdasarkan durasi dan harga sewa lapangan per jam
        int durasi = jadwal.hitungDurasiJam();
        int totalHarga = lapangan.getHargaPerJam() * durasi;
        // object Pembayaran dibuat di dalam class Reservasi
        this.pembayaran = new Pembayaran(totalHarga, metodePembayaran);
    }

    // customer membayar
    public void prosesPembayaran() {
        pembayaran.bayar();
    }

    public void detailReservasi() {
        System.out.println("Customer     : " + customer.getNama());
        System.out.println("Email        : " + customer.getEmail());
        System.out.println("Reservasi ID : " + reservasiId);
        System.out.println("Lapangan     : " + lapangan.getFieldInfo());
        System.out.println("Jadwal       : " + jadwal.infoJadwal());
        System.out.println("Status bayar : " + pembayaran.getStatus());

    }

}