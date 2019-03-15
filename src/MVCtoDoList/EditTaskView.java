/**
 * This view class displays an instruction to edit an existing task.
 * It allows user to select a task from a task list by typing task's ID.
 * It gives user an option to edit title/detail/dueDate
 * Then instruction is displayed to enter e new title/details/dueDate
 * Entered data is validated and together with a selected task is passed to a controller which completes the edit operation.
 * */

package MVCtoDoList;

import ToDoList.ToDoTask;

import java.time.LocalDate;

public class EditTaskView extends abstractView {

    private ToDoTask selectedTaskToEdit;
    private String editedTaskTitle;
    private String editedTaskDetails;
    private LocalDate editedTaskDueDate;


    public EditTaskView() {
        super("\t\t\tTASK EDIT MENU",
                "Please select the task you want to edit by typing task's No ");
    }

    @Override
    public void display() {
        super.printUserInterface();
        passedDataManager.printList();
    }


    private void displaySubMenu() {
        System.out.println("\n Press:" +
                "\n 0 - To edit task's title" +
                "\n 1 - To edit task's due date" +
                "\n 2 - To edit task's details");
    }

    @Override
    public void readInput() {
        selectedTaskToEdit = selectTask();
        displaySubMenu();

        int menuChoice = readIntegerInput();
        switch (menuChoice) {
            case 0:
                System.out.println("Please enter a new Title of your task");
                scanner.nextLine();
                editedTaskTitle = scanner.nextLine();
                break;
            case 1:
                System.out.println("Please enter a new due date of your task in format: (d/MM/yyyy)");
                scanner.nextLine();
                editedTaskDueDate = readDateInput();
                sendInput1();
                break;
            case 2:
                System.out.println("Please enter a new details of your task");
                scanner.nextLine();
                editedTaskDetails = scanner.nextLine();
                sendInput2();
                break;
        }
    }

    public void sendInput() {
        inputData.put("operationType", 3);
        inputData.put("selectedTaskToEdit", selectedTaskToEdit);
        inputData.put("editedTaskTitle", editedTaskTitle);
        setChanged();
        notifyObservers(inputData);

    }

    private void sendInput1() {
        inputData.put("operationType", 4);
        inputData.put("selectedTaskToEdit", selectedTaskToEdit);
        inputData.put("editedTaskDueDate", editedTaskDueDate);
        setChanged();
        notifyObservers(inputData);

    }

    private void sendInput2() {
        inputData.put("operationType", 5);
        inputData.put("selectedTaskToEdit", selectedTaskToEdit);
        inputData.put("editedTaskDetails", editedTaskDetails);
        setChanged();
        notifyObservers(inputData);
    }
}



