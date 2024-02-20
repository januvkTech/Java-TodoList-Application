package javatodolistapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaTodoListApplication extends JFrame {

    TodoList todolist = new TodoList();
    public JPanel page1 = new JPanel();
    public JPanel page2 = new JPanel();
    public JPanel page3 = new JPanel();
    public JPanel page4 = new JPanel();
    public JPanel page5 = new JPanel();
//    public JPanel page6;

    private CardLayout cardLayout;
    private JPanel contentPanel;

    public JavaTodoListApplication() {
        setTitle("Java TodoList");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);

        // Create content panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        add(contentPanel);

        // Panel for user input
        JPanel homePanel = new JPanel();

        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(new JLabel("Todo List Menu:"));
        verticalBox.add(new JLabel("1. Add Task"));
        verticalBox.add(new JLabel("2. View Tasks"));
        verticalBox.add(new JLabel("3. Mark Task as Completed"));
        verticalBox.add(new JLabel("4. Delete Task"));
        verticalBox.add(new JLabel("5. View Priority based Tasks"));
        verticalBox.add(new JLabel("6. Exit"));

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(new JLabel("Enter your choice: "));
        JTextField inputField = new JTextField(10);
        horizontalBox.add(inputField);
        JButton submitButton = new JButton("Submit");
        horizontalBox.add(submitButton);

        verticalBox.add(horizontalBox);
        homePanel.add(verticalBox);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText();
                try {
                    int pageNumber = Integer.parseInt(userInput);
                    if (pageNumber >= 1 && pageNumber <= 6) {
                        switch (pageNumber) {
                            case 1:
                                showPage1();
                                break;
                            case 2:
                                showPage2();
                                break;
                            case 3:
                                showPage3();
                                break;
                            case 4:
                                showPage4();
                                break;
                            case 5:
                                showPage5();
                                break;
                            case 6:
                                System.exit(0);
                                break;
                        }
                        inputField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(JavaTodoListApplication.this, "Please enter a valid page number (1-3)!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JavaTodoListApplication.this, "Please enter a valid number!");
                }
            }
        });

        // Add pages to content panel
        contentPanel.add(homePanel, "userInput");

        // Show user input page by default
        cardLayout.show(contentPanel, "userInput");
    }

    //Add Task Panel=page1
    private void showPage1() {
        Box verticalBox1 = Box.createVerticalBox();
        verticalBox1.add(new JLabel("Page-Add Task"));
        Box horizontalBox1 = Box.createHorizontalBox();
        horizontalBox1.add(new JLabel("Enter task description: "));
        JTextField description = new JTextField(10);
        horizontalBox1.add(description);
        verticalBox1.add(horizontalBox1);
        Box horizontalBox2 = Box.createHorizontalBox();
        horizontalBox2.add(new JLabel("Enter task priority: "));
        JTextField importance = new JTextField(1);
        horizontalBox2.add(importance);
        verticalBox1.add(horizontalBox2);
        JButton submit1 = new JButton("Submit");
        verticalBox1.add(submit1);
        page1.add(verticalBox1);
        submit1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String description1 = description.getText();
                String importance1 = importance.getText();

                if (!description1.isEmpty() && !importance1.isEmpty()) {
                    try {
                        Task newTask = new Task(description1, Integer.parseInt(importance1));
                        if (todolist.addTask(newTask)) {
                            verticalBox1.add(new JLabel("Task Added Successfully!"));
                            verticalBox1.add(new JLabel("Please wait for 4seconds."));
                        } else {
                            verticalBox1.add(new JLabel("Error while saving"));
                        }
                        importance.setText("");
                        description.setText("");
                        revalidate();
                        repaint();

                        // Create and start the timer
                        Timer timer = new Timer(4000, new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                cardLayout.show(contentPanel, "userInput");
                                ((Timer) e.getSource()).stop();
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(JavaTodoListApplication.this, "Importance must be an integer!");
                    }
                } else {
                    JOptionPane.showMessageDialog(JavaTodoListApplication.this, "Description and importance cannot be empty!");
                }
            }
        });
        contentPanel.add(page1, "page1");
        cardLayout.show(contentPanel, "page1");
    }

    //View Tasks Panel=page2
    private void showPage2() {
        Box vertical2 = Box.createVerticalBox();
        vertical2.add(new JLabel("Page-View tasks"));
//        page2.add(vertical22);
//        Box vertical2 = Box.createVerticalBox();
        vertical2.add(todolist.showAllTasks());
        vertical2.add(new JLabel("Please wait for 10seconds."));
        page2.add(vertical2);
        revalidate();
        repaint();
        Timer timer = new Timer(10000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "userInput");
                ((Timer) e.getSource()).stop();
            }
        });
        timer.setRepeats(false);
        timer.start();
        contentPanel.add(page2, "page2");
        cardLayout.show(contentPanel, "page2");
    }

    //mark as completed=page3
    private void showPage3() {
        Box vertical3 = Box.createVerticalBox();
        vertical3.add(new JLabel("Page-Mark task as completed"));
        Box horizontal3 = Box.createHorizontalBox();
        horizontal3.add(new JLabel("enter the index:"));
        JTextField index3 = new JTextField(5);
        horizontal3.add(index3);
        JButton submit3 = new JButton("Submit");
        horizontal3.add(submit3);
        vertical3.add(horizontal3);
//        Box vertical3 = Box.createVerticalBox();
        vertical3.add(todolist.showAllTasks());
//        page3.add(vertical3);
        page3.add(vertical3);
        revalidate();
        repaint();
        submit3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String index33 = index3.getText();
                int indexInt3 = (Integer.parseInt(index33));
                if ("false".equals(todolist.markTaskAsCompleted(indexInt3 - 1))) {
                    page3.add(new JLabel("error found!"));
                } else {
                    page3.add(new JLabel("Task marked as completed: " + todolist.markTaskAsCompleted(Integer.parseInt(index33))));
                    page3.add(new JLabel("Please wait for 4seconds."));

                }
                index3.setText("");
                revalidate();
                repaint();
                Timer timer = new Timer(4000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(contentPanel, "userInput");
                        ((Timer) e.getSource()).stop();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
        );
        contentPanel.add(page3, "page3");
        cardLayout.show(contentPanel, "page3");
    }

    //delete tawsk-page
    private void showPage4() {
        Box vertical4 = Box.createVerticalBox();
        vertical4.add(
                new JLabel("Page-Delete Task"));
        Box horizontal4 = Box.createHorizontalBox();
        horizontal4.add(new JLabel("Enter task index to delete: "));
        JTextField index4 = new JTextField(5);
        horizontal4.add(index4);
        JButton submit4 = new JButton("Submit");
        horizontal4.add(submit4);
        vertical4.add(horizontal4);
//        page4.add(vertical44);
//        Box vertical4 = Box.createVerticalBox();
        vertical4.add(todolist.showAllTasks());
        page4.add(vertical4);
        revalidate();
        repaint();
        submit4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String index44 = index4.getText();
                int indexInt4 = Integer.parseInt(index44);
                if ("false".equals(todolist.deleteTask(indexInt4 - 1))) {
                    page4.add(new JLabel("error found!"));
                } else {
                    page4.add(new JLabel("Task Removed:" + todolist.deleteTask(Integer.parseInt(index44))));
                    page4.add(new JLabel("Please wait for 4seconds."));

                }
                index4.setText("");
                revalidate();
                repaint();
                Timer timer = new Timer(4000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(contentPanel, "userInput");
                        ((Timer) e.getSource()).stop();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
        );
        contentPanel.add(page4, "page4");
        cardLayout.show(contentPanel, "page4");
    }

    //View priority based task
    private void showPage5() {
        Box vertical5 = Box.createVerticalBox();
        vertical5.add(
                new JLabel("Page-View priority based task"));
        Box horizontal5 = Box.createHorizontalBox();
        horizontal5.add(new JLabel("Enter priority of the desired Tasks: "));
        JTextField index5 = new JTextField(5);
        horizontal5.add(index5);
        JButton submit5 = new JButton("Submit");
        horizontal5.add(submit5);
        vertical5.add(horizontal5);
        page5.add(vertical5);
        submit5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                showPriorityTask;
                String index55 = index5.getText();
                if (todolist.showPriorityTask(Integer.parseInt(index55)).size() > 0) {
                    Box vertical55 = Box.createVerticalBox();
                    for (int i = 0; i < todolist.showPriorityTask(Integer.parseInt(index55)).size(); i++) {
                        Task task = todolist.showPriorityTask(Integer.parseInt(index55)).get(i);
                        JLabel taskLabel = new JLabel((i + 1) + "Task Description:" + task.getDescription() + "-Priority:" + task.getPriority()
                                + "- Completed:" + task.isCompleted());
                        vertical55.add(taskLabel);
                    }
                    page5.add(vertical55);
                } else if (todolist.showPriorityTask(Integer.parseInt(index55)).size() == 0) {
                    page5.add(new JLabel("Zero task with this priority."));
                }
                index5.setText("");
                revalidate();
                repaint();
                Timer timer = new Timer(4000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(contentPanel, "userInput");
                        ((Timer) e.getSource()).stop();
                    }
                });
                timer.setRepeats(false);
                timer.start();

            }
        }
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JavaTodoListApplication().setVisible(true);
            }
        });
    }
}
