public class Quadruped extends Vehicle{

    private static int Qcount = 1;

    public Quadruped(){
        super();
        this.name = "Q" + Qcount++;
        speed = Lab03.rand.nextInt(21)+20;
        fuel = Lab03.rand.nextInt(11) + 10;
        roadFactors[0]=0.50f;
        roadFactors[1]=1.00f;
        roadFactors[2]=0.75f;
    }
    @Override
    public void move(int roadType) {
        currentRoadFactor = roadFactors[roadType];
        super.move(roadType);
    }
    public static void reset(){
        Qcount = 1;
    }
}
