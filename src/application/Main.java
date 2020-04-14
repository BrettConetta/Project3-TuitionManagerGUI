package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
/**
 * This class launches the GUI program by loading the TuitionManagerGUI FXML
 * file. It provides a main method that launches the program.
 * 
 * @author Brett Conetta, Stephen Prospero
 *
 */
public class Main extends Application
{
	/**
	 * This method loads the TuitionManagerGUI FXML file, initiates a new Scene
	 * object and sets the primary stage to show the newly created scene.
	 * 
	 * @param primaryStage Stage object used for the GUI
	 */
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			primaryStage.setTitle("Project 3 - Tuition Manager");
			BorderPane root = (BorderPane) FXMLLoader
					.load(getClass().getResource("TuitionManagerGUI.fxml"));
			Scene scene = new Scene(root, 688, 619);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Main method that launches GUI program.
	 * 
	 * @param args default argument for the main method
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
}