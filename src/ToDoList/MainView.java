package ToDoList;

public class MainView extends ViewSuperClass {

    private int menuChoice;

    public MainView() {
        super("MAIN MENU",
                "Select an option from the menu below: ");
    }

    @Override
    public void display() {
        super.printUserInterface();

        System.out.println("\n Press:" +
                "\n 0 - To add a new Task" +
                "\n 1 - To assign existing task to the project" +
                "\n 2 - To edit task" +
                "\n 3 - To mark task as done/not done" +
                "\n 4 - To delete task" +
                "\n 5 - To view all tasks" +
                "\n 6 - To sort tasks by due date" +
                "\n 7 - To view tasks by given project" +
                "\n 8 - To reassign task to a different project" +
                "\n 9 - To delete project" +
                "\n10 - To save and quit");

    }

    @Override
    public void readInput() {
        menuChoice = readIntegerInput();
    }

    @Override
    public void sendInput() {
        inputData.put("menuType" , 0);
        inputData.put("menuChoice" , menuChoice);
        setChanged();
        notifyObservers(inputData);

    }

    @Override
    public void notification() {
    }
}
