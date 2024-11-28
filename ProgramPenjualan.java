import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

// Base class
class Penjualan {
    // Atribut dasar
    protected String noFaktur; 
    protected String kodeBarang; 
    protected String namaBarang;
    protected int hargaJual; 
    protected int jumlahBeli;

    // Constructor default
    public Penjualan() {
        this.noFaktur = ""; 
        this.kodeBarang = ""; 
        this.namaBarang = ""; 
        this.hargaJual = 0;
        this.jumlahBeli = 0;
    }

    // Method untuk menghitung total harga
    public int hitungTotal() {
        return hargaJual * jumlahBeli;
    }
}

// Derived class (Inheritance dari kelas Penjualan)
class FakturPenjualan extends Penjualan {

    // Constructor default
    public FakturPenjualan() {
        super(); // Memanggil constructor dari superclass
    }

    // Method untuk menampilkan informasi faktur
    public void tampilkanFaktur(String namaKasir) { // String digunakan untuk parameter namaKasir
        SimpleDateFormat formatTanggal = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // SimpleDateFormat (Date digunakan di sini)
        // Formatter untuk format mata uang Rupiah
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        System.out.println("\n+----------------------------------------------------+");
        System.out.println("Selamat Datang di Dewansyah Dealer");
        System.out.println("Tanggal dan Waktu : " + formatTanggal.format(new Date())); // Menggunakan Date untuk menampilkan waktu
        System.out.println("+----------------------------------------------------+");
        System.out.println("No Faktur    : " + noFaktur); 
        System.out.println("Kode Barang  : " + kodeBarang); 
        System.out.println("Nama Barang  : " + namaBarang); 
        System.out.println("Harga Jual   : " + formatRupiah.format(hargaJual));
        System.out.println("Jumlah Beli  : " + jumlahBeli);
        System.out.println("Total Harga  : " + formatRupiah.format(hitungTotal()));
        System.out.println("+----------------------------------------------------+");
        System.out.println("Kasir : " + namaKasir); 
        System.out.println("+----------------------------------------------------+");
    }
}

public class ProgramPenjualan {

    // Method untuk menghasilkan CAPTCHA kombinasi angka dan huruf
    public static String generateCaptcha(int length) { 
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; 
        StringBuilder captcha = new StringBuilder(); // StringBuilder digunakan untuk menyusun CAPTCHA
        Random random = new Random();

        // Membentuk CAPTCHA dengan panjang tertentu
        for (int i = 0; i < length; i++) {
            captcha.append(chars.charAt(random.nextInt(chars.length()))); 
        }

        return captcha.toString(); 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FakturPenjualan faktur = new FakturPenjualan(); // Menggunakan inheritance (FakturPenjualan mewarisi Penjualan)

        // Login Section
        String username, password, captcha, generatedCaptcha; // String digunakan untuk menyimpan data login dan CAPTCHA
        boolean isLoggedIn = false;

        while (!isLoggedIn) {
            System.out.println("+-----------------------------------------------------+");
            System.out.print("Username : ");
            username = scanner.nextLine(); // String input untuk username

            System.out.print("Password : ");
            password = scanner.nextLine(); // String input untuk password

            // Generate CAPTCHA dengan kombinasi angka dan huruf
            generatedCaptcha = generateCaptcha(6); // CAPTCHA panjang 6 karakter
            System.out.println("Captcha  : " + generatedCaptcha); 
            System.out.print("Enter Captcha: ");
            captcha = scanner.nextLine(); // String input untuk validasi CAPTCHA

            // Validasi login
            if (username.equals("admin") && password.equals("12345") && captcha.equalsIgnoreCase(generatedCaptcha)) { // String method equals() dan equalsIgnoreCase()
                isLoggedIn = true;
                System.out.println("Login berhasil!");
            } else {
                System.out.println("Login gagal, silakan coba lagi.");
            }
        }

        // Transaksi Penjualan
        try {
            // Input No Faktur
            while (true) {
                try {
                    System.out.print("Masukkan No Faktur: ");
                    faktur.noFaktur = scanner.nextLine(); // String digunakan untuk menyimpan no faktur
                    if (faktur.noFaktur.isEmpty()) { // String method .isEmpty()
                        throw new IllegalArgumentException("No Faktur tidak boleh kosong."); // String exception
                    }
                    break; // Keluar dari loop jika input valid
                } catch (IllegalArgumentException e) {
                    System.out.println("Kesalahan: " + e.getMessage()); // String exception message
                }
            }

            // Input Kode Barang
            while (true) {
                try {
                    System.out.print("Masukkan Kode Barang: ");
                    faktur.kodeBarang = scanner.nextLine(); // String input untuk kode barang
                    if (faktur.kodeBarang.isEmpty()) { // String method .isEmpty()
                        throw new IllegalArgumentException("Kode Barang tidak boleh kosong."); // String exception
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Kesalahan: " + e.getMessage()); // String exception message
                }
            }

            // Input Nama Barang
            while (true) {
                try {
                    System.out.print("Masukkan Nama Barang: ");
                    faktur.namaBarang = scanner.nextLine(); // String input untuk nama barang
                    if (faktur.namaBarang.isEmpty()) { // String method .isEmpty()
                        throw new IllegalArgumentException("Nama Barang tidak boleh kosong."); // String exception
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Kesalahan: " + e.getMessage()); // String exception message
                }
            }

            // Input Harga Jual
            while (true) {
                try {
                    System.out.print("Masukkan Harga Jual: ");
                    String inputHarga = scanner.nextLine(); // String digunakan untuk input harga

                    if (!inputHarga.matches("\\d+")) { // String method .matches()
                        throw new IllegalArgumentException("Input hanya boleh berupa angka.");
                    }

                    int harga = Integer.parseInt(inputHarga); // String method untuk parsing integer

                    if (harga <= 0) {
                        throw new IllegalArgumentException("Harga harus lebih dari 0.");
                    }

                    faktur.hargaJual = harga;
                    break; // Keluar dari loop jika input valid
                } catch (IllegalArgumentException e) {
                    System.out.println("Kesalahan: " + e.getMessage()); // String exception message
                }
            }

            // Input Jumlah Beli
            while (true) {
                try {
                    System.out.print("Masukkan Jumlah Beli : ");
                    String input = scanner.nextLine(); // String input untuk jumlah beli
                    if (!input.matches("\\d+")) { // String method .matches()
                        throw new IllegalArgumentException("Input hanya boleh berupa angka.");
                    }
                    faktur.jumlahBeli = Integer.parseInt(input); // String parsing menjadi integer
                    if (faktur.jumlahBeli <= 0) {
                        throw new IllegalArgumentException("Jumlah Beli harus lebih dari 0.");
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Kesalahan: " + e.getMessage()); // String exception message
                }
            }

            // Input Nama Kasir
            String namaKasir; 
            System.out.print("Masukkan Nama Kasir: ");
            namaKasir = scanner.nextLine(); // String input nama kasir

            // Menampilkan Faktur
            faktur.tampilkanFaktur(namaKasir);

        } finally {
            scanner.close(); // Menutup scanner di blok finally untuk memastikan resource ditutup
        }
    }
}
