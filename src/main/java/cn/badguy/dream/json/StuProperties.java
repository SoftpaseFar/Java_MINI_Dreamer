package cn.badguy.dream.json;

public class StuProperties {
    private double prob;
    private StuTect rect;
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public StuProperties() {
    }

    public double getProb() {
        return prob;
    }

    public void setProb(double prob) {
        this.prob = prob;
    }

    public StuTect getRect() {
        return rect;
    }

    public void setRect(StuTect rect) {
        this.rect = rect;
    }
}
