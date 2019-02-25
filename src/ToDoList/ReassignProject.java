package ToDoList;

import java.util.*;

public class ReassignProject extends ViewSuperClass {

    private Library lib;
    private Scanner scanner = new Scanner(System.in);

    public ReassignProject() {
        super("LIST OF YOUR PROJECTS",
                "Please enter the name of existing Project from the list below: ");
    }

    public void getLibrary(Library library) {

        lib = library;
    }


    @Override
    public void display() {
        super.printUserInterface();
        lib.getAllProjects().keySet().stream()
                .forEach(k -> System.out.println(k));
    }


    @Override
    public void readInput() {
        Map<String, Object> inputData = getInputData();
        inputData.put("menuType", 9);

        Set<String> keys = lib.getAllProjects().keySet();
        String projectName;
        while (true) {
            projectName = readStringInput();
            if (keys.contains(projectName)) {
                inputData.put("oldProjectName", projectName);
                break;
            } else {
                System.out.println("Project doesn't exist. " +
                        "\nPlease enter the existing Project's name this task belonged to: ");
            }
        }

        System.out.println("Please select the task you want to reassign by typing task's No ");
        lib.printList();

        lib.sortByProject(projectName);

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


        System.out.println("Please enter the new Project's name for this task: ");
        String newProjectName = readStringInput();


        inputData.put("selectedTaskToReassign", selectedTask);
        inputData.put("newProjectName", newProjectName);
        setChanged();
        notifyObservers(inputData);
    }
}


