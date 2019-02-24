package ToDoList;

import java.util.*;

public class AssignProject extends MenuSuperClass {

    private Library lib;
    private Scanner scanner = new Scanner(System.in);

    public AssignProject() {
        super("List of your tasks", "",
                "Please select the task by typing task's No ");
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
        ToDoTask selectedTask;
        Integer id;
        while (true) {
            id = readIntegerInput();
            if (allTasks.containsKey(id)) {
                selectedTask = allTasks.get(id);
                break;
            } else {
                System.out.println("Please enter a valid number of Selected task");
            }
        }

        System.out.println("Please enter the name of the Project for this task: ");
        String projectName = readStringInput();

        Map<String, Object> inputData = getInputData();
        inputData.put("menuType" , 2);
        inputData.put("selectedTask" , selectedTask);
        inputData.put("projectName" , projectName);
        setChanged();
        notifyObservers(inputData);
    }
}
