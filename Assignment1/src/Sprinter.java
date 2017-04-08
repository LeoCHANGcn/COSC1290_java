import java.util.Random;
public class Sprinter extends Althlete {
	public Sprinter(int ID, String name, int age, String state)
	{
		super(ID,name,age,state);
	}
	
	public void complete()
	{
		Random random=new Random();
		super.setCompleteTime(random.nextInt(10)+10);
	}

}
