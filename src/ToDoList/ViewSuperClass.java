package ToDoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public abstract class ViewSuperClass extends Observable {
    private String viewTitle;
    private String viewDescription;
    private Scanner scanner = new Scanner(System.in);
    protected Map<String, Object> inputData;
    protected Library passedLibraryData;


    public void getLibrary(Library library) {
        passedLibraryData = library;
    }

    public ViewSuperClass(String menuTitle, String menuDescription) {
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


    public String readStringInput() {
        String input = scanner.next();
        scanner.nextLine();
        return input;
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
