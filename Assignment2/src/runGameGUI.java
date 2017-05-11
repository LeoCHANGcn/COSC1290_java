/**
 * @author Shichao Zhang s3637077
 *
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class runGameGUI implements Initializable{
	@FXML
	private TextField gameIDInput;
	
	
	
	@FXML
	private ComboBox<String> selectGame;//Select a game type
	@FXML
	private ToggleGroup officer;
	
	//AABOUT THE TABLE
	@FXML
	private TableView<Althlete> tableView;
	@FXML
	private TableColumn<Althlete, CheckBox> tCheck;
	@FXML
	private TableColumn<Althlete,Number> tID;
	@FXML
	private TableColumn<Althlete,String> tName;
	@FXML
	private TableColumn<Althlete,Number> tAge;
	@FXML
	private TableColumn<Althlete,String> tType;
	@FXML
	private TableColumn<Althlete,String> tState;
	
	
	@FXML
	private RadioButton o1;
	@FXML
	private RadioButton o2;
	@FXML
	private RadioButton o3;
	@FXML
	private RadioButton o4;
	@FXML
	private RadioButton o5;
	@FXML
	private RadioButton o6;
	@FXML
	private RadioButton o7;
	@FXML
	private RadioButton o8;
	
	@FXML
	private Button run;
	@FXML
	private Button back;
	
	@FXML
	private TextField numOfAthShow;
	
	
	private boolean hasSelectedGameType;
	private boolean hasAssignOfficer;
	private boolean gameIDPattern;
	private boolean numOfAthIsOK;
	
	
	private static Scanner input;
	private static int gameSelected;
	private static int predictedWinnerID;
	private final static String prophetMessage="Congratulations! You make a correct prediction"
			+ " for the result!";
	private static Officer[] referee;
	private static Althlete[] athleteData;
	private static Game[] games;
	
	private static Random random;
	private static int[] participants;
	private static int[] currentGameResults;
	private static int currentSelectedNumOfAth=0;
	private static String selectedGameType;
	private static String gameID;
	private static int numOfParticipants;
	private static int numOfOfficer=0;
	
	private static String warningMessage="";
	private static String selectedReferee;
	
	//File reader
	private static BufferedReader fileInput=null;
	private static BufferedReader resultReader=null;
	private static BufferedReader athletPointReader=null;
	//File writer
	private static FileWriter resultWriter=null;
	private static FileWriter athletePointWriter=null;
	
	//About Database
	/*private Server hsqlServer = null;
	private Connection connection = null;
	private ResultSet rs = null;
	
	hsqlServer = new Server();
	hsqlServer.setLogWriter(null);
	hsqlServer.setSilent(true);
	hsqlServer.setDatabaseName(0, "TestDB");
	hsqlServer.setDatabasePath(0, "file:MYDB");
	
	hsqlServer.start();*/
	
	

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		//initialize game combo box
		//Reference:http://stackoverflow.com/questions/35260061/combobox-items-via-scene-builder
		selectGame.getItems().removeAll(selectGame.getItems());
		selectGame.getItems().addAll("Swim", "Cycle", "Run");
		selectGame.getSelectionModel().select("Swim");
	    tableView.autosize();
	    
	    //initialize participants
		iniAthlete();
		
		//
		iniReferee();
		
		//initialize participants table
		//Reference: http://stackoverflow.com/questions/20879242/get-checkbox-value-in-a-table-in-javafx
		renderTable();
		
		
	}

	public void runSelectedGame()
	{
		selectedGameType=selectGame.getValue().toString();
		Alert alert=new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning...");
		alert.setResizable(true);
		alert.setHeight(500);
		alert.getDialogPane().setPrefSize(300,400);
		
		alert.setHeight(Region.USE_COMPUTED_SIZE);
		alert.setHeaderText("Please check you input");
		gameID=gameIDInput.getText();
		
		if(gameID.length()==0)
		{
			warningMessage+="you must input a game ID.\n\n";
		}
		Pattern p=Pattern.compile("[S|C|R]\\d*\\d$");
		if(p.matcher(gameID).find())
		{
		}
		else if(gameID.length()!=0)
		{
			warningMessage+="gameID must has the pattern:S|R|C with digits\n"
					+ "For example, S021 means Swim 021\n\n";	
		}
		RadioButton selectedRadioButton =
		        (RadioButton) officer.getSelectedToggle();
		
		if(selectedRadioButton==null)
		{
			warningMessage+="Please assign an officer\n\n";
		}
		else{
			selectedReferee=selectedRadioButton.getText().toString();
			System.out.println(selectedRadioButton.getId());
			System.out.println(selectedGameType);
			System.out.println(gameID);
		}
		//get the participants indexs in athletesData and return some warning message;
		
		assignParticipants();
		if(currentSelectedNumOfAth<4)
		{
			warningMessage+="At least 4 participants, please add more participants!\n\n";
		}
		
		checkAthleteType();
		if(warningMessage.length()!=0)
		{
			alert.setContentText(warningMessage+"Please Check!!  \n\n");
			warningMessage="";
			alert.show();
		}
		else{
		
		//Now run the game
		for(int i=0;i<participants.length;++i)
		{
			if(athleteData[participants[i]] instanceof SuperAthlete)
			{
				athleteData[participants[i]].complete2(selectedGameType);
			}
			else
				athleteData[participants[i]].complete();
			currentGameResults[i]=athleteData[participants[i]].getCompleteTime();
		}
		//sort results
		Arrays.sort(currentGameResults);
		
		for(int i=0;i<participants.length;++i)
		{
			
			if(athleteData[participants[i]].getCompleteTime()==currentGameResults[0])
			{
				athleteData[participants[i]].setCurrentPoinnts(5);
				athleteData[participants[i]].setTotalPoints();
				
			}
			else if(athleteData[participants[i]].getCompleteTime()==currentGameResults[1])
			{
				athleteData[participants[i]].setCurrentPoinnts(2);
				athleteData[participants[i]].setTotalPoints();
				
			}
			else if(athleteData[participants[i]].getCompleteTime()==currentGameResults[2])
			{
				athleteData[participants[i]].setCurrentPoinnts(1);
				athleteData[participants[i]].setTotalPoints();
				
			}
			else
			{
				athleteData[participants[i]].setCurrentPoinnts(0);
				athleteData[participants[i]].setTotalPoints();
				//predictRightly=true;
			}
		}
		saveAllResults();
		saveAllPoints();
		showCurrentGameResult();
		}
		
	}
	
	public void setSuperAthleteGameType(SuperAthlete a,String type)
	{
		a.setType(type);
	}
	
	public void saveAllPoints()
	{
		String s="";
		try {
			resultWriter=new FileWriter("./data/allPoints.txt");
			for(Althlete a:athleteData)
			{
				s+=a.getID()+","
						+a.getName()+","
						+a.getAge()+","
						+a.getClass().toString().substring(6)+","
						+a.getState()+","
						+a.getTotalPoints()+"\n";
				resultWriter.write(s);
				s="";
			}
			resultWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void showCurrentGameResult()
	{
		System.out.println("AthleteID  currentPoints  totalPoints");
		try {
			resultWriter=new FileWriter("./data/current.txt");
			String s=""+gameID+","
					+selectedReferee+",";
			for(int i=0;i<participants.length;++i)
			{
				 s+=athleteData[participants[i]].getID()+","
						 +athleteData[participants[i]].getName()+","
						 +athleteData[participants[i]].getAge()+","
						 +athleteData[participants[i]].getClass().toString().substring(6)+","
						 +athleteData[participants[i]].getState()+","
						 +athleteData[participants[i]].getCompleteTime()+","
						 +athleteData[participants[i]].getCurrentPoinnts()+","
						 +athleteData[participants[i]].getTotalPoints();
				 if (athleteData[participants[i]].getCurrentPoinnts()==5)
				 {
					 s+=","+1;
				 }
				 else if(athleteData[participants[i]].getCurrentPoinnts()==2)
				 {
					 s+=","+2;
				 }
				 else if(athleteData[participants[i]].getCurrentPoinnts()==1)
				 {
					 s+=","+3;
				 }
				 else
				 {
					 s+=","+0;
				 }
				 s+="\n";
				 resultWriter.write(s);
				 s=""+gameID+","
							+selectedReferee+",";
			}
			resultWriter.close();
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0;i<participants.length;++i)
		{
			System.out.println(athleteData[participants[i]].getID()+
					"            "+athleteData[participants[i]].getCurrentPoinnts()+
					"            "+athleteData[participants[i]].getTotalPoints());
		}
		
		for(int i=0;i<participants.length;++i)
			System.out.println(currentGameResults[i]);
		//save current result
		
		try {
			Stage primaryStage=new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("displayCurrentResult.fxml"));
			
            primaryStage.setTitle("results ...");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveAllResults()
	{
		try {
			resultWriter=new FileWriter("./data/result.txt",true);
			String s=gameID+","
					+selectedReferee+","+participants.length+","+selectedGameType+"\n";
			resultWriter.write(s);
			for(int i=0;i<participants.length;++i)
			{
				s="";
				 s+=athleteData[participants[i]].getID()+","
						 +athleteData[participants[i]].getName()+","
						 +athleteData[participants[i]].getAge()+","
						 +athleteData[participants[i]].getClass().toString().substring(6)+","
						 +athleteData[participants[i]].getState()+","
						 +athleteData[participants[i]].getCompleteTime()+","
						 +athleteData[participants[i]].getCurrentPoinnts()+","
						 +athleteData[participants[i]].getTotalPoints();
				 if (athleteData[participants[i]].getCurrentPoinnts()==5)
				 {
					 s+=","+1;
				 }
				 else if(athleteData[participants[i]].getCurrentPoinnts()==2)
				 {
					 s+=","+2;
				 }
				 else if(athleteData[participants[i]].getCurrentPoinnts()==1)
				 {
					 s+=","+3;
				 }
				 else
				 {
					 s+=","+0;
				 }
				 s+="\n";
				 resultWriter.write(s);
				 s=""+gameID+","
							+selectedReferee+",";
			}
			resultWriter.close();
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void checkAthleteType()
	{
		String type=selectGame.getValue().toString();
		
		
		for (int i:participants)
		{
			String s=athleteData[i].getClass().toString().substring(6);
			System.out.println(s);
			switch(type)
			{
			case "Swim":
				
				if(!(s.equals("Swimmer")) && !(s.equals("SuperAthlete")))
				{
					warningMessage+="ID"+(i+9)+"  You can only select Swimmers "
							+ "or SuperAthletes\n\n";
				}
				break; 
			case "Cycle":
				if(!s.equals("Cyclist") && !s.equals("SuperAthlete"))
				{
					warningMessage+="ID"+(i+9)+"  You can only select Cyclists "
							+ "or SuperAthletes\n\n";
				}
				break;
			case "Run":
				if( !s.equals("SuperAthlete") && !s.equals("Sprinter"))
				{
					warningMessage+="ID: "+(i+9)+"  You can only select Sprinters "
							+ "or SuperAthletes\n\n";
				}
				break;
			default:
				break;
			}
		}
	}
	@FXML
	public void backToMainWindow()
	{
		displayAllResults();
	}
	public void displayAllResults() 
	{
		try {
			fileInput=new BufferedReader(new FileReader("./data/result.txt"));
			String next=fileInput.readLine();
			getAResult(next,fileInput);
		} 
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void getAResult(String s, BufferedReader f)
	{
		if(s==null)
		{
			
		}
		else
		{
			int num=Integer.parseInt(s.split(",")[2].toString());
			while(num!=0)
			{
				try {
					s=f.readLine();
					System.out.println(s);
					num-=1;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			try {
				s=f.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getAResult(s,f);
		}
	}
	//render table of list
	public void renderTable() 
	{
		final ObservableList<Althlete> data=FXCollections.observableArrayList(
				athleteData
				);
		
		tCheck.setCellValueFactory(c-> c.getValue().getCB().getCheckBox());
		tID.setCellValueFactory(c -> new SimpleIntegerProperty( c.getValue().getID() ));
		tName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName().toString()) );
		tState.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getState()));
		tType.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getClass().toString().substring(5)));
		tAge.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getAge()));
		tableView.setItems(data);
		updateNum();
	}
	public String assignParticipants()
	{
		currentSelectedNumOfAth=0;
		ObservableList<Althlete> list=tableView.getItems();
		for(Althlete o:list)
        {
			if(o.getCB().isSelected())
				currentSelectedNumOfAth+=1;
        }
		participants=new int[currentSelectedNumOfAth];
		currentGameResults=new int[currentSelectedNumOfAth];
		currentSelectedNumOfAth=0;
		
		//Use participants to store the index of selected athletes
		//i is the index of participants
		//j is the index of athleteData which contains All Athletes)
		//We use athleteData[participants[i]] to access the selected athletes later
		int i=0;int j=0;
        for(Althlete o:list)
        {
        	//o.showPerson();
            if(o.getCB().isSelected())
            {
            	currentSelectedNumOfAth+=1;
            	if(currentSelectedNumOfAth>8)
            	{
            		warningMessage+="Too many participants!!\n\n";
            	}
            	else
            	{
            		participants[i]=j;
            		i++;
            	}	
            }
            ++j;
        }
        for (int k : participants)
        {
        	System.out.println(k);
        }
		numOfAthShow.setText(""+currentSelectedNumOfAth);
		return warningMessage;
	}
	@FXML
	public void updateNum()
	{
		currentSelectedNumOfAth=0;
		ObservableList<Althlete> list=tableView.getItems();
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Warning...");
		alert.setContentText("You cannot select more than 8 althletes!!!");
		alert.setHeaderText("Too many Athletes!");
        for(Althlete o:list)
        {
        	//o.showPerson();
            if(o.getCB().isSelected())
            {
            	currentSelectedNumOfAth+=1;
            	if(currentSelectedNumOfAth>8)
            	{
            		alert.show();
            		//currentSelectedNumOfAth=0;
            	}
            }
        }
		numOfAthShow.setText(""+currentSelectedNumOfAth);
	}
	
	public Althlete[] getathleteData()
	{
		return athleteData;
	}
	public int[] getParticipants()
	{
		return participants;
	}
	public static void delay()
	{
		for(int i=0;i<=3;++i)
		{
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("... ");
		}
		System.out.print("\n");
	}
	
	public static void iniAthlete()
	{
		//read data from participants.txt file
		numOfParticipants=0;
		try {
			fileInput=new BufferedReader(new FileReader("./data/participants.txt"));
			String next=fileInput.readLine();
			while(next!=null)
			{
				String[] s=next.split(", ");
				String type=s[1].toString();
				if(type.equals("swimmer")||type.equals("cyclist")||type.equals("sprinter")||type
						.equals("superAthlete"))
				{
					numOfParticipants+=1;
				}
				
				next=fileInput.readLine();
			}
			fileInput.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(numOfParticipants);
		athleteData=new Althlete[numOfParticipants];
		try {
			fileInput=new BufferedReader(new FileReader("./data/participants.txt"));
			String next=fileInput.readLine();
			
			int i=0;
			while (next != null)
			{
				String[] s=next.split(", ");
				//System.out.println(s[1]);
				int id=Integer.parseInt(s[0].toString());
				int age=Integer.parseInt(s[3].toString());
				if(s[1].toString().equals("swimmer"))
				{
					athleteData[i]=new Swimmer(id,s[2].toString(),age,s[4].toString());++i;
				}
				else if(s[1].toString().equals("cyclist"))
				{
					athleteData[i]=new Cyclist(id,s[2].toString(),age,s[4].toString());++i;
				}
				else if(s[1].toString().equals("sprinter"))
				{
					athleteData[i]=new Sprinter(id,s[2].toString(),age,s[4].toString());++i;
				}
				else if(s[1].toString().equals("superAthlete"))
				{
					athleteData[i]=new SuperAthlete(id,s[2].toString(),age,s[4].toString());++i;
				}
				next = fileInput.readLine();
				
			}
			fileInput.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File cannot be found!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void iniReferee()
	{
		numOfOfficer=0;
		try {
			fileInput=new BufferedReader(new FileReader("./data/participants.txt"));
			String next=fileInput.readLine();
			while(next!=null)
			{
				String[] s=next.split(", ");
				String type=s[1].toString();
				if(type.equals("officer"))
				{
					numOfOfficer+=1;
				}
				
				next=fileInput.readLine();
			}
			fileInput.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File cannot be found!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		referee=new Officer[numOfOfficer];
		System.out.print(numOfOfficer);
		try {
			fileInput=new BufferedReader(new FileReader("./data/participants.txt"));
			String next=fileInput.readLine();
			
			int i=0;
			while (next != null)
			{
				String[] s=next.split(", ");
				//System.out.println(s[1]);
				int id=Integer.parseInt(s[0].toString());
				int age=Integer.parseInt(s[3].toString());
				if(s[1].toString().equals("officer"))
				{
					referee[i]=new Officer(id,s[2].toString(),age,s[4].toString());++i;
				}
				
				next = fileInput.readLine();
			}
			fileInput.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File cannot be found!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i!=4;++i)
		{
			referee[i]=new Officer(i,"referee"+(i+1),(i+3)*6,"VIC");
			//referee[i].showPerson();
		}
		
	}
	public static boolean isIn(int a,int[] b)
	{
		boolean sign=false;
		for(int i=0;i<b.length;++i)
		{
			if(a==b[i])
			{
				sign=true;
			}
		}
		return sign;
	}
	
	
	public static void displayAllGames()
	{
		//fakeClear();
		System.out.println("Final results of all games");
		System.out.println("\n*****\nWant to back to main menu?(y or n)");
		//String s=input.next();
		while(!input.next().equals("y"))
		{
			System.out.println("\n*****\nWant to back to main menu?(y or n)");
		}
		System.out.println("Back to main menu in 3 seconds...");
	}
	public static void displayAllPoints()
	{
		//fakeClear();
		System.out.println("Final points of all athletes:");
		for(int i=0;i<athleteData.length;++i)
		{
			athleteData[i].showPerson();
			System.out.println("Total points: "+athleteData[i].getTotalPoints());
		}
		System.out.println("\n*****\nWant to back to main menu?(y or n)");
		//String s=input.next();
		while(!input.next().equals("y"))
		{
			System.out.println("\n*****\nWant to back to main menu?(y or n)");
		}
		System.out.println("Back to main menu in 3 seconds...");
	}
	
	
}
