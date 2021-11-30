package mx.edu.j2se.gonzalez.tasks.tests;

import mx.edu.j2se.gonzalez.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

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

    }

    @Test
    public void testException(){
        try {
            Task alarm1 = new Task("Alarm",-1);
        }
        catch (IllegalArgumentException e){
            System.out.println("Illegal Argument for non-repetitive task");
        }
        try {
            Task alarm2 = new Task("Alarm",0,-1,0);
        }
        catch (IllegalArgumentException e){
            System.out.println("Illegal Argument for repetitive task");
        }
        try {
            Task alarm3 = new Task("Alarm",5);
            alarm3.setTime(-1);
        }
        catch (IllegalArgumentException e){
            System.out.println("Illegal Argument for setTime");
        }

    }

}
