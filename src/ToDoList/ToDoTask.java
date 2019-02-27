package ToDoList ;

import java.io.Serializable;
import java.time.LocalDate;

public class ToDoTask implements Serializable {
    private String taskTitle;
    private String taskDetails;
    private boolean taskStatus;
    private int taskID;
    private LocalDate taskDueDate;
    private static int countID = 0;


    public ToDoTask(String taskTitle, String taskDetails, LocalDate taskDueDate) {
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskDueDate = taskDueDate;
        this.taskStatus = false;
        this.countID++;
        taskID = this.countID;
    }

    public int getTaskID() {
        return taskID;
    }

    /**
     * This method is used to set static ID field after deserialization.
     */

    static public void updateTaskID(int iD ) {
        countID = iD;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public LocalDate getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(LocalDate newTaskDueDate) {
            this.taskDueDate = newTaskDueDate;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    private boolean isTaskDone() {
        return taskStatus;
    }

    public String taskStatus() {
        if(isTaskDone()) {
            return "Done";
        } else {
            return "Not done";
        }
    }

    public void changeTaskStatus() {
        if(isTaskDone()) {
            taskStatus = false;
        } else {
            taskStatus = true;
        }
    }
}


