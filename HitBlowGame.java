// 2025オブジェクト指向設計演習 最終課題 jx24281 松井陽希
import java.util.*;

public class HitBlowGame {

    private int[] answer = new int[3];

    public HitBlowGame() {
        generate();
    }

    public HitBlowGame(int[] answer) {
        this.answer = answer;
    }

    public int[] getAnswer() {
        return answer;
    }

    private void generate() {
        Random r = new Random();
        Set<Integer> set = new HashSet<>();//生成AI、重複をさけるため表示のように出力された。
        int i = 0;

        while (i < 3) {
            int n = r.nextInt(10);
            if (set.add(n)) {
                answer[i++] = n;
            }
        }
    }

    public int[] judge(int[] input) {
        int hit = 0;
        int blow = 0;

        for (int i = 0; i < 3; i++) {
            if (input[i] == answer[i]) {
                hit++;
            } else {
                for (int j = 0; j < 3; j++) {
                    if (input[i] == answer[j]) {
                        blow++;
                    }
                }
            }
        }
        return new int[]{hit, blow};
    }

    public boolean isClear(int hit) {
        return hit == 3;
    }
}
