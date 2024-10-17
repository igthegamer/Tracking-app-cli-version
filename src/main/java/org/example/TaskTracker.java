package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.json.*;
public class TaskTracker {
    private List<Task> tasks;
    private String jsonFilePath;
    public TaskTracker(String jsonFilePath){
        this.jsonFilePath=jsonFilePath;
        tasks = new ArrayList<>();
        loadTasks();
    }

    public void addTask(String description){
        int id = tasks.size()+1;
        Task task = new Task(id, description, "not done");
        tasks.add(task);
        saveTasks();

    }

    public  void updateTask(int id, String status){
       for (Task task : tasks){
           if (task.getId() == id){
               task.setStatus(status);
               break;
           }
       }
    }
    public void deleteTask(int id){
        tasks.removeIf(task -> task.getId() ==id);
        saveTasks();
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public List<Task> getTasksByStatus(String status){
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks){
            if (task.getStatus().equals(status)){
                filteredTasks.add(task);

            }
        }
        return filteredTasks;
    }
    public void loadTasks(){
        try{
            File jsonFile = new File(jsonFilePath);
            if (!jsonFile.exists()){
                jsonFile.createNewFile();
                return;
            }

        String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        if (content.isEmpty()) return;

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonArray jsonArray = reader.readArray();
        tasks.clear();
        for (JsonValue value : jsonArray) {
            JsonObject obj = value.asJsonObject();
            Task task = new Task(
                    obj.getInt("id"),
                    obj.getString("description"),
                    obj.getString("status")
            );
            tasks.add(task); // Add the task to the list
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    public void saveTasks() {
        try (FileWriter fileWriter = new FileWriter(jsonFilePath)) {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Task task : tasks) {
                JsonObjectBuilder taskObject = Json.createObjectBuilder()
                        .add("id", task.getId())
                        .add("description", task.getDescription())
                        .add("status", task.getStatus());
                arrayBuilder.add(taskObject);
            }
            JsonArray jsonArray = arrayBuilder.build();
            fileWriter.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }













}
