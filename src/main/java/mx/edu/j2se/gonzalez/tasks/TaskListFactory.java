package mx.edu.j2se.gonzalez.tasks;

public class TaskListFactory {
    /**
     * @param types The type of list, either ARRAY or LINKED
     * @return An ArrayList or LinkedList
     * @throws NullPointerException-if the type is null
     */
    public static AbstractTaskList createTaskList(ListTypes.types types) throws NullPointerException{
        if(types == null) throw new NullPointerException();
        return ListTypes.types.ARRAY == types?new ArrayTaskList():new LinkedTaskList();
    }
}
