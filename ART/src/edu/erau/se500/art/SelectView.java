package edu.erau.se500.art;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
<<<<<<< HEAD
import org.eclipse.swt.widgets.DirectoryDialog;
=======
>>>>>>> refs/remotes/origin/master
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class SelectView extends ViewPart {
	
	public static final String ID = "edu.erau.se500.art.MainView";
	
	Composite mainPanel;
	
	File umlFile, javaFile, projectDirectory;
	
	public void createPartControl(Composite mainPanel) {
		
		this.mainPanel = mainPanel;
	    
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
		
		Button btnBrowseUML = new Button(grpUML, SWT.PUSH);
		btnBrowseUML.setText("Browse");
		btnBrowseUML.setLocation(200, 20);
		btnBrowseUML.pack();
		btnBrowseUML.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dlg = new FileDialog(btnBrowseUML.getShell(),  SWT.OPEN  );
				dlg.setText("Select Class Diagram");
				final String[] allowedExtensions = {"*.xml"};
				dlg.setFilterExtensions(allowedExtensions);
				String path = dlg.open();
				if (path == null) return;
				lblFilenameUML.setText(dlg.getFileName());
				umlFile = new File(path);
			}
		});
		
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
		
		Button btnBrowseProject = new Button(grpCode, SWT.PUSH);
		btnBrowseProject.setText("Browse");
		btnBrowseProject.setLocation(200, 40);
		btnBrowseProject.pack();
		btnBrowseProject.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dlg = new DirectoryDialog(btnBrowseProject.getShell());
				dlg.setText("Select Java Project");
				String path = dlg.open();
				if (path == null) return;
				lblFilenameProject.setText(path);
				projectDirectory = new File(path);
			}
		});
	    
	    Button btnSingleRadio = new Button(grpCode, SWT.RADIO);
	    btnSingleRadio.setLocation(10, 70);
	    btnSingleRadio.setText("Single File");
	    btnSingleRadio.pack();
	    
		Label lblFilenameSingle = new Label (grpCode, SWT.NONE);
		lblFilenameSingle.setBounds(30, 95, 170, 20);
		lblFilenameSingle.setText("FilenameGoesHere.java");
		lblFilenameSingle.pack();
		
		Button btnBrowseSingle = new Button(grpCode, SWT.PUSH);
		btnBrowseSingle.setText("Browse");
		btnBrowseSingle.setLocation(200, 90);
		btnBrowseSingle.pack();
		btnBrowseSingle.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dlg = new FileDialog(btnBrowseSingle.getShell(),  SWT.OPEN  );
				dlg.setText("Select Java File");
				final String[] allowedExtensions = {"*.java"};
				dlg.setFilterExtensions(allowedExtensions);
				String path = dlg.open();
				if (path == null) return;
				lblFilenameSingle.setText(dlg.getFileName());
				javaFile = new File(path);
			}
		});
	    
	    grpCode.pack();
	    
		Button btnCompute = new Button(mainPanel, SWT.PUSH);
		btnCompute.setText("Compute");
		btnCompute.pack();
	}
	
	public void setFocus() {
		mainPanel.setFocus();
	}

}
