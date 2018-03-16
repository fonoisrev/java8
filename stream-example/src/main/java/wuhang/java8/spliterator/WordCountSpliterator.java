package wuhang.java8.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCountSpliterator implements Spliterator<Character> {
    
    private String toDo;
    private int current = 0;
    
    public WordCountSpliterator(String toDo) {
        this.toDo = toDo;
    }
    
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(toDo.charAt(current++));
        return current < toDo.length();
    }
    
    @Override
    public Spliterator<Character> trySplit() {
        int remainSize = this.toDo.length() - current;
        if (remainSize < 15) {
            return null;
        }
        for (int splitPos = (remainSize / 2 + current); splitPos < toDo
                .length(); ++splitPos) {
            char c = this.toDo.charAt(splitPos);
            if (Character.isWhitespace(c)) {
                Spliterator<Character> subSplit = new WordCountSpliterator
                        (toDo.substring(current, splitPos));
                current = splitPos;
                return subSplit;
            }
        }
        
        return null;
    }
    
    @Override
    public long estimateSize() {
        return toDo.length() - current;
    }
    
    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
