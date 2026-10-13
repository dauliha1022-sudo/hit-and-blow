// 2025オブジェクト指向設計演習 最終課題 jx24281 松井陽希
import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel(MainFrame frame, Record record) {

        setLayout(new BorderLayout());

        JLabel recordLabel = new JLabel(record.getRecord(), JLabel.CENTER);
        recordLabel.setFont(new Font("Meiryo", Font.BOLD, 18));

        JButton startButton = new JButton("試合開始");

        startButton.addActionListener(e -> frame.showGamePanel());

        add(recordLabel, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
    }
}
