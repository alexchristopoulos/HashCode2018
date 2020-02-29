import java.util.ArrayList;

public class Vehicle {
	int id;
	int x=0,y=0;
	int used_until=0;
	ArrayList<Ride> rds = new ArrayList<Ride>();
	public Vehicle(int i)
	{
		id=i;
	}
}
