package javatodolistapplication;

import java.util.*;
import javax.swing.*;

class TodoList {

    private ArrayList<Task> tasks;
    private JPanel page2 = new JPanel();

    public TodoList() {
        tasks = new ArrayList<>();
//        this.page = page;
    }

    public boolean addTask(Task task) {
        if (tasks.add(task)) {
            Collections.sort(tasks, Comparator.comparingInt(Task::getPriority).reversed());
            return true;
        } else {
            return false;
        }
    }

    public JPanel showAllTasks() {
        if (tasks.size() == 0) {
            page2.add(new JLabel("TodoList is Empty!"));
        } else {
            page2.setLayout(new BoxLayout(page2, BoxLayout.Y_AXIS));
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                JLabel taskLabel = new JLabel((i + 1) + "Task Description:" + task.getDescription() + "-Priority:" + task.getPriority()
                        + "- Completed:" + task.isCompleted());
                page2.add(taskLabel);
            }
        }
        return page2;
    }

    public ArrayList<Task> showPriorityTask(int priority) {
        ArrayList<Task> priorityTasks = new ArrayList<>();
        if (tasks.size() > 0) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task.getPriority() == priority) {
                    System.out.println((i + 1) + "Task Description:" + task.getDescription() + "-Priority:" + task.getPriority()
                            + "- Completed:" + task.isCompleted());
                    priorityTasks.add(task);
                }
            }
        }
        return priorityTasks;
    }

    public String deleteTask(int index) {
        if (index > 0 && index < tasks.size()) {
            Task task = tasks.remove(index);
            System.out.println("Task Removed:" + task.getDescription());
            return task.getDescription();
        } else {
            System.out.print("Invalid Task Index!");
            return "false";
        }
    }

    public String markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.makedAsCompleted();
            System.out.println("Task marked as completed: " + task.getDescription());
            return task.getDescription();
        } else {
            System.out.println("Invalid task index.");
            return "false";
        }
    }
}
