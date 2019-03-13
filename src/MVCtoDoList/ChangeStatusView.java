/**
 * This view class displays an instruction to change task status.
 * It allows user to select a task from printed task list by typing task's ID.
 * Selected task is then passed to a controller which completes the change status operation.
 * */

package MVCtoDoList;

import ToDoList.ToDoTask;

public class ChangeStatusView extends abstractView {

    private ToDoTask selectedTask;

    public ChangeStatusView() {
        super("\t\t\tLIST OF YOUR TASKS",
                "\nPlease select the task you want to mark as done" +
                        "by typing task's No ");
    }

    @Override
    public void display() {
        super.printUserInterface();
        passedDataManager.printList();
    }

    @Override
    public void readInput() {
       selectedTask = selectTask();
    }

    @Override
    public void sendInput() {
        inputData.put("operationType", 6);
        inputData.put("selectedTaskToChange", selectedTask);
        setChanged();
        notifyObservers(inputData);
    }

}

