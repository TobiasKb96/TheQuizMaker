package com.quizmaker.quizmaker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quizmaker.quizmaker.model.HighScore;
import com.quizmaker.quizmaker.model.QuestionOK;
import com.quizmaker.quizmaker.model.Topic;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonRWC {  //Jason Read/Write/Check

    //saves a Topic to a .json file (overwrites existing files)
    public static void toFile(Topic topic) {
        String fileName = topic.getName() + ".json";
        List<QuestionOK> questionsInTopic = topic.getQuestions();
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

    public static void saveHighScore(String playerName, String score) {
        String fileName = "HighScores.json";
        List<HighScore> existingHighScores = readHighScores(fileName);

        HighScore newHighScore = new HighScore(playerName, score);
        existingHighScores.add(newHighScore);
        try (Writer writer = new FileWriter(fileName)) {
            Gson gson = new Gson();
            gson.toJson(existingHighScores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<HighScore> readHighScores(String fileName) {
        List<HighScore> existingHighScores = new ArrayList<>();

        try (Reader reader = new FileReader(fileName)) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<HighScore>>() {}.getType();
            existingHighScores = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return existingHighScores != null ? existingHighScores : new ArrayList<>();
    }

    // temporary score data
    public static void saveScore(String score) {
        String fileName = "Scores.json";
        List<HighScore> existingScores = readScores(fileName);

        HighScore newScore = new HighScore(score);
        existingScores.add(newScore);

        try (Writer writer = new FileWriter(fileName)) {
            Gson gson = new Gson();
            gson.toJson(existingScores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<HighScore> readScores(String fileName) {
        List<HighScore> existingScores = new ArrayList<>();
        try (Reader reader = new FileReader(fileName)) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<HighScore>>() {}.getType();
            existingScores = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return existingScores != null ? existingScores : new ArrayList<>();
    }

    public static void clearScoresFile() {
        String fileName = "Scores.json";

        try {
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
                System.out.println("Scores file cleared successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //returns a Topic object from a saved .json file
    public static Topic fromFile(String fileName) {

        String filePath = fileName + ".json";

        try {
            FileReader fileReader = new FileReader(filePath);
            Gson gson = new Gson();
            Type questionListType = new TypeToken<ArrayList<QuestionOK>>(){}.getType();
            List<QuestionOK> questions = gson.fromJson(fileReader, questionListType);
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
