Nama : Revalinda Bunga Nayla Laksono <br>
NRP : 5027251011 <br>
Mata Kuliah : Struktur Data dan Pemrograman Berorientasi Objek <br>
Kelas : A <br>

# RESERVASI LAPANGAN OLAHRAGA
## Deskripsi Masalah 
Setelah masa pandemi COVID-19, aktivitas olahraga kembali menjadi kebiasaan yang populer, termasuk teman-teman sebaya saya yang mulai rutin berolahraga, seperti berlari maupun olahraga lapangan seperti badminton dan voli. Karena kami tinggal di daerah perkotaan, kami mengandalkan jasa sewa lapangan olahraga, namun informasi mengenai ketersediaan jadwal dan biaya sewa lapangan sering kali tidak jelas, sehingga kami kesulitan mengetahui apakah lapangan masih tersedia pada waktu tertentu, berapa lama durasi pemakaian yang dapat dipilih, serta berapa total biaya yang harus dibayarkan. <br>
Berdasarkan peristiwa tersebut, saya mensimulasikan sebuah Sistem Reservasi Lapangan Olahraga yang membantu calon customer menentukan dan melihat informasi penting seperti jenis lapangan olahraga, harga sewa per jam, jadwal penggunaan, status pembayaran, total biaya reservasi beserta metode pembayaranya seperti tunai atau _e-wallet_. Di sisi lain, Admin dapat mengelola data lapangan sehingga informasi yang ditampilkan kepada customer menjadi lebih terstruktur dan mudah dipahami. <br>

## Class Diagram
<img width="8192" height="6928" alt="Class Diagram_Reservasi Lapangan" src="https://github.com/user-attachments/assets/2bde9ec6-eb25-4241-abfd-530e58abd288" /> <br>

## Kode Program

## Output
<img width="782" height="796" alt="Cuplikan layar 2026-03-24 173515" src="https://github.com/user-attachments/assets/aa4c75c3-5179-4a8f-aca4-76e649d15c29" /> <br>


## Prinsip-Prinsip OOP
### Abstraction
Abstraction adalah konsep menyembunyikan detail implementasi dan hanya menampilkan apa yang perlu diketahui oleh pengguna. Pada kode program ini terlihat pada <br>
``````java
abstract class User{
abstract void login();}
``````
Artinya `User` hanya mengatakan setiap pengguna bisa login (setiap jenis pengguna mempunyai method `login()`), tanpa perlu tau cara tiap jenis pengguna login, dimana implementasi diberikan di class turunan: <br> 
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
   Interface MetodePembayaran memiliki beberapa implementasi yaitu Tunai dan EWallet.
Saat `metodePembayaran.bayar(harga) dipanggil`, Java otomatis menjalankan versi yang benar berdasarkan objek aslinya 
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
- class Reservasi memiliki Jadwal
- class Reservasi memiliki Pembayaran
Object Jadwal dan Pembayaran dibuat di dalam constructor Reservasi. <br>
Jika Reservasi dihapus, maka Jadwal dan Pembayaran juga ikut hilang.
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
## Keunikan
1. oca
2. ads
3. Simulasi Alur di Dunia Nyata
   - Admin menambahkan lapangan
   - Customer memilih lapangan
   - Customer menentukan jadwal
   - Sistem menghitung biaya otomatis
   - Customer melakukan pembayaran
   - Memperbarui status pembayaran 
5. dwe
6. M
