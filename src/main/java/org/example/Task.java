package org.example;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class Task {
    private int id;
    private String description;
    private String status;

    public Task(int id, String description, String status){
        this.id=id;
        this.description=description;
        this.status=status;
    }
}
