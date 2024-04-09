import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Lab01{
   static Scanner in = new Scanner(System.in);

   public static void mainMenu(){
    System.out.println();
    String text = """
        1. Prime Numbers
        2. Volume Filling
        3. Union Area
        4. Random Questions
        5. Exit

        Please enter your choice:""";
    System.out.print(text + " ");
   }

//This method is called only for the main menu since only one integer is to be read.
   public static int asker(boolean isUserAtMainMenu) {
    int toReturn = 0;
    boolean stop = false;
    while(stop == false){
        if(in.hasNextInt()){
            toReturn = in.nextInt();
            if(toReturn > 0 && (toReturn <= 5 || isUserAtMainMenu == false)){
                stop = true;
            }
            else if (toReturn <= 0){
                System.out.print("Cannot process non-positive integers. Please enter a positive integer: ");
            }
            else if(toReturn> 5 && isUserAtMainMenu == true){
                System.out.print("Input integer out of bounds. Please enter an integer between 1 and 5: ");
            }
        }
        else{
            System.out.print("Wrong input. Please enter an integer: ");                
        }

        in.nextLine();
    }
    return toReturn;
   }
    //This method is called each time the user is going to input a value. This is in order to avoid non-integer values.
    public static int[] asker(int size, boolean isOp2, boolean isOp3){

        int[] toReturn = new int[size];
        boolean stop = false;
        int count = 0;

        while(stop == false){
            if(in.hasNextInt()){                
                toReturn[count] = in.nextInt();
                if((toReturn[count] >= 0 && isOp2==false) ||(toReturn[count]>0 && isOp2 == true) || isOp3){
                    count++;
                }
                else{
                    if(isOp2 == true){
                        System.out.print("Cannot process non-positive integers. Please enter a positive integer: ");
                    }
                    else{
                        System.out.print("Cannot process negative integers. Please enter a positive integer: ");
                    }
                    in.nextLine(); 
                } 
            }

            else{
                System.out.print("Wrong input. Please enter an integer: ");
                in.next();
                in.nextLine();               
            }
            if(count == size){
                stop = true;
            }
        }
        in.nextLine();
        return toReturn;
    }


    //This method checks if the input number is prime, works as a helper method for first task.
    public static boolean isPrime(int x){
        for(int i = 2; i<x/2; i++){
            if(x%i == 0){
                return false;
            }
        }
        if(x == 1 || x == 0){
            return false;
        }
        return true;
    }

    //Method for Operation 1
    public static void PrimePrinter(int x, int y){
        
        int low = 0;
        int high = 0;
        if(x<y){
            low = x;
            high = y;
        }
        else{
            low = y;
            high = x;
        }
        int[] primes = new int[high-low+1];
        int count = 0;
        for(int i = low; i<=high; i++){
            if(isPrime(i) == true){
                primes[count++] = i;
            }            
        }
        if(count == 0){
            System.out.println("There are no prime numbers in this range.");
            System.out.println();
            return;
        }
        System.out.print("Result: Prime numbers in range [" + low + ", " + high + "] are ");
        for (int i = 0; i < count-1; i++){
            System.out.print(primes[i] + ", ");
        }
        System.out.println(primes[count-1]);
        System.out.println();
    }
    //Helper method for Op2
    public static int GCD(int x, int y) {
        int low = 0;
        int high = 0;
        if(x<y){
            low = x;
            high = y;
        }
        else if(x>y){
            low = y;
            high = x;
        }
        else{
            return x;
        }
        int check = low;
        while(check > 1){
            if(low % check == 0 && high % check == 0){
                return check;
            }
            check--;
        }
        return check;
    }

    //Method for Operation 2
    public static void cubeNumberPrinter(int x, int y, int z) {
        int GCD = GCD(x,y);
        GCD = GCD(GCD,z);        
        int cubeNumber = (x*y*z)/(GCD*GCD*GCD);     
        System.out.println("Result: Using cubes of edge length " + GCD + " you need " + cubeNumber +  " blocks minimum.");
        System.out.println();           
    }

    //Method for Operation 3
    public static void unionPrinter(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        int xIntersection = 0;
        int yIntersection = 0;
        /*I will think of the x and y lines as infinite sets of numbers, such that the interval [0,1] corresponds to 1,
        the interval [1,2] corresponds to 2 and so on. This way I will convert the intervals from real numbers into sets of integers.
        */

        ArrayList<Integer> elementsOfFirstX = new ArrayList<Integer>();
        ArrayList<Integer> elementsOfSecondX = new ArrayList<Integer>();
        int x1E = x1+w1;
        int x2E = x2+w2;

        for(int i = (x1+1); i<=x1E; i++){
            elementsOfFirstX.add(i);
        }
        for(int i = (x2+1); i<=x2E; i++){
            elementsOfSecondX.add(i);
        }
        for(int i = 0; i< elementsOfFirstX.size(); i++){
            if(elementsOfSecondX.contains(elementsOfFirstX.get(i))){
                xIntersection++;
            }
        }


        ArrayList<Integer> elementsOfFirstY = new ArrayList<Integer>();
        ArrayList<Integer> elementsOfSecondY = new ArrayList<Integer>();
        int y1E = y1-h1;
        int y2E = y2-h2;

        for(int i = (y1E+1); i<=y1; i++){
            elementsOfFirstY.add(i);
        }
        for(int i = (y2E+1); i<=y2; i++){
            elementsOfSecondY.add(i);
        }
        for(int i = 0; i< elementsOfFirstY.size(); i++){
            if(elementsOfSecondY.contains(elementsOfFirstY.get(i))){
                yIntersection++;
            }
        }
        int intersectionArea = xIntersection * yIntersection;
        int totalArea = (w1*h1) + (w2*h2);
        int unionArea = totalArea - intersectionArea;
        System.out.println("Result: Intersection area is " + intersectionArea + " thus the total area of the union is " + unionArea + ".");
        System.out.println();

    }

    public static void main(String[] args) {
        Random rand = new Random();
        boolean stop = false;

        while(stop == false){

            mainMenu();
            int userChoice = asker(true);
            
            if(userChoice == 1){
                System.out.println("-Find the prime numbers in the range between X and Y.");
                System.out.print("Please enter X, Y: ");

                int firstOpArray[] = asker(2, false, false);
                PrimePrinter(firstOpArray[0], firstOpArray[1]);
            }
            
            if(userChoice == 2){
                String text = """
                    - A rectangular prism volume of dimensions X, Y and Z is to be filled using
                    cube blocks. What is the minimum number of cubes required?
                    Please enter X, Y, Z: """;
                        System.out.print(text + " ");
                        int secondOpArray[] = asker(3, true, false);
                        cubeNumberPrinter(secondOpArray[0], secondOpArray[1], secondOpArray[2]);
                        
            }
           
            if(userChoice == 3){
                String text = """
                    - What is the area of the union of two rectangles R1 and R2, where top left
                    corner of R1 is (X1,Y1) and its size is (W1,H1), and top left corner of R2
                    is (X2,Y2) and its size is (W2,H2)?

                    Please enter X1, Y1, W1, H1, X2, Y2, W2, H2: """;                        
                System.out.print(text + " ");
                int thirdOpArray[] = asker(8, false, true);
                unionPrinter(thirdOpArray[0], thirdOpArray[1], thirdOpArray[2], thirdOpArray[3], thirdOpArray[4], thirdOpArray[5],
                thirdOpArray[6], thirdOpArray[7]);
            }
           
           
            if(userChoice == 4){
                System.out.print("Please enter the number of questions you want: ");
                int questionCount = asker(false);
                int currentQuestion = 0;
                System.out.println();
                while(questionCount>0){
                    currentQuestion++;
                    int questionType = rand.nextInt(3) + 1;
                    System.out.print(currentQuestion + ") ");

                    if(questionType == 1){
                        int bound1 = rand.nextInt(50);
                        int bound2 = rand.nextInt(50);
                        int lowerBound = 0;
                        int higherBound = 0;

                        if(bound1<bound2){
                            lowerBound = bound1;
                            higherBound = bound2;
                        }
                        else{
                            lowerBound = bound2;
                            higherBound = bound1;
                        }
                        System.out.println("Find the prime numbers in the range between [" + lowerBound + ", " + higherBound + "].");
                        System.out.println();
                        PrimePrinter(lowerBound, higherBound);

                    }
                    else if(questionType == 2){
                        int k = rand.nextInt(10)+1;
                        int x = rand.nextInt(10)+1;
                        int y = rand.nextInt(10)+1;
                        int z = rand.nextInt(10)+1;
                        System.out.println("A rectangular prism volume of dimensions " + x + ", " + y + ", " + z + ", is to be filled\n" +
                        "using cube blocks. What is the minimum number of cubes required?");
                        System.out.println();
                        cubeNumberPrinter(k*x,k*y,k*z);

                    }
                    else if(questionType == 3){
                        int doTheyIntersect = rand.nextInt(3);
                        int x1; int x2; int y1; int y2;
                        if(doTheyIntersect<1){
                             x1 = rand.nextInt(10);
                             y1 = rand.nextInt(10);
                             x2 = rand.nextInt(10);
                             y2 = rand.nextInt(10);
                        }
                        else{
                            int xForBoth = rand.nextInt(10);
                            int yForBoth = rand.nextInt(10);
                            x1 = xForBoth;
                            x2 = xForBoth;
                            y1 = yForBoth;
                            y2 = yForBoth;                            
                        }
                        int w1 = rand.nextInt(10);
                        int h1 = rand.nextInt(10);
                        int w2 = rand.nextInt(10);
                        int h2 = rand.nextInt(10);
                        System.out.println("What is the area of the union of two rectangles R1 and R2, where top left \n" +
                        "corner of R1 is (" + x1 + ", " + y1 + ") and its size is (" + w1 + " ," + h1 + "), and top\n" +
                        "left corner of R2 is (" + x2 + ", " + y2 + ") and its size is (" + w2 + ", " + h2 + ")?" );
                        System.out.println();
                        unionPrinter(x1, y1, w1, h1, x2, y2, w2, h2);

                    }                   
                    questionCount--;                
                }

            }
           
            if(userChoice == 5){
                stop = true;
                System.out.println();
                System.out.println("Goodbye!");
                in.close();
            }
        }
       

        }
    }
