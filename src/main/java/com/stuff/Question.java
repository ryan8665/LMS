/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stuff;

/**
 *
 * @author ryan
 */
public class Question {
     private String id,question,tip,solution,lesson,video,pdf,answer1,answer2,answer3,answer4,right,aid1,aid2,aid3,aid4,sc,time;

    public Question(String id, String question, String tip,String solution, String answer1, String answer2,
            String answer3, String answer4, String right, String aid1, String aid2, String aid3, String aid4,String sc ,String time) {
        this.id = id;
        this.question = question;
        this.tip = tip;
        this.solution = solution;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.right = right;
        this.aid1 = aid1;
        this.aid2 = aid2;
        this.aid3 = aid3;
        this.aid4 = aid4;
         this.sc = sc;
         this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }
    

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getAid1() {
        return aid1;
    }

    public void setAid1(String aid1) {
        this.aid1 = aid1;
    }

    public String getAid2() {
        return aid2;
    }

    public void setAid2(String aid2) {
        this.aid2 = aid2;
    }

    public String getAid3() {
        return aid3;
    }

    public void setAid3(String aid3) {
        this.aid3 = aid3;
    }

    public String getAid4() {
        return aid4;
    }

    public void setAid4(String aid4) {
        this.aid4 = aid4;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
    
     
   
}
