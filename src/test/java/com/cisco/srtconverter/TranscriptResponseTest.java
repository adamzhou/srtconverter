package com.cisco.srtconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by linzhou on 4/9/17.
 */
public class TranscriptResponseTest {
    @Test
    public void testParseTranscript() {
        try {
            String transcriptJson = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("transcriptResponse.json"),
                    "UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            TranscriptResponse transcript = mapper.readValue(transcriptJson, TranscriptResponse.class);
            System.out.println(transcript);

            //Pretty print
            String prettyTranscript = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(transcript);
            System.out.println(prettyTranscript);

            System.out.println("=====Convert to SRT=====");
            System.out.println(transcript.toSRT());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTranscriptSentence() {
        TranscriptSentence sentence = new TranscriptSentence();
        sentence.setFrom(0.99);
        sentence.setText("hello!");
        sentence.setTo(34.88734);
        //Pretty print
        ObjectMapper mapper = new ObjectMapper();
        String prettyTranscript = null;
        try {
            prettyTranscript = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sentence);
            System.out.println(prettyTranscript);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTranscriptResult() {
        TranscriptResult result = new TranscriptResult();
        result.setRecording_id(1);
        result.setStatus("completed");

        TranscriptSentence sentence1 = new TranscriptSentence();
        sentence1.setFrom(0.99);
        sentence1.setText("hello!");
        sentence1.setTo(2.88734);

        TranscriptSentence sentence2 = new TranscriptSentence();
        sentence2.setFrom(2.99);
        sentence2.setText("hello!");
        sentence2.setTo(4.88734);

        List<TranscriptSentence> sentenceList = new ArrayList<>();
        sentenceList.add(sentence1);
        sentenceList.add(sentence2);

        List<List<TranscriptSentence>> channels = new ArrayList<>();
        channels.add(sentenceList);
        result.setChannels(channels);

        //Pretty print
        ObjectMapper mapper = new ObjectMapper();
        String prettyResult = null;
        try {
            prettyResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            System.out.println(prettyResult);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTranscriptSentenceSort() {
        TranscriptSentence sentence1 = new TranscriptSentence();
        sentence1.setFrom(0.99);
        sentence1.setText("hello!");
        sentence1.setTo(2.88734);

        TranscriptSentence sentence2 = new TranscriptSentence();
        sentence2.setFrom(2.99);
        sentence2.setText("hello!");
        sentence2.setTo(4.88734);

        TranscriptSentence sentence3 = new TranscriptSentence();
        sentence3.setFrom(6.99);
        sentence3.setText("hello!");
        sentence3.setTo(7.88734);

        TranscriptSentence sentence4 = new TranscriptSentence();
        sentence4.setFrom(9.99);
        sentence4.setText("hello!");
        sentence4.setTo(14.88734);

        List<TranscriptSentence> sentenceList = new ArrayList<>();
        sentenceList.add(sentence4);
        sentenceList.add(sentence2);
        sentenceList.add(sentence1);
        sentenceList.add(sentence3);

        System.out.println("=====Before sort=====");
        printSentences(sentenceList);
        Collections.sort(sentenceList);
        System.out.println("=====After sort=====");
        printSentences(sentenceList);

        assertEquals(sentence1.getFrom(), sentenceList.get(0).getFrom());
    }

    private void printSentences(List<TranscriptSentence> sentenceList) {
        if (sentenceList != null) {
            for (TranscriptSentence sentence : sentenceList) {
                System.out.println("from: " + sentence.getFrom());
            }
        }
    }
}
