package com.hello.helloSpring.domain;

public class Member {
    private long id;
    //식별자
    private String name;
    //이름


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
