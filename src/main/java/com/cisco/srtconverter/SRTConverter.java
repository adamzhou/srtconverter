package com.cisco.srtconverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * Created by linzhou on 4/7/17.
 */
public class SRTConverter {
    static private String USAGE = "Usage:\n  java -jar converter.jar <JSON file> <SRT file>\n";
    private String jsonFilePath;
    private String srtFilePath;

    public SRTConverter(String jsonFilePath, String srtFilePath) {
        this.jsonFilePath = jsonFilePath;
        this.srtFilePath = srtFilePath;
    }

    public static void main(String[] args) {
        if (!checkArguments(args)) {
            System.out.println(USAGE);
            return;
        }

        SRTConverter converter = new SRTConverter(args[0], args[1]);
        converter.run();
    }

    private void run() {
        File jsonFile = new File(jsonFilePath);
        if (!jsonFile.isFile() || !jsonFile.exists()) {
            System.out.println("The input " + jsonFilePath + " is not an existing file!");
            return;
        }

        try {
            String transcriptJson = FileUtils.readFileToString(jsonFile);
            ObjectMapper mapper = new ObjectMapper();
            TranscriptResponse transcript = mapper.readValue(transcriptJson, TranscriptResponse.class);
            File srtFile = new File(srtFilePath);
            FileUtils.writeStringToFile(srtFile, transcript.toSRT());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean checkArguments(String[] args) {
        if (args.length < 2) {
            return false;
        }

        if (StringUtils.isNotEmpty(args[0]) && StringUtils.isNotEmpty(args[1])) {
            return true;
        }

        return false;
    }
}
