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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
public class displayAllResults implements Initializable{
	@FXML
	private ComboBox<String> selectGame;
	@FXML
	private TableView<Althlete> fTableView;
	
	@FXML
	private TableColumn<Althlete,Number> fID;
	@FXML
	private TableColumn<Althlete,String> fName;
	@FXML
	private TableColumn<Althlete,Number> fAge;
	@FXML
	private TableColumn<Althlete,String> fType;
	@FXML
	private TableColumn<Althlete,String> fState;
	@FXML
	private TableColumn<Althlete,Number> fPoint;
	@FXML
	private TableColumn<Althlete,Number> fTotalPoint;
	@FXML
	private TableColumn<Althlete,Number> fRank;
	private static int index=0;
	
	private static String[] gameIDs;
	private static int[] gameIndex;
	private static Althlete[] allResults;
	private static String[] type;
	private static String[] referee;
	private static int numOfGroups;
	private static int numOfAllPar=0;
	private static int iter=0;
	private static BufferedReader fileInput=null;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			fileInput=new BufferedReader(new FileReader("./data/result.txt"));
			String firstLine=fileInput.readLine();
			numG(firstLine,fileInput);
			fileInput.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println(numOfGroups);
		
		//
		gameIDs=new String[numOfGroups];
		gameIndex=new int[numOfGroups];
		type=new String[numOfGroups];
		referee=new String[numOfGroups];
		allResults=new Althlete[numOfAllPar];
		displayAllResults() ;
		selectGame.getItems().removeAll(selectGame.getItems());
		for(int i=0;i<gameIDs.length;++i)
		{
			selectGame.getItems().add(gameIDs[i]);
		}
		//selectGame.getItems().addAll("Swim", "Cycle", "Run");
		//selectGame.getSelectionModel().select("Swim");
	    
		
	}
	@FXML
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
	
	public void numG(String s,BufferedReader f)
	{
		{
			if(s==null)
			{
				
			}
			else
			{
				int num=Integer.parseInt(s.split(",")[2].toString());
				numOfGroups+=1;
				numOfAllPar+=num;
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
				numG(s,f);
			}
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
			iter+=1;
			gameIndex[index]=num;
			String id=s.split(",")[0].toString();
			gameIDs[index]=id;
			String t=s.split(",")[3].toString();
			type[index]=t;
			String r=s.split(",")[1].toString();
			referee[index]=r;
			index+=1;
			int j=num;
			while(num!=0)
			{
				try {
					s=f.readLine();
					String[] a=s.split(",");
					int numBefore=0;
					for(int k=0;j!=iter;++j)
					{
						numBefore+=gameIndex[k];
					}
					int idc=Integer.parseInt(a[0].toString());
					int agec=Integer.parseInt(a[2].toString());
					int tc=Integer.parseInt(a[5].toString());
					int cpc=Integer.parseInt(a[6].toString());
					int tpc=Integer.parseInt(a[7].toString());
					int rankc=Integer.parseInt(a[8].toString());
					if(a[3].toString().equals("Swimmer"))
					{
						allResults[numBefore+j-num]=
								new Swimmer(idc,a[1].toString(),agec,a[3].toString());
					}
					else if(a[3].toString().equals("SuperAthlete"))
					{
						allResults[numBefore+j-num]=
								new SuperAthlete(idc,a[1].toString(),agec,a[3].toString());
					}
					else if(a[3].toString().equals("Cyclist"))
					{
						allResults[numBefore+j-num]=
								new Cyclist(idc,a[1].toString(),agec,a[3].toString());
					}
					else if(a[3].toString().equals("Sprinter"))
					{
						allResults[numBefore+j-num]=
								new Sprinter(idc,a[1].toString(),agec,a[3].toString());
					}
					allResults[numBefore+j-num].setCompleteTime(tc);
					allResults[numBefore+j-num].setCurrentPoinnts(cpc);
					allResults[numBefore+j-num].setTotalPointsForDis(tpc);
					allResults[numBefore+j-num].setRank(rankc);
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

}
