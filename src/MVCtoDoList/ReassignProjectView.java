/**
 * This view class displays an instruction to reassign the existing task to e new or another existing project.
 * It allows user to select a project from printed project list by typing a projects name.
 * Then user can select a task from the task list by typing task's ID (only tasks belonging to a selected project are displayed)
 * Finally user can select another existing project from the printed project list or type a new project name.
 * All input and choices are passed to a controller which completes the reassign operation.
 * */
package MVCtoDoList;

import ToDoList.ToDoTask;

public class ReassignProjectView extends abstractView {
    private String selectedProjectName;
    private ToDoTask selectedTask;
    private String newProjectName;

    public ReassignProjectView() {
        super("\t\t\tLIST OF YOUR PROJECTS",
                "Please enter the name of existing Project from the list below: \n");
    }


    @Override
    public void display() {
        super.printUserInterface();
        passedDataManager.printProjectList();
    }

    private void displayTasksByProject() {
        if (!passedDataManager.getAllProjects().isEmpty()) {
            System.out.println("Please select the task you want to reassign by typing task's No ");
            passedDataManager.sortByProject(selectedProjectName);
        } else {
            System.out.println("This project is Empty.Please reselect");
            display();
            readInput();
        }
    }

    private void displayAdditionalInstruction() {
        System.out.println("Please enter the new Project's name for this task: ");
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
                        "\nPlease enter the existing Project's name this task belonged to: ");
            }
        }

        displayTasksByProject();
        selectedTask = selectTask();
        displayAdditionalInstruction();
        scanner.nextLine();
        newProjectName = scanner.nextLine();
    }

    public void sendInput() {
        inputData.put("operationType", 9);
        inputData.put("oldProjectName", selectedProjectName);
        inputData.put("selectedTaskToReassign", selectedTask);
        inputData.put("newProjectName", newProjectName);
        setChanged();
        notifyObservers(inputData);
    }
}



