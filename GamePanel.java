// 2025オブジェクト指向設計演習 最終課題 jx24281 松井陽希
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private JTextArea history = new JTextArea();
    private JLabel messageLabel =
            new JLabel("あなたの3桁の数字を入力してください");

    private boolean gameStarted = false;
    private boolean playerTurn = true;

    private int[] playerSecret;
    private HitBlowGame playerGame;
    private HitBlowGame aiGame;
    private AIPlayer ai = new AIPlayer();

    public GamePanel(MainFrame frame, Record record) {

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 1));

        JPanel topPanel = new JPanel();
        topPanel.add(messageLabel);

        JPanel bottomPanel = new JPanel();
        JTextField inputField = new JTextField(10);
        JButton enterButton = new JButton("✓");
        bottomPanel.add(inputField);
        bottomPanel.add(enterButton);

        inputPanel.add(topPanel);
        inputPanel.add(bottomPanel);

        history.setEditable(false);

        enterButton.addActionListener(e -> {
            String text = inputField.getText();

            if (!isValid(text)) {
                JOptionPane.showMessageDialog(
                        this,
                        "3桁の異なる数字を入力してください"
                );
                return;
            }

            int[] nums = parse(text);

            if (!gameStarted) {
                playerSecret = nums;
                playerGame = new HitBlowGame(playerSecret);
                aiGame = new HitBlowGame();

                history.append("ゲーム開始！\n");
                history.append("あなたの秘密の数字を設定しました\n\n");

                messageLabel.setText("予想する3桁の数字を入力してください");

                gameStarted = true;
                inputField.setText("");
                return;
            }

            if (playerTurn) {
                int[] r = aiGame.judge(nums);

                history.append("あなた: " + text + " → " + r[0] + "H " + r[1] + "B\n");

                if (aiGame.isClear(r[0])) {
                    record.win();
                    JOptionPane.showMessageDialog(this, "あなたの勝ち！");
                    frame.showMainPanel();
                    return;
                }
                playerTurn = false;
            }

            int[] aiGuess = ai.guess();
            int[] r2 = playerGame.judge(aiGuess);

            history.append("AI: " + aiGuess[0] + aiGuess[1] + aiGuess[2] + " → " + r2[0] + "H " + r2[1] + "B\n\n");
            ai.feedback(aiGuess, r2[0], r2[1]);

            if (playerGame.isClear(r2[0])) {
                record.lose();
                JOptionPane.showMessageDialog(this, "AIの勝ち！");
                frame.showMainPanel();
                return;
            }

            playerTurn = true;
            inputField.setText("");
        });

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(history), BorderLayout.CENTER);
    }

    private boolean isValid(String s) {
        if (s.length() != 3) return false;
        return s.charAt(0) != s.charAt(1) && s.charAt(1) != s.charAt(2) && s.charAt(0) != s.charAt(2);
    }

    private int[] parse(String s) {
        return new int[]{s.charAt(0) - '0', s.charAt(1) - '0', s.charAt(2) - '0'};
    }
}
