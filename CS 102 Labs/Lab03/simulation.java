public class simulation{
    
    private int numberOfVehicles;
    private int totalRoadLength;
    private int remainingRoadLength;
    private int remainingRoadParts;
    private String[] roadTypeNames = {"Asphalt" , "Dirt" , "Stone"};
    private int[] roadType;
    private int [] roadLength;
    private Vehicle[] vehicles;
    private int roadParts;
    public simulation(){

    }
    private int getType(float position){
        int[] partialSums = new int[roadParts+1];
        partialSums[0] = 0;
        int coveredRoad = 0;
        for(int i=1; i<=roadParts; i++){
            coveredRoad += roadLength[i-1];
            partialSums[i] = coveredRoad;
        }
        for(int i = 0; i<roadParts; i++){
            if(position>= partialSums[i] && position< partialSums[i+1]){
                return roadType[i];
            }
        }
        return -1;
    }
    public void simulate(){

        boolean playAgain = true;
        while(playAgain == true){
        Wheeled.reset();
        Flying.reset();
        Quadruped.reset();
        System.out.print("Please enter the road length: ");
        totalRoadLength = Lab03.asker(false);
        System.out.println("The following road is generated: ");
        remainingRoadLength = totalRoadLength;
        roadParts = Lab03.rand.nextInt(5)+3;
        remainingRoadParts = roadParts;
        roadType = new int[roadParts];
        roadLength = new int[roadParts];
        for(int i=0; i<roadType.length; i++){
            roadType[i] = Lab03.rand.nextInt(3);
        }
        System.out.print("|");
        for(int i=0; i<roadParts; i++){
            System.out.print("-");                
            roadLength[i]=remainingRoadLength/(remainingRoadParts*2) * (Lab03.rand.nextInt(remainingRoadParts)+1);
            remainingRoadLength -= roadLength[i];
            remainingRoadParts--;
            if(i == roadLength.length-1){
                roadLength[i] += remainingRoadLength;
                remainingRoadLength = 0;
            }
            if(roadType[i]==0){
                System.out.print("Asphalt ");
            }
            else if(roadType[i]==1){
                System.out.print("Dirt ");
            }
            else if(roadType[i] == 2){
                System.out.print("Stone ");
            }
            System.out.print(roadLength[i]+"-|");
        }
        System.out.println();
        System.out.println();
        System.out.print("Please enter vehicle count: ");
        numberOfVehicles = Lab03.asker(true);
        vehicles = new Vehicle[numberOfVehicles];
        System.out.println();
        System.out.println("The following vehicles are generated:");
        for(int i =0; i<numberOfVehicles; i++){                
            int randomVehicle = Lab03.rand.nextInt(100)+1;
            if(randomVehicle<=40){
                vehicles[i]= new Wheeled();
            }
            else if(randomVehicle>40 && randomVehicle <=65){
                vehicles[i]= new Flying();
            }
            else if(randomVehicle>65){
                vehicles[i]= new Quadruped();
            }
            System.out.println(vehicles[i].getName() + " - Speed: " + vehicles[i].getSpeed() + " - Fuel: " + vehicles[i].getFuel());
        }
        
        boolean isThereAWinner = false;
        int turn = 1;

        //Start of simulation
        while(isThereAWinner == false){
            System.out.println();
            System.out.println("Turn " + turn++ + ":");
            for(Vehicle vehicle : vehicles){
                System.out.println(vehicle.getPositionalInfo());
            }

        System.out.println();
        System.out.println("Movements:");
        for(int i = 0; i<vehicles.length; i++){
            int type = getType(vehicles[i].getCurrentPosition());
            int speed = (int) vehicles[i].getSpeed();
            vehicles[i].move(type);
            float factor = vehicles[i].getCurrentRoadFactor();
            System.out.println(vehicles[i].getName() + " moves from " + roadTypeNames[type] + ", for " +
            speed + " * " + factor + " = " + ((float)speed*factor) + " units");
            if(vehicles[i].getCurrentPosition() >= totalRoadLength){
                System.out.println();
                System.out.println(vehicles[i].getName() + " finishes the race! Position: " + vehicles[i].getCurrentPosition() + " - Speed: " +
                vehicles[i].getSpeed() + " - Fuel: " + vehicles[i].getFuel());
                isThereAWinner = true;
                break;
            }
        }
        boolean areAllFuelsZero = true;
        for(int i=0; i<vehicles.length; i++){
            if(vehicles[i].getFuel()!= 0){
                areAllFuelsZero = false;
                break;
            }
        }
        if(areAllFuelsZero == true && isThereAWinner == false){
            isThereAWinner = true;
            System.out.println("All vehicles are out of fuel!");
            System.out.println();
            float maxValue = 0;
            int maxIndex = -1;
            for(int i = 0; i<vehicles.length; i++){
                System.out.println(vehicles[i].getPositionalInfo());
                if(vehicles[i].getCurrentPosition() > maxValue){
                    maxValue = vehicles[i].getCurrentPosition();
                    maxIndex = i;
                }
            }
            System.out.println();
            System.out.println("No one finished the race, but one participant performed the best:");
            System.out.println(vehicles[maxIndex].getName() + " - Position: " + vehicles[maxIndex].getCurrentPosition() + " - Speed: " +
                vehicles[maxIndex].getSpeed() + " - Fuel: " + vehicles[maxIndex].getFuel());
        }
    }
    System.out.println();
    System.out.println("End of simulation. Do you want to play again? Enter 1 for yes and 0 for no.");
    int choice = Lab03.continueAsker();
    if(choice == 0){
        playAgain = false;
    } // else it is true.
    }
}
}