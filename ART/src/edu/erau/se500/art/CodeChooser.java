package edu.erau.se500.art;

import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public final class CodeChooser extends Application
{
	public static void main(String[] args) 
	{
		Application.launch(args);
	}

	public void start(final Stage codeChooserStage)
	{
		codeChooserStage.setTitle("Source Code Chooser");

		final Button singleCodeButton = new Button("Single Code File");
		final Button multiCodeButton = new Button("Multiple Code Files");

		final FileChooser codeChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Java files (*.java)", "*.java");
		codeChooser.getExtensionFilters().add(extFilter);

		singleCodeButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(final ActionEvent e) 
			{
				File codeFile = codeChooser.showOpenDialog(codeChooserStage);
				if (codeFile != null) 
				{
					
				}
				
				codeChooserStage.close();
			}
		});
		multiCodeButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(final ActionEvent e) 
			{
				List<File> listOfCodes = codeChooser.showOpenMultipleDialog(codeChooserStage);
				if (listOfCodes != null) 
				{
					for (@SuppressWarnings("unused") File codeFile : listOfCodes) 
					{
						//save file
					}
				}
				
				codeChooserStage.close();
			}
		});

		final GridPane inputGridPane = new GridPane();
		GridPane.setConstraints(singleCodeButton, 0, 0);
		GridPane.setConstraints(multiCodeButton, 1, 0);
		inputGridPane.setHgap(6);
		inputGridPane.setVgap(6);
		inputGridPane.getChildren().addAll(singleCodeButton, multiCodeButton);

		final Pane rootGroup = new VBox(12);
		rootGroup.getChildren().addAll(inputGridPane);
		rootGroup.setPadding(new Insets(12, 12, 12, 12));

		codeChooserStage.setScene(new Scene(rootGroup));
		codeChooserStage.show();

	}


}