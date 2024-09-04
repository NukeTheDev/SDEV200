// Charles "Nuke" Phillips
// p. 49
package ChapterTwo.Tutorial2;

public class IntegerDemo {
    public static void main(String[] args) {
        int anInt = 1234;
        byte aByte = 12;
        short aShort = 12345;
        long aLong = 1234567890987654321L;
        System.out.println("The int is " + anInt);
        System.out.println("The byte is " + aByte);
        System.out.println("The short is " + aShort);
        System.out.println("The long is " + aLong);
        int anotherInt = anInt * 10000000;
        System.out.println("Another int is " + anotherInt);
    }
}
