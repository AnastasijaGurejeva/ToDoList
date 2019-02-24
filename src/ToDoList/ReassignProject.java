package ToDoList;
import java.util.*;

public class ReassignProject extends MenuSuperClass {

    private Library lib;
    private Scanner scanner = new Scanner(System.in);

    public ReassignProject() {
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
        Map<String, Object> inputData = getInputData();
        inputData.put("menuType" , 9);

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

        HashMap<String, Project> allProjects = lib.getAllProjects();
        Set<String> keys = allProjects.keySet();
        System.out.println("Please enter the existing Project's name this task belonged to: ");
        String projectName;
        while (true) {
            projectName = readStringInput();
            if (keys.contains(projectName)) {
                inputData.put("oldProjectName" , projectName);
                break;
            } else {
                System.out.println("Project doesn't exist. " +
                        "\nPlease enter the existing Project's name this task belonged to: ");
            }

                System.out.println("Please enter the new Project's name for this task: ");
                String newProjectName = readStringInput();


                inputData.put("selectedTaskToReassign" , selectedTask);
                inputData.put("newProjectName" , newProjectName);
                setChanged();
                notifyObservers(inputData);
            }
        }
}

