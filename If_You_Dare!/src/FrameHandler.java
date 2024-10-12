import javax.swing.JFrame;

public class FrameHandler 
{
    private static JFrame currentFrame;

    public static void switchFrame(JFrame frame)
    {
        // switch to the latest frame
        if (currentFrame != frame)
        {
            currentFrame.setVisible(false);
            currentFrame = frame;
            currentFrame.pack();
            currentFrame.setLocationRelativeTo(null);
            currentFrame.setVisible(true);
        }
    }
    public void setFrame(JFrame frame)
    {
        this.currentFrame = frame;
    }
}
