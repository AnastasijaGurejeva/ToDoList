package ToDoList;

public class FilterByProject extends ViewSuperClass {

    public FilterByProject() {
        super("LIST OF YOUR PROJECTS",
                "Please enter the name of the Project for this task or" +
                        "\n type in the new name for your project: ");
    }

    @Override
    public void display() {
        super.printUserInterface();
        passedLibraryData.getAllProjects().keySet().stream()
                .forEach(k -> System.out.println(k));


    }


    @Override
    public void readInput() {
        String selectedProject = readStringInput();
        inputData.put("menuType", 8);
        inputData.put("selectedProject", selectedProject);
        setChanged();
        notifyObservers(inputData);
    }
}


