//
package ChapterSix.Tutorial6;

import java.time.*;
public class TestFusedLoopTime 
{
    public static void main(String[] args) {
        int startTime, endTime;
        int x;
        final int REPEAT = 5_000_000;
        final int FACTOR = 1_000_000;

        // Test case for dual loop efficiency
        LocalDateTime now;
        now = LocalDateTime.now();
        startTime = now.getNano();
        for(x = 0; x < REPEAT; ++x)
        {
            method1();
        }
        for(x = 0; x < REPEAT; ++x)
        {
            method2();
        }
        now = LocalDateTime.now();
        endTime = now.getNano();
        System.out.println("Time for loops executed separately: " + 
        ((endTime - startTime / FACTOR) + "milliseconds"));
        
        // Test case for single loop efficiency
        now = LocalDateTime.now();
        startTime = now.getNano();
        for(x = 0; x < REPEAT; ++x)
        {
            method1();
            method2();
        }
        now = LocalDateTime.now();
        startTime = now.getNano();
        System.out.println("Time for loops executed in a block: " + 
        ((endTime - startTime / FACTOR) + "milliseconds"));


    }
    public static void method1()
    {
        
    }
    public static void method2()
    {

    }
}
