package ibf2022.paf.assessment.server.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.paf.assessment.server.Payload.TaskRequest;
import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4, Task 8
@Controller
public class TasksController {

    @Autowired
    private TodoService todoService;

    @PostMapping(path = "/task", produces = "application/json", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<?> save(
            @RequestParam("username") String username,
            @RequestParam Map<String, String> formData) {

        // Create a list because can have multiple task objects
        List<TaskRequest> taskRequests = new ArrayList<>();

        // To match the naming of Description-0/1/2 etc. and store it in
        // a list of TaskRequest
        int index = 0;

        while (formData.containsKey("description-" + index)) {
            
            String description = formData.get("description-" + index);
            int priority = Integer.parseInt(formData.get("priority-" + index));
            Date dueDate = Date.valueOf(formData.get("dueDate-" + index));

            TaskRequest taskRequest = new TaskRequest();
            taskRequest.setUsername(username);
            taskRequest.setDescription(description);
            taskRequest.setPriority(priority);
            taskRequest.setDueDate(dueDate);

            taskRequests.add(taskRequest);
            index++;
        }

        // Call the upsertTask method from the ToDoService to insert tasks
        List<Task> tasks = todoService.upsertTask(username, taskRequests);

        return ResponseEntity.ok().body(tasks);
    }

}
