package ToDoList;

public class DeleteTask extends ViewSuperClass {

    private ToDoTask selectedTask;

    public DeleteTask() {
        super("LIST OF YOUR TASKS",
                "Please select the task you want to delete" +
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
        inputData.put("menuType", 7);
        inputData.put("selectedTaskToDelete", selectedTask);
        setChanged();
        notifyObservers(inputData);

    }
}


