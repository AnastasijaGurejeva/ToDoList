package ToDoList;

public class ReassignProject extends ViewSuperClass {
    private String projectName;
    private ToDoTask selectedTask;
    private String newProjectName;

    public ReassignProject() {
        super("LIST OF YOUR PROJECTS",
                "Please enter the name of existing Project from the list below: \n");
    }


    @Override
    public void display() {
        super.printUserInterface();
        passedLibraryData.getAllProjects().keySet().stream()
                .forEach(k -> System.out.println(k));
    }

    public void displayTasksByProject() {
        if (!passedLibraryData.getAllProjects().isEmpty()) {
            System.out.println("Please select the task you want to reassign by typing task's No ");
            passedLibraryData.sortByProject(projectName);
        } else {
            System.out.println("This project is Empty");
            display();
        }
    }

    public void displayAdditionalInstruction() {
        System.out.println("Please enter the new Project's name for this task: ");
    }


    @Override
    public void readInput() {

        while (true) {
            String inputProjectName = readStringInput();
            if (passedLibraryData.getAllProjects().containsKey(inputProjectName)) {
                this.projectName = inputProjectName;
                break;
            } else {
                System.out.println("Project doesn't exist. " +
                        "\nPlease enter the existing Project's name this task belonged to: ");
            }
        }


        displayTasksByProject();

        Integer id;
        while (true) {
            id = readIntegerInput();
            if (passedLibraryData.getAllTasks().containsKey(id)) {
                this.selectedTask = passedLibraryData.getAllTasks().get(id);
                break;
            } else {
                System.out.println("Please enter a valid number of Selected task");
            }
        }

        displayAdditionalInstruction();
        newProjectName = readStringInput();

    }

    public void sendInput() {
        inputData.put("menuType", 9);
        inputData.put("oldProjectName", projectName);
        inputData.put("selectedTaskToReassign", selectedTask);
        inputData.put("newProjectName", newProjectName);
        setChanged();
        notifyObservers(inputData);
    }
}



