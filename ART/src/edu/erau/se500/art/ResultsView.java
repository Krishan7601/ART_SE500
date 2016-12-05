package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

/**
 * Eclipse view to display the results of the traceability computation
 */
public class ResultsView extends ViewPart {

	/**
	 * Unique identifier of this view in case it needs to be referenced.
	 */
	public static final String ID = "edu.erau.se500.art.ResultsView";

	/**
	 * Reference to the parent Composite that contains the UI elements of this view
	 */
	Composite mainComposite;
	
	/**
	 * References to the TableViewers that display the results for classes, attributes, and methods 
	 */
	private TableViewer classTV, attributeTV, methodTV;
	/**
	 * Reference to the UI element that allows the tables to be stacked on top of each other and resizeable.
	 */
	private SashForm sashForm;
	
	/**
	 * Font to style the tooltips with a monospace font to allow the text to line up
	 */
	private Font tooltipFont;
	
	/**
	 * Red (pink) background color of table cells where there is no matchh 
	 */
	private Color redBG;

	/** 
	 * Generates the GUI for the view. Calls other methods to create the tables
	 */
	public void createPartControl(Composite parent) {
		mainComposite = parent;
		parent.setLayout(new FillLayout());
		sashForm = new SashForm(parent, SWT.VERTICAL);

		tooltipFont = new Font(mainComposite.getDisplay(), new FontData("Consolas", 10, 0));
		
		redBG = new Color (mainComposite.getDisplay(), 255, 200, 200);
		
		createClassTable();
		createAttributeTable();
		createMethodTable();

		sashForm.setWeights(new int[]{2, 1, 1});
	}

