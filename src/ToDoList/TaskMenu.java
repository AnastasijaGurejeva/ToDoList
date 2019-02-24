package ToDoList;

import java.util.Map;

public class TaskMenu extends MenuSuperClass {


    public TaskMenu() {
        super("TASK MENU", "Welcome! Here you can create and edit a task",
                "Select an option from instructions below: ");
    }


    @Override
    public void display() {
        super.printUserInterface();

      System.out.println("\n Press:" +
                         "\n 0 - To create a new Task" +
                         "\n 1 - To edit task's title" +
                         "\n 2 - To edit task's due date" +
                         "\n 3 - To edit task's details" +
                         "\n 4 - Mark task as done");

    }

    @Override
    public void readInput() {
        int menuChoice = readIntegerInput();
        Map<String, Object> inputData = getInputData();
        inputData.put("menuChoice" , menuChoice);
        setChanged();
        notifyObservers(inputData);
    }

}


