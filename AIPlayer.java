// 2025オブジェクト指向設計演習 最終課題 jx24281 松井陽希
//AIの導入を行った際に生成AIを使用してエラーのプログラムを直した。アルゴリズの案も生成AIを使用した。
import java.util.*;

public class AIPlayer {

    private List<int[]> candidates = new ArrayList<>();

    public AIPlayer() {
        for (int a = 0; a < 10; a++)
            for (int b = 0; b < 10; b++)
                for (int c = 0; c < 10; c++)
                    if (a != b && b != c && a != c)
                        candidates.add(new int[]{a, b, c});
    }

    public int[] guess() {
        return candidates.get(new Random().nextInt(candidates.size()));
    }

    public void feedback(int[] guess, int hit, int blow) {
        candidates.removeIf(c ->
            !match(c, guess, hit, blow)
        );
    }

    private boolean match(int[] a, int[] b, int hit, int blow) {
        int h = 0, bl = 0;
        for (int i = 0; i < 3; i++) {
            if (a[i] == b[i]) h++;
            else for (int j = 0; j < 3; j++)
                if (a[i] == b[j]) bl++;
        }
        return h == hit && bl == blow;
    }
}
