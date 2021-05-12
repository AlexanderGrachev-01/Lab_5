import java.util.ArrayList;

public class Controller implements Runnable
{
    private int elevatorCapacity;
    private int floorsAmount;
    private ArrayList<Elevator> elevators;
    private ArrayList<Integer> elevatorsIDs;
    private ArrayList<Passenger> passengers;

    public ArrayList<Passenger> getPassengers()
    {
        return passengers;
    }

    public void setPassengers()
    {
        this.passengers = new ArrayList<>();
    }

    public void addPassenger(Passenger passenger)
    {
        getPassengers().add(passenger);
    }

    public void popPassenger(Passenger passenger)
    {
        getPassengers().remove(passenger);
    }

    Controller(int elevatorsAmount, int elevatorCapacity, int floorsAmount)
    {
        setPassengers();
        setFloorsAmount(floorsAmount);
        setElevatorCapacity(elevatorCapacity);

        setElevatorsIDs();

        for (int i = 0; i < elevatorsAmount; i++)
        {
            getElevatorsIDs().add(i);
        }

        setElevators();

        for (int i = 0; i < elevatorsAmount; i++)
        {
            getElevators().add(new Elevator(i));
        }
    }

    public void run()
    {
        while (true)
        {
            int activeElevatorsInIteration = 0;
            for (int elevatorID : getElevatorsIDs())
            {
                getElevators().get(elevatorID).movePassengers();
            }

            System.out.println("---------LOGS----------");

            for (int elevatorID: getElevatorsIDs())
            {
                Elevator tempElevator = getElevators().get(elevatorID);
                int currentFloor = tempElevator.getCurrentFloor();
                int freePlacesOfElevator = getElevatorCapacity() - tempElevator.getPassengersAmount();

                if (freePlacesOfElevator > 0)
                {
                    ArrayList<Passenger> upPassengers = new ArrayList<>();
                    ArrayList<Passenger> downPassengers = new ArrayList<>();

                    ArrayList<Passenger> floorUpPassengers = new ArrayList<>();
                    ArrayList<Passenger> floorDownPassengers = new ArrayList<>();

                    for (Passenger passenger : getPassengers())
                    {
                        if (getFloorsAmount() > passenger.getStartFloor() &&
                                passenger.getStartFloor() >= currentFloor)
                            upPassengers.add(passenger);
                        else if (passenger.getStartFloor() < currentFloor)
                            downPassengers.add(passenger);

                        if (passenger.getStartFloor() == tempElevator.getCurrentFloor()
                                && passenger.getDirection() == Direction.UP)
                        {
                            floorUpPassengers.add(passenger);
                        }
                        else if (passenger.getStartFloor() == tempElevator.getCurrentFloor()
                                && passenger.getDirection() == Direction.DOWN)
                        {
                            floorDownPassengers.add(passenger);
                        }
                    }

                    if (freePlacesOfElevator == getElevatorCapacity())
                    {
                        if (tempElevator.getDirection() == Direction.UP &&
                                upPassengers.size() < activeElevatorsInIteration * getElevatorCapacity())
                        {
                            tempElevator.setDirection(Direction.FREE);
                        }

                        else if (tempElevator.getDirection() == Direction.DOWN &&
                                downPassengers.size() < activeElevatorsInIteration * getElevatorCapacity())
                        {
                            tempElevator.setDirection(Direction.FREE);
                        }
                    }

                    if (currentFloor == getFloorsAmount() - 1)
                    {
                        if (downPassengers.size() > activeElevatorsInIteration * getElevatorCapacity())
                            tempElevator.setDirection(Direction.DOWN);
                        else
                            tempElevator.setDirection(Direction.FREE);
                    }

                    if (currentFloor == 0)
                    {
                        if (upPassengers.size() > activeElevatorsInIteration * getElevatorCapacity())
                            tempElevator.setDirection(Direction.UP);
                        else
                            tempElevator.setDirection(Direction.FREE);
                    }

                    if (tempElevator.getDirection() == Direction.FREE)
                    {
                        if (floorUpPassengers.size() > floorDownPassengers.size()
                                && upPassengers.size() > activeElevatorsInIteration * getElevatorCapacity())
                        {
                            tempElevator.setDirection(Direction.UP);
                        }

                        else if (floorUpPassengers.size() <= floorDownPassengers.size()
                                && downPassengers.size() > activeElevatorsInIteration * getElevatorCapacity())
                        {
                            tempElevator.setDirection(Direction.DOWN);
                        }

                    }

                    ArrayList<Passenger> dominatePassengers = new ArrayList<>();

                    if (tempElevator.getDirection() == Direction.UP)
                    {
                        dominatePassengers = floorUpPassengers;
                    }
                    else if (tempElevator.getDirection() == Direction.DOWN)
                    {
                        dominatePassengers = floorDownPassengers;
                    }

                    while (dominatePassengers.size() != 0 &&
                            tempElevator.getPassengersAmount() < getElevatorCapacity())
                    {
                        tempElevator.addPassengers(dominatePassengers.get(0));
                        popPassenger(dominatePassengers.get(0));
                        dominatePassengers.remove(0);
                    }

                }
                else
                {
                    if (currentFloor == getFloorsAmount() - 1)
                        tempElevator.setDirection(Direction.DOWN);

                    if (currentFloor == 0)
                        tempElevator.setDirection(Direction.UP);
                }

                System.out.println("ID:" + elevatorID + " | Floor:"
                        + currentFloor + " | Passengers:"
                        + (getElevatorCapacity() - freePlacesOfElevator) + " | Direction:" + tempElevator.getDirection());

                activeElevatorsInIteration += 1;
            }
            System.out.println("------------------------");
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                System.out.println("Thread was interrupted");
            }

        }
    }

    public int getElevatorCapacity()
    {
        return this.elevatorCapacity;
    }

    public void setElevatorCapacity(int elevatorCapacity)
    {
        this.elevatorCapacity = elevatorCapacity;
    }

    public int getFloorsAmount()
    {
        return this.floorsAmount;
    }

    public void setFloorsAmount(int floorsAmount)
    {
        this.floorsAmount = floorsAmount;
    }

    public ArrayList<Elevator> getElevators()
    {
        return this.elevators;
    }

    public void setElevators()
    {
        this.elevators = new ArrayList<>();
    }

    public ArrayList<Integer> getElevatorsIDs()
    {
        return this.elevatorsIDs;
    }

    public void setElevatorsIDs()
    {
        this.elevatorsIDs = new ArrayList<>();
    }
}
