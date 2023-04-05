package ibf2022.paf.assessment.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.assessment.server.Exceptions.InvalidUsernameException;
import ibf2022.paf.assessment.server.Exceptions.TaskInsertFailedException;
import ibf2022.paf.assessment.server.Exceptions.UserInsertFailedException;
import ibf2022.paf.assessment.server.Payload.TaskRequest;
import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 7
@Service
public class TodoService {

    @Autowired
    private UserRepository uRepo;

    @Autowired
    private TaskRepository tRepo;

    @Transactional
    public List<Task> upsertTask(String username, List<TaskRequest> taskRequests) {

        // Validate username (only letters and digits allowed)
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if (!pattern.matcher(username).matches()) {
            throw new InvalidUsernameException(
                    "Invalid username. Username should not contain spaces or special characters.");
        }

        // 1) checks if the username exists
        Optional<User> userOpt = uRepo.findUserByUsername(username);
        String userId;

        // Checking if userOpt is empty. Created new user if yes
        if (userOpt.isEmpty()) {
            // Creates a new user
            User newUser = new User();
            newUser.setUserId(generateUserId()); // generate UUID String
            newUser.setUsername(username);
            newUser.setName(capitalizeFirstLetter(username)); // Generates the name from username
            userId = uRepo.insertUser(newUser); // carries the user id

            if (null == userId) {
                throw new UserInsertFailedException("Failed to insert user: " + username);
            }

        } else {
            userId = userOpt.get().getUserId();
        }

        // Insert tasks
        List<Task> tasks = new ArrayList<>();
        for (TaskRequest taskRequest : taskRequests) {

            Task task = new Task();
            task.setUserId(userId);
            task.setDescription(taskRequest.getDescription());
            task.setPriority(taskRequest.getPriority());
            task.setDueDate(taskRequest.getDueDate());
            boolean isInserted = tRepo.insertTask(task);

            if (!isInserted) {
                throw new TaskInsertFailedException("Failed to insert task for user: " + username);
            }

            tasks.add(task);
        }

        return tasks;
    }

    // Helper method to generate UUID
    private String generateUserId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }

    // Helper method to capitalize the first letter of username for name
    // Think it is correct because of the trend
    private String capitalizeFirstLetter(String username) {
        if (username == null || username.length() == 0) {
            return username;
        }
        return username.substring(0, 1).toUpperCase() + username.substring(1);
    }
}
