package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        PomodoroUI pomodoroUI = new PomodoroUI();

        Scene scene = new Scene(pomodoroUI.getRoot());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Pomodoro Timer");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
