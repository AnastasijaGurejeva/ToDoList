package ToDoList;

import java.time.LocalDate;

public class EditTask extends ViewSuperClass {

    private ToDoTask selectedTaskToEdit;
    private String editedTaskTitle;
    private String editedTaskDetails;
    private LocalDate editedTaskDueDate;


    public EditTask() {
        super("TASK MENU",
                "Please select the task you want to edit by typing task's No ");
    }

    @Override
    public void display() {
        super.printUserInterface();
        passedLibraryData.printList();
    }


    private void displaySubMenu() {
        System.out.println("\n Press:" +
                "\n 0 - To edit task's title" +
                "\n 1 - To edit task's due date" +
                "\n 2 - To edit task's details");
    }

    @Override
    public void readInput() {

        Integer id;
        while (true) {
            id = readIntegerInput();
            if (passedLibraryData.getAllTasks().containsKey(id)) {
                selectedTaskToEdit = passedLibraryData.getAllTasks().get(id);
                break;
            } else {
                System.out.println("Please enter a valid number of Selected task");
            }
        }

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
        inputData.put("menuType", 3);
        inputData.put("selectedTaskToEdit", selectedTaskToEdit);
        inputData.put("editedTaskTitle", editedTaskTitle);
        setChanged();
        notifyObservers(inputData);

    }

    private void sendInput1() {
        inputData.put("menuType", 4);
        inputData.put("selectedTaskToEdit", selectedTaskToEdit);
        inputData.put("editedTaskDueDate", editedTaskDueDate);
        setChanged();
        notifyObservers(inputData);

    }

    private void sendInput2() {
        inputData.put("menuType", 5);
        inputData.put("selectedTaskToEdit", selectedTaskToEdit);
        inputData.put("editedTaskDetails", editedTaskDetails);
        setChanged();
        notifyObservers(inputData);
    }

    @Override
    public void notification() {
        System.out.println("Your task was edited");
    }

}



