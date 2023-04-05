package ibf2022.paf.assessment.server.Payload;

import java.sql.Date;

public class TaskRequest {
    
    private String username;
    private String description;
    private int priority;
    private Date dueDate;
    
    public TaskRequest() {
    }

    public TaskRequest(String username, String description, int priority, Date dueDate) {
        this.username = username;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
        return "TaskRequest [username=" + username + ", description=" + description + ", priority=" + priority
                + ", dueDate=" + dueDate + "]";
    }

    
}
