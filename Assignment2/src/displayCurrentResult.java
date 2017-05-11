import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public  class displayCurrentResult implements Initializable {
	private static BufferedReader fileInput=null;
	private static Althlete[] athleteData;
	private static int numOfAth=0;
	@FXML
	private TableView<Althlete> rTableView;
	@FXML
	private Label label;
	
	private static String gameID;
	private static String referee;
	@FXML
	private TableColumn<Althlete,Number> rID;
	@FXML
	private TableColumn<Althlete,String> rName;
	@FXML
	private TableColumn<Althlete,Number> rAge;
	@FXML
	private TableColumn<Althlete,String> rType;
	@FXML
	private TableColumn<Althlete,String> rState;
	@FXML
	private TableColumn<Althlete,Number> rTime;
	@FXML
	private TableColumn<Althlete,Number> rCurrentPoints;
	@FXML
	private TableColumn<Althlete,Number> rTotalPoints;
	
	@FXML
	private TableColumn<Althlete,Number> rRank;
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println("dis\n\n");
		numOfAth=0;
		try {
			fileInput=new BufferedReader(new FileReader("./data/current.txt"));
			String next=fileInput.readLine();
			String[] s=next.split(",");
			gameID=s[0].toString();
			referee=s[1].toString();
			while(next!=null)
			{
				numOfAth+=1;
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
		System.out.println(numOfAth);
		
		athleteData=new Althlete[numOfAth];
		
		try {
			fileInput=new BufferedReader(new FileReader("./data/current.txt"));
			String next=fileInput.readLine();
			
			int i=0;
			while (next != null)
			{
				String[] s=next.split(",");
				
				int id=Integer.parseInt(s[2].toString());
				int age=Integer.parseInt(s[4].toString());
				if(s[5].toString().equals("Swimmer"))
				{
					athleteData[i]=new Swimmer(id,s[3].toString(),age,s[6].toString());
					int completeTime=Integer.parseInt(s[7].toString());
					int currentPoint=Integer.parseInt(s[8].toString());
					int totalPoint=Integer.parseInt(s[9].toString());
					athleteData[i].setCompleteTime(completeTime);
					athleteData[i].setCurrentPoinnts(currentPoint);
					athleteData[i].setTotalPointsForDis(totalPoint);
					if(Integer.parseInt(s[10])!=0)
					{
						athleteData[i].setRank(Integer.parseInt(s[10]));
					}
					++i;
					
				}
				else if(s[5].toString().equals("Cyclist"))
				{
					athleteData[i]=new Cyclist(id,s[3].toString(),age,s[6].toString());
					int completeTime=Integer.parseInt(s[7].toString());
					int currentPoint=Integer.parseInt(s[8].toString());
					int totalPoint=Integer.parseInt(s[9].toString());
					athleteData[i].setCompleteTime(completeTime);
					athleteData[i].setCurrentPoinnts(currentPoint);
					athleteData[i].setTotalPointsForDis(totalPoint);
					if(Integer.parseInt(s[10])!=0)
					{
						athleteData[i].setRank(Integer.parseInt(s[10]));
					}
					++i;
					
				}
				else if(s[5].toString().equals("Sprinter"))
				{
					athleteData[i]=new Sprinter(id,s[3].toString(),age,s[6].toString());
					int completeTime=Integer.parseInt(s[7].toString());
					int currentPoint=Integer.parseInt(s[8].toString());
					int totalPoint=Integer.parseInt(s[9].toString());
					athleteData[i].setCompleteTime(completeTime);
					athleteData[i].setCurrentPoinnts(currentPoint);
					athleteData[i].setTotalPointsForDis(totalPoint);
					if(Integer.parseInt(s[10])!=0)
					{
						athleteData[i].setRank(Integer.parseInt(s[10]));
					}
					++i;
					
				}
				else if(s[5].toString().equals("SuperAthlete"))
				{
					athleteData[i]=new SuperAthlete(id,s[3].toString(),age,s[6].toString());
					int completeTime=Integer.parseInt(s[7].toString());
					int currentPoint=Integer.parseInt(s[8].toString());
					int totalPoint=Integer.parseInt(s[9].toString());
					athleteData[i].setCompleteTime(completeTime);
					athleteData[i].setCurrentPoinnts(currentPoint);
					athleteData[i].setTotalPointsForDis(totalPoint);
					if(Integer.parseInt(s[10])!=0)
					{
						athleteData[i].setRank(Integer.parseInt(s[10]));
					}
					++i;
					
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
		
		label.setText("Game ID: "+gameID+"    Referee: "+referee+"           Date: 2016.05");
	    final ObservableList<Althlete> data=FXCollections.observableArrayList(
				athleteData
				);
	    
		rID.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getID()));
		rID.setCellValueFactory(c -> new SimpleIntegerProperty( c.getValue().getID() ));
		rName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName().toString()) );
		rState.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getState()));
		rType.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getClass().toString().substring(5)));
		rAge.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getAge()));
		rTime.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getCompleteTime()));
		rCurrentPoints.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getCurrentPoinnts()));
		rTotalPoints.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getTotalPoints()));
		rRank.setCellValueFactory(c-> new SimpleIntegerProperty(c.getValue().getRank()));
		
		rTableView.setItems(data);
	}

}
