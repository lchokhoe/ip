package duke.Commands;

import duke.*;

public class MarkCommand extends Commands {

    private int index;

    public MarkCommand(DukeHistory history, String[] userInput, DukeUi ui) {
        super(history, userInput, ui);
    }

    @Override
    public String validateAndExecute() {
        try {
            validate();
            return execute();
        } catch (NumberFormatException | IndexOutOfBoundsException ex1) {
            return ui.printInvalidArgumentError();
        } catch (DukeException ex2) {
            return ui.printFailedAccess();
        }
    }

    @Override
    public void validate() throws IndexOutOfBoundsException {
        this.index = Integer.parseInt(userInput[1]) - 1;
        if (this.index < 0 || this.index > history.getSize() - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * A method that, when called, checks the validity of a given entry index before getting the inputted instance
     * of DukeHistory to mark that entry in its records as done.
     *
     * @return Mark response.
     */
    @Override
    public String execute() throws DukeException {
        history.getTask(index).isMarked();
        StringBuilder tasking = accessHistory();
        return "_______________________________________________________\n"
                + "Well done! You have completed the task:\n"
                + "    " + tasking.toString()
                + "_______________________________________________________\n";

    }

    public StringBuilder accessHistory() throws DukeException {
        Task currTask = history.getTask(this.index);
        if (currTask instanceof ToDos) {
            ToDos temp = (ToDos) currTask;
            return new StringBuilder(temp.getToDo());
        } else if (currTask instanceof Deadlines) {
            Deadlines temp = (Deadlines) currTask;
            return new StringBuilder(temp.getDeadline());
        } else if (currTask instanceof Event) {
            Event temp = (Event) currTask;
            return new StringBuilder(temp.getEvent());
        } else {
            throw new DukeException("Failed to access history!");
        }
    }
}