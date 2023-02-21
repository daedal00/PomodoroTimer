package model;

import gui.PomodoroUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

public class PomodoroTimer {
    private IntegerProperty currentTime;
    private int workTime;
    private int breakTime;
    private boolean isWorking;
    private boolean isPaused;
    private Timeline timeline;
    private PomodoroUI pomodoroUI;

    public PomodoroTimer(int workTime, int breakTime, PomodoroUI pomodoroUI) {
        this.currentTime = new SimpleIntegerProperty(workTime);
        this.workTime = workTime;
        this.breakTime = breakTime;
        this.isWorking = true;
        this.isPaused = true;
        this.pomodoroUI = pomodoroUI;
    }

    public void start() {
        if (isPaused) {
            isPaused = false;
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                currentTime.set(currentTime.get() - 1);
                updateUI();
                if (currentTime.get() <= 0) {
                    stop();
                    isWorking = !isWorking;
                    if (isWorking) {
                        currentTime.set(workTime);
                    } else {
                        currentTime.set(breakTime);
                    }
                    updateUI();
                    start();
                }
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }

    public void pause() {
        if (!isPaused) {
            isPaused = true;
            timeline.pause();
        }
    }

    public void stop() {
        isPaused = true;
        timeline.stop();
        currentTime.set(workTime);
        updateUI();
    }

    private void updateUI() {
        pomodoroUI.updateTimerLabel(currentTime.get());
    }
}
