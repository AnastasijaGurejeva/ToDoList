/**
 * This view class displays an instruction to delete an existing task.
 * It allows user to select a task from a task list by typing a task's ID.
 * Selected task is then passed to a controller which completes the delete operation.
 * */

package MVCtoDoList;

import ToDoList.ToDoTask;

public class DeleteTaskView extends abstractView {

    private ToDoTask selectedTask;

    public DeleteTaskView() {
        super("\t\t\tLIST OF YOUR TASKS",
                "Please select the task you want to delete" +
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
        inputData.put("operationType", 7);
        inputData.put("selectedTaskToDelete", selectedTask);
        setChanged();
        notifyObservers(inputData);
    }
}


