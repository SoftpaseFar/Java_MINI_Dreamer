package cn.badguy.dream.json;

import java.util.ArrayList;

public class ListOfAll {

    private String term;
    private ArrayList<DetailOfAll> list;

    public ListOfAll() {
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public ArrayList<DetailOfAll> getList() {
        return list;
    }

    public void setList(ArrayList<DetailOfAll> list) {
        this.list = list;
    }
}
