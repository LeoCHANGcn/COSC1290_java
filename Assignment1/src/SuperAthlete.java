import java.util.Random;
public class SuperAthlete extends Althlete {
	private String gameType;
	public SuperAthlete(int ID, String name, int age, String state, String gameType)
	{
		super(ID,name,age,state);
		this.gameType = gameType;
	}
	
	public void complete()
	{
		if(this.gameType == "Swimming"){
			Random random=new Random();
			super.setCompleteTime(random.nextInt(100)+100);
			
		}else if(this.gameType == "Cycling"){
			Random random=new Random();
			super.setCompleteTime(random.nextInt(300)+500);
			
		}else if(this.gameType == "Running"){
			Random random=new Random();
			super.setCompleteTime(random.nextInt(10)+10);
			
		}
		
	}

}
