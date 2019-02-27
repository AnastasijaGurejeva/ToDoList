package ToDoList;

import java.util.HashMap;

public class ChangeStatus extends ViewSuperClass {


    public ChangeStatus() {
        super("LIST OF YOUR TASKS",
                "Please select the task you want to mark as done" +
                        "by typing task's No ");
    }


    @Override
    public void display() {
        super.printUserInterface();
        passedLibraryData.printList();
    }


    @Override
    public void readInput() {

        HashMap<Integer, ToDoTask> allTasks = passedLibraryData.getAllTasks();
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

        inputData.put("menuType", 6);
        inputData.put("selectedTaskToChange", selectedTask);
        setChanged();
        notifyObservers(inputData);
    }
}

