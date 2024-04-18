package PGS.Level3;

import java.util.HashSet;
import java.util.Set;

public class PGS_43163 {

    static int answer = 51;

    public int solution(final String begin, final String target, final String[] words) {
        final Set<String> wordSet = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            wordSet.add(words[i]);
        }
        if (!wordSet.contains(target)) {
            return 0;
        }

        dfs(wordSet, begin, target, 0);

        return answer;
    }

    private void dfs(final Set<String> words, final String now, final String target, final int cnt) {
        if (now.equals(target)) {
            answer = Math.min(cnt, answer);
            return;
        }
        for (final String word : words) {
            int diff = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != now.charAt(i)) {
                    diff++;
                }
                if (diff >= 2) {
                    break;
                }
            }
            if (diff == 1) {
                final Set<String> newWordSet = new HashSet<>(words);
                newWordSet.remove(word);
                dfs(newWordSet, word, target, cnt + 1);
            }
        }
    }
}
