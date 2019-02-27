package ToDoList;

import java.util.*;

public class AssignProject extends ViewSuperClass {

    public AssignProject() {
        super("LIST OF YOUR TASKS",
                "Please select the task by typing task's No ");
    }


    @Override
    public void display() {
        super.printUserInterface();
        passedLibraryData.printList();
    }

    public void displayTasks() {
        System.out.println("LIST OF YOUR PROJECTS");
        System.out.println("Please enter the existing name of the Project for this task or " +
                "\n type in a new name of the project for your task: ");
        passedLibraryData.getAllProjects().keySet().stream()
                .forEach(k-> System.out.println(k));
    }


    @Override
    public void readInput() {

        HashMap<Integer, ToDoTask> allTasks = passedLibraryData.getAllTasks();
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

        displayTasks();
        String projectName = readStringInput();


        inputData.put("menuType" , 2);
        inputData.put("selectedTask" , selectedTask);
        inputData.put("projectName" , projectName);
        setChanged();
        notifyObservers(inputData);
    }
}
