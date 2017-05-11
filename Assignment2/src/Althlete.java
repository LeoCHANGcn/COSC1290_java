import java.util.Random;

//package default;
/**
 * @author Siqi Yang s3583574
 *
 */
public abstract class Althlete extends Person{
	
	private int totalPoints;
	private int currentPoinnts;
	private int completeTime;
	
	public Althlete(int ID, String name, int age, String state)
	{
		super(ID, name, age, state);
		this.totalPoints=0;
		this.setCurrentPoinnts(0);
		this.completeTime=0;
	}
	//create random time
	public void complete(){}
	public void complete2(String type)
	{
		if(type == "Swim"){
			Random random=new Random();
			setCompleteTime(random.nextInt(100)+100);
			
		}else if(type == "Cycle"){
			Random random=new Random();
			setCompleteTime(random.nextInt(300)+500);
			
		}else if(type == "Run"){
			Random random=new Random();
			setCompleteTime(random.nextInt(10)+10);
			
		}
		
	}
	

	public int getTotalPoints() {
		return totalPoints;
	}
	//used to update the total points
	public void setTotalPoints() {
		this.totalPoints += this.currentPoinnts;
	}
	public void setTotalPointsForDis(int a)
	{
		this.totalPoints=a;
	}
	public int getCurrentPoinnts() {
		return currentPoinnts;
	}

	public void setCurrentPoinnts(int currentPoinnts) {
		this.currentPoinnts = currentPoinnts;
	}
	public int getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(int completeTime) {
		this.completeTime = completeTime;
	}
	

}
