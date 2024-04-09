public class Wheeled extends Vehicle{
    
    private static int Wcount = 1;
 
    public Wheeled(){
        super();
        this.name = "W" + Wcount++;
        speed = Lab03.rand.nextInt(11)+15;
        fuel = Lab03.rand.nextInt(11) + 20;
        roadFactors[0]=1.00f;
        roadFactors[1]=0.75f;
        roadFactors[2]=0.75f;
    }
    @Override
    public void move(int roadType) {
        currentRoadFactor = roadFactors[roadType];
        super.move(roadType);
    }
    public static void reset(){
        Wcount = 1;
    }
}