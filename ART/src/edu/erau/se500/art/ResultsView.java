package edu.erau.se500.art;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;

public class ResultsView extends ViewPart {
	
	public static final String ID = "edu.erau.se500.art.ResultsView";
	
	Composite mainPanel;
	
	public void createPartControl(Composite mainPanel) {
		
		this.mainPanel = mainPanel;
		
		final Table table = new Table (mainPanel, SWT.BORDER);
		 TableColumn tc1 = new TableColumn(table, SWT.CENTER);
		    TableColumn tc2 = new TableColumn(table, SWT.CENTER);
		    TableColumn tc3 = new TableColumn(table, SWT.CENTER);
		    TableColumn tc4 = new TableColumn(table, SWT.CENTER);
		    tc1.setText("Class Name");
		    tc2.setText("Match Found");
		    tc3.setText("Attribute Matches");
		    tc4.setText("Method Matches");
		    tc1.setWidth(100);
		    tc2.setWidth(100);
		    tc3.setWidth(100);
		    tc4.setWidth(100);
		    table.setHeaderVisible(true);
		    
		    TableItem item1 = new TableItem(table, SWT.NONE);
		    item1.setText(new String[] { "Tim", "Yes", "Hatton", "Kentucky" });
		    TableItem item2 = new TableItem(table, SWT.NONE);
		    item2.setText(new String[] { "Caitlyn", "Yes", "Warner", "Ohio" });
		    TableItem item3 = new TableItem(table, SWT.NONE);
		    item3.setText(new String[] { "Reese", "Yes", "Miller", "Ohio" });
		
	}
	
	public void setFocus() {
		mainPanel.setFocus();
	}

}
