package edu.erau.se500.art;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;

public class ResultsView extends ViewPart {
	
	public static final String ID = "edu.erau.se500.art.ResultsView";
	
	Composite mainPanel;
	
	public void createPartControl(Composite mainPanel) {
		
		this.mainPanel = mainPanel;
		
		  final Table table = new Table (mainPanel, SWT.VIRTUAL | SWT.BORDER);
		  table.setItemCount (1000000);
		  table.addListener (SWT.SetData, new Listener () {
		      public void handleEvent (Event event) {
		          TableItem item = (TableItem) event.item;
		          int index = table.indexOf (item);
		          item.setText ("Item " + index);
		          System.out.println (item.getText ());
		      }
		  });
		
	}
	
	public void setFocus() {
		mainPanel.setFocus();
	}

}
