public class Pelanggan {
    private static int counter = 1;

    private String idPelanggan;
    private String nama;
    private String nomorTelepon;

    public Pelanggan(String idPelanggan, String nama, String nomorTelepon) {
        this.idPelanggan = (idPelanggan == null || idPelanggan.trim().isEmpty())
                ? String.format("P%03d", counter++) : idPelanggan;
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
    }

    public boolean validasiData() {
        if (nama == null || nama.trim().isEmpty()) {
            return false;
        }
        if (nomorTelepon == null || nomorTelepon.trim().isEmpty()) {
            return false;
        }
        if (!nomorTelepon.matches("\\d+")) {
            return false;
        }
        return true;
    }

    public void tampilkanInfo() {
        System.out.println("=== Info Pelanggan ===");
        System.out.println("ID Pelanggan : " + idPelanggan);
        System.out.println("Nama         : " + nama);
        System.out.println("No. Telepon  : " + nomorTelepon);
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }
}