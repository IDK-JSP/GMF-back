package com.fmg.gmf_core.entitys;


public class Diet {

    private int id_diet;
    private String name;
    private String content;

    public Diet(int id_diet, String name, String content) {
        this.id_diet = id_diet;
        this.name = name;
        this.content = content;
    }

    public int getId_diet() {
        return id_diet;
    }

    public void setId_diet(int id_diet) {
        this.id_diet = id_diet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
