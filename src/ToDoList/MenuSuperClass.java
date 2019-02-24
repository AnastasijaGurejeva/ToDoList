package ToDoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public abstract class MenuSuperClass extends Observable {
    private String menuTitle;
    private String welcomeMessage;
    private String menuDescription;
    private Scanner scanner = new Scanner(System.in);
    private Map<String, Object> inputData;



    public MenuSuperClass(String menuTitle, String welcomeMessage, String menuDescription) {
        this.menuTitle = menuTitle;
        this.welcomeMessage = welcomeMessage;
        this.menuDescription = menuDescription;
        this.inputData = new HashMap<>();
    }

    public void printUserInterface() {
        System.out.println(welcomeMessage);
        System.out.println("\n" + menuTitle);
        System.out.println("\n" + menuDescription);
    }

    abstract public void display();
    abstract public void readInput();

    public Map<String, Object> getInputData() {
        return inputData;
    }


    public int readIntegerInput() {
        int choice;
        while (true) {
            try {
                choice = scanner.nextInt();
                return choice;
            } catch (InputMismatchException e ) {
                System.out.println("Please enter a valid number for your choice in the menu");
                scanner.nextLine();
            } catch (NoSuchElementException e) {
                throw new NoSuchElementException("Invalid input");
            }
        }
    }


    public String readStringInput() {
        String input;
        try {
            input = scanner.next();
            scanner.nextLine();
            return input;


        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Invalid input");
        }
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

//    public void sendInputToController() {
//
//    }



}
