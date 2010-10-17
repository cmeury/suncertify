package suncertify.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import suncertify.tools.Message;
import suncertify.tools.Strings;

/**
 * Constructs the GUI
 *
 */
public class MainWindowView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 700;
	
    private JPanel rootPanel;
	private JLabel introLabel;
	private JTable table;
	private JScrollPane scrollPane;
	private MainWindowModel mainWindowModel;
	private JLabel nameLabel;
	private JLabel locationLabel;
	private JTextField searchTextField;
	private JTextField nameTextField;
	private JTextField locationTextField;

	private JPanel namePanel;
	private JPanel locationPanel;

	private JPanel casePanel;

	private JCheckBox caseCheckBox;
	
	public MainWindowView(MainWindowModel model) {
		if(model == null) {
			throw new NullPointerException("Cannot construct view without a model");
		}
		this.mainWindowModel = model;
		Message.infoToLog("Creating and displaying the main window");

		// Create root panel
		rootPanel = new JPanel(new BorderLayout());

		// Create introductory label
		introLabel = new JLabel(Strings.getMainWindowIntroLabelText());
		
		// Name search panel and its elements
		namePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		nameLabel = new JLabel(Strings.getMainwindowNameLabelText());
		nameTextField = new JTextField(25);
		namePanel.add(nameLabel);
		namePanel.add(nameTextField);

		// Location search panel and its elements
		locationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		locationLabel = new JLabel(Strings.getMainWindowLocationLabelText());
		locationTextField = new JTextField(25);
		locationPanel.add(locationLabel);
		locationPanel.add(locationTextField);

		casePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		caseCheckBox = new JCheckBox(Strings.getMainWindowCaseCheckBoxText());
		caseCheckBox.setSelected(false);
		model.setCaseSensitive(false);
		casePanel.add(caseCheckBox);
		
		// Create a box layout, add the search panels 
		JPanel searchPanels = new JPanel();
		searchPanels.setLayout(new BoxLayout(searchPanels, BoxLayout.PAGE_AXIS));
		searchPanels.add(namePanel);
		searchPanels.add(locationPanel);
		searchPanels.add(caseCheckBox);

		// Create result table
		table = new JTable(mainWindowModel.getTableModel());
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		rootPanel.add(introLabel, BorderLayout.PAGE_START);
		rootPanel.add(scrollPane, BorderLayout.PAGE_END);
		rootPanel.add(searchPanels);
		getContentPane().add(rootPanel);

		// Set some properties and show this frame
		setTitle(Strings.getApplicationName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT)); 
		pack();
		setVisible(true);
	}

	public String getNameSearchText() {
		return nameTextField.getText();
	}

	public String getLocationSearchText() {
		return locationTextField.getText();
	}
	
	// Event Handling
	public void addSearchDocumentListener(DocumentListener documentListener) {
        nameTextField.getDocument().addDocumentListener(documentListener);
        locationTextField.getDocument().addDocumentListener(documentListener);
	}
	
	public void addCaseListener(ItemListener itemListener) {
		caseCheckBox.addItemListener(itemListener);
	}
	
    class CancelAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent ev) {
            searchTextField.setText("");
        }
    }
}
