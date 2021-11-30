package mx.edu.j2se.gonzalez.tasks.tests;

import mx.edu.j2se.gonzalez.tasks.LinkedTaskList;
import mx.edu.j2se.gonzalez.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

public class LinkedTaskListTest {
    @Test
    public void test(){
        Task alarm = new Task("Alarm",4,11,2);
        Task meal = new Task("Dinner",6);
        Task exercise = new Task("Exercise",30);
        Task medicine = new Task("Medicine",0,72,8);

        alarm.setActive(true);
        meal.setActive(true);
        exercise.setActive(true);
        medicine.setActive(true);

        LinkedTaskList scheduler = new LinkedTaskList();

        Assert.assertEquals(scheduler.size(),0);

        scheduler.add(alarm);
        Assert.assertEquals(scheduler.size(),1);

        scheduler.add(meal);
        Assert.assertEquals(scheduler.size(),2);

        scheduler.add(exercise);
        Assert.assertEquals(scheduler.size(),3);

        Assert.assertEquals(scheduler.incoming(24,48).size(),1);

        Assert.assertFalse(scheduler.remove(medicine));

        Assert.assertSame(scheduler.getTask(1),meal);
        Assert.assertTrue(scheduler.remove(meal));
        Assert.assertSame(scheduler.getTask(1),exercise);
        Assert.assertEquals(scheduler.size(),2);

    }
}
