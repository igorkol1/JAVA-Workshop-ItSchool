package Domain;

import java.time.LocalDateTime;

public class Solution {

    int id;
    LocalDateTime created;
    LocalDateTime updated;
    String description;
    int excercise_id;
    int user_id;

    public Solution() {
    }

    public Solution(LocalDateTime created, LocalDateTime updated, String description, int excercise_id, int user_id) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.excercise_id = excercise_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExcercise_id() {
        return excercise_id;
    }

    public void setExcercise_id(int excercise_id) {
        this.excercise_id = excercise_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
