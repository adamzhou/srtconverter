package com.cisco.srtconverter;

import java.util.*;

/**
 * Created by linzhou on 4/7/17.
 */
public class TranscriptResponse {

    //created_at, id, language, recording_ids, results, status, success, updated_at
    private String created_at;
    private Integer id;
    private String language;
    private List<Integer> recording_ids;
    private List<TranscriptResult> results;
    private String status;
    private Boolean success;
    private String updated_at;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Integer> getRecording_ids() {
        return recording_ids;
    }

    public void setRecording_ids(List<Integer> recording_ids) {
        this.recording_ids = recording_ids;
    }

    public List<TranscriptResult> getResults() {
        return results;
    }

    public void setResults(List<TranscriptResult> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String toSRT() {
        List<TranscriptSentence> sentences = new ArrayList<TranscriptSentence>();
        for (TranscriptResult result : results) {
            for (List<TranscriptSentence> sentenceList : result.getChannels()) {
                sentences.addAll(sentenceList);
            }
        }

        Collections.sort(sentences);

        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (TranscriptSentence sentence : sentences) {
            sb.append(index).append('\n');
            sb.append(TranscriptUtils.toSRTTime(sentence.getFrom())).append(" --> ").append(TranscriptUtils.toSRTTime(sentence.getTo())).append('\n');
            sb.append(sentence.getText()).append("\n\n");
            index++;
        }

        return sb.toString();
    }
}
