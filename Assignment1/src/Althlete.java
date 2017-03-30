
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
	abstract public void complete();
	

	public int getTotalPoints() {
		return totalPoints;
	}
	//used to update the total points
	public void setTotalPoints() {
		this.totalPoints += this.currentPoinnts;
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
