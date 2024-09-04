// Charles "Nuke" Phillips
// p. 63
package ChapterTwo.Tutorial2;

import java.util.Scanner;

public class IntegerDemoInteractiveWithName {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int anInt;
        byte aByte;
        short aShort;
        long aLong;
        String name;
        System.out.print("Please enter an integer >> ");
        anInt = input.nextInt();
        System.out.print("Please enter a byte integer >> ");
        aByte = input.nextByte();
        System.out.print("Please enter a short integer >> ");
        aShort = input.nextShort();
        System.out.print("Please enter a long integer >> ");
        aLong = input.nextLong();
        input.nextLine(); // Used to consume the enter key character leftover in the input buffer.
        System.out.print("Please enter your name >> ");
        name = input.nextLine();
        System.out.println("Thank you, " + name);


        System.out.println("The int is " + anInt);
        System.out.println("The byte is " + aByte);
        System.out.println("The short is " + aShort);
        System.out.println("The long is " + aLong);
        input.close();
    }
}

