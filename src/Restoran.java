import java.util.ArrayList;

public class Restoran {
    private String namaRestoran;
    private ArrayList<Menu> daftarMenu;
    private ArrayList<Pelanggan> daftarPelanggan;
    private AntreanPesanan antreanPesanan;

    public Restoran(String namaRestoran, int kapasitasAntrean) {
        this.namaRestoran = namaRestoran;
        this.daftarMenu = new ArrayList<>();
        this.daftarPelanggan = new ArrayList<>();
        this.antreanPesanan = new AntreanPesanan(kapasitasAntrean);
    }

    public void tambahMenu(Menu menu) {
        if (menu != null && menu.validasiData()) {
            daftarMenu.add(menu);
        }
    }

    public void tampilkanMenu() {
        System.out.println("=== Daftar Menu " + namaRestoran + " ===");
        if (daftarMenu.isEmpty()) {
            System.out.println("Belum ada menu.");
        } else {
            for (Menu menu : daftarMenu) {
                menu.tampilkanInfo();
                System.out.println();
            }
        }
    }

    public void tambahPelanggan(Pelanggan pelanggan) {
        if (pelanggan != null && pelanggan.validasiData()) {
            daftarPelanggan.add(pelanggan);
        }
    }

    public Menu cariMenu(String idMenu) {
        for (Menu menu : daftarMenu) {
            if (menu.getIdMenu().equals(idMenu)) {
                return menu;
            }
        }
        return null;
    }

    public Pesanan buatPesanan(String idPesanan, Pelanggan pelanggan, DetailPesanan... details) {
        Pesanan pesanan = new Pesanan(idPesanan, pelanggan);
        for (DetailPesanan detail : details) {
            if (detail.validasiData()) {
                pesanan.tambahDetail(detail);
            }
        }
        return pesanan;
    }

    public void masukkanAntrean(Pesanan pesanan) {
        if (pesanan != null && pesanan.validasiPesanan()) {
            antreanPesanan.enqueue(pesanan);
        }
    }

    public Pesanan prosesPesanan() {
        return antreanPesanan.dequeue();
    }

    public void tampilkanAntrean() {
        System.out.println("=== Antrean Pesanan ===");
        if (antreanPesanan.isEmpty()) {
            System.out.println("Antrean kosong.");
            return;
        }
        // Note: AntreanPesanan doesn't expose internal array, so we can't iterate directly
        // This would need a getAll() method in AntreanPesanan or we show size only
        System.out.println("Jumlah pesanan menunggu: " + antreanPesanan.size());
        System.out.println("Pesanan depan: " + antreanPesanan.front().getIdPesanan());
    }

    public String getNamaRestoran() {
        return namaRestoran;
    }

    public ArrayList<Menu> getDaftarMenu() {
        return daftarMenu;
    }

    public ArrayList<Pelanggan> getDaftarPelanggan() {
        return daftarPelanggan;
    }

    public AntreanPesanan getAntreanPesanan() {
        return antreanPesanan;
    }
}