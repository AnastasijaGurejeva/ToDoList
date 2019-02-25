package ToDoList;

import java.time.LocalDate;
import java.util.*;

class Controller implements Observer {
    private ViewSuperClass view;
    private Library library = new Library();
    private List<ViewSuperClass> views;

    public Controller() {
        views = new ArrayList<>();
    }

    public void addViews() {
        views.add(new MainView());
        views.add(new CreateTask());
        views.add(new AssignProject());
        views.add(new EditTask());
        views.add(new ChangeStatus());
        views.add(new DeleteTask());
        views.add(new SelectProject());
        views.add(new ReassignProject());
    }


    public void start() {
        view = views.get(0);
        view.addObserver(this);
        view.display();
        view.readInput();

    }

    public void callCreateTask() {
        view = views.get(1);
        view.addObserver(this);
        view.display();
        view.readInput();
    }

    public void callAssignTask() {
        view = views.get(2);
        view.addObserver(this);
        ((AssignProject) view).getLibrary(library);
        view.display();
        view.readInput();
    }

    public void callEditTask() {
        view = views.get(3);
        view.addObserver(this);
        ((EditTask) view).getLibrary(library);
        view.display();
        view.readInput();
    }


    public void callChangeTaskStatus() {
        view = views.get(4);
        view.addObserver(this);
        ((ChangeStatus) view).getLibrary(library);
        view.display();
        view.readInput();
    }

    public void CallDeleteTask() {
        view = views.get(5);
        view.addObserver(this);
        ((DeleteTask) view).getLibrary(library);
        view.display();
        view.readInput();
    }

    public void callSelectProject() {
        view = views.get(6);
        view.addObserver(this);
        ((SelectProject) view).getLibrary(library);
        view.display();
        view.readInput();
    }

    public void callReassignProject() {
        view = views.get(7);
        view.addObserver(this);
        ((ReassignProject) view).getLibrary(library);
        view.display();
        view.readInput();
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
                    callReassignProject();
                    break;
                case 9:
                   //saveDataQuit();
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



