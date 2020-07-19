package com.example.springbootjavassist.service;


import com.example.springbootjavassist.test.SayHello;

/**
 * created by dbx on 2019/1/9
 */
public class SayService {

    public String tosay(String str){

        SayHello say=new SayHello();
        String arg0 = say.say(str);

        return arg0;
    }

}
