/**
 * This view class displays an instruction to assign the task to an existing or a new project.
 * It allows user to select a task from printed task list by typing task's ID and select project from printed project list by typing project Name
 * If user wants to create a new project, he simply types in a new Project name.
 * Selected task and project name are then passed to a controller which completes the assign operation.
 * */

package MVCtoDoList;

import ToDoList.ToDoTask;

public class AssignProjectView extends abstractView {

    private ToDoTask selectedTask;
    private String projectName;

    public AssignProjectView() {
        super("\t\t\tLIST OF YOUR TASKS",
                "Please select the task by typing task's No ");
    }

    @Override
    public void display() {
        super.printUserInterface();
        passedDataManager.printList();
    }

    private void displayTasks() {
        System.out.println("\t\t\tLIST OF YOUR PROJECTS");
        System.out.println("\nPlease enter the existing name of the Project " +
                "\nor type in a new name of the project for your task: \n");
        passedDataManager.printProjectList();
    }

    @Override
    public void readInput() {
        selectedTask = selectTask();
        displayTasks();
        scanner.nextLine();
        projectName = scanner.nextLine();
    }

    @Override
    public void sendInput() {
        inputData.put("operationType" , 2);
        inputData.put("selectedTask" , selectedTask);
        inputData.put("projectName" , projectName);
        setChanged();
        notifyObservers(inputData);
    }
}
