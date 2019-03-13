/**
 * This class is an abstract class for all View classes.
 * View classes are displaying instruction for users, reads their inputs and sends data to controller,which operates the data.
 * There are 3 abstract methods which are implemented in all View classes: display; readInput; sendInput.
 * This class has few protected fields which are shared between all View classes.
 * It also has input validation methods and selectTask method which are used in view classes.
 * */

package MVCtoDoList;

import ToDoList.DataManager;
import ToDoList.ToDoTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public abstract class abstractView extends Observable {
    private String viewTitle;
    private String viewDescription;
    protected Scanner scanner = new Scanner(System.in);
    protected Map<String, Object> inputData;
    protected DataManager passedDataManager;


    public void getDataManager(DataManager dataManager) {
        passedDataManager = dataManager;
    }

    public abstractView(String menuTitle, String menuDescription) {
        this.viewTitle = menuTitle;
        this.viewDescription = menuDescription;
        this.inputData = new HashMap<>();
    }

    public void printUserInterface() {
        System.out.println("\n" + viewTitle);
        System.out.println("\n" + viewDescription);
    }

    abstract public void display();
    abstract public void readInput();
    abstract public void sendInput();

    public int readIntegerInput() {
        int choice;
        while (true) {
            try {
                choice = scanner.nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number for your choice in the menu");
                scanner.nextLine();
            }
        }
    }

    public ToDoTask selectTask() {
        ToDoTask selectedTask;
        Integer id;
        while (true) {
            id = readIntegerInput();
            if (passedDataManager.getAllTasks().containsKey(id)) {
                selectedTask = passedDataManager.getAllTasks().get(id);
                break;
            } else {
                System.out.println("Please enter a valid number of Selected task");
            }
        }
        return selectedTask;
    }

    public LocalDate readDateInput() {

        String date;
        while (true) {
            try {
                date = scanner.nextLine();
                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern("d/MM/yyyy");
                LocalDate taskDueDate = LocalDate.parse(date, formatter);

                if (taskDueDate.isAfter(LocalDate.now())) {
                    return taskDueDate;
                } else {
                    System.out.println("The date is set before today's date. Please enter correct due date");
                }

            } catch (DateTimeParseException exc) {
                System.out.print("Please enter date in a correct format: d/MM/yyyy");
            }
        }
    }

}
