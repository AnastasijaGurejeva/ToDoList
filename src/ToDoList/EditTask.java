package ToDoList;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class EditTask extends MenuSuperClass {


    private Library lib;
    private Map<String, Object> inputData = getInputData();

    public EditTask() {
        super("TASK MENU", "Welcome! Here you can edit a task",
                "Please select the task you want to edit by typing task's No ");
    }

    public void getLibrary(Library library) {
        lib = library;
    }


    @Override
    public void display() {
        super.printUserInterface();
        lib.printList();

    }

    @Override
    public void readInput() {
        HashMap<Integer, ToDoTask> allTasks = lib.getAllTasks();
        ToDoTask selectedTaskToEdit;
        Integer id;
        while (true) {
            id = readIntegerInput();
            if (allTasks.containsKey(id)) {
                selectedTaskToEdit = allTasks.get(id);
                break;
            } else {
                System.out.println("Please enter a valid number of Selected task");
            }
        }

        System.out.println("\n Press:" +
                "\n 0 - To edit task's title" +
                "\n 1 - To edit task's due date" +
                "\n 2 - To edit task's details");

        Map<String, Object> inputData = getInputData();
        int menuChoice = readIntegerInput();
        switch (menuChoice) {
            case 0:
                System.out.println("Please enter a new Title of your task");
                String editedTaskTitle = readStringInput();
                inputData.put("selectedTaskToEdit" , selectedTaskToEdit);
                inputData.put("editedTaskTitle", editedTaskTitle);
                inputData.put("menuType" , 3);
                setChanged();
                notifyObservers(inputData);
                break;
            case 1:
                System.out.println("Please enter a new due date of your task in format: (d/MM/yyyy)");
                LocalDate editedTaskDueDate = readDateInput();
                inputData.put("selectedTaskToEdit" , selectedTaskToEdit);
                inputData.put("editedTaskDueDate", editedTaskDueDate);
                inputData.put("menuType" , 4);
                setChanged();
                notifyObservers(inputData);
                break;
            case 2:
                System.out.println("Please enter a new details of your task");
                String editedTaskDetails = readStringInput();
                inputData.put("selectedTaskToEdit" , selectedTaskToEdit);
                inputData.put("editedTaskDetails", editedTaskDetails);
                inputData.put("menuType" , 5);
                setChanged();
                notifyObservers(inputData);
                break;
        }

    }

}



