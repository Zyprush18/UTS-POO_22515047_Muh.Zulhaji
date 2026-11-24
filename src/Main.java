import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Restoran restoran = new Restoran("Restoran Nusantara", 10);

    public static void main(String[] args) {
        System.out.println("Selamat datang di " + restoran.getNamaRestoran());
        boolean jalan = true;
        while (jalan) {
            tampilkanMenu();
            int pilihan = bacaInt("Pilih menu: ");
            jalan = prosesPilihan(pilihan);
        }
        System.out.println("Terima kasih. Sampai jumpa!");
        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n=== Menu Aplikasi ===");
        System.out.println("1. Tambah Menu");
        System.out.println("2. Tampilkan Menu");
        System.out.println("3. Tambah Pelanggan");
        System.out.println("4. Buat Pesanan");
        System.out.println("5. Lihat Antrean");
        System.out.println("6. Proses Pesanan");
        System.out.println("0. Keluar");
    }

    private static boolean prosesPilihan(int pilihan) {
        switch (pilihan) {
            case 1: tambahMenu(); break;
            case 2: restoran.tampilkanMenu(); break;
            case 3: tambahPelanggan(); break;
            case 4: buatPesanan(); break;
            case 5: restoran.tampilkanAntrean(); break;
            case 6: prosesPesanan(); break;
            case 0: return false;
            default: System.out.println("Pilihan tidak valid.");
        }
        return true;
    }

    private static void tambahMenu() {
        String id = bacaTeks("ID Menu       : ");
        String nama = bacaTeks("Nama Menu     : ");
        String kategori = bacaTeks("Kategori      : ");
        double harga = bacaDouble("Harga         : ");
        Menu menu = new Menu(id, nama, kategori, harga);
        if (menu.validasiData()) {
            restoran.tambahMenu(menu);
            System.out.println("Menu berhasil ditambahkan.");
        } else {
            System.out.println("Data menu tidak valid (nama kosong / harga harus > 0).");
        }
    }

    private static void tambahPelanggan() {
        String id = bacaTeks("ID Pelanggan  : ");
        String nama = bacaTeks("Nama          : ");
        String telepon = bacaTeks("No. Telepon   : ");
        Pelanggan pelanggan = new Pelanggan(id, nama, telepon);
        if (pelanggan.validasiData()) {
            restoran.tambahPelanggan(pelanggan);
            System.out.println("Pelanggan berhasil didaftarkan.");
        } else {
            System.out.println("Data pelanggan tidak valid (nama kosong / telepon harus angka).");
        }
    }

    private static void buatPesanan() {
        ArrayList<Pelanggan> pelanggans = restoran.getDaftarPelanggan();
        if (pelanggans.isEmpty()) {
            System.out.println("Belum ada pelanggan. Daftarkan pelanggan dulu.");
            return;
        }
        if (restoran.getDaftarMenu().isEmpty()) {
            System.out.println("Belum ada menu. Tambahkan menu dulu.");
            return;
        }

        System.out.println("Daftar Pelanggan:");
        for (int i = 0; i < pelanggans.size(); i++) {
            System.out.println((i + 1) + ". " + pelanggans.get(i).getNama()
                    + " (" + pelanggans.get(i).getIdPelanggan() + ")");
        }
        int idx = bacaInt("Pilih pelanggan (nomor): ") - 1;
        if (idx < 0 || idx >= pelanggans.size()) {
            System.out.println("Pelanggan tidak valid.");
            return;
        }

        String idPesanan = bacaTeks("ID Pesanan    : ");
        Pesanan pesanan = new Pesanan(idPesanan, pelanggans.get(idx));

        while (true) {
            restoran.tampilkanMenu();
            String idMenu = bacaTeks("ID Menu (kosong untuk selesai): ");
            if (idMenu.isEmpty()) break;
            Menu menu = restoran.cariMenu(idMenu);
            if (menu == null) {
                System.out.println("Menu tidak ditemukan.");
                continue;
            }
            int jumlah = bacaInt("Jumlah        : ");
            DetailPesanan detail = new DetailPesanan(menu, jumlah);
            if (detail.validasiData()) {
                pesanan.tambahDetail(detail);
                System.out.println("Ditambahkan: " + menu.getNamaMenu() + " x" + jumlah);
            } else {
                System.out.println("Detail tidak valid (jumlah minimal 1).");
            }
        }

        if (!pesanan.validasiPesanan()) {
            System.out.println("Pesanan kosong / tidak valid. Dibatalkan.");
            return;
        }
        restoran.masukkanAntrean(pesanan);
        System.out.println("Pesanan " + idPesanan + " masuk antrean. Total: Rp "
                + String.format("%,.0f", pesanan.hitungTotal()));
    }

    private static void prosesPesanan() {
        if (restoran.getAntreanPesanan().isEmpty()) {
            System.out.println("Antrean kosong.");
            return;
        }
        Pesanan pesanan = restoran.prosesPesanan();
        System.out.println("=== Memproses Pesanan " + pesanan.getIdPesanan() + " ===");
        Pelanggan p = pesanan.getPelanggan();
        System.out.println("Pelanggan : " + (p != null ? p.getNama() : "-"));
        for (DetailPesanan d : pesanan.getDaftarDetail()) {
            System.out.println("- " + d.getMenu().getNamaMenu() + " x" + d.getJumlah()
                    + " = Rp " + String.format("%,.0f", d.hitungSubtotal()));
        }
        System.out.println("Subtotal  : Rp " + String.format("%,.0f", pesanan.hitungSubtotal()));
        System.out.println("Pajak 10% : Rp " + String.format("%,.0f", pesanan.hitungPajak()));
        System.out.println("Total     : Rp " + String.format("%,.0f", pesanan.hitungTotal()));
    }

    private static String bacaTeks(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int bacaInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka. Coba lagi.");
            }
        }
    }

    private static double bacaDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka. Coba lagi.");
            }
        }
    }
}