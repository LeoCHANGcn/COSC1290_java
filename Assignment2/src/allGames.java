import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class allGames implements Initializable{
	@FXML
	private TextArea text;
	
	private static String results;
	private static BufferedReader fileInput;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			fileInput=new BufferedReader(new FileReader("./data/result.txt"));
			String next=fileInput.readLine();
			getAResult(next,fileInput);
			text.setText(results);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e)
		{
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
			results+=s+"\n";
			results+="ID,Name,Age,Type,State,Time,cp,tp,r(cp:currentPoints;tp:totalPoints;r:rank)\n";
			int num=Integer.parseInt(s.split(",")[2].toString());
			while(num!=0)
			{
				try {
					s=f.readLine();
					results+=s+"\n";
					System.out.println(s);
					num-=1;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			results+="\n\n\n";
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
