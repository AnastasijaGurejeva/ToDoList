package ToDoList;

import java.time.LocalDate;
import java.util.Map;

public class CreateTaskMenu extends MenuSuperClass {

    private Map<String, Object> inputData = getInputData();

    public CreateTaskMenu() {
        super("CREATE NEW TASK", "",
                "");

    }

    @Override
    public void display() {
        super.printUserInterface();
        System.out.println("Please follow instructions below:\n");
    }

    @Override
    public void readInput() {

        System.out.println("Please enter a Title of your task");
        String taskTitle = readStringInput();

        System.out.println("Please enter a description of your task. \n" +
                "(Press enter to leave it empty. You can always add edit later)");
        String taskDetails = readStringInput();

        System.out.println("Please enter a due date of your task in format: (d/MM/yyyy)");
        LocalDate taskDueDate = readDateInput();


        inputData.put("newTaskTitle" , taskTitle);
        inputData.put("newTaskDetails" , taskDetails);
        inputData.put("newDueDate" , taskDueDate);
        setChanged();
        notifyObservers(inputData);
    }

    public void editingTaskTitle() {
        System.out.println("Please enter a new Title of your task");
        String editedTaskTitle = readStringInput();

        inputData.put("editedTaskTitle" , editedTaskTitle);
        setChanged();
        notifyObservers(inputData);
    }

    public void editingTaskDetails() {
        System.out.println("Please enter a new description of your task");
        String editedTaskDetails = readStringInput();

        inputData.put("editedTaskDetails" , editedTaskDetails);
        setChanged();
        notifyObservers(inputData);
    }

    public void editingTaskDueDate() {
        System.out.println("Please enter a new due date of your task in format: (d/MM/yyyy)");
        String editedTaskDueDate = readStringInput();

        inputData.put("editedTaskDueSate" , editedTaskDueDate);
        setChanged();
        notifyObservers(inputData);
    }


}
