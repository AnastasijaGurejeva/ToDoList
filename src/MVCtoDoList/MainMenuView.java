/**
 * This view class displays an instruction for a main menu.
 * It reads and validates all input and then pass that input to a controller which acts in accordance with a selected choice.
 * */

package MVCtoDoList;

public class MainMenuView extends abstractView {

    private int menuChoice;

    public MainMenuView() {
        super("––––––––––––––––––––––––––––––––––––––––––––\n" +
                        "\t\t\t\tMAIN MENU\n" +
                        "____________________________________________",
                "Select an option from the menu below: ");
    }

    @Override
    public void display() {
        super.printUserInterface();

        System.out.println("\n Press:" +
                "\n 0 - To add a new Task" +
                "\n 1 - To assign existing task to a project" +
                "\n 2 - To edit a task" +
                "\n 3 - To mark a task as done/not done" +
                "\n 4 - To delete a task" +
                "\n 5 - To view all tasks" +
                "\n 6 - To sort tasks by due date" +
                "\n 7 - To view tasks by given project" +
                "\n 8 - To reassign a task to a different project" +
                "\n 9 - To delete a project" +
                "\n10 - To save and quit");

    }

    @Override
    public void readInput() {
        while (true) {
            int integerInput = readIntegerInput();
            if (integerInput <= 10 && integerInput >= 0) {
                menuChoice = integerInput;
                break;
            } else {
                System.out.println("Please enter a valid menu number");
            }
        }
    }

    @Override
    public void sendInput() {
        inputData.put("operationType" , 0);
        inputData.put("menuChoice" , menuChoice);
        setChanged();
        notifyObservers(inputData);

    }
}
