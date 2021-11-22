package mx.edu.j2se.gonzalez.tasks.tests;
import mx.edu.j2se.gonzalez.tasks.ArrayTaskList;
import mx.edu.j2se.gonzalez.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ScheduledExecutorService;

public class TaskTest {
    @Test
    public void test(){
        //given
        Task alarm = new Task("Alarm", 4,11,2);

        Assert.assertFalse(alarm.isActive());
        alarm.setActive(true);
        Assert.assertTrue(alarm.isActive());

        Assert.assertEquals(alarm.nextTimeAfter(5), 6);
        Assert.assertEquals(alarm.nextTimeAfter(3), 4);
        Assert.assertEquals(alarm.nextTimeAfter(8), 10);
        Assert.assertEquals(alarm.nextTimeAfter(12), -1);

        Task meal = new Task("Dinner", 6);

        Assert.assertFalse(meal.isActive());
        meal.setActive(true);
        Assert.assertTrue(meal.isActive());

        Assert.assertEquals(meal.nextTimeAfter(5),6);

        ArrayTaskList scheduler = new ArrayTaskList();

        Assert.assertEquals(scheduler.size(),0);
        scheduler.add(alarm);
        scheduler.add(meal);
        Assert.assertEquals(scheduler.size(),2);

        ArrayTaskList tomorrow = scheduler.incoming(24,48);
        Assert.assertEquals(tomorrow.size(),0);

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
