// 2025オブジェクト指向設計演習 最終課題 jx24281 松井陽希
public class Record {
    private int win = 0;
    private int lose = 0;

    public void win() {
        win++;
    }

    public void lose() {
        lose++;
    }

    public String getRecord() {
        return "勝ち: " + win + "  負け: " + lose;
    }
}
