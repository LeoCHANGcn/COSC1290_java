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
public class allPoints  implements Initializable {
	private static BufferedReader fileInput=null;
	private static Althlete[] athleteData;
	private static int numOfParticipants;
	
	@FXML
	private TableView<Althlete> rTableView;
	
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
	private TableColumn<Althlete,Number> rTotalPoints;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		preparedata();
		renderTable();
	}
	
	public void preparedata()
	{
		try {
			fileInput=new BufferedReader(new FileReader("./data/allPoints.txt"));
			String next=fileInput.readLine();
			while(next!=null)
			{
				String[] s=next.split(",");
				String type=s[3].toString();
				if(type.equals("Swimmer")||type.equals("Cyclist")||type.equals("Sprinter")||type
						.equals("SuperAthlete"))
				{
					numOfParticipants+=1;
				}
				
				next=fileInput.readLine();
			}
			fileInput.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		athleteData=new Althlete[numOfParticipants];
		numOfParticipants=0;
		try {
			fileInput=new BufferedReader(new FileReader("./data/allPoints.txt"));
			String next=fileInput.readLine();
			
			int i=0;
			while (next != null)
			{
				String[] s=next.split(",");
				//System.out.println(s[1]);
				int id=Integer.parseInt(s[0].toString());
				int age=Integer.parseInt(s[2].toString());
				int tp=Integer.parseInt(s[5].toString());
				if(s[3].toString().equals("Swimmer"))
				{
					athleteData[i]=new Swimmer(id,s[1].toString(),age,s[4].toString());
					athleteData[i].setTotalPointsForDis(tp);
					++i;
				}
				else if(s[3].toString().equals("Cyclist"))
				{
					athleteData[i]=new Cyclist(id,s[1].toString(),age,s[4].toString());
					athleteData[i].setTotalPointsForDis(tp);
					++i;
				}
				else if(s[3].toString().equals("Sprinter"))
				{
					athleteData[i]=new Sprinter(id,s[1].toString(),age,s[4].toString());
					athleteData[i].setTotalPointsForDis(tp);
					++i;
				}
				else if(s[3].toString().equals("SuperAthlete"))
				{
					athleteData[i]=new SuperAthlete(id,s[1].toString(),age,s[4].toString());
					athleteData[i].setTotalPointsForDis(tp);
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
	}
	
	public void renderTable()
	{
		final ObservableList<Althlete> data=FXCollections.observableArrayList(
				athleteData
				);
	    
		
		rID.setCellValueFactory(c -> new SimpleIntegerProperty( c.getValue().getID() ));
		rName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName().toString()) );
		rState.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getState()));
		rType.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getClass().toString().substring(6)));
		rAge.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getAge()));
		rTotalPoints.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getTotalPoints()));
		rTableView.setItems(data);
	}

}
