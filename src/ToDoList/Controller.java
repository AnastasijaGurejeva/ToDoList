package ToDoList;

import java.time.LocalDate;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

class Controller implements Observer {
    private MenuSuperClass menu;
    private Library library = new Library();


    public void start() {
        menu = new MainMenu();
        menu.addObserver(this);
        menu.display();
        menu.readInput();

    }

    public void callCreateTask() {
        menu = new CreateTask();
        menu.addObserver(this);
        menu.display();
        menu.readInput();
    }

    public void callAssignTask() {
        menu = new AssignProject();
        menu.addObserver(this);
        ((AssignProject) menu).getLibrary(library);
        menu.display();
        menu.readInput();
    }

    public void callEditTask() {
        menu = new EditTask();
        menu.addObserver(this);
        ((EditTask) menu).getLibrary(library);
        menu.display();
        menu.readInput();
    }


    public void callChangeTaskStatus() {
        menu = new ChangeStatus();
        menu.addObserver(this);
        ((ChangeStatus) menu).getLibrary(library);
        menu.display();
        menu.readInput();
    }

    public void CallDeleteTask() {
        menu = new DeleteTask();
        menu.addObserver(this);
        ((DeleteTask) menu).getLibrary(library);
        menu.display();
        menu.readInput();
    }

    public void callSelectProject() {
        menu = new SelectProject();
        menu.addObserver(this);
        menu.display();
        menu.readInput();
    }

    public void callReassignProject() {
        menu = new ReassignProject();
        menu.addObserver(this);
        ((ReassignProject) menu).getLibrary(library);
        menu.display();
        menu.readInput();
    }


    @Override
    public void update(Observable sender, Object arg) {
        Map inputData = (Map) arg;
        int menuType = (int) inputData.get("menuType");

        if (menuType == 0) {
            int menuChoice;
            menuChoice = (int) inputData.get("menuChoice");


            switch (menuChoice) {
                case 0:
                    callCreateTask();
                    break;
                case 1:
                    callAssignTask();
                    break;
                case 2:
                    callEditTask();
                    break;
                case 3:
                    callChangeTaskStatus();
                    break;
                case 4:
                    CallDeleteTask();
                    break;
                case 5:
                    library.printList();
                    start();
                    break;
                case 6:
                    library.sortByDueDate();
                    start();
                    break;
                case 7:
                    callSelectProject();
                    break;
                case 8:
                    callSelectProject();
                    break;
                case 9:
                    callReassignProject();
                    break;

            }

        } else if (menuType == 1) {
            ToDoTask newTask = new ToDoTask((String) inputData.get("newTaskTitle"),
                    (String) inputData.get("newTaskDetails"),
                    (LocalDate) inputData.get("newDueDate"));
            library.addTask(newTask);
            System.out.println("Your task is created.");
            start();
        } else if (menuType == 2) {
            library.assignTaskToProject((ToDoTask) inputData.get("selectedTask"), (String) inputData.get("projectName"));
            start();
        } else if (menuType == 3) {
            ToDoTask task = (ToDoTask) inputData.get("selectedTaskToEdit");
            task.setTaskTitle((String) inputData.get("editedTaskTitle"));
            System.out.println("You have changed task's title " + task.getTaskTitle());
            start();
        } else if (menuType == 4) {
            ToDoTask task = (ToDoTask) inputData.get("selectedTaskToEdit");
            task.setTaskDueDate((LocalDate) inputData.get("editedTaskDueDate"));
            System.out.println("You have changed task's due date " + task.getTaskDueDate());
            start();
        } else if (menuType == 5) {
            ToDoTask task = (ToDoTask) inputData.get("selectedTaskToEdit");
            task.setTaskTitle((String) inputData.get("editedTaskDetails"));
            System.out.println("You have changed task's details " + task.getTaskDetails());
            start();
        } else if (menuType == 6) {
            ToDoTask task = (ToDoTask) inputData.get("selectedTaskToChange");
            task.changeTaskStatus();
            System.out.println("You have changed task's status");
            start();
        } else if (menuType == 7) {
            ToDoTask task = (ToDoTask) inputData.get("selectedTaskToDelete");
            library.deleteTask(task);
            System.out.println("You have deleted a task");
            start();
        }
        else if (menuType == 8) {
            library.sortByProject((String) inputData.get("selectedProject"));
            start();
        } else if (menuType == 9) {
            library.reassignTasksProject((ToDoTask) inputData.get("selectedTaskToReassign"),
                    (String) inputData.get("oldProjectName"), (String) inputData.get("newProjectName"));
            start();
        }

    }
}



