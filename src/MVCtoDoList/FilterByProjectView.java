/**
 * This view class displays instruction to filter by existing project.
 * It allows user to select a project from a project list by typing an existing project name.
 * It checks if input matches existing projects name.
 * Selected project name is then passed to a controller which completes sort operation.
 * */

package MVCtoDoList;

public class FilterByProjectView extends abstractView {
    private String selectedProjectName;

    public FilterByProjectView() {
        super("\t\t\tLIST OF YOUR PROJECTS",
                "Please enter the name of the existing Project:");
    }

    @Override
    public void display() {
        super.printUserInterface();
        passedDataManager.printProjectList();
    }

    @Override
    public void readInput() {
        while (true) {
            String inputProjectName = scanner.nextLine();
            if (passedDataManager.getAllProjects().containsKey(inputProjectName)) {
                selectedProjectName = inputProjectName;
                break;
            } else {
                System.out.println("Project doesn't exist. " +
                        "\nPlease enter the existing Project's name ");
            }
        }
    }

    @Override
    public void sendInput() {
        inputData.put("operationType", 8);
        inputData.put("selectedProjectName", selectedProjectName);
        setChanged();
        notifyObservers(inputData);
    }
}


