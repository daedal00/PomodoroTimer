package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.PomodoroTimer;

public class PomodoroUI {
    private Label timerLabel;
    private Button startButton;
    private Button pauseButton;
    private Button stopButton;
    private PomodoroTimer pomodoroTimer;

    public PomodoroUI() {
        this.timerLabel = new Label("25:00");
        this.startButton = new Button("Start");
        this.pauseButton = new Button("Pause");
        this.stopButton = new Button("Stop");

        // Set up the event handlers for the buttons
        startButton.setOnAction(e -> pomodoroTimer.start());
        pauseButton.setOnAction(e -> pomodoroTimer.pause());
        stopButton.setOnAction(e -> pomodoroTimer.stop());

        // Set up the layout of the UI
        HBox buttonBox = new HBox(10, startButton, pauseButton, stopButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox root = new VBox(20, timerLabel, buttonBox);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setPrefSize(300, 200);

        // Initialize the timer
        this.pomodoroTimer = new PomodoroTimer(25 * 60, 5 * 60, this);
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
