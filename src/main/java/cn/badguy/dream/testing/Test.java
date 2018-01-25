package cn.badguy.dream.testing;

import cn.badguy.dream.untils.RE;

import java.util.Random;
import java.util.regex.Matcher;

class RandomHan {
    private Random ran = new Random();
    private final static int delta = 0x9fa5 - 0x4e00 + 1;

    public char getRandomHan() {
        return (char) (0x4e00 + ran.nextInt(delta));
    }
}

public class Test {
    public static void main(String[] args) {
//        RandomHan han = new RandomHan();
//        System.out.print(han.getRandomHan());
        String line = "show.action?pid=09519f9de9f96581dfbda8b0f1462a30\"  />";
        Matcher m = RE.match("action\\?(pid.*)?\"  />", line);
        if(m.find()){
            System.out.println(m.group(1).toString());
        }
    }

}

