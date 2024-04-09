import java.util.Random;
import java.util.Scanner;

public class Lab03{
    static Random rand = new Random();
    static Scanner in = new Scanner(System.in);
    public static int asker(boolean isVehicleCheck){
        int input = -1;
        boolean check = false;
        while(check == false){
            if(in.hasNextInt()){
                input = in.nextInt();
                if(input>0){
                    check = true;
                }
                else{
                    System.out.println("Vehicle count must be a positive integer. Please enter vehicle count again: ");
                    in.nextLine();
                }
            }
            else{
                if(isVehicleCheck == true){
                    System.out.print("Vehicle count must be a positive integer. Please enter vehicle count again: ");
                    in.next();
                    in.nextLine();
                }
                else{
                    System.out.print("Road length must be a positive integer. Please enter road length again: ");
                    in.next();
                    in.nextLine();
                }
            }
            

        }
        in.nextLine();
        return input;
    }
    public static int continueAsker(){
        int input = -1;
        boolean check = false;
        while(check == false){
            if(in.hasNextInt()){
                input = in.nextInt();
                if(input==0 || input == 1){
                    check = true;
                }
                else{
                    System.out.println("Wrong input. Enter 1 to restart the program and 0 to exit: ");
                    in.nextLine();
                }
            }
            else{
                System.out.print("Wrong input. Enter 1 to restart the program and 0 to exit: ");
                in.next();
                in.nextLine();
            }
        }
        in.nextLine();
        return input;
    }
    public static void main(String[] args) {
        simulation sim = new simulation();
        sim.simulate();
    }
}