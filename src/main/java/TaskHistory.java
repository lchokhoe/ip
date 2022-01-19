import java.lang.StringBuilder; // Imported StringBuilder class
import static java.lang.System.exit; // Imported System.exit

public class TaskHistory {
    private final Task[] record = new Task[100]; // ArrayList of size 100 by default
    private int taskCounter = 0;

    public TaskHistory() { //Empty Constructor
    }

    void addToDo(String description) {
        ToDos tempToDo = new ToDos(description);
        record[taskCounter] = tempToDo;
        taskCounter++;
        String msg = "_______________________________________________________\n"
                + "Understood, adding this task now:\n"
                + "    " + tempToDo.getToDo()
                + "Currently you have " + taskCounter + " tasks in our records.\n"
                + "_______________________________________________________\n";
        System.out.println(msg);
    }

    void addDeadline(String description, String timeFrame) {

    }

    String printAll() {
        int count = 1;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskCounter; i++) {
            Task nextTask = record[i];
            if (nextTask instanceof ToDos) {
                ToDos temp = (ToDos) nextTask;
                result.append(count).append(".").append(temp.getToDo());
            } else if (nextTask instanceof Deadlines) {
                Deadlines temp = (Deadlines) nextTask;
                result.append(count).append(".").append(temp.getDeadline());
            } else if (nextTask instanceof Event) {
                Event temp = (Event) nextTask;
                result.append(count).append(".").append(temp.getEvent());
            } else {
                System.out.println("Error occured while processing " + nextTask.getTask()); // Temporary error handler
                exit(1);
            }
            count++;
        }
        if (result.length() == 0) {
            return "There has been no recorded user input!\n";
        } else {
            return result.toString();
        }
    }

    Task getTask(int index) {
        return record[index];
    }
}
