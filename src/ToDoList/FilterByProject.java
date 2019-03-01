package ToDoList;

public class FilterByProject extends ViewSuperClass {
    private String selectedProject;

    public FilterByProject() {
        super("LIST OF YOUR PROJECTS",
                "Please enter the name of the existing Project:");
    }

    @Override
    public void display() {
        super.printUserInterface();
        passedLibraryData.getAllProjects().keySet().stream()
                .forEach(k -> System.out.println(k.toUpperCase()));

    }

    @Override
    public void readInput() {
        while (true) {
            String inputProjectName = scanner.nextLine();
            if (passedLibraryData.getAllProjects().containsKey(inputProjectName)) {
                selectedProject = inputProjectName;
                break;
            } else {
                System.out.println("Project doesn't exist. " +
                        "\nPlease enter the existing Project's name ");
            }
        }
    }

    @Override
    public void sendInput() {
        inputData.put("menuType", 8);
        inputData.put("selectedProject", selectedProject);
        setChanged();
        notifyObservers(inputData);
    }

    @Override
    public void notification() {
        System.out.println("Your tasks displayed by selected project: " + selectedProject);
    }
}


