import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class mainGUI implements Initializable{

	@FXML
	private Button startNewGame;
	@FXML
	private Button displayResultsForGames;
	@FXML
	private Button displayPointssForPerAthletes;
	@FXML
	private Button exitGame;
	@FXML
	private MenuItem guide;
	@FXML
	private MenuItem exit;
	@FXML
	private MenuItem theme;
	@FXML
	private MenuItem about;
	
	private static boolean signal=true;
	private static boolean first=true;
	
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void startNewGame()
	{
		try {
			Stage primaryStage=new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("RunGame.fxml"));
			
            primaryStage.setTitle("New Game");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void displayResultsOfGames()
	{
		try {
			Stage primaryStage=new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("allGames.fxml"));
			
            primaryStage.setTitle("All Results ...");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void displayResultsOfAthletes()
	{
		try {
			Stage primaryStage=new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("allPoints.fxml"));
			
            primaryStage.setTitle("All information");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exitGame()
	{
		//Reference
		//http://stackoverflow.com/questions/25037724/how-to-close-a-java-window-with-a-button-click-javafx-project
		Stage stage = (Stage) exitGame.getScene().getWindow();
	    stage.close();
	}
	@FXML
	public void guide()
	{
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("OZLYMPIC GAME");
		alert.setHeaderText("A small olympic game for fun!");
		alert.setContentText("Click rungame button to run a new game.");
		alert.show();
	}
	@FXML
	public void exit()
	{
		exitGame();
	}
	@FXML
	public void theme()
	{
		//http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/apply-css.htm#CHDGHCDG
		//System.out.print(signal);
		if(first)
		{
			first=false;
			Stage stage = (Stage) exitGame.getScene().getWindow();
			Scene scene=stage.getScene();
			//scene.getStylesheets().remove(0);
			scene.getStylesheets().add(getClass().getResource("dark.css").toExternalForm());
			stage.setScene(scene);
			stage.close();
			stage.show();
		}
		else{
			if(signal==true)
			{
				Stage stage = (Stage) exitGame.getScene().getWindow();
				Scene scene=stage.getScene();
				scene.getStylesheets().remove(0);
				scene.getStylesheets().add(getClass().getResource("dark.css").toExternalForm());
				stage.setScene(scene);
				stage.close();
				stage.show();
				
			}
			else
			{
				
				Stage stage = (Stage) exitGame.getScene().getWindow();
				Scene scene=stage.getScene();
				scene.getStylesheets().remove(0);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.close();
				stage.show();
			}
		}
		signal=!signal;
		System.out.println(signal);
	}
	@FXML
	public void about()
	{
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("OZLYMPIC GAME");
		alert.setHeaderText("A small olympic game for fun!");
		alert.setContentText("@CopyRight:ShichaoZHANG SiqiYANG\n"
				+ "\nAsignment 2 for COSC1290\n");
		alert.show();
	}

}
