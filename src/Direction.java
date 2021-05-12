public enum Direction
{
    DOWN(-1), FREE(0), UP(1);
    private int direction;

    Direction(int direction)
    {
        this.direction = direction;
    }

    public int getValue()
    {
        return this.direction;
    }
}