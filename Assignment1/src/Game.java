
public class Game {
	private String ID;
	private Officer referee;
	private int numOfAlthlete;
	private String type;
	
	//Constructor
	public Game(String ID, Officer referee, int numOfAlthelete, String type)
	{
		this.setID(ID);
		this.setReferee(referee);
		this.setNumOfAlthlete(numOfAlthelete);
		this.setType(type);
	}
	
	//All the get and set methods
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNumOfAlthlete() {
		return numOfAlthlete;
	}

	public void setNumOfAlthlete(int numOfAlthlete) {
		this.numOfAlthlete = numOfAlthlete;
	}

	public Officer getReferee() {
		return referee;
	}

	public void setReferee(Officer referee) {
		this.referee = referee;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	public void showGame()
	{
		System.out.println("*******************\nGame ID:"+this.ID);
		System.out.print("Referee: "+referee.getName());
		//this.referee.showPerson();
		System.out.println("  Number of athletes:"+this.numOfAlthlete+" Game type:"+this.type);
	}

}
