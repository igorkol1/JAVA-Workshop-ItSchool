package Domain;

import java.time.LocalDateTime;

public class Homework {

    private int id;
    private int groupId;
    private int exerciseId;
    private boolean isActive;
    LocalDateTime created;

    public Homework() {
    }

    public Homework(int groupId, int exerciseId, boolean isActive, LocalDateTime created) {
        this.groupId = groupId;
        this.exerciseId = exerciseId;
        this.isActive = isActive;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", groupId=" + groupId +
                ", exerciseId=" + exerciseId +
                ", isActive=" + isActive +
                ", created=" + created;
    }
}
