package ibf2022.paf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;

// TODO: Task 6
@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate jTemplate;

    private static final String INSERT_TASK_SQL = """
            insert into task (user_id, description, priority, due_date)
            values (?, ?, ?, ?)
                """;

    // Method that creates a task according to the userId. Returns true 
    // if created successfully, false if not
    public Boolean insertTask(Task task) {

        int isCreated = jTemplate.update(INSERT_TASK_SQL, task.getUserId(),
                task.getDescription(), task.getPriority(), task.getDueDate());

        return isCreated > 0;
    }
}
