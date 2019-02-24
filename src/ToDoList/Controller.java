package ToDoList;

import java.time.LocalDate;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

class Controller implements Observer {
    private int menuChoice;
    private MenuSuperClass menu;
    private Library library = new Library();
    private TaskMenu taskMenu = new TaskMenu();
    private CreateTaskMenu createTaskMenu = new CreateTaskMenu();

    public void start() {
       taskMenu.addObserver(this);
       taskMenu.display();
       taskMenu.readInput();

    }

    public void callCreateTaskMenu() {
        createTaskMenu.addObserver(this);
        createTaskMenu.display();

    }


    @Override
    public void update(Observable sender, Object arg) {
        Map inputData = (Map) arg;
        menuChoice = (int) inputData.get("menuChoice");



        System.out.println("-------values");
        inputData.values().stream()
                .forEach(t->System.out.println(t));
        System.out.println("-------keys");
        inputData.keySet().stream()
                .forEach(k->System.out.println(k));

        boolean quit = false;

//        while (!quit) {

            switch (menuChoice) {
                case 0:

                    callCreateTaskMenu();
                    createTaskMenu.readInput();

                    System.out.println("-------values 2");
                    inputData.values().stream()
                            .forEach(t->System.out.println(t));
                    System.out.println("-------key 20s");
                    inputData.keySet().stream()
                            .forEach(k->System.out.println(k));

                    ToDoTask newTask = new ToDoTask((String) inputData.get("newTaskTitle"),
                            (String) inputData.get("newTaskDetails"),
                            (LocalDate) inputData.get("newDueDate"));
                    library.addTask(newTask);
                    library.printList();
                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;

            }


 //       }
    }
}

//    public void inputIntValidation() {
//       if (menuChoice
//
//    }
//    public void inputStringValidation() {
//
//    }
//    Taks task = new Task( map.get(name), map.get(date),

