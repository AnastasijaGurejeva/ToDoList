package ToDoList;

import java.time.LocalDate;

public class CreateTask extends ViewSuperClass {
    private String taskTitle;
    private String taskDetails;
    private LocalDate taskDueDate;


    public CreateTask() {
        super("CREATE A NEW TASK",
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
        taskTitle = readStringInput();

        System.out.println("Please enter a details of your task");
        taskDetails = readStringInput();

        System.out.println("Please enter a due date of your task in format: (d/MM/yyyy)");
        taskDueDate = readDateInput();

    }

    @Override
    public void sendInput() {
        inputData.put("menuType" , 1);
        inputData.put("newTaskTitle" , taskTitle);
        inputData.put("newTaskDetails" , taskDetails);
        inputData.put("newDueDate" , taskDueDate);
        setChanged();
        notifyObservers(inputData);
    }
}
