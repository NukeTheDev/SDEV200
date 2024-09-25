package ChapterFour.Excercise4_5;

public class Team 
{
    private String hsName, sport, teamName;
    public final static String MOTTO = "Sportsmanship!";

    public Team(String name, String sp, String team)
   {
      hsName = name;
      sport = sp;
      teamName = team;
   }
   public String getHsName()
   {
      return hsName;
   }
   public String getSport()
   {
    return sport;
   }
   public String getTeamName()
   {
    return teamName;
   }
   public String getTeamMotto()
   {
    return MOTTO;
   }
}
