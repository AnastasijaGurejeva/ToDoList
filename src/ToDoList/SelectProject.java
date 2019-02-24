package ToDoList;

import java.util.Map;

public class SelectProject extends MenuSuperClass {



        public SelectProject() {
            super(" ", " ",
                    "Please enter the name of the project: ");
        }

        @Override
        public void display() {
            super.printUserInterface();

        }


        @Override
        public void readInput() {

            String selectedProject = readStringInput();
            Map<String, Object> inputData = getInputData();
            inputData.put("menuType", 8);
            inputData.put("selectedProject", selectedProject);
            setChanged();
            notifyObservers(inputData);
        }
    }


