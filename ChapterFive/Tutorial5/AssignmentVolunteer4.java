package ChapterFive.Tutorial5;

public class AssignmentVolunteer4 {
    public static void main(String[] args) {

        switch(donationType)
        {
            case (CLOTHING_CODE):
            {
                volunteer = CLOTHING_PRICER;
                message = "a clothing donation";
                break;
            }
            case (FURNITURE_CODE) :
            {
                volunteer = FURNITURE_PRICER;
                message = "a furniture donation";
                break;
            }
            case (ELECTRONICS_CODE) :
            {
                volunteer = ELECTRONICS_PRICER;
                message = "an electronics donation";
                break;
            }
            case (OTHER_CODE) :
            {
                volunteer = OTHER_PRICER;
                message = "another donation type";
                break;
            }
            default:
            {
                volunteer = "invalid";
                message = "an invalid donation type";
            }
        }
    }
}
