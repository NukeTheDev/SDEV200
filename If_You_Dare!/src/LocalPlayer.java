public class LocalPlayer extends Player {

    public LocalPlayer(String name) {
        super(name);
    }
    @Override
    public boolean isCPU()
    {
        return false;
    }
}