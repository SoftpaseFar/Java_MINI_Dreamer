package cn.badguy.dream.pojo;

import java.util.ArrayList;

public class Dogs {
    public ArrayList<detil> wordList;
    public class detil {
        public Integer offset;
        public String pos;
        public Integer length;
        public String word;

        @Override
        public String toString() {
            return "detil{" +
                    "offset=" + offset +
                    ", pos='" + pos + '\'' +
                    ", length=" + length +
                    ", word='" + word + '\'' +
                    '}';
        }
    }
    public String dataType;
    public String appCode;
    public String version;

    @Override
    public String toString() {
        return "Dogs{" +
                "wordList=" + wordList +
                ", dataType='" + dataType + '\'' +
                ", appCode='" + appCode + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
