public class AntreanPesanan {
    private Pesanan[] data;
    private int front;
    private int rear;
    private int jumlah;
    private int kapasitas;

    public AntreanPesanan(int kapasitas) {
        this.kapasitas = kapasitas;
        this.data = new Pesanan[kapasitas];
        this.front = 0;
        this.rear = -1;
        this.jumlah = 0;
    }

    public void enqueue(Pesanan pesanan) {
        if (isFull()) {
            throw new IllegalStateException("Antrean penuh. Tidak dapat menambah pesanan.");
        }
        rear = (rear + 1) % kapasitas;
        data[rear] = pesanan;
        jumlah++;
    }

    public Pesanan dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Antrean kosong. Tidak ada pesanan untuk diambil.");
        }
        Pesanan pesanan = data[front];
        data[front] = null;
        front = (front + 1) % kapasitas;
        jumlah--;
        return pesanan;
    }

    public Pesanan front() {
        if (isEmpty()) {
            throw new IllegalStateException("Antrean kosong. Tidak ada pesanan di depan.");
        }
        return data[front];
    }

    public boolean isEmpty() {
        return jumlah == 0;
    }

    public boolean isFull() {
        return jumlah == kapasitas;
    }

    public int size() {
        return jumlah;
    }

    public int getKapasitas() {
        return kapasitas;
    }
}