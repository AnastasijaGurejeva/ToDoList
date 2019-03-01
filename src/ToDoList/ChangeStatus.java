package ToDoList;

public class ChangeStatus extends ViewSuperClass {

    private ToDoTask selectedTask;

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
        Integer id;
        while (true) {
            id = readIntegerInput();
            if (passedLibraryData.getAllTasks().containsKey(id)) {
                selectedTask = passedLibraryData.getAllTasks().get(id);
                break;
            } else {
                System.out.println("Please enter a valid number of Selected task");
            }
        }
    }

    @Override
    public void sendInput() {
        inputData.put("menuType", 6);
        inputData.put("selectedTaskToChange", selectedTask);
        setChanged();
        notifyObservers(inputData);
    }

    @Override
    public void notification() {
        System.out.println("Your task status was changed");
    }
}

