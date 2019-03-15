/**
 * This view class displays instruction to delete an existing project.
 * It allows user to select a project from a project list by typing an existing project name.
 * It checks if input matches existing projects name.
 * It gives user an option to just delete a project or delete it with all content (all tasks inside).
 * Selected project name together with a selected option is then passed to a controller which completes the delete operation.
 * */

package MVCtoDoList;

public class DeleteProjectView extends abstractView {
    private String selectedProject;

    public DeleteProjectView() {
        super("\t\t\tLIST OF YOUR PROJECTS",
                "Please enter the name of the existing Project:");
    }

    @Override
    public void display() {
        super.printUserInterface();
        passedDataManager.getAllProjects().keySet().stream()
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
            if (passedDataManager.getAllProjects().containsKey(inputProjectName)) {
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
        inputData.put("operationType", 10);
        inputData.put("selectedProjectToDelete", selectedProject);
        setChanged();
        notifyObservers(inputData);
    }

    private void sendInput1() {
        inputData.put("operationType", 11);
        inputData.put("selectedProjectToDelete", selectedProject);
        setChanged();
        notifyObservers(inputData);
    }
}



