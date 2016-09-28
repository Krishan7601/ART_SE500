package edu.erau.se500.art;

import java.io.File;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public final class UMLChooser extends Application
{
	public static void main(String[] args) 
	{
		Application.launch(args);
	}

	public void start(final Stage umlChooserStage)
	{
		umlChooserStage.setTitle("UML Requirement Chooser");

		final FileChooser umlChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = 
				new FileChooser.ExtensionFilter("XMI files (*.XMI)", "*.XMI");
		umlChooser.getExtensionFilters().add(extFilter);
		@SuppressWarnings("unused")
		File umlRequirementFile = umlChooser.showOpenDialog(umlChooserStage);
		
		//save file
	}
}


