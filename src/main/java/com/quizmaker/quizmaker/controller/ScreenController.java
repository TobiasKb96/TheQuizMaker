package com.quizmaker.quizmaker.controller;

import com.google.gson.Gson;
import com.quizmaker.quizmaker.GameLogic;
import com.quizmaker.quizmaker.JsonRWC;
import com.quizmaker.quizmaker.model.HighScore;
import com.quizmaker.quizmaker.model.QuestionOK;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class ScreenController implements Initializable {
    private int rounds = 10;
    public int getRounds(){return rounds;}
    public void setRounds(int rounds){this.rounds = rounds;}

    public ScreenController(){}
    private ObservableList<QuestionOK> questionList;
    private int currentQuestionIndex = 0;
    private GameLogic game;
    private int counter = 0;
    private static final Integer STARTTIME = 200;
    private Timeline timeline;
    private Integer totalSeconds = STARTTIME;
    private Integer score = 0;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    private Integer lives = 3;
    private boolean cancelTwoOptionsBtnClicked = false;

    @FXML
    private Button a1, a2, a3, a4;
    @FXML
    public Button life1, life2, life3;
    @FXML
    private Label question;

    @FXML
    private ListView<String> listView;

    @FXML
    public Button fiftyBtn;
    @FXML
    public Button callAFriendBtn;
    @FXML
    public Button passBtn;
    @FXML
    public Button extraLifeBtn;
    @FXML
    public Label minuteLbl;
    @FXML
    public Label secondLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        countDownTimer();
        question.setStyle("-fx-background-color: yellow");
        for (int i = rounds;  i >= counter; i--){
            listView.getItems().add(i + "......ECTS");
        }

        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setFont(Font.font("Lucida Console", 10));
                            setText(item);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        getQuestions();
        displayCurrentQuestion();
    }
    public void onA1Click(ActionEvent event) {
        handleAnswerButtonClick(event, a1.getText());
        if (cancelTwoOptionsBtnClicked) resetButtonStyles();
    }
    public void onA2Click(ActionEvent event) {
        handleAnswerButtonClick(event, a2.getText());
        if (cancelTwoOptionsBtnClicked) resetButtonStyles();
    }
    public void onA3Click(ActionEvent event) {
        handleAnswerButtonClick(event, a3.getText());
        if (cancelTwoOptionsBtnClicked) resetButtonStyles();
    }
    public void onA4Click(ActionEvent event) {
        handleAnswerButtonClick(event, a4.getText());
        if (cancelTwoOptionsBtnClicked) resetButtonStyles();
    }

    public void countDownTimer(){
        timeline = new Timeline();
        KeyFrame frame= new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                totalSeconds--;
                int minutes = totalSeconds / 60;
                int seconds = totalSeconds % 60;

                // Update the labels
                minuteLbl.setText(String.format("%02d", minutes));
                secondLbl.setText(String.format("%02d", seconds));

                if(totalSeconds <= 0){
                    timeline.stop();
                    gameOverLost();
                }
            }
        });

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(frame);
        if (timeline != null) {
            timeline.stop();
        }

        timeline.play();
    }

    @FXML
    void cancelTwoOptions(ActionEvent event) throws IOException{
        try {
            ((Button) event.getSource()).setDisable(true);
            cancelTwoOptionsBtnClicked = true;
            QuestionOK currentQuestion = questionList.get(currentQuestionIndex);

            String correctAnswer = currentQuestion.getCorrectAnswer();

            List<String> wrongAnswers = new ArrayList<>(Arrays.asList(currentQuestion.getA1(), currentQuestion.getA2(), currentQuestion.getA3(), currentQuestion.getA4()));
            wrongAnswers.remove(correctAnswer);
            Collections.shuffle(wrongAnswers);

            String selectedWrongAnswer1 = wrongAnswers.get(0);
            String selectedWrongAnswer2 = wrongAnswers.get(1);

            setButtonVisibility(a1, correctAnswer, selectedWrongAnswer1, selectedWrongAnswer2);
            setButtonVisibility(a2, correctAnswer, selectedWrongAnswer1, selectedWrongAnswer2);
            setButtonVisibility(a3, correctAnswer, selectedWrongAnswer1, selectedWrongAnswer2);
            setButtonVisibility(a4, correctAnswer, selectedWrongAnswer1, selectedWrongAnswer2);

            //resetButtonStyles();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setButtonVisibility(Button button, String correctAnswer, String wrongAnswer1, String wrongAnswer2) {
        String buttonText = button.getText();
        if (buttonText.equals(correctAnswer) || (!buttonText.equals(wrongAnswer1) && !buttonText.equals(wrongAnswer2))) {
            button.setVisible(true);
        } else {
            button.setVisible(false);
        }
    }

    private void resetButtonStyles() {
        a1.setVisible(true);
        a2.setVisible(true);
        a3.setVisible(true);
        a4.setVisible(true);
    }

    @FXML
    void callAFriend(ActionEvent event) throws IOException{
        ((Button) event.getSource()).setDisable(true);
    }

    @FXML
    void skipTheQuestion(ActionEvent event) throws IOException{
            ((Button) event.getSource()).setDisable(true);
            currentQuestionIndex++;

        if (currentQuestionIndex < questionList.size()) {
            displayCurrentQuestion();
        }else {
                System.out.println("End of the game/quiz");
                gameOverWin();
            }
        }

    @FXML
    void extraLife(ActionEvent event) throws IOException{
        ((Button) event.getSource()).setDisable(true);
        if(lives == 2){
            life3.setVisible(true);
            lives++;
        }
        if(lives == 1){
            life2.setVisible(true);
            lives++;
        }
    }

    public void getQuestions(){
        Gson gson = new Gson();
        try (Reader reader = new FileReader("Sciency things.json")) {
            QuestionOK[] scoreList = gson.fromJson(reader, QuestionOK[].class);
            questionList = FXCollections.observableArrayList(scoreList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayCurrentQuestion(){
        if (currentQuestionIndex < questionList.size()) {
            QuestionOK currentQuestion = questionList.get(currentQuestionIndex);
            question.setText(currentQuestion.getQuestion());
            a1.setText(currentQuestion.getA1());
            a2.setText(currentQuestion.getA2());
            a3.setText(currentQuestion.getA3());
            a4.setText(currentQuestion.getA4());
        } else {
            try {
                gameOverWin();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void handleAnswerButtonClick(ActionEvent event, String selectedAnswer) {
        boolean isCorrect = checkAnswer(selectedAnswer);
        if (isCorrect) {
            ((Button) event.getSource()).setStyle("-fx-background-color: green;");
            score++;
            this.setScore(score);
        } else {
            ((Button) event.getSource()).setStyle("-fx-background-color: red;");
            handleWrongAnswer();
        }
        PauseTransition pause = new PauseTransition(Duration.seconds(0.1));
        pause.setOnFinished(e -> {
            ((Button) event.getSource()).setStyle("");
            currentQuestionIndex++;
            displayCurrentQuestion();
        });
        pause.play();
    }

    private void handleWrongAnswer() {
        if (lives > 0) {
            switch (lives) {
                case 3 -> life3.setVisible(false);
                case 2 -> life2.setVisible(false);
                case 1 -> life1.setVisible(false);
            }
            lives--;
        }
        if (lives == 0){
            gameOverLost();
        }
    }

    private void gameOverLost() {
        JsonRWC.saveScore(Integer.toString(score));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("GAME OVER!!");
        alert.setContentText("You Must Repay Sbth.");

        ButtonType closeButton = new ButtonType("Close", ButtonType.OK.getButtonData());
        alert.getButtonTypes().setAll(closeButton);
        alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent event) {
                redirectToLoserView();
            }
        });
        Platform.runLater(alert::showAndWait);
    }

    private void redirectToLoserView() {
        try {
            URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/gameoverlose-view.fxml").toUri().toURL();
            Parent root = FXMLLoader.load(url);
            Scene newGameScene = new Scene(root);
            Stage stage = (Stage) a1.getScene().getWindow();
            stage.setScene(newGameScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void gameOverWin() throws IOException{
        JsonRWC.saveScore(Integer.toString(score));
        URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/gameoverwin-view.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene newGameScene = new Scene(root);
        Stage stage = (Stage) a1.getScene().getWindow();
        stage.setScene(newGameScene);
    }

    boolean checkAnswer(String selectedAnswer) {
        QuestionOK currentQuestion = questionList.get(currentQuestionIndex);
        if (currentQuestion.getCorrectAnswer().equalsIgnoreCase(selectedAnswer)) {
            System.out.println("Correct!");
            return true;
        } else {
            System.out.println("Incorrect!");

            return false;
        }
    }

}