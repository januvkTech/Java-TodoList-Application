package javatodolistapplication;

import java.util.*;

public class Task {

    private String description;
    private int importance;
    private boolean completed;

    public Task(String description, int importance) {
        this.description = description;
        this.importance = importance;
        completed = false;
    }

    public void makedAsCompleted() {
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return importance;
    }
}
