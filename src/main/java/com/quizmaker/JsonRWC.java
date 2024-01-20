package com.quizmaker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonRWC {  //Jason Read/Write/Check

    //saves a Topic to a .json file (overwrites existing files)
    public static void toFile(Topic topic) {
        String fileName = topic.getName() + ".json";
        List<Question> questionsInTopic = topic.getQuestions();
        File newFile = new File(fileName);
            try {
                FileWriter fileWriter = new FileWriter(newFile);
                Gson gson = new Gson();
                gson.toJson(questionsInTopic, fileWriter);
                fileWriter.close();
            } catch (
                    IOException e) {
                System.err.println("Error in writing the file");
            }

    }

    //returns a Topic object from a saved .json file
    public static Topic fromFile(String fileName) {

        String filePath = fileName + ".json";

        try {
            FileReader fileReader = new FileReader(filePath);
            Gson gson = new Gson();
            Type questionListType = new TypeToken<ArrayList<Question>>(){}.getType();
            List<Question> questions = gson.fromJson(fileReader, questionListType);
            fileReader.close();
            Topic topic = new Topic(fileName,questions);
            return topic;

        } catch (IOException e) {
            System.err.println("Error in reading the file");
            return null;

        }
    }

    //returns list of available topics
    public static List<String> getFileNames() {
        List<String> fileNames = new ArrayList<>();
        File directory = new File(".");
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".json") && !file.getName().contains("Blank") ) {
                    fileNames.add(file.getName().substring(0, file.getName().lastIndexOf(".")));
                }
            }
        }
        return fileNames;
    }

    //returns true if filename exists
    public static boolean checkIfFileExists (String filename){
        List<String> existingFileNames = getFileNames();
        return existingFileNames.contains(filename);
    }

}
