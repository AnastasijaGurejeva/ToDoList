package Testing;

import ToDoList.DataManager;
import ToDoList.Project;
import ToDoList.ToDoTask;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DataManagerTest {
    private ToDoTask task = new ToDoTask("buy cake", "chocolate", LocalDate.of(2019, 05,20));
    private ToDoTask task1 = new ToDoTask("buy milk", "oat", LocalDate.of(2019, 04,25));
    private Project existingProject;
    private DataManager data;

    @Before
    public void setUp() {
        existingProject = new Project("shopping");
        data = new DataManager();
        data.addProject(existingProject);
        data.addTask(task);
        data.addTask(task1);
    }


    @Test
    public void assignTaskToExistingProject() {
        Integer taskID = task.getTaskID();
        data.assignTaskToProject(task, "shopping");
        assertEquals (taskID, existingProject.getListOfTasks().get(0));
    }

    /** This test is checking if a new Project is created,
     * when task is assigned to a Projects title which doesn't exist.
     **/

    @Test
    public void assignTaskToNewProject() {
        Integer taskID = task.getTaskID();
        data.assignTaskToProject(task, "for birthday party");
        Project newProject = data.getAllProjects().get("for birthday party");
        assertEquals (taskID, newProject.getListOfTasks().get(0));
    }

    @Test
    public void assignSameTaskTo2Projects() {
        Project newProject = new Project("for birthday party");
        data.addProject(newProject);
        data.assignTaskToProject(task, "shopping");
        data.assignTaskToProject(task, "for birthday party");
        assertEquals (existingProject.getListOfTasks().get(0), newProject.getListOfTasks().get(0));
    }

    @Test
    public void deleteTask() {
        Integer taskID = task1.getTaskID();
        data.deleteTask(task1);
        assertNull(data.getAllTasks().get(taskID));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void deleteTaskFromProjectAlso() {
        data.assignTaskToProject(task1, "shopping");
        data.deleteTask(task1);
        existingProject.getListOfTasks().get(0);
    }

    @Test
    public void removeTaskFromProject() {
        data.assignTaskToProject(task, "shopping");
        data.removeTaskFromProject(task, "shopping");
        assertEquals(0, existingProject.getListOfTasks().size());
    }

    @Test
    public void deleteAllTasksInTheProject() {
        data.assignTaskToProject(task1, "shopping");
        data.assignTaskToProject(task, "shopping");
        data.deleteAllTasksInTheProject("shopping");
        assertEquals(0, existingProject.getListOfTasks().size());
    }

    @Test
    public void deleteAllTasksInTheProjectAndFromMainTaskList() {
        data.assignTaskToProject(task, "shopping");
        data.deleteAllTasksInTheProject("shopping");
        assertNull(data.getAllTasks().get(task.getTaskID()));
    }
}