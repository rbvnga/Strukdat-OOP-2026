Nama        : Revalinda Bunga Nayla Laksono <br>
NRP         : 5027251011 <br>
Mata Kuliah : Struktur Data dan Pemrograman Berorientasi Objek <br>
Kelas       : A <br>

# RESERVASI LAPANGAN OLAHRAGA
## Deskripsi Masalah 
Setelah masa pandemi COVID-19, aktivitas olahraga kembali menjadi kebiasaan yang populer, termasuk teman-teman sebaya saya yang mulai rutin berolahraga, seperti berlari maupun olahraga lapangan seperti badminton dan voli. Karena kami tinggal di daerah perkotaan, kami mengandalkan jasa sewa lapangan olahraga, namun informasi mengenai ketersediaan jadwal dan biaya sewa lapangan sering kali tidak jelas, sehingga kami kesulitan mengetahui apakah lapangan masih tersedia pada waktu tertentu, berapa lama durasi pemakaian yang dapat dipilih, serta berapa total biaya yang harus dibayarkan. <br>
Berdasarkan peristiwa tersebut, saya mensimulasikan sebuah Sistem Reservasi Lapangan Olahraga yang membantu calon customer menentukan dan melihat informasi penting seperti jenis lapangan olahraga, harga sewa per jam, jadwal penggunaan, status pembayaran, total biaya reservasi beserta metode pembayaranya seperti tunai atau _e-wallet_. Di sisi lain, Admin dapat mengelola data lapangan sehingga informasi yang ditampilkan kepada customer menjadi lebih terstruktur dan mudah dipahami. <br>

## Class Diagram
<img width="29000" height="27000" alt="Class Diagram_Reservasi Lapangan" src="https://github.com/user-attachments/assets/2bde9ec6-eb25-4241-abfd-530e58abd288" /> <br>

