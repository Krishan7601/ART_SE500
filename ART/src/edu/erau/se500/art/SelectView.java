package edu.erau.se500.art;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class SelectView extends ViewPart {
	
	public static final String ID = "edu.erau.se500.art.MainView";
	
	Composite mainPanel;
	
	public void createPartControl(Composite parent) {
		
		mainPanel = parent;
	    
	    RowLayout overallLayout = new RowLayout ();
		overallLayout.type = SWT.VERTICAL;
		overallLayout.spacing = 5;
		mainPanel.setLayout (overallLayout);

		Group grpUML = new Group (mainPanel, SWT.NONE);
		grpUML.setText ("UML Class Diagram");
		
		Label lblFilenameUML = new Label (grpUML, SWT.NONE);
		lblFilenameUML.setBounds(30, 25, 160, 20);
		lblFilenameUML.setText("FilenameGoesHere.xml");
		lblFilenameUML.pack();
		
		Button btnBrowseUML = new Button(grpUML, SWT.NONE);
		btnBrowseUML.setText("Browse");
		btnBrowseUML.setLocation(200, 20);
		btnBrowseUML.pack();
		
		grpUML.pack();

		Group grpCode = new Group (mainPanel, SWT.NONE);
		grpCode.setText ("Source Code");

	    Button btnProjectRadio = new Button(grpCode, SWT.RADIO);
	    btnProjectRadio.setLocation(10, 20);
	    btnProjectRadio.setText("Project");
	    btnProjectRadio.pack();
	    
		Label lblFilenameProject = new Label (grpCode, SWT.NONE);
		lblFilenameProject.setBounds(30, 45, 170, 20);
		lblFilenameProject.setText("ProjectNameGoesHere");
		lblFilenameProject.pack();
		
		Button btnBrowseProject = new Button(grpCode, SWT.NONE);
		btnBrowseProject.setText("Browse");
		btnBrowseProject.setLocation(200, 40);
		btnBrowseProject.pack();
	    
	    Button btnSingleRadio = new Button(grpCode, SWT.RADIO);
	    btnSingleRadio.setLocation(10, 70);
	    btnSingleRadio.setText("Single File");
	    btnSingleRadio.pack();
	    
		Label lblFilenameSingle = new Label (grpCode, SWT.NONE);
		lblFilenameSingle.setBounds(30, 95, 170, 20);
		lblFilenameSingle.setText("FilenameGoesHere.java");
		lblFilenameSingle.pack();
		
		Button btnBrowseSingle = new Button(grpCode, SWT.NONE);
		btnBrowseSingle.setText("Browse");
		btnBrowseSingle.setLocation(200, 90);
		btnBrowseSingle.pack();
	    
	    grpCode.pack();
	}
	
	public void setFocus() {
		mainPanel.setFocus();
	}

}
