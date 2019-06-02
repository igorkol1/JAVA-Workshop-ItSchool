package Domain;

import java.time.LocalDateTime;

public class Solution {

    int id;
    LocalDateTime created;
    LocalDateTime updated;
    String description;
    int homework_id;
    int user_id;

    public Solution() {
    }

    public Solution(LocalDateTime created, LocalDateTime updated, String description, int homework_id, int user_id) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.homework_id = homework_id;
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

    public int getHomework_id() {
        return homework_id;
    }

    public void setHomework_id(int homework_id) {
        this.homework_id = homework_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Solution: " +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", description='" + description + '\'' +
                ", homework_id=" + homework_id +
                ", user_id=" + user_id;
    }
}
