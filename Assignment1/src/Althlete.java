
public class Althlete extends Person{
	private int ID;
	private String name;
	private int age;
	private String state;
	private String type;
	
	public Althlete(int ID, String name, int age, String state, String type)
	{
		super(ID, name, age, state);
		this.type=type;
	}

}
