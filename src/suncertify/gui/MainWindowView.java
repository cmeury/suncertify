package suncertify.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import suncertify.tools.Message;
import suncertify.tools.Strings;

/**
 * Constructs the GUI
 *
 */
public class MainWindowView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JLabel label;
	private RecordsTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;

	private MainWindowModel mainWindowModel;
	
	public MainWindowView(MainWindowModel model) {
		this.mainWindowModel = model;
		Message.getLogger().info("Creating and displaying the main window");

		this.setTitle(Strings.getApplicationName());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel(new BorderLayout());
		this.getContentPane().add(panel);

		label = new JLabel(Strings.getMainwindowlabel());
		panel.add(label, BorderLayout.NORTH);
		
		table = new JTable(mainWindowModel.getTableModel());
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.SOUTH);
		table.setFillsViewportHeight(true);
		
		this.pack();
		this.setSize(800, 500); 
		this.setVisible(true);
	}
}
