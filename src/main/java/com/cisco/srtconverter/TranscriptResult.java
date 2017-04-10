package com.cisco.srtconverter;

import java.util.List;

/**
 * Created by linzhou on 4/7/17.
 */
public class TranscriptResult {

    private List<List<TranscriptSentence>> channels;
    private Integer recording_id;
    private String status;

    public List<List<TranscriptSentence>> getChannels() {
        return channels;
    }

    public void setChannels(List<List<TranscriptSentence>> channels) {
        this.channels = channels;
    }

    public Integer getRecording_id() {
        return recording_id;
    }

    public void setRecording_id(Integer recording_id) {
        this.recording_id = recording_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
