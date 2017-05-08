import java.util.Random;
/**
 * @author Siqi Yang s3583574
 *
 */
public class Cyclist extends Althlete {
	public Cyclist(int ID, String name, int age, String state)
	{
		super(ID,name,age,state);
	}
	
	public void complete()
	{
		Random random=new Random();
		super.setCompleteTime(random.nextInt(300)+500);
	}

}
