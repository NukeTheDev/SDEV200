// Charles "Nuke" Phillips
// p. 62
package ChapterTwo.Tutorial2;

import java.util.Scanner;

public class IntegerDemoInteractive {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int anInt;
        byte aByte;
        short aShort;
        long aLong;
        System.out.print("Please enter an integer >> ");
        anInt = input.nextInt();
        System.out.print("Please enter a byte integer >> ");
        aByte = input.nextByte();
        System.out.print("Please enter a short integer >> ");
        aShort = input.nextShort();
        System.out.print("Please enter a long integer >> ");
        aLong = input.nextLong();

        System.out.println("The int is " + anInt);
        System.out.println("The byte is " + aByte);
        System.out.println("The short is " + aShort);
        System.out.println("The long is " + aLong);
        input.close();
    }
}

