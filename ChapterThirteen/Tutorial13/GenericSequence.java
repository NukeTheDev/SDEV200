package ChapterThirteen.Tutorial13;

public class GenericSequence 
{
    public static void main(String[] args) 
    {
        Character grade = 'A';
        Integer score = 0;
        Double pay = 21.34;
        String word = "Hello";
        duplicate(grade, 5);
        duplicate(score, 10);
        duplicate(pay, 3);
        duplicate(word, 7);
    }
    public static <T> void duplicate(T obj, int num)
    {
        int x;
        for(x=0; x < num; ++x)
        {
            System.out.print(obj + " ");
        }
        System.out.println();
    }
}
