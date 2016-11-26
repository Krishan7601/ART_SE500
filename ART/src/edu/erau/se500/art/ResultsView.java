package edu.erau.se500.art;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

public class ResultsView extends ViewPart {

	public static final String ID = "edu.erau.se500.art.ResultsView";

	Composite mainComposite;
	private TableViewer classTV, attributeTV, methodTV;
	private SashForm sashForm;

	public void createPartControl(Composite parent) {
		mainComposite = parent;
		parent.setLayout(new FillLayout());
		sashForm = new SashForm(parent, SWT.VERTICAL);
		
		createClassTable();
		createAttributeTable();
		createMethodTable();

		sashForm.setWeights(new int[]{2, 1, 1});
	}


	
	// create the columns for the class table
	private void createClassTable() {
		classTV = new TableViewer(sashForm, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		
		String[] titles = { "Class name", "Matched", "Attributes", "Methods" };
		int[] bounds = { 200, 100, 100, 100 };

		// first column is for the class name
		TableViewerColumn col = createTableViewerColumn(classTV, titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				return r.name;
			}
		});

		// second column is for whether or not match found
		col = createTableViewerColumn(classTV, titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				if (r.isMatched) return "Yes";
				else return "No";
			}
		});

		// now attribute matches
		col = createTableViewerColumn(classTV, titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				//CompareResult r = (CompareResult) element;
				//return r.attributesFound+"/"+r.attributesTotal;
				return "!@#$";
			}
		});

		// now the status married
		col = createTableViewerColumn(classTV, titles[3], bounds[3], 3);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				//CompareResult r = (CompareResult) element;
				//return r.methodsFound+"/"+r.methodsTotal;
				return "$#@!";
			}
		});
		
		final Table classTable = classTV.getTable();
		classTable.setHeaderVisible(true);
		classTable.setLinesVisible(true);

		classTV.setContentProvider(new ArrayContentProvider());
		classTV.setInput(Compare.results);
		getSite().setSelectionProvider(classTV);

		GridData classGridData = new GridData();
		classGridData.verticalAlignment = GridData.FILL;
		classGridData.horizontalSpan = 2;
		classGridData.grabExcessHorizontalSpace = true;
		classGridData.grabExcessVerticalSpace = true;
		classGridData.horizontalAlignment = GridData.FILL;
		classTV.getControl().setLayoutData(classGridData);
	}
	
	// create the attribute table
	private void createAttributeTable() {
		attributeTV = new TableViewer(sashForm, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		
		String[] titles = { "Attribute name", "Matched", "Attributes", "Methods" };
		int[] bounds = { 200, 100, 100, 100 };

		// first column is for the class name
		TableViewerColumn col = createTableViewerColumn(attributeTV, titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				return r.name;
			}
		});

		// second column is for whether or not match found
		col = createTableViewerColumn(attributeTV, titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				if (r.isMatched) return "Yes";
				else return "No";
			}
		});

		// now attribute matches
		col = createTableViewerColumn(attributeTV, titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				//CompareResult r = (CompareResult) element;
				//return r.attributesFound+"/"+r.attributesTotal;
				return "!@#$";
			}
		});

		// now the status married
		col = createTableViewerColumn(attributeTV, titles[3], bounds[3], 3);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				//CompareResult r = (CompareResult) element;
				//return r.methodsFound+"/"+r.methodsTotal;
				return "$#@!";
			}
		});

		final Table attributeTable = attributeTV.getTable();
		attributeTable.setHeaderVisible(true);
		attributeTable.setLinesVisible(true);
		
		//TODO MORE
	}
	
	private void createMethodTable() {
		methodTV = new TableViewer(sashForm, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		
		String[] titles = { "Method name", "Matched", "Attributes", "Methods" };
		int[] bounds = { 200, 100, 100, 100 };

		// first column is for the class name
		TableViewerColumn col = createTableViewerColumn(methodTV, titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				return r.name;
			}
		});

		// second column is for whether or not match found
		col = createTableViewerColumn(methodTV, titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				if (r.isMatched) return "Yes";
				else return "No";
			}
		});

		// now attribute matches
		col = createTableViewerColumn(methodTV, titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				//CompareResult r = (CompareResult) element;
				//return r.attributesFound+"/"+r.attributesTotal;
				return "!@#$";
			}
		});

		// now the status married
		col = createTableViewerColumn(methodTV, titles[3], bounds[3], 3);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				//CompareResult r = (CompareResult) element;
				//return r.methodsFound+"/"+r.methodsTotal;
				return "$#@!";
			}
		});

		final Table methodTable = methodTV.getTable();
		methodTable.setHeaderVisible(true);
		methodTable.setLinesVisible(true);
		
		//TODO MORE
	}

	private TableViewerColumn createTableViewerColumn(TableViewer thisViewer, String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(thisViewer,SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

	public void setFocus() {
		mainComposite.setFocus();
		classTV.refresh();
		//TODO: refresh other tables
	}
}
