/**
 * This view class displays an instruction to create a new task.
 * It reads and validates all input and then pass that input to a controller which completes the task create operation.
 * */

package MVCtoDoList;

import java.time.LocalDate;

public class CreateTaskView extends abstractView {
    private String taskTitle;
    private String taskDetails;
    private LocalDate taskDueDate;


    public CreateTaskView() {
        super("\t\t\tCREATE A NEW TASK",
                "");
    }

    @Override
    public void display() {
        super.printUserInterface();
        System.out.println("Please follow the instructions below:\n");
    }

    @Override
    public void readInput() {

        System.out.println("Please enter a Title of your task");
        taskTitle = scanner.nextLine();

        System.out.println("Please enter a details of your task");
        taskDetails = scanner.nextLine();

        System.out.println("Please enter a due date of your task in format: (d/MM/yyyy)");
        taskDueDate = readDateInput();

    }

    @Override
    public void sendInput() {
        inputData.put("operationType" , 1);
        inputData.put("newTaskTitle" , taskTitle);
        inputData.put("newTaskDetails" , taskDetails);
        inputData.put("newDueDate" , taskDueDate);
        setChanged();
        notifyObservers(inputData);
    }
}
