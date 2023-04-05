package ibf2022.paf.assessment.server.models;

import java.sql.Date;

// TODO: Task 4

public class Task {

    private int taskId;
    private String userId;
    private String description;
    private int priority;
    private Date dueDate;

    public Task() {
    }

    public Task(int taskId, String userId, String description, int priority, Date dueDate) {
        this.taskId = taskId;
        this.userId = userId;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public int getTaskId() {
        return taskId;
    }
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Task [taskId=" + taskId + ", userId=" + userId + ", description=" + description + ", priority="
                + priority + ", dueDate=" + dueDate + "]";
    }

}
