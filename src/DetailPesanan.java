public class DetailPesanan {
    private Menu menu;
    private int jumlah;

    public DetailPesanan(Menu menu, int jumlah) {
        this.menu = menu;
        this.jumlah = jumlah;
    }

    public double hitungSubtotal() {
        return menu.getHarga() * jumlah;
    }

    public boolean validasiData() {
        if (menu == null) {
            return false;
        }
        if (jumlah < 1) {
            return false;
        }
        if (!menu.validasiData()) {
            return false;
        }
        return true;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getJumlah() {
        return jumlah;
    }
}