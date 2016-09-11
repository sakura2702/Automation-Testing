import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by ADMIN on 12/28/2015.
 */
public class TimerCronTest {

    private int count = 0;

    @Test
    public void TimerDemo() throws Exception{
        // creating timer task, timer
        TimerTask tasknew = new TimerTask() {
            @Override
            public void run() {
                count++;
                System.out.print(count);
            }
        };
        Timer timer = new Timer();

        // scheduling the task at interval
        timer.schedule(tasknew, new Date(),10);
        while(count < 10)
            if(count >= 10){
                timer.cancel();
            }
    }
}
