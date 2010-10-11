package suncertify.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentListener;

import suncertify.db.Schema;
import suncertify.tools.Message;
import suncertify.tools.Strings;

/**
 * Constructs the GUI
 *
 */
public class MainWindowView extends JFrame {
	private static final long serialVersionUID = 1L;
    private static final String CANCEL_ACTION = "cancel-search";
	
    private JPanel rootPanel;
	private JLabel introLabel;
	private JTable table;
	private JScrollPane scrollPane;
	private MainWindowModel mainWindowModel;
	private JPanel searchPanel;
	private JLabel searchLabel;
	private JTextField searchTextField;

	private JButton searchButton;
	private JComboBox columnComboBox;
	
	public MainWindowView(MainWindowModel model) {
		this.mainWindowModel = model;
		Message.getLogger().info("Creating and displaying the main window");

		// Create root panel
		rootPanel = new JPanel(new BorderLayout());

		// Create introductory label
		introLabel = new JLabel(Strings.getMainWindowIntroLabelText());
		rootPanel.add(introLabel, BorderLayout.PAGE_START);
		
		// Create search label, list box, text field and button
		searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		searchLabel = new JLabel(Strings.getMainWindowSearchLabelText());
		columnComboBox = new JComboBox(Schema.getFieldNames());
		columnComboBox.setSelectedIndex(2);
		
		searchButton = new JButton(Strings.getMainWindowSearchButtonText());
        InputMap im = searchButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = searchButton.getActionMap();
        im.put(KeyStroke.getKeyStroke("ESCAPE"), CANCEL_ACTION);
        am.put(CANCEL_ACTION, new CancelAction());
		
        searchTextField = new JTextField(30);
		searchPanel.add(searchLabel);
		searchPanel.add(columnComboBox);
		searchPanel.add(searchTextField);
		searchPanel.add(searchButton);
		rootPanel.add(searchPanel, BorderLayout.CENTER);
		
		// Create result table
		table = new JTable(mainWindowModel.getTableModel());
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		rootPanel.add(scrollPane, BorderLayout.PAGE_END);

		getContentPane().add(rootPanel);

		// Set some properties and show this frame
		setTitle(Strings.getApplicationName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500); 
		pack();
		setVisible(true);
	}

	public String getSearchText() {
		return searchTextField.getText();
	}
	
	public int getColumnComboBoxIndex() {
		return columnComboBox.getSelectedIndex();
	}
	
	// Event Handling
	public void addSearchListener(ActionListener actionListener) {
		searchButton.addActionListener(actionListener);
		searchButton.addKeyListener(new KeyAdapter() {
		});
	}
	
	public void addSearchDocumentListener(DocumentListener documentListener) {
        searchTextField.getDocument().addDocumentListener(documentListener);
	}
	
    class CancelAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent ev) {
            searchTextField.setText("");
        }
    }
}
