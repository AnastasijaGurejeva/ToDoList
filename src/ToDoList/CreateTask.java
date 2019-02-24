package ToDoList;

import java.time.LocalDate;
import java.util.Map;

public class CreateTask extends MenuSuperClass {

    private Map<String, Object> inputData = getInputData();

    public CreateTask() {
        super("CREATE A NEW TASK", "",
                "");

    }

    @Override
    public void display() {
        super.printUserInterface();
        System.out.println("Please follow instructions below:\n");
    }

    @Override
    public void readInput() {
        inputData.put("menuType" , 1);


        System.out.println("Please enter a Title of your task");
        String taskTitle = readStringInput();

        System.out.println("Please enter a description of your task. \n" +
                "(Press press space and enter to leave it empty. You can always edit it later)");
        String taskDetails = readStringInput();

        System.out.println("Please enter a due date of your task in format: (d/MM/yyyy)");
        LocalDate taskDueDate = readDateInput();


        inputData.put("newTaskTitle" , taskTitle);
        inputData.put("newTaskDetails" , taskDetails);
        inputData.put("newDueDate" , taskDueDate);
        setChanged();
        notifyObservers(inputData);
    }


}
