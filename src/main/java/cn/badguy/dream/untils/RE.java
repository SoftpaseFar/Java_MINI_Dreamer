package cn.badguy.dream.untils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RE {
    public final static Matcher match(String pattern, String line) {
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        return m;
    }

    public final static String replaceAll(String pattern, String line, String replaceLine) {
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        String m = r.matcher(line).replaceAll(replaceLine);
        return m;
    }
}
