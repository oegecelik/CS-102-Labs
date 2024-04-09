public class Flying extends Vehicle{
   
    private static int Fcount = 1;

    public Flying(){
        super();
        this.name = "F" + Fcount++;
        speed = Lab03.rand.nextInt(11)+20;
        fuel = Lab03.rand.nextInt(11) + 20;
        roadFactors[0]=1.00f;
        roadFactors[1]=1.00f;
        roadFactors[2]=1.00f;
    }
    @Override
    public void move(int roadType) {
        currentRoadFactor = roadFactors[roadType];
        super.move(roadType);
    }
    public static void reset(){
        Fcount = 1;
    }
    }