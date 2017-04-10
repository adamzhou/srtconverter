package com.cisco.srtconverter;

/**
 * Created by linzhou on 4/7/17.
 */
public class TranscriptSentence implements Comparable{
    //from, text, to
    private double from;
    private String text;
    private double to;

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public int compareTo(Object obj) {
        TranscriptSentence sentence = (TranscriptSentence) obj;
        return (int)(this.getFrom() - sentence.getFrom());
    }
}
