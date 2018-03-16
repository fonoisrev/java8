package wuhang.java8.spliterator;

public class WordCounter {
    
    private int count;
    
    public WordCounter(int count) {
        this.count = count;
    }
    
    public WordCounter wordCounter(Character c) {
        // has bug
        if (Character.isWhitespace(c)) {
            return new WordCounter(count + 1);
        }else {
            return  this;
        }
    }
    
    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(count + wordCounter.count);
    }
    
    public int getCount() {
        return count;
    }
}
