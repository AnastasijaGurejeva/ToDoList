package ToDoList;

public class DeleteProject extends ViewSuperClass {
    private String selectedProject;

    public DeleteProject() {
        super("LIST OF YOUR PROJECTS",
                "Please enter the name of the existing Project:");
    }

    @Override
    public void display() {
        super.printUserInterface();
        passedLibraryData.getAllProjects().keySet().stream()
                .forEach(k -> System.out.println(k.toUpperCase()));

    }

    private void additionalDisplay() {
        System.out.println(" \n Press:" +
                            "\n 0 - To delete just the project" +
                            "\n 1 - To delete the project and all tasks inside of this project" +
                            "(Please note, if the same task was assigned to a different project it will also be deleted)");
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

        additionalDisplay();

        int menuChoice = readIntegerInput();
        switch (menuChoice) {
            case 0:
                sendInput();
                break;
            case 1:
                sendInput1();
                break;
        }
    }

    @Override
    public void sendInput() {
        inputData.put("menuType", 10);
        inputData.put("selectedProjectToDelete", selectedProject);
        setChanged();
        notifyObservers(inputData);
    }

    private void sendInput1() {
        inputData.put("menuType", 11);
        inputData.put("selectedProjectToDelete", selectedProject);
        setChanged();
        notifyObservers(inputData);
    }

    @Override
    public void notification() {
        System.out.println("Your project was deleted");
    }
}



