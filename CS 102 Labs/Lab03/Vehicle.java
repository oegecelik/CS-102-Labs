public abstract class Vehicle{
    protected String name;
    protected float speed;
    protected float currentPosition = 0; 
    protected int fuel;
    protected float[] roadFactors = new float[3];
    protected float currentRoadFactor;

    //getters
    public String getName() {
        return name;
    }
    public float getSpeed() {
        return speed;
    }
    public float getCurrentPosition() {
        return currentPosition;
    }
    public int getFuel() {
        return fuel;
    }
    public float getCurrentRoadFactor(){
        return currentRoadFactor;
    }

    public void move(int roadType){
        if(fuel>0){
            currentPosition+=speed*currentRoadFactor;
            fuel--;
        }
    }
    public String getPositionalInfo(){
        return this.getName() + " - Position: " + this.getCurrentPosition()
        + " - Speed: " + this.getSpeed() + " - Fuel: " + this.getFuel();
    }
}