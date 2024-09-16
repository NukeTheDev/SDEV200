package ChapterFive.Tutorial5;

public class PreschoolStudent 
{
    int idNum;
    int age;
    public final int HIGH_ID = 9999;
    public final int HIGH_AGE = 6;

    public PreschoolStudent(int num, int studentAge)
    {
        setIdNum(num);
        setAge(studentAge);
    }

    public void setIdNum(int num)
    {
        if(num <= HIGH_ID){
            idNum = num;
        } else {
            idNum = 0;
        }
    }
    public void setAge(int studentAge)
    {
        if (studentAge <= HIGH_AGE){
            age = studentAge;
        } else {
            age = 0;
        }
    }

    public int getIdNum() {
        return idNum;
    }
    public int getAge() {
        return age;
    }
}
    