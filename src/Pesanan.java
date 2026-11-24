import java.util.ArrayList;

public class Pesanan {
    private String idPesanan;
    private Pelanggan pelanggan;
    private ArrayList<DetailPesanan> daftarDetail;

    public Pesanan(String idPesanan, Pelanggan pelanggan) {
        this.idPesanan = idPesanan;
        this.pelanggan = pelanggan;
        this.daftarDetail = new ArrayList<>();
    }

    public void tambahDetail(DetailPesanan detail) {
        daftarDetail.add(detail);
    }

    public double hitungSubtotal() {
        double subtotal = 0;
        for (DetailPesanan detail : daftarDetail) {
            subtotal += detail.hitungSubtotal();
        }
        return subtotal;
    }

    public double hitungPajak() {
        return hitungSubtotal() * 0.10;
    }

    public double hitungTotal() {
        return hitungSubtotal() + hitungPajak();
    }

    public boolean validasiPesanan() {
        if (daftarDetail.isEmpty()) {
            return false;
        }
        for (DetailPesanan detail : daftarDetail) {
            if (!detail.validasiData()) {
                return false;
            }
        }
        return true;
    }

    public String getIdPesanan() {
        return idPesanan;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public ArrayList<DetailPesanan> getDaftarDetail() {
        return daftarDetail;
    }
}