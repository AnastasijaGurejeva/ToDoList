package ToDoList;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

class Controller implements Observer {
    private ViewSuperClass view;
    private Library library = new Library();
    private List<ViewSuperClass> views;


    public Controller() {
        views = new ArrayList<>();
    }


    public void loadDataLibrary() {
        Library library1 = new Library();
        try {
            FileInputStream dataLibrary = new FileInputStream("library.ser");
            ObjectInputStream inDataLibrary = new ObjectInputStream(dataLibrary);

            library1 = (Library) inDataLibrary.readObject();
            inDataLibrary.close();
            dataLibrary.close();

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        if (library1 != null) {
            this.library = library1;
            loadStaticTaskId();
        }
    }

    /**
     * This Method updates Static field of ToDoTask after the library is deserialized.
     * During serialization static fields are ignored. Needs to be updated manually.
     * To find the last ID in the HashMap reduce function is used.
     * ID can differ from HashMap size value as tasks are added and deleted multiple times during use
     * */

    private void loadStaticTaskId() {
        int maxId = library.getAllTasks().keySet().stream()
        .reduce(0, (a, b) -> Integer.max(a, b));
        ToDoTask.updateTaskID(maxId);
    }

    private void saveData() {
        try {
            FileOutputStream dataLibrary =
                    new FileOutputStream("library.ser");
            ObjectOutputStream outDataLibrary = new ObjectOutputStream(dataLibrary);
            outDataLibrary.writeObject(library);
            dataLibrary.close();
            outDataLibrary.close();
            System.out.println("Library was serialized");
        } catch (IOException ioe) {
            System.out.println("IOException is caught");
            ioe.printStackTrace();
        }
    }

    /**
     * Initializing view classes for menu options. These classes are stored in ArrayList for convenience.
     */

    public void addViews() {
        views.add(new MainView());
        views.add(new CreateTask());
        views.add(new AssignProject());
        views.add(new EditTask());
        views.add(new ChangeStatus());
        views.add(new DeleteTask());
        views.add(new FilterByProject());
        views.add(new ReassignProject());
        views.add(new DeleteProject());
    }

    private void startView(ViewSuperClass view) {
        view.addObserver(this);
        view.getLibrary(library);
        view.display();
        view.readInput();
        view.sendInput();
    }

    private void generatePause() {
        try {
            TimeUnit.MILLISECONDS.sleep(400);
        } catch (InterruptedException e) {
        }
    }

    private void generatePauseAfterPrintingList() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
        }
    }

    public void start() {
        view = views.get(0);
        startView(view);
    }

    private void callCreateTask() {
        view = views.get(1);
        startView(view);
    }

    /**
     * This method is calling corresponding view Class to display instructions for the user and read the users input.
     * First retrieving initialized view object from ArrayList
     * Passing Library value to View class in order to display current list of ToDoTasks.
     */

    private void callAssignProject() {
        view = views.get(2);
        startView(view);
    }

    private void callEditTask() {
        view = views.get(3);
        startView(view);
    }

    private void callChangeTaskStatus() {
        view = views.get(4);
        startView(view);
    }

    private void CallDeleteTask() {
        view = views.get(5);
        startView(view);
    }

    private void callFilterByProject() {
        view = views.get(6);
        startView(view);
    }

    private void callReassignProject() {
        view = views.get(7);
        startView(view);
    }

    private void callDeleteProject() {
        view = views.get(8);
        startView(view);
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
                    callAssignProject();
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
                    generatePauseAfterPrintingList();
                    start();
                    break;
                case 6:
                    library.sortByDueDate();
                    generatePauseAfterPrintingList();
                    start();
                    break;
                case 7:
                    callFilterByProject();
                    break;
                case 8:
                    callReassignProject();
                    break;
                case 9:
                    callDeleteProject();
                    break;
                case 10:
                    saveData();
                    break;

            }

        } else if (menuType == 1) {
            ToDoTask newTask = new ToDoTask((String) inputData.get("newTaskTitle"),
                    (String) inputData.get("newTaskDetails"), (LocalDate) inputData.get("newDueDate"));
            library.addTask(newTask);
            view.notification();
            generatePause();
            start();

        } else if (menuType == 2) {
            library.assignTaskToProject((ToDoTask) inputData.get("selectedTask"), (String) inputData.get("projectName"));
            view.notification();
            generatePause();
            start();

        } else if (menuType == 3) {
            ToDoTask task = (ToDoTask) inputData.get("selectedTaskToEdit");
            task.setTaskTitle((String) inputData.get("editedTaskTitle"));
            view.notification();
            generatePause();
            start();

        } else if (menuType == 4) {
            ToDoTask task = (ToDoTask) inputData.get("selectedTaskToEdit");
            task.setTaskDueDate((LocalDate) inputData.get("editedTaskDueDate"));
            view.notification();
            generatePause();
            start();

        } else if (menuType == 5) {
            ToDoTask task = (ToDoTask) inputData.get("selectedTaskToEdit");
            task.setTaskDetails((String) inputData.get("editedTaskDetails"));
            view.notification();
            generatePause();
            start();

        } else if (menuType == 6) {
            ToDoTask task = (ToDoTask) inputData.get("selectedTaskToChange");
            task.changeTaskStatus();
            view.notification();
            generatePause();
            start();

        } else if (menuType == 7) {
            ToDoTask task = (ToDoTask) inputData.get("selectedTaskToDelete");
            library.deleteTask(task);
            view.notification();
            generatePause();
            start();

        } else if (menuType == 8) {
            library.sortByProject((String) inputData.get("selectedProject"));
            generatePauseAfterPrintingList();
            start();

        } else if (menuType == 9) {
            library.reassignTasksProject((ToDoTask) inputData.get("selectedTaskToReassign"),
                    (String) inputData.get("oldProjectName"), (String) inputData.get("newProjectName"));
            view.notification();
            generatePause();
            start();

        } else if (menuType == 10) {
            library.deleteProject((String) inputData.get("selectedProjectToDelete"));
            view.notification();
            generatePause();
            start();

        } else if (menuType == 11) {
            library.deleteAllTasksInTheProject((String) inputData.get("selectedProjectToDelete"));
            library.deleteProject((String) inputData.get("selectedProjectToDelete"));
            view.notification();
            generatePause();
            start();
        }
    }
}



