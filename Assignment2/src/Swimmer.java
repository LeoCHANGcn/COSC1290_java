import java.util.Random;
/**
 * @author Siqi Yang s3583574
 *
 */
public class Swimmer extends Althlete {
	public Swimmer(int ID, String name, int age, String state)
	{
		super(ID,name,age,state);
	}
	
	public void complete()
	{
		Random random=new Random();
		super.setCompleteTime(random.nextInt(100)+100);
	}

}
