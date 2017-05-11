import java.util.Random;
/**
 * @author Siqi Yang s3583574
 *
 */
public class SuperAthlete extends Althlete {
	private String gameType;
	public SuperAthlete(int ID, String name, int age, String state)
	{
		super(ID,name,age,state);
		gameType="None";
	}
	public SuperAthlete(int ID, String name, int age, String state, String gameType)
	{
		super(ID,name,age,state);
		this.gameType = gameType;
	}
	
	public void complete(String type)
	{
		if(type == "Swim"){
			Random random=new Random();
			super.setCompleteTime(random.nextInt(100)+100);
			
		}else if(type == "Cycle"){
			Random random=new Random();
			super.setCompleteTime(random.nextInt(300)+500);
			
		}else if(type == "Run"){
			Random random=new Random();
			super.setCompleteTime(random.nextInt(10)+10);
			
		}
		
	}
	public String getType()
	{
		return this.gameType;
	}
	
	public void setType(String type)
	{
		this.gameType=type;
	}
	@Override
	public void complete() {
		// TODO Auto-generated method stub
		
	}

}
