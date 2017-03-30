
public abstract class Person {
	private int ID;
	private String name;
	private int age;
	private String state;
	
	public Person(int id,String name,int age, String state)
	{
		this.ID=id;
		this.name=name;
		this.age=age;
		this.state=state;
	}
	
	public int getID()
	{
		return this.ID;
	}
	public void setID(int ID)
	{
		this.ID=ID;
	}
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public int getAge()
	{
		return this.age;
	}
	public void setAge(int age)
	{
		this.age=age;
	}
	public String getState()
	{
		return this.state;
	}
	public void setState(String state)
	{
		this.state=state;
	}
	public void showPerson()
	{
		System.out.println("ID:"+this.ID+" Name:"+this.name+" Age:"+this.age+" State:"+this.state);
	}

}
