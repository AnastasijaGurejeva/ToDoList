package MVCtoDoList;

public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.loadData();
        controller.addViews();
        controller.start();


    }
}