## Kode Program
Berikut adalah kode program Reservasi Lapangan Olahraga: [App.java](https://github.com/rbvnga/Strukdat-OOP-2026/blob/main/Assignment%202%20-%20Class%20Diagram/Assignment2_Reservasi%20Lapangan%20Olahraga/src/App.java) <br>
## Output
<img width="782" height="796" alt="Cuplikan layar 2026-03-24 173515" src="https://github.com/user-attachments/assets/aa4c75c3-5179-4a8f-aca4-76e649d15c29" /> <br>


## Prinsip-Prinsip OOP
### Abstraction
Abstraction adalah konsep menyembunyikan detail implementasi dan hanya menampilkan apa yang perlu diketahui oleh pengguna. Pada kode program ini terlihat pada <br>
``````java
abstract class User{
abstract void login();}
``````
Artinya `User` hanya mengatakan setiap pengguna bisa login (setiap jenis pengguna mempunyai method `login()`, tanpa perlu tau cara tiap jenis pengguna login, dimana implementasi diberikan di class turunan: <br> 
``````java
class Customer extends User {
    @Override
    public void login() {
        System.out.println("(Customer) " + nama + " Login");
    }
}
class Admin extends User {
    @Override
    public void login() {
        System.out.println("(Admin) " + nama + " Login");
    }
}
``````
Jadi `User` mendefinisikan konsep umum pengguna, `Customer` dan `Admin` mengisi implementasinya. Contoh lain penerapan Abstraction ialah 
``````java
interface TipeOlahraga {
    String getNamaOlahraga(); // belum ada implementasi 
}
interface MetodePembayaran {
    void bayar(int harga); // belum ada implementasi 
}
``````
## Encapsulation
Encapsulation adalah membungkus data dan mengontrol akses terhadap data tersebut, dimana akses data hanya lewat method getter/setter. Contoh penerapannya ialah 
``````java
class Field {
    private String fieldId;      
    private TipeOlahraga namaOlahraga;
    private int hargaPerJam;
    public int getHargaPerJam() { 
        return hargaPerJam;
    }
}
class Pembayaran {
    private String status = "UNPAID"; 
    public String getStatus() {
    return status; } 
    // tidak ada setStatus() → tidak bisa diubah sembarangan
}
``````
Karena `hargaPerJam` dan `status` bersifat `private`, property ini tidak bisa diakses langsung dari luar class, aksesnya harus lewat method getter/setter. <br>
### Inheritance 
Class `User` menjadi _parent class_ yang memiliki atribut umum berupa `userId`, `nama`, dan `email`. Class `Admin` dan `Customer` merupakan turunan dari Class `User` (_Child Class_), sehingga keduanya mewarisi atribut dan method yang sama yaitu `login()`. Inheritance digunakan untuk membuat struktur class yang lebih rapi dan menghindari duplikasi kode.
<pre>
User (parent)
 ├── Admin (child)
 └── Customer (child) </pre>
``````java
abstract class User {
    protected String userId, nama, email; // atribut diwariskan ke subclass
    abstract void login();                // wajib diimplementasi
}

class Customer extends User { ... } 
class Admin extends User { ... }   
``````

 ### Polymorphism
Polymorphism adalah konsep dimana method yang sama bisa punya implementasi berbeda pada class yang berbeda. <br>
 - Polymorphism pada method abstract
``````java
abstract void login();
``````
Lalu di `Costumer` dan `Admin` di-override menjadi:
``````java
    @Override
    public void login() {
        System.out.println("(Customer) " + nama + " Login");
    }
    @Override
    public void login() {
        System.out.println("(Admin) " + nama + " Login");
    }
``````
 - Polymorphism pada interface Metode Pembayaran
   Interface MetodePembayaran memiliki beberapa implementasi yaitu `Tunai` dan `EWallet`.
Saat `metodePembayaran.bayar(harga)` dipanggil, Java otomatis menjalankan versi yang benar berdasarkan objek aslinya 
 - Polymorphism pada interface Tipe Olahraga
Interface `TipeOlahraga` memungkinkan sistem mendukung berbagai jenis olahraga tanpa mengubah class `Field`. `Basket`, `Badminton`, dan `Voli` dapat memanggil method yang sama yaitu `getNamaOlahraga()` dengan isi kode yang berbeda.
``````java
public class App{
public static void main(String[] args) throws Exception {
TipeOlahraga badminton = new Badminton(); // bentuk: Badminton
TipeOlahraga basket    = new Basket();    // bentuk: Basket
MetodePembayaran bayarEWallet = new EWallet(); // bentuk: EWallet
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
class Basket implements TipeOlahraga {
    @Override
    public String getNamaOlahraga() {
        return "Basket";
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
``````
### Composition
Composition terjadi ketika suatu class memiliki object lain yang dibuat di dalam class tersebut, sehingga siklus hidupnya bergantung pada class utama. Pada sistem ini:
- class `Reservasi` memiliki `Jadwal`
- class `Reservasi` memiliki `Pembayaran` <br>
Object `Jadwal` dan `Pembayaran` dibuat di dalam constructor `Reservasi`. <br>
Jika Reservasi dihapus, maka `Jadwal` dan `Pembayaran` juga ikut hilang.
``````java
class Reservasi {
    private Jadwal jadwal;         // Reservasi PUNYA Jadwal
    private Pembayaran pembayaran; // Reservasi PUNYA Pembayaran

    public Reservasi(...) {
        this.jadwal = new Jadwal(...);       // Object Jadwal dibuat di sini
        this.pembayaran = new Pembayaran(...); // Object Pembayaran dibuat di sini
    }
}
``````
### Association 
Association adalah hubungan antar class dimana satu class mengetahui class lain, tetapi keduanya tetap dapat berdiri sendiri.
**1. Reservasi — Customer**
``````java
class Reservasi {
    private Customer customer; // menyimpan referensi Customer
}
``````
`Reservasi` mengenal `Customer`, tapi tidak membuatnya. `Customer` sudah dibuat di `main()`, lalu dikirim masuk ke `Reservasi` lewat parameter:
``````java
// di main():
Customer c1 = new Customer("C01", "Budi", "budiRizz@mail.com");
// lalu dikirim ke Reservasi:
Reservasi r1 = c1.buatReservasi("r1", lapangan1, "10-01-2026", "10:00", "12:00", bayarEWallet);
``````
Di dalam `buatReservasi()`, **Customer** dikirim sebagai `this` 
``````java
class Customer extends User {
    public Reservasi buatReservasi(String reservasiId, Field lapangan, String tanggal, String waktuMulai, String waktuBerakhir, MetodePembayaran metodePembayaran) {
        Reservasi reservasi = new Reservasi(reservasiId, this, // object customer
                lapangan, tanggal, waktuMulai, waktuBerakhir, metodePembayaran);
        return reservasi;
    }
}
class Reservasi {
    public Reservasi(String reservasiId, Customer customer, Field lapangan, String tanggal, String waktuMulai, String waktuBerakhir, MetodePembayaran metodePembayaran) {...}
}
``````
`Reservasi` hanya meminjam referensi `Customer` untuk keperluan menampilkan nama dan email di `detailReservasi()`. Customer dapat membuat banyak reservasi, tetapi Customer tetap dapat ada tanpa reservasi. <br>
**2. Reservasi — Field**
``````java
class Reservasi {
    private Field lapangan; // menyimpan referensi Field
}
``````
``````java
// di main():
Field lapangan1 = new Field("F01", badminton, 50000); // Field lahir duluan
admin1.tambahLapangan(lapangan1);

// Field (lapangan1)  dikirim ke Reservasi:
Reservasi r1 = c1.buatReservasi("r1", lapangan1, ...);
``````
`Reservasi` menggunakan `Field` digunakan mengambil `hargaPerJam` saat menghitung total biaya, dan menampilkan info lapangan di `detailReservasi()`. `Field` tetap hidup meski `Reservasi` dihapus. <br>
**3. Field — TipeOlahraga**
``````java
class Field {
    private TipeOlahraga namaOlahraga; // menyimpan referensi TipeOlahraga
}
``````
`TipeOlahraga` adalah interface, dan objek konkretnya (`Badminton`, `Basket`, `Voli`) dibuat duluan di `main()` lalu dikirim ke `Field`:
``````java
// di main():
TipeOlahraga badminton = new Badminton(); 
Field lapangan1 = new Field("F01", badminton, 50000); // TipeOlahraga (badminton) dikirim ke Field
``````
`Field` menggunakannya hanya untuk memanggil `getNamaOlahraga()` di method `getFieldInfo()`. <br>
**4. Pembayaran — MetodePembayaran**
`````java
class Pembayaran {
    private MetodePembayaran metodePembayaran; // menyimpan referensi MetodePembayaran
}
``````
`MetodePembayaran` adalah interface, objek konkretnya dibuat di `main()` lalu diteruskan masuk melewati beberapa layer:
``````java
// di main():
MetodePembayaran bayarEWallet = new EWallet(); 
// dikirim ke buatReservasi() → ke konstruktor Reservasi → ke konstruktor Pembayaran:
c1.buatReservasi("r1", lapangan1, "10-01-2026", "10:00", "12:00", bayarEWallet);
``````
saat `prosesPembayaran` di panggil di `main()`, objek `pembayaran` akan memanggil method `bayar()`. Saat method `pembayaran.bayar()` dipanggil, dia mendelegasikan ke `metodePembayaran.bayar(harga)`, tanpa peduli apakah itu `EWallet` atau `Tunai`. <br>

## Keunikan
1. Menggunakan Composition pada Jadwal dan Pembayaran <br>
Class Reservasi langsung membuat object Jadwal dan Pembayaran di dalam constructor, sehingga struktur data reservasi menjadi lebih rapi dan terikat kuat. Hal ini memastikan bahwa setiap reservasi pasti memiliki jadwal dan informasi pembayaran. <br>
2. Perhitungan Otomatis Total Harga Berdasarkan Durasi <br>
Durasi pemakaian lapangan dihitung otomatis dari waktu mulai dan waktu selesai. Kemudian total harga dihitung menggunakan rumus:
<pre> total harga = harga per jam × durasi </pre>
Implementasi pada kode: 
``````java
    // hitung durasi sewa lapangan para customer
    public int hitungDurasiJam() {
        int mulai = Integer.parseInt(waktuMulai.substring(0, 2));
        int selesai = Integer.parseInt(waktuBerakhir.substring(0, 2));
        return selesai - mulai;
    }
    int durasi = jadwal.hitungDurasiJam();
    int totalHarga = lapangan.getHargaPerJam() * durasi; 
``````
3. Simulasi Alur di Dunia Nyata
   - Admin menambahkan lapangan
   - Customer memilih lapangan
   - Customer menentukan jadwal
   - Sistem menghitung biaya otomatis
   - Customer melakukan pembayaran
   - Memperbarui status pembayaran 
4. Fleksibilitas metode pembayaran mencerminkan kebiasaan masyarakat modern <br>
Di jaman sekarang ini, orang tidak selalu membayar dengan cara yang sama, ada yang lebih nyaman cash ataupun lebih nyaman e-wallet terutama para anak muda. Dengan penggunaan interface untuk `Metode Pembayaran` sistem dapat dikembangkan sesuai dengan kebiasaan pembayaran masa kini.

