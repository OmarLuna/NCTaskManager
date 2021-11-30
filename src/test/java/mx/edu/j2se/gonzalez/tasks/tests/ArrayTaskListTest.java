package mx.edu.j2se.gonzalez.tasks.tests;

import mx.edu.j2se.gonzalez.tasks.ArrayTaskList;
import mx.edu.j2se.gonzalez.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

public class ArrayTaskListTest {
    @Test
    public void test(){
        Task alarm = new Task("Alarm",4,11,2);
        Task meal = new Task("Dinner", 6);

        alarm.setActive(true);
        meal.setActive(true);

        ArrayTaskList scheduler = new ArrayTaskList();

        Assert.assertEquals(scheduler.size(),0);
        scheduler.add(alarm);
        scheduler.add(meal);
        Assert.assertEquals(scheduler.size(),2);

        ArrayTaskList tomorrow = scheduler.incoming(24,48);
        Assert.assertNull(tomorrow);                        //No tasks tomorrow

        Task exercise = new Task("Exercise",30);
        exercise.setActive(true);

        Assert.assertEquals(exercise.nextTimeAfter(24),30);
        scheduler.add(exercise);

        Assert.assertEquals(scheduler.size(),3);
        tomorrow = scheduler.incoming(24,48);
        Assert.assertEquals(tomorrow.size(),1);

        Assert.assertSame(scheduler.getTask(0),alarm);

        scheduler.remove(alarm);
        Assert.assertEquals(scheduler.size(),2);
        Assert.assertSame(scheduler.getTask(0),meal);
    }
}
