package ToDoList;

import java.util.Map;

public class SelectProject extends ViewSuperClass {
    private Library lib;

    public SelectProject() {
        super("LIST OF YOUR PROJECTS",
                "Please enter the name of the project from existing list:");
    }

    public void getLibrary(Library library) {
        lib = library;
    }

    @Override
    public void display() {
        super.printUserInterface();

    }


    @Override
    public void readInput() {
        System.out.println("LIST OF YOUR PROJECTS");
        System.out.println("Please enter the name of the Project for this task or " +
                "\n type in the new name for your project : ");
        lib.getAllProjects().keySet().stream()
                .forEach(k -> System.out.println(k));


        String selectedProject = readStringInput();
        Map<String, Object> inputData = getInputData();
        inputData.put("menuType", 8);
        inputData.put("selectedProject", selectedProject);
        setChanged();
        notifyObservers(inputData);
    }
}


