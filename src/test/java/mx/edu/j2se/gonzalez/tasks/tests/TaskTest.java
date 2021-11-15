package mx.edu.j2se.gonzalez.tasks.tests;
import com.sun.xml.internal.ws.policy.AssertionSet;
import mx.edu.j2se.gonzalez.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

public class TaskTest {
    @Test
    public void testAlarma(){
        //given
        Task alarma = new Task("Alarma", 4,11,2);

        Assert.assertFalse(alarma.isActive());

        alarma.setActive(true);

        Assert.assertTrue(alarma.isActive());
        Assert.assertEquals(alarma.nextTimeAfter(5), 6);
        Assert.assertEquals(alarma.nextTimeAfter(3), 6);
        Assert.assertEquals(alarma.nextTimeAfter(8), 10);
        Assert.assertEquals(alarma.nextTimeAfter(12), -1);

    }

}
