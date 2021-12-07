package mx.edu.j2se.gonzalez.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types types){
        return types.name().equals("ARRAY") ?new ArrayTaskList():new LinkedTaskList();
    }
}
