package ToDoList;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ProjectTest {

    private Project project;

    @Before
    public void setup(){
        project = new Project("ShoppingList");
    }

    @Test
    public void getProjectName() throws Exception {
        assertEquals("ShoppingList", project.getProjectName());
    }

    @Test
    public void addNewTaskToProject() throws Exception {
        project.addNewTaskToProject(new ToDoTask("buy milk", "in Willys", LocalDate.of(2019, 02,20)));
    }

    @Test
    public void deleteTask() throws Exception {
    }

    @Test
    public void printProjectTasks() throws Exception {
        ToDoTask toDoTask = new ToDoTask("buy milk", "in Willys", LocalDate.of(2019, 02,20));
        String testPrintout = project.constructTaskDetails(toDoTask);
        assertEquals("buy milk task due: 2019-02-20false", testPrintout);
    }

    @Test
    public void sortTasksByDueDate() throws Exception {
    }
}