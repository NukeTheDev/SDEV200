// Charles Phillips
// p.167
package ChapterFive.Tutorial5;

public class ShortCircuitTestAnd {
    public static void main(String[] args) {
        if(trueMethod() && falseMethod()) {
            System.out.println("Both are true");
        } else {
            System.out.println("Both are true");
        }
    }
    public static boolean trueMethod() {
        System.out.println("Within trueMethod()");
        return true;
    }
    public static boolean falseMethod() {
        System.out.println("Within falseMethod()");
        return false;
    }
}
