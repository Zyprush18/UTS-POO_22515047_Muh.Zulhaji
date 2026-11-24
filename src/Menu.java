public class Menu {
    private static int counter = 1;

    private String idMenu;
    private String namaMenu;
    private String kategori;
    private double harga;

    public Menu(String idMenu, String namaMenu, String kategori, double harga) {
        this.idMenu = (idMenu == null || idMenu.trim().isEmpty())
                ? String.format("M%03d", counter++) : idMenu;
        this.namaMenu = namaMenu;
        this.kategori = kategori;
        this.harga = harga;
    }

    public boolean validasiData() {
        if (namaMenu == null || namaMenu.trim().isEmpty()) {
            return false;
        }
        if (harga <= 0) {
            return false;
        }
        return true;
    }

    public void tampilkanInfo() {
        System.out.println("=== Info Menu ===");
        System.out.println("ID Menu    : " + idMenu);
        System.out.println("Nama Menu  : " + namaMenu);
        System.out.println("Kategori   : " + kategori);
        System.out.println("Harga      : Rp " + String.format("%,.0f", harga));
    }

    public String getIdMenu() {
        return idMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public String getKategori() {
        return kategori;
    }

    public double getHarga() {
        return harga;
    }
}