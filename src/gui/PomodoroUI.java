package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.PomodoroTimer;

import java.util.Objects;

public class PomodoroUI {
    private Label timerLabel;
    private Button startButton;
    private Button pauseButton;
    private Button stopButton;
    private PomodoroTimer pomodoroTimer;

    public PomodoroUI() {
        this.timerLabel = new Label("25:00");
        this.timerLabel.getStyleClass().add("timer-label");
        this.timerLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf"), 50));
        this.startButton = new Button();
        this.startButton.getStyleClass().add("icon-button");
        this.pauseButton = new Button();
        this.pauseButton.getStyleClass().add("icon-button");
        this.stopButton = new Button();
        this.stopButton.getStyleClass().add("icon-button");

        // Set the text for the buttons
        this.startButton.setText("Start");
        this.pauseButton.setText("Pause");
        this.stopButton.setText("Stop");

        // Set up the event handlers for the buttons
        startButton.setOnAction(e -> pomodoroTimer.start());
        pauseButton.setOnAction(e -> pomodoroTimer.pause());
        stopButton.setOnAction(e -> pomodoroTimer.stop());

        // Set up the layout of the UI
        HBox buttonBox = new HBox(10, startButton, pauseButton, stopButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox root = new VBox(40, timerLabel, buttonBox);
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setPrefSize(400, 300);
        root.getStyleClass().add("root");

        // Initialize the timer
        this.pomodoroTimer = new PomodoroTimer(25 * 60, 5 * 60, this);

        // Add CSS styling to the UI
        root.getStylesheets().add("styles.css");
    }

    public void updateTimerLabel(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    public VBox getRoot() {
        return (VBox) timerLabel.getParent();
    }
}