	/**
	 * Creates the tableviewer for the class results
	 */
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
					cell.setBackground(redBG);
				}
				cell.setText(r.name);
			}
		});

		col = createTableViewerColumn(classTV, titles[1], bounds[1], 1);
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareClassResult r = (CompareClassResult) cell.getElement();
				if (r.isMatched) {
					if (r.accessMatch.isMatched) {
						cell.setText("Matched");
						cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
					} else {
						cell.setText("Not Matched");
						cell.setBackground(redBG);
					}
				} else {
					cell.setText(null);
				}
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
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareClassResult r = (CompareClassResult) cell.getElement();
				if (r.isMatched) {
					if (r.abstractMatch.isMatched) {
						cell.setText("Matched");
						cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
					} else {
						cell.setText("Not Matched");
						cell.setBackground(redBG);
					}
				} else {
					cell.setText(null);
				}
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
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareClassResult r = (CompareClassResult) cell.getElement();
				if (r.isMatched) {
					if (r.finalMatch.isMatched) {
						cell.setText("Matched");
						cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
					} else {
						cell.setText("Not Matched");
						cell.setBackground(redBG);
					}
				} else {
					cell.setText(null);
				}
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
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareClassResult r = (CompareClassResult) cell.getElement();
				if (r.isMatched) {
					if (r.parentMatch.isMatched) {
						cell.setText("Matched");
						cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
					} else {
						cell.setText("Not Matched");
						cell.setBackground(redBG);
					}
				} else {
					cell.setText(null);
				}
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
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareClassResult r = (CompareClassResult) cell.getElement();
				if (r.isMatched) {
					int matched = r.matchedInterfaces.size();
					int total = r.matchedInterfaces.size()+r.unmatchedInterfaces.size();
					if (matched == total) {
						cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
					} else {
						cell.setBackground(redBG);
					}
					cell.setText(matched+"/"+total);
				} else {
					cell.setText(null);
				}
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

	/**
	 * Creates the tableviewer for the attribute results
	 */
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
					cell.setBackground(redBG);
				}
				cell.setText(r.name);
			}			
		});

		col = createTableViewerColumn(attributeTV, titles[1], bounds[1], 1);
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareAttributeResult r = (CompareAttributeResult) cell.getElement();
				if (r.isMatched) {
					if (r.typeMatch.isMatched) {
						cell.setText("Matched");
						cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
					} else {
						cell.setText("Not Matched");
						cell.setBackground(redBG);
					}
				} else {
					cell.setText(null);
				}
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
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareAttributeResult r = (CompareAttributeResult) cell.getElement();
				if (r.isMatched) {
					if (r.accessMatch.isMatched) {
						cell.setText("Matched");
						cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
					} else {
						cell.setText("Not Matched");
						cell.setBackground(redBG);
					}
				} else {
					cell.setText(null);
				}
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
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareAttributeResult r = (CompareAttributeResult) cell.getElement();
				if (r.isMatched) {
					if (r.staticMatch.isMatched) {
						cell.setText("Matched");
						cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
					} else {
						cell.setText("Not Matched");
						cell.setBackground(redBG);
					}
				} else {
					cell.setText(null);
				}
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
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareAttributeResult r = (CompareAttributeResult) cell.getElement();
				if (r.isMatched) {
					if (r.finalMatch.isMatched) {
						cell.setText("Matched");
						cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
					} else {
						cell.setText("Not Matched");
						cell.setBackground(redBG);
					}
				} else {
					cell.setText(null);
				}
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

	/**
	 * Creates the tableviewer for the method results
	 */
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
					cell.setBackground(redBG);
				}
				cell.setText(r.name);
			}			
		});

		col = createTableViewerColumn(methodTV, titles[1], bounds[1], 1);
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareMethodResult r = (CompareMethodResult) cell.getElement();
				if (r.isMatched) {
					if (r.returnTypeMatch.isMatched) {
						cell.setText("Matched");
						cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
					} else {
						cell.setText("Not Matched");
						cell.setBackground(redBG);
					}
				} else {
					cell.setText(null);
				}
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
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareMethodResult r = (CompareMethodResult) cell.getElement();
				if (r.isMatched) {
					if (r.accessMatch.isMatched) {
						cell.setText("Matched");
						cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
					} else {
						cell.setText("Not Matched");
						cell.setBackground(redBG);
					}
				} else {
					cell.setText(null);
				}
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
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareMethodResult r = (CompareMethodResult) cell.getElement();
				if (r.isMatched) {
					if (r.abstractMatch.isMatched) {
						cell.setText("Matched");
						cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
					} else {
						cell.setText("Not Matched");
						cell.setBackground(redBG);
					}
				} else {
					cell.setText(null);
				}
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
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareMethodResult r = (CompareMethodResult) cell.getElement();
				if (r.isMatched) {
					if (r.staticMatch.isMatched) {
						cell.setText("Matched");
						cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
					} else {
						cell.setText("Not Matched");
						cell.setBackground(redBG);
					}
				} else {
					cell.setText(null);
				}
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
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareMethodResult r = (CompareMethodResult) cell.getElement();
				if (r.isMatched) {
					if (r.finalMatch.isMatched) {
						cell.setText("Matched");
						cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
					} else {
						cell.setText("Not Matched");
						cell.setBackground(redBG);
					}
				} else {
					cell.setText(null);
				}
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
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				CompareMethodResult r = (CompareMethodResult) cell.getElement();
				if (r.isMatched) {
					int matched = r.matchedParameters.size();
					int total = r.matchedParameters.size()+r.unmatchedParameters.size();
					if (matched == total) {
						cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
					} else {
						cell.setBackground(redBG);
					}
					cell.setText(matched+"/"+total);
				} else {
					cell.setText(null);
				}
			}
			@Override
			public String getToolTipText(Object element) {
				CompareMethodResult r = (CompareMethodResult) element;
				if (r.isMatched) {
					return generateTooltip("Matched", "Unmatched", r.matchedParameters, r.unmatchedParameters);
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

	/** Generic method that creates a column for each table
	 * @param thisViewer - The tableviewer that the new column is assigned to
	 * @param title - The label in the header of the column
	 * @param bound - The width of the column in pixels
	 * @param colNumber - The index of the column to indicate the order
	 * @return
	 */
	private TableViewerColumn createTableViewerColumn(TableViewer thisViewer, String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(thisViewer,SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}
	
	/** Generates formatted text to display the tooltip for a specific tableviewer cell
	 * @param head1 - The label for the left column
	 * @param head2 - The label for the right column
	 * @param data1 - The list of elements to display in the left column
	 * @param data2 - The list of elements to display in the right column
	 * @return
	 */
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
	
	/** Generates formatted text to display the tooltip for a specific tableviewer cell 
	 * when only one element is displayed on left and right sides
	 * @param head1 - Label for left column
	 * @param head2 - Label for right column
	 * @param data1 - Data for left column
	 * @param data2 - Data for right column
	 * @return
	 */
	private String generateTooltip(String head1, String head2, String data1, String data2) {
		List<String> d1 = new ArrayList<String>();
		List<String> d2 = new ArrayList<String>();
		d1.add(data1);
		d2.add(data2);
		return generateTooltip(head1, head2, d1, d2);
	}

	
	/**
	 * Sets focus to the view and refreshes the class table with any updates
	 */
	public void setFocus() {
		mainComposite.setFocus();
		classTV.refresh();
	}
}
