import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        try {
            System.out.println("Enter floors amount, please.");
            int floorsAmount = in.nextInt();

            System.out.println("Enter elevators amount, please.");
            int elevatorsAmount = in.nextInt();

            System.out.println("Enter elevators capacity, please.");
            int elevatorsCapacity = in.nextInt();

            System.out.println("Enter max people spawn amount (per request), please.");
            int maxPeopleAmount = in.nextInt();

            if (floorsAmount > 0 && elevatorsAmount > 0 && elevatorsCapacity > 0 && maxPeopleAmount > 0) {
                Controller controller = new Controller(elevatorsAmount, elevatorsCapacity, floorsAmount);
                RequestCreator requestCreator = new RequestCreator(floorsAmount, controller, maxPeopleAmount);

                Thread requestsThread = new Thread(requestCreator);
                Thread elevatorsThread = new Thread(controller);

                requestsThread.start();
                elevatorsThread.start();
            } else {
                System.out.println("All parameters must be bigger than 0!");
            }
        }
        catch (java.util.InputMismatchException e)
        {
            System.out.println("You must enter numbers not strings");
        }
    }
}
