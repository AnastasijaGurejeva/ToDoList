/**
 * This class is a main operation class. It initializes a DataManager.
 * This class is an Observer which receives input information from View classes.
 * It initializes all views for different menu options.
 * Main Menu View is launched automatically and it sends users input to a controller, which activates requested view class.
 * It retrieves the data which was send by Observable (View classes) and performs required action.
 * This class also has methods for saving and loading data
 * */

package MVCtoDoList;

import ToDoList.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

class Controller implements Observer {
    private abstractView view;
    private DataManager dataManager = new DataManager();
    private List<abstractView> views;


    public Controller() {
        views = new ArrayList<>();
    }


    public void loadDataLibrary() {
        DataManager dataManager1 = new DataManager();
        try {
            FileInputStream dataLibrary = new FileInputStream("dataManager.ser");
            ObjectInputStream inDataLibrary = new ObjectInputStream(dataLibrary);

            dataManager1 = (DataManager) inDataLibrary.readObject();
            inDataLibrary.close();
            dataLibrary.close();

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        if (dataManager1 != null) {
            this.dataManager = dataManager1;
            loadStaticTaskId();
        }
    }

    /**
     * This Method updates Static field of ToDoTask after the dataManager is deserialized.
     * During serialization static fields are ignored. Needs to be updated manually.
     * To find the last ID in the HashMap reduce function is used.
     * ID can differ from HashMap size value as tasks are added and deleted multiple times during use
     * */

    private void loadStaticTaskId() {
        int maxId = dataManager.getAllTasks().keySet().stream()
        .reduce(0, (a, b) -> Integer.max(a, b));
        ToDoTask.updateTaskID(maxId);
    }

    private void saveData() {
        try {
            FileOutputStream dataLibrary =
                    new FileOutputStream("dataManager.ser");
            ObjectOutputStream outDataLibrary = new ObjectOutputStream(dataLibrary);
            outDataLibrary.writeObject(dataManager);
            dataLibrary.close();
            outDataLibrary.close();
            System.out.println("DataManager was serialized");
        } catch (IOException ioe) {
            System.out.println("IOException is caught");
            ioe.printStackTrace();
        }
    }

    /**
     * Initializing view classes for menu options. These classes are stored in ArrayList for convenience.
     */

    public void addViews() {
        views.add(new MainMenuView());
        views.add(new CreateTaskView());
        views.add(new AssignProjectView());
        views.add(new EditTaskView());
        views.add(new ChangeStatusView());
        views.add(new DeleteTaskView());
        views.add(new FilterByProjectView());
        views.add(new ReassignProjectView());
        views.add(new DeleteProjectView());
    }

    private void startView(abstractView view) {
        view.addObserver(this);
        view.getDataManager(dataManager);
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
     * Passing DataManager value to View class in order to display current list of ToDoTasks.
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
        int operationType = (int) inputData.get("operationType");

        if (operationType == 0) {
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
                    dataManager.printList();
                    generatePauseAfterPrintingList();
                    start();
                    break;
                case 6:
                    dataManager.sortByDueDate();
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

        } else if (operationType == 1) {
            ToDoTask newTask = new ToDoTask((String) inputData.get("newTaskTitle"),
                    (String) inputData.get("newTaskDetails"), (LocalDate) inputData.get("newDueDate"));
            dataManager.addTask(newTask);
            generatePause();
            start();

        } else if (operationType == 2) {
            dataManager.assignTaskToProject((ToDoTask) inputData.get("selectedTask"), (String) inputData.get("projectName"));
            generatePause();
            start();

        } else if (operationType == 3) {
            ToDoTask task = (ToDoTask) inputData.get("selectedTaskToEdit");
            task.setTaskTitle((String) inputData.get("editedTaskTitle"));
            generatePause();
            start();

        } else if (operationType == 4) {
            ToDoTask selectedTask = (ToDoTask) inputData.get("selectedTaskToEdit");
            selectedTask.setTaskDueDate((LocalDate) inputData.get("editedTaskDueDate"));
            generatePause();
            start();

        } else if (operationType == 5) {
            ToDoTask selectedTask = (ToDoTask) inputData.get("selectedTaskToEdit");
            selectedTask.setTaskDetails((String) inputData.get("editedTaskDetails"));
            generatePause();
            start();

        } else if (operationType == 6) {
            ToDoTask selectedTask = (ToDoTask) inputData.get("selectedTaskToChange");
            selectedTask.changeTaskStatus();
            generatePause();
            start();

        } else if (operationType == 7) {
            ToDoTask selectedTask = (ToDoTask) inputData.get("selectedTaskToDelete");
            dataManager.deleteTask(selectedTask);
            generatePause();
            start();

        } else if (operationType == 8) {
            dataManager.sortByProject((String) inputData.get("selectedProjectName"));
            generatePauseAfterPrintingList();
            start();

        } else if (operationType == 9) {
            dataManager.reassignTasksProject((ToDoTask) inputData.get("selectedTaskToReassign"),
                    (String) inputData.get("oldProjectName"), (String) inputData.get("newProjectName"));
            generatePause();
            start();

        } else if (operationType == 10) {
            dataManager.deleteProject((String) inputData.get("selectedProjectToDelete"));
            generatePause();
            start();

        } else if (operationType == 11) {
            dataManager.deleteAllTasksInTheProject((String) inputData.get("selectedProjectToDelete"));
            dataManager.deleteProject((String) inputData.get("selectedProjectToDelete"));
            generatePause();
            start();
        }
    }
}



