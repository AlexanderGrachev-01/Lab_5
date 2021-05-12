public class RequestCreator implements Runnable
{
    private int floorsAmount;
    private int maxPeopleAmount;
    private Controller controller;

    RequestCreator(int floorsAmount, Controller controller, int maxPeopleAmount)
    {
        setFloorsAmount(floorsAmount);
        setController(controller);
        setMaxPeopleAmount(maxPeopleAmount);
    }

    public int getFloorsAmount()
    {
        return this.floorsAmount;
    }

    public void setFloorsAmount(int floorsAmount)
    {
        this.floorsAmount = floorsAmount;
    }

    public Controller getController()
    {
        return this.controller;
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public int getMaxPeopleAmount()
    {
        return maxPeopleAmount;
    }

    public void setMaxPeopleAmount(int maxPeopleAmount)
    {
        this.maxPeopleAmount = maxPeopleAmount;
    }

    public void run()
    {
        while(true)
        {
            int startFloor = (int)Math.floor(Math.random() * getFloorsAmount());
            int peopleAmount = (int)Math.floor(Math.random() * (getMaxPeopleAmount() + 1));


            for (int i = 0; i < peopleAmount; i++)
            {
                int targetFloor = (int)Math.floor(Math.random() * getFloorsAmount());

                while (startFloor == targetFloor)
                {
                    targetFloor = (int)Math.floor(Math.random() * getFloorsAmount());
                }

                getController().addPassenger(new Passenger(startFloor, targetFloor));
            }

            try
            {
                Thread.sleep(2500);
            }
            catch (InterruptedException e)
            {
                System.out.println("Thread was interrupted");
            }
        }
    }
}