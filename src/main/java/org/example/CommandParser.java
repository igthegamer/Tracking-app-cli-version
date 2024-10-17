package org.example;

import java.util.List;
import java.util.Scanner;

public class CommandParser {

    public static void main(String[] args) {
        TaskTracker taskTracker = new TaskTracker("tasks.json");
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the Task Tracker CLI!");
        System.out.println("Type 'help' for a list of available commands.");

        while (true) {
            System.out.print("\nEnter a command: ");
            command = scanner.nextLine().trim();


            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting Task Tracker...");
                break;
            }

            switch (command) {
                case "add":
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine().trim();
                    taskTracker.addTask(description);
                    System.out.println("Task added: " + description);
                    break;

                case "update":
                    System.out.print("Enter task ID: ");
                    int id = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Enter new status (not done, in progress, done): ");
                    String status = scanner.nextLine().trim();
                    taskTracker.updateTask(id, status);
                    System.out.println("Task " + id + " updated to: " + status);
                    break;

                case "delete":
                    System.out.print("Enter task ID to delete: ");
                    id = Integer.parseInt(scanner.nextLine().trim());
                    taskTracker.deleteTask(id);
                    System.out.println("Task " + id + " deleted.");
                    break;

                case "list":
                    List<Task> tasks = taskTracker.getTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks available.");
                    } else {
                        for (Task task : tasks) {
                            System.out.println(task);
                        }
                    }
                    break;

                case "list-done":
                    List<Task> doneTasks = taskTracker.getTasksByStatus("done");
                    if (doneTasks.isEmpty()) {
                        System.out.println("No tasks are done.");
                    } else {
                        for (Task task : doneTasks) {
                            System.out.println(task);
                        }
                    }
                    break;

                case "list-not-done":
                    List<Task> notDoneTasks = taskTracker.getTasksByStatus("not done");
                    if (notDoneTasks.isEmpty()) {
                        System.out.println("All tasks are done or in progress.");
                    } else {
                        for (Task task : notDoneTasks) {
                            System.out.println(task);
                        }
                    }
                    break;

                case "list-in-progress":
                    List<Task> inProgressTasks = taskTracker.getTasksByStatus("in progress");
                    if (inProgressTasks.isEmpty()) {
                        System.out.println("No tasks are in progress.");
                    } else {
                        for (Task task : inProgressTasks) {
                            System.out.println(task);
                        }
                    }
                    break;

                case "help":
                    System.out.println("Available commands:");
                    System.out.println("add - Add a new task");
                    System.out.println("update - Update a task's status");
                    System.out.println("delete - Delete a task");
                    System.out.println("list - List all tasks");
                    System.out.println("list-done - List tasks marked as done");
                    System.out.println("list-not-done - List tasks not done");
                    System.out.println("list-in-progress - List tasks in progress");
                    System.out.println("exit - Exit the application");
                    break;

                default:
                    System.out.println("Unknown command. Type 'help' for a list of available commands.");
            }
        }

        scanner.close();
    }
}
