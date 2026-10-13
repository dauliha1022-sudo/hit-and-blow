// 2025オブジェクト指向設計演習 最終課題 jx24281 松井陽希
import javax.swing.*;

public class MainFrame extends JFrame {

    private Record record = new Record();

    public MainFrame() {
        setTitle("Hit & Blow");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        showMainPanel();

        setVisible(true);
    }

    public void showMainPanel() {
        setContentPane(new MainPanel(this, record));
        revalidate();
    }
        public void showGamePanel() {
        setContentPane(new GamePanel(this, record));
        revalidate();
    }
}
