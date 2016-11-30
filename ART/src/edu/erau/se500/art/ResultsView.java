package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

public class ResultsView extends ViewPart {

	public static final String ID = "edu.erau.se500.art.ResultsView";

	Composite mainComposite;
	private TableViewer classTV, attributeTV, methodTV;
	private SashForm sashForm;
	
	private Font tooltipFont;

	public void createPartControl(Composite parent) {
		mainComposite = parent;
		parent.setLayout(new FillLayout());
		sashForm = new SashForm(parent, SWT.VERTICAL);

		createClassTable();
		createAttributeTable();
		createMethodTable();

		sashForm.setWeights(new int[]{2, 1, 1});
		
		tooltipFont = new Font(mainComposite.getDisplay(), new FontData("Consolas", 10, 0));
	}



	// create the columns for the class table
	private void createClassTable() {
		classTV = new TableViewer(sashForm, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		
		ColumnViewerToolTipSupport.enableFor(classTV);

		String[] titles = { "Class name", "Access", "Abstract", "Final", "Parent Class", "Interfaces" };
		int[] bounds = { 200, 100, 100, 100, 100, 100 };

		TableViewerColumn col = createTableViewerColumn(classTV, titles[0], bounds[0], 0);
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareClassResult r = (CompareClassResult) cell.getElement();
				if (r.isMatched) {
					cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
				} else {
					cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_MAGENTA));
				}
				cell.setText(r.name);
			}			
		});

		col = createTableViewerColumn(classTV, titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				if (!r.isMatched) return null;
				if (r.accessMatch.isMatched) return "Yes";
				else return "No";
			}
			@Override
			public String getToolTipText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				if (r.isMatched) {
					return generateTooltip("Source Value", "Compare Value", r.accessMatch.sourceValue, r.accessMatch.compareValue);
				} else {
					return null;
				}
			}
			@Override
			public Font getToolTipFont(Object element) {			
				return tooltipFont;
			}
		});

		col = createTableViewerColumn(classTV, titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				if (!r.isMatched) return null;
				if (r.abstractMatch.isMatched) return "Yes";
				else return "No";
			}
			@Override
			public String getToolTipText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				if (r.isMatched) {
					return generateTooltip("Source Value", "Compare Value", r.abstractMatch.sourceValue, r.abstractMatch.compareValue);
				} else {
					return null;
				}
			}
			@Override
			public Font getToolTipFont(Object element) {			
				return tooltipFont;
			}
		});

		col = createTableViewerColumn(classTV, titles[3], bounds[3], 3);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				if (!r.isMatched) return null;
				if (r.finalMatch.isMatched) return "Yes";
				else return "No";
			}
			@Override
			public String getToolTipText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				if (r.isMatched) {
					return generateTooltip("Source Value", "Compare Value", r.finalMatch.sourceValue, r.finalMatch.compareValue);
				} else {
					return null;
				}
			}
			@Override
			public Font getToolTipFont(Object element) {			
				return tooltipFont;
			}
		});
		
		col = createTableViewerColumn(classTV, titles[4], bounds[4], 4);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				if (!r.isMatched) return null;
				if (r.parentMatch.isMatched) return "Yes";
				else return "No";
			}
			@Override
			public String getToolTipText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				if (r.isMatched) {
					return generateTooltip("Source Value", "Compare Value", r.parentMatch.sourceValue, r.parentMatch.compareValue);
				} else {
					return null;
				}
			}
			@Override
			public Font getToolTipFont(Object element) {			
				return tooltipFont;
			}
		});
		
		col = createTableViewerColumn(classTV, titles[5], bounds[5], 5);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				if (!r.isMatched) return null;
				return r.matchedInterfaces.size()+"/"+(r.matchedInterfaces.size()+r.unmatchedInterfaces.size());
			}
			@Override
			public String getToolTipText(Object element) {
				CompareClassResult r = (CompareClassResult) element;
				if (r.isMatched) {
					return generateTooltip("Matched", "Unmatched", r.matchedInterfaces, r.unmatchedInterfaces);
				} else {
					return null;
				}
			}
			@Override
			public Font getToolTipFont(Object element) {			
				return tooltipFont;
			}
		});

		classTV.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = classTV.getStructuredSelection();
				CompareClassResult thisClass = (CompareClassResult)selection.getFirstElement();
				if (thisClass != null) {
					attributeTV.setInput(thisClass.attributes);
					methodTV.setInput(thisClass.methods);
					attributeTV.refresh();
				}
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
		attributeTV = new TableViewer(sashForm, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		
		ColumnViewerToolTipSupport.enableFor(attributeTV);

		String[] titles = { "Attribute name", "Type", "Access", "Static", "Final" };
		int[] bounds = { 200, 100, 100, 100, 100 };

		TableViewerColumn col = createTableViewerColumn(attributeTV, titles[0], bounds[0], 0);
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareAttributeResult r = (CompareAttributeResult) cell.getElement();
				if (r.isMatched) {
					cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
				} else {
					cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_MAGENTA));
				}
				cell.setText(r.name);
			}			
		});

		col = createTableViewerColumn(attributeTV, titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareAttributeResult r = (CompareAttributeResult) element;
				if (!r.isMatched) return null;
				if (r.typeMatch.isMatched) return "Yes";
				else return "No";
			}
			@Override
			public String getToolTipText(Object element) {
				CompareAttributeResult r = (CompareAttributeResult) element;
				if (r.isMatched) {
					return generateTooltip("Source Value", "Compare Value", r.typeMatch.sourceValue, r.typeMatch.compareValue);
				} else {
					return null;
				}
			}
			@Override
			public Font getToolTipFont(Object element) {			
				return tooltipFont;
			}
		});

		col = createTableViewerColumn(attributeTV, titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareAttributeResult r = (CompareAttributeResult) element;
				if (!r.isMatched) return null;
				if (r.accessMatch.isMatched) return "Yes";
				else return "No";
			}
			@Override
			public String getToolTipText(Object element) {
				CompareAttributeResult r = (CompareAttributeResult) element;
				if (r.isMatched) {
					return generateTooltip("Source Value", "Compare Value", r.accessMatch.sourceValue, r.accessMatch.compareValue);
				} else {
					return null;
				}
			}
			@Override
			public Font getToolTipFont(Object element) {			
				return tooltipFont;
			}
		});

		col = createTableViewerColumn(attributeTV, titles[3], bounds[3], 3);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareAttributeResult r = (CompareAttributeResult) element;
				if (!r.isMatched) return null;
				if (r.staticMatch.isMatched) return "Yes";
				else return "No";
			}
			@Override
			public String getToolTipText(Object element) {
				CompareAttributeResult r = (CompareAttributeResult) element;
				if (r.isMatched) {
					return generateTooltip("Source Value", "Compare Value", r.staticMatch.sourceValue, r.staticMatch.compareValue);
				} else {
					return null;
				}
			}
			@Override
			public Font getToolTipFont(Object element) {			
				return tooltipFont;
			}
		});
		
		col = createTableViewerColumn(attributeTV, titles[4], bounds[4], 4);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareAttributeResult r = (CompareAttributeResult) element;
				if (!r.isMatched) return null;
				if (r.finalMatch.isMatched) return "Yes";
				else return "No";
			}
			@Override
			public String getToolTipText(Object element) {
				CompareAttributeResult r = (CompareAttributeResult) element;
				if (r.isMatched) {
					return generateTooltip("Source Value", "Compare Value", r.finalMatch.sourceValue, r.finalMatch.compareValue);
				} else {
					return null;
				}
			}
			@Override
			public Font getToolTipFont(Object element) {			
				return tooltipFont;
			}
		});

		final Table attributeTable = attributeTV.getTable();
		attributeTable.setHeaderVisible(true);
		attributeTable.setLinesVisible(true);

		attributeTV.setContentProvider(new ArrayContentProvider());

		GridData attributeGridData = new GridData();
		attributeGridData.verticalAlignment = GridData.FILL;
		attributeGridData.horizontalSpan = 2;
		attributeGridData.grabExcessHorizontalSpace = true;
		attributeGridData.grabExcessVerticalSpace = true;
		attributeGridData.horizontalAlignment = GridData.FILL;
		attributeTV.getControl().setLayoutData(attributeGridData);
	}

	private void createMethodTable() {
		methodTV = new TableViewer(sashForm, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		
		ColumnViewerToolTipSupport.enableFor(methodTV);

		String[] titles = { "Method name", "Return Type", "Access", "Abstract", "Static", "Final", "Parameters" };
		int[] bounds = { 200, 100, 100, 100, 100, 100, 100 };

		TableViewerColumn col = createTableViewerColumn(methodTV, titles[0], bounds[0], 0);
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareMethodResult r = (CompareMethodResult) cell.getElement();
				if (r.isMatched) {
					cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
				} else {
					cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_MAGENTA));
				}
				cell.setText(r.name);
			}			
		});

		col = createTableViewerColumn(methodTV, titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareMethodResult r = (CompareMethodResult) element;
				if (!r.isMatched) return null;
				if (r.returnTypeMatch.isMatched) return "Yes";
				else return "No";
			}
			@Override
			public String getToolTipText(Object element) {
				CompareMethodResult r = (CompareMethodResult) element;
				if (r.isMatched) {
					return generateTooltip("Source Value", "Compare Value", r.returnTypeMatch.sourceValue, r.returnTypeMatch.compareValue);
				} else {
					return null;
				}
			}
			@Override
			public Font getToolTipFont(Object element) {			
				return tooltipFont;
			}
		});

		col = createTableViewerColumn(methodTV, titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareMethodResult r = (CompareMethodResult) element;
				if (!r.isMatched) return null;
				if (r.accessMatch.isMatched) return "Yes";
				else return "No";
			}
			@Override
			public String getToolTipText(Object element) {
				CompareMethodResult r = (CompareMethodResult) element;
				if (r.isMatched) {
					return generateTooltip("Source Value", "Compare Value", r.accessMatch.sourceValue, r.accessMatch.compareValue);
				} else {
					return null;
				}
			}
			@Override
			public Font getToolTipFont(Object element) {			
				return tooltipFont;
			}
		});

		col = createTableViewerColumn(methodTV, titles[3], bounds[3], 3);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareMethodResult r = (CompareMethodResult) element;
				if (!r.isMatched) return null;
				if (r.abstractMatch.isMatched) return "Yes";
				else return "No";
			}
			@Override
			public String getToolTipText(Object element) {
				CompareMethodResult r = (CompareMethodResult) element;
				if (r.isMatched) {
					return generateTooltip("Source Value", "Compare Value", r.abstractMatch.sourceValue, r.abstractMatch.compareValue);
				} else {
					return null;
				}
			}
			@Override
			public Font getToolTipFont(Object element) {			
				return tooltipFont;
			}
		});
		
		col = createTableViewerColumn(methodTV, titles[4], bounds[4], 4);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareMethodResult r = (CompareMethodResult) element;
				if (!r.isMatched) return null;
				if (r.staticMatch.isMatched) return "Yes";
				else return "No";
			}
			@Override
			public String getToolTipText(Object element) {
				CompareMethodResult r = (CompareMethodResult) element;
				if (r.isMatched) {
					return generateTooltip("Source Value", "Compare Value", r.staticMatch.sourceValue, r.staticMatch.compareValue);
				} else {
					return null;
				}
			}
			@Override
			public Font getToolTipFont(Object element) {			
				return tooltipFont;
			}
		});
		
		col = createTableViewerColumn(methodTV, titles[5], bounds[5], 5);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareMethodResult r = (CompareMethodResult) element;
				if (!r.isMatched) return null;
				if (r.finalMatch.isMatched) return "Yes";
				else return "No";
			}
			@Override
			public String getToolTipText(Object element) {
				CompareMethodResult r = (CompareMethodResult) element;
				if (r.isMatched) {
					return generateTooltip("Source Value", "Compare Value", r.finalMatch.sourceValue, r.finalMatch.compareValue);
				} else {
					return null;
				}
			}
			@Override
			public Font getToolTipFont(Object element) {			
				return tooltipFont;
			}
		});
		
		col = createTableViewerColumn(methodTV, titles[6], bounds[6], 6);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CompareMethodResult r = (CompareMethodResult) element;
				if (!r.isMatched) return null;
				return r.matchedParameters.size()+"/"+(r.matchedParameters.size()+r.unmatchedParameters.size());
			}
			@Override
			public String getToolTipText(Object element) {
				CompareMethodResult r = (CompareMethodResult) element;
				if (r.isMatched) {
					return generateTooltipFromListofArrays("Matched", "Unmatched", r.matchedParameters, r.unmatchedParameters);
				} else {
					return null;
				}
			}
			@Override
			public Font getToolTipFont(Object element) {			
				return tooltipFont;
			}
		});

		final Table methodTable = methodTV.getTable();
		methodTable.setHeaderVisible(true);
		methodTable.setLinesVisible(true);

		methodTV.setContentProvider(new ArrayContentProvider());

		GridData methodGridData = new GridData();
		methodGridData.verticalAlignment = GridData.FILL;
		methodGridData.horizontalSpan = 2;
		methodGridData.grabExcessHorizontalSpace = true;
		methodGridData.grabExcessVerticalSpace = true;
		methodGridData.horizontalAlignment = GridData.FILL;
		methodTV.getControl().setLayoutData(methodGridData);
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
	
	private String generateTooltip(String head1, String head2, List<String> data1, List<String> data2) {
		StringBuilder sb = new StringBuilder();
		
		int dataSize = data1.size();
		if (data2.size() > dataSize) dataSize = data2.size();
		
		int strLength = head1.length();
		for(String d : data1) {
			if (d == null) d = "null";
			if (d.length() > strLength) strLength = d.length();
		}
		strLength+=5;
		
		for(String d : data2) {
			if (d == null) d = "null";
		}
		
		sb.append(String.format("%-"+strLength+"s", head1)+head2+"\n");
		
		StringBuilder line1 = new StringBuilder();
		for (int i = 0; i < head1.length(); i++) {
			line1.append("-");
		}
		sb.append(String.format("%-"+strLength+"s", line1.toString()));
		
		StringBuilder line2 = new StringBuilder();
		for (int i = 0; i < head2.length(); i++) {
			line2.append("-");
		}
		sb.append(line2.toString());
		
		for (int i = 0; i < dataSize; i++) {
			sb.append("\n");
			if (data1.size() >= (i+1) && data1.get(i) != null) {
				sb.append(String.format("%-"+strLength+"s", data1.get(i)));
			} else {
				sb.append(String.format("%-"+strLength+"s", " "));
			}
			if (data2.size() >= (i+1) && data2.get(i) != null) {
				sb.append(data2.get(i));
			}
		}
		return sb.toString();
	}
	
	private String generateTooltip(String head1, String head2, String data1, String data2) {
		List<String> d1 = new ArrayList<String>();
		List<String> d2 = new ArrayList<String>();
		d1.add(data1);
		d2.add(data2);
		return generateTooltip(head1, head2, d1, d2);
	}
	
	private String generateTooltipFromListofArrays(String head1, String head2, List<String[]> data1, List<String[]> data2) {
		List<String> d1 = new ArrayList<String>();
		List<String> d2 = new ArrayList<String>();
		for (String[] dataArray : data1) {
			d1.add(dataArray[0]+" "+dataArray[1]);
		}
		for (String[] dataArray : data2) {
			d2.add(dataArray[0]+" "+dataArray[1]);
		}
		return generateTooltip(head1, head2, d1, d2);
	}

	public void setFocus() {
		mainComposite.setFocus();
		classTV.refresh();
		//TODO: refresh other tables
	}
}
