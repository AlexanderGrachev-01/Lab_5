import java.util.ArrayList;

public class Elevator
{
    private int ID;
    private int currentFloor;
    private Direction direction;
    private ArrayList<Passenger> passengers;

    Elevator(int ID)
    {
        setCurrentFloor(0);
        setID(ID);
        setDirection(Direction.FREE);
        setPassengers();
    }

    public int getID()
    {
        return this.ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public int getCurrentFloor()
    {
        return this.currentFloor;
    }

    public void setCurrentFloor(int currentFloor)
    {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection()
    {
        return this.direction;
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    public ArrayList<Passenger> getPassengers()
    {
        return this.passengers;
    }

    public void setPassengers()
    {
        this.passengers = new ArrayList<>();
    }

    public void addPassengers(Passenger passenger)
    {
        getPassengers().add(passenger);
    }

    public void movePassengers()
    {
        setCurrentFloor(getCurrentFloor() + getDirection().getValue());
        getPassengers().removeIf(passenger_ -> passenger_.getTargetFloor() == getCurrentFloor());
    }

    public int getPassengersAmount()
    {
        return getPassengers().size();
    }
}


