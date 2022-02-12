package duke.Commands;

import duke.DukeHistory;
import duke.DukeUi;
import duke.ToDos;

public class TodoCommand extends Commands {

    public TodoCommand(DukeHistory history, String[] userInput, DukeUi ui) {
        super(history, userInput, ui);
    }

    @Override
    public String validateAndExecute() {
        validate();
        return execute();
    }

    @Override
    public void validate() {
    }

    /**
     * A method that, when called, uses the provided String[] tokens to build a description for a ToDos Task.
     *
     * It then gets the inputted instance of DukeHistory to add a ToDos task entry into it's record
     * using the generated description.
     *
     * @return Todo_task response.
     */
    @Override
    public String execute() {
        String description = generateDescription();
        ToDos tempToDo = history.addToDo(description);
        return "_______________________________________________________\n"
                + "Understood, adding this task now:\n"
                + "    " + tempToDo.getToDo()
                + "Currently you have " + history.getSize() + " tasks in our records.\n"
                + "_______________________________________________________\n";
    }

    /**
     * A method that, when called, uses the provided String[] tokens to build a description for a ToDos Task.
     *
     * It then gets the inputted instance of DukeHistory to add a ToDos task entry into it's record
     * using the generated description.
     *
     * @return Todo_task response.
     */

    public String generateDescription() {
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < userInput.length; i++) {
            description.append(userInput[i]);
            if (i != (userInput.length - 1)) {
                description.append(" ");
            }
        }
        return description.toString();
    }
}