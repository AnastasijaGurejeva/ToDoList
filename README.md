# ToDoList

This is a simple ToDoList application which uses text based user interface via the command-line.
It allows user to create tasks, assign them to a project, edit task, mark as done, sort the list and other.
More detail users menu is displayed below.
![alt text](https://github.com/AnastasijaGurejeva/ToDoList/blob/master/Screenshot%202019-03-15%20at%2011.42.07.png)



### ToDoList architecture
This java project consists of 3 packages: ToDoList, MVCtoDoList, Testing.
ToDoList has 3 lasses: ToDoTask, Project and DataManager.
MVC consists of 11 classes. When application is launched controller initializes DataManager and all View classes 
for different menu options. Main menu view is launched. View classes display instructions to the users, reads their input and send
that input to controller which performs required operation. 
### Class Diagram
![alt text](https://github.com/AnastasijaGurejeva/ToDoList/blob/UserInputHandler/Class%20Diagram%20ToDoList.jpeg)
