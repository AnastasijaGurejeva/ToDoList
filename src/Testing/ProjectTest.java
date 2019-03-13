package Testing;

import ToDoList.Project;
import ToDoList.ToDoTask;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ProjectTest {

    private ToDoTask task1 = new ToDoTask("buy bread", "sourdough", LocalDate.of(2019, 05,20));
    private Project project;
    private Integer taskID;

    @Before
    public void setUp() {
      project = new Project("shopping list");
    }

    @Test
    public void addTaskIDtoProject() {
        taskID = task1.getTaskID();
        project.addTaskIDtoProject(taskID);
        assertEquals (taskID, project.getListOfTasks().get(0));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void removeTaskByID() {
        taskID = task1.getTaskID();
        project.addTaskIDtoProject(taskID);
        project.removeTaskByID(taskID);
        project.getListOfTasks().get(0);
    }

}