package ChapterFour.Excercise4_5;
import java.util.Scanner;

public class TestTeam 
{
    public static void main(String[] args) 
    {
        Team teamOne, teamTwo, teamThree;
        teamOne = setTeamData(); teamTwo = setTeamData(); teamThree = setTeamData();
        displayTeamData(teamOne); displayTeamData(teamTwo); displayTeamData(teamThree);
    }
    public static Team setTeamData()
    {
        String hsName, sport, teamName;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter highschool name >> ");
        hsName = input.nextLine();
        System.out.print("Enter the sport >> ");
        sport = input.nextLine();
        System.out.print("Enter the team name >> ");
        teamName = input.nextLine();
        Team tempTeam = new Team(hsName, sport, teamName);
        return tempTeam;
    }
    public static void displayTeamData(Team aTeam)
    {
        System.out.printf(
        "\nHigh school name: %s %n" +
        "Sport is: %s %n" + 
        "Team name is: %s %n " +
        "Team motto is: %s %n", 
         aTeam.getHsName(), aTeam.getSport(), aTeam.getTeamName(), aTeam.getTeamMotto());
    }
}