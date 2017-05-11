import java.util.Random;
/**
 * @author Siqi Yang s3583574
 *
 */
public class Sprinter extends Althlete {
	public Sprinter(int ID, String name, int age, String state)
	{
		super(ID,name,age,state);
	}
	
	public void complete()
	{
		//used for runner
		Random random=new Random();
		super.setCompleteTime(random.nextInt(10)+10);
	}

}
