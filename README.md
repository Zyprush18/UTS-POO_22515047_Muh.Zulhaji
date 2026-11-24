# Sistem Manajemen Restoran

**UTS Pemrograman Berorientasi Objek (POO)**  
**NIM:** 22515047  
**Nama:** Muh. Zulhaji  

## Deskripsi
Aplikasi **console** berbasis Java untuk mengelola operasional restoran: manajemen menu, pendaftaran pelanggan, pembuatan pesanan, perhitungan pajak, dan pemrosesan antrean pesanan (FIFO).

## Struktur Proyek
```
SistemManajemenRestoran/
├── src/          # Kode sumber Java
│   ├── Main.java
│   ├── Restoran.java
│   ├── Menu.java
│   ├── Pelanggan.java
│   ├── DetailPesanan.java
│   ├── Pesanan.java
│   └── AntreanPesanan.java
├── diagram/      # Diagram UML, ERD, arsitektur
├── pengujian/    # Test case, hasil pengujian
├── README.md
└── .gitignore
```

## Teknologi
- Java 17+ (tanpa dependency eksternal)
- Penyimpanan in-memory (`ArrayList` + array)

## Class Diagram (UML)
File diagram: `diagram/UML-Class-Diagram.png`

### Daftar Class & Anggota

**Menu**
- Atribut: `idMenu: String`, `namaMenu: String`, `kategori: String`, `harga: double`
- Method: `validasiData(): boolean`, `tampilkanInfo(): void`, getter (`getIdMenu`, `getNamaMenu`, `getKategori`, `getHarga`)

**Pelanggan**
- Atribut: `idPelanggan: String`, `nama: String`, `nomorTelepon: String`
- Method: `validasiData(): boolean`, `tampilkanInfo(): void`, getter (`getIdPelanggan`, `getNama`, `getNomorTelepon`)

**DetailPesanan**
- Atribut: `menu: Menu`, `jumlah: int`
- Method: `hitungSubtotal(): double`, `validasiData(): boolean`, getter (`getMenu`, `getJumlah`)

**Pesanan**
- Atribut: `idPesanan: String`, `pelanggan: Pelanggan`, `daftarDetail: ArrayList<DetailPesanan>`
- Method: `tambahDetail(DetailPesanan)`, `hitungSubtotal(): double`, `hitungPajak(): double`, `hitungTotal(): double`, `validasiPesanan(): boolean`, getter

**AntreanPesanan** (ADT Queue)
- Atribut: `data: Pesanan[]`, `front: int`, `rear: int`, `jumlah: int`, `kapasitas: int`
- Method: `enqueue(Pesanan)`, `dequeue(): Pesanan`, `front(): Pesanan`, `isEmpty(): boolean`, `isFull(): boolean`, `size(): int`, `getKapasitas(): int`

**Restoran**
- Atribut: `namaRestoran: String`, `daftarMenu: ArrayList<Menu>`, `daftarPelanggan: ArrayList<Pelanggan>`, `antreanPesanan: AntreanPesanan`
- Method: `tambahMenu`, `tampilkanMenu`, `tambahPelanggan`, `cariMenu(String): Menu`, `buatPesanan(...)`, `masukkanAntrean`, `prosesPesanan(): Pesanan`, `tampilkanAntrean`, getter

**Main**
- Method: `main(String[])`, `tampilkanMenu()`, `prosesPilihan(int)`, helper input (`bacaTeks`, `bacaInt`, `bacaDouble`)

### Relasi Antar-Class
- `Pesanan` **1 → many** `DetailPesanan` (komposisi)
- `DetailPesanan` **many → 1** `Menu` (asosiasi)
- `Pesanan` **many → 1** `Pelanggan` (asosiasi)
- `Restoran` **1 → many** `Menu`, `Pelanggan`, `Pesanan` (via `AntreanPesanan`)
- `Restoran` **1 → 1** `AntreanPesanan`
- `Main` **→** `Restoran` (delegasi)

## ADT Queue (AntreanPesanan)
Antrean pesanan diimplementasikan sebagai **circular array queue** dengan prinsip **FIFO**:

| Operasi | Deskripsi |
|---|---|
| `enqueue(p)` | Menambah pesanan di belakang antrean |
| `dequeue()` | Mengambil & menghapus pesanan paling depan |
| `front()` | Melihat pesanan paling depan tanpa menghapus |
| `isEmpty()` / `isFull()` | Cek kondisi kosong / penuh |
| `size()` | Jumlah pesanan dalam antrean |

- Indeks bergerak melingkar: `(index + 1) % kapasitas`.
- `enqueue` saat penuh dan `dequeue`/`front` saat kosong melempar `IllegalStateException`.

## Aturan Bisnis
1. **Menu**: nama tidak boleh kosong; harga harus > 0.
2. **Pelanggan**: nama tidak boleh kosong; nomor telepon hanya angka.
3. **DetailPesanan**: menu valid & jumlah minimal 1; subtotal = `harga × jumlah`.
4. **Pesanan**: wajib memiliki minimal 1 detail valid.
5. **Pajak**: 10% dari subtotal.
   - `Subtotal = Σ detail`
   - `Pajak = Subtotal × 10%`
   - `Total = Subtotal + Pajak`
6. **Antrean**: pesanan diproses berurutan FIFO.

## Cara Menjalankan
```bash
# Dari root proyek
cd src
javac *.java

# Jalankan
java Main
```
Atau dengan folder output terpisah:
```bash
javac -d out src/*.java
java -cp out Main
```

## Menu Aplikasi
1. Tambah Menu
2. Tampilkan Menu
3. Tambah Pelanggan
4. Buat Pesanan
5. Lihat Antrean
6. Proses Pesanan
0. Keluar

## Lisensi
Proyek ini dibuat untuk keperluan akademik UTS POO.