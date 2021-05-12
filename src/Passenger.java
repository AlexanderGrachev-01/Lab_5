public class Passenger
{
    private int startFloor;
    private int targetFloor;
    private Direction direction;

    public Passenger(int startFloor, int targetFloor)
    {
        setStartFloor(startFloor);
        setTargetFloor(targetFloor);
        setDirection(startFloor, targetFloor);
    }

    public int getStartFloor()
    {
        return this.startFloor;
    }

    public void setStartFloor(int startFloor)
    {
        this.startFloor = startFloor;
    }

    public int getTargetFloor()
    {
        return this.targetFloor;
    }

    public void setTargetFloor(int targetFloor)
    {
        this.targetFloor = targetFloor;
    }

    public Direction getDirection()
    {

        return this.direction;
    }

    public void setDirection(int startFloor, int targetFloor)
    {
        if (startFloor < targetFloor)
            this.direction = Direction.DOWN;
        else if (startFloor > targetFloor)
            this.direction = Direction.UP;
        else
            this.direction = Direction.FREE;
    }
}
