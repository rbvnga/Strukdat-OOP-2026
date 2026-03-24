Nama : Revalinda Bunga Nayla Laksono <br>
NRP : 5027251011 <br>
Mata Kuliah : Struktur Data dan Pemrograman Berorientasi Objek <br>
Kelas : A <br>

# RESERVASI LAPANGAN OLAHRAGA
## Deskripsi Masalah 
Setelah masa pandemi COVID-19, aktivitas olahraga kembali menjadi kebiasaan yang populer, termasuk teman-teman sebaya saya yang mulai rutin berolahraga, seperti berlari maupun olahraga lapangan seperti badminton dan voli. Karena kami tinggal di daerah perkotaan, kami mengandalkan jasa sewa lapangan olahraga, namun informasi mengenai ketersediaan jadwal dan biaya sewa lapangan sering kali tidak jelas, sehingga kami kesulitan mengetahui apakah lapangan masih tersedia pada waktu tertentu, berapa lama durasi pemakaian yang dapat dipilih, serta berapa total biaya yang harus dibayarkan. <br>
Berdasarkan peristiwa tersebut, saya mensimulasikan sebuah Sistem Reservasi Lapangan Olahraga yang membantu calon customer menentukan dan melihat informasi penting seperti jenis lapangan olahraga, harga sewa per jam, jadwal penggunaan, status pembayaran, total biaya reservasi beserta metode pembayaranya seperti tunai atau _e-wallet_. Di sisi lain, Admin dapat mengelola data lapangan sehingga informasi yang ditampilkan kepada customer menjadi lebih terstruktur dan mudah dipahami. <br>

## Class Diagram
<img width="8192" height="5928" alt="Class Diagram_Reservasi Lapangan" src="https://github.com/user-attachments/assets/2bde9ec6-eb25-4241-abfd-530e58abd288" /> <br>

## Kode Program

## Output

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
``````
Pengguna Car cukup tahu bahwa setiap mobil bisa startEngine() — tidak perlu tahu cara tiap jenis mobil menyalakan mesinnya.
Class Car hanya mengatakan bahwa setiap mobil harus punya method startEngine(), tetapi cara menyalakan mesin tidak dijelaskan di Car.
### Inheritance 
Class `User` menjadi _parent class_ yang memiliki atribut umum berupa `userId`, `nama`, dan `email`. Class `Admin` dan `Customer` merupakan turunan dari Class **User**, sehingga keduanya mewarisi atribut dan method yang sama yaitu `login()`. 
<pre>
User (parent)
 ├── Admin (child)
 └── Customer (child) </pre>

 ### Polymorphism
 - Polymorphism pada method abstract
 - Polymorphism pada metode pembayaran
   Interface MetodePembayaran memiliki beberapa implementasi yaitu Tunai dan EWallet
 - Polymorphism pada tipe olahraga

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
