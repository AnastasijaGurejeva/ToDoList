package ToDoList;

import java.util.HashMap;
import java.util.Map;

public class DeleteTask extends MenuSuperClass {
    private Library lib;


    public DeleteTask() {
        super("List of your tasks", "",
                "Please select the task you want to delete" +
                        "by typing task's No ");
    }

    public void getLibrary(Library library) {
        lib = library;
    }


    @Override
    public void display() {
        super.printUserInterface();
        lib.printList();
    }


    @Override
    public void readInput() {

        HashMap<Integer, ToDoTask> allTasks = lib.getAllTasks();
        ToDoTask selectedTask;
        Integer id;
        while (true) {
            id = readIntegerInput();
            if (allTasks.containsKey(id)) {
                selectedTask = allTasks.get(id);
                break;
            } else {
                System.out.println("Please enter a valid number of Selected task");
            }
        }

        Map<String, Object> inputData = getInputData();
        inputData.put("menuType", 7);
        inputData.put("selectedTaskToDelete", selectedTask);
        setChanged();
        notifyObservers(inputData);
    }
}


