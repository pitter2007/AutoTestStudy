package com.course.extendstest;

public class Animal {
    private void beat(){
        System.out.println("心脏跳动");
    }
    public void breath(){
        this.beat();
        System.out.println("呼吸正常");
    }
}
