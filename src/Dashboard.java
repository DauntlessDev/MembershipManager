/* Dashboard Window - the main ui of the program, this is where the admin will be able to 
 * manage the membership information list of the organization, this window includes all other buttons 
 * such as add,update,sort, export,import, delete and log out. This is also where you can see the table of the 
 * membership list.
 * 						
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.JScrollPane;
import javax.swing.SortOrder;

import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.RowFilter;

public class Dashboard extends JFrame implements InputValidator {
	// set the serialVersion UID to default
	private static final long serialVersionUID = 1L;
	// declare variables needed for the GUI
	static DefaultTableModel model = new DefaultTableModel();
	private JPanel contentPane;
	static JTable table;
	private JTextField searchField;
	boolean searched = false; // for search button condition

	// constructor
	public Dashboard() {
		//set main design and settings
		setForeground(new Color(0, 0, 0));
		setTitle("Membership Manager");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/images/acmlogo.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1168, 662);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		
		// for search field
		searchField = new JTextField();
		searchField.setBounds(215, 139, 182, 33);
		contentPane.add(searchField);
		searchField.setColumns(10);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		//initialize and set location and settings for panels 
		// blue panels for modern look design
		JPanel middleBluePanel = new JPanel();
		middleBluePanel.setBackground(new Color(25, 25, 112));
		middleBluePanel.setBounds(0, 0, 185, 45);
		contentPane.add(middleBluePanel);
		middleBluePanel.setLayout(null);

		JPanel leftBluePanel = new JPanel();
		leftBluePanel.setBackground(new Color(70, 130, 180));
		leftBluePanel.setBounds(0, 45, 185, 582);
		contentPane.add(leftBluePanel);
		leftBluePanel.setLayout(null);
		
		JPanel upperBluePanel = new JPanel();
		upperBluePanel.setBackground(new Color(70, 130, 180));
		upperBluePanel.setBounds(185, 0, 1096, 45);
		contentPane.add(upperBluePanel);
		upperBluePanel.setLayout(null);
		
		JPanel memberBluePanel = new JPanel();
		memberBluePanel.setBounds(215, 75, 911, 51);
		contentPane.add(memberBluePanel);
		memberBluePanel.setLayout(null);
		
		// set fonts and design
		// for labels(text in interface) - set fonts, size and location
		JLabel acmLabel = new JLabel("A C M");
		acmLabel.setForeground(Color.WHITE);
		acmLabel.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 20));
		acmLabel.setBounds(65, 13, 78, 16);
		middleBluePanel.add(acmLabel);
		
		JLabel memberLabel = new JLabel("Membership Dashboard");
		memberLabel.setForeground(Color.DARK_GRAY);
		memberLabel.setBounds(22, 13, 237, 23);
		memberLabel.setFont(new Font("SansSerif", Font.BOLD, 17));
		memberBluePanel.add(memberLabel);

		JLabel adminIconLabel = new JLabel("Admin Panel");
		adminIconLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		adminIconLabel.setForeground(new Color(255, 255, 255));
		adminIconLabel.setBounds(47, 126, 94, 30);
		leftBluePanel.add(adminIconLabel);
		
		//for the labels with no text but pictures
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(Dashboard.class.getResource("/images/dash.jpg")));
		iconLabel.setBounds(32, 0, 31, 44);
		middleBluePanel.add(iconLabel);

		JLabel adminpicLabel = new JLabel("");
		adminpicLabel.setHorizontalAlignment(SwingConstants.CENTER);
		adminpicLabel.setBounds(37, 30, 104, 99);
		adminpicLabel.setIcon(new ImageIcon(Dashboard.class.getResource("/images/profpicss.jpg")));
		leftBluePanel.add(adminpicLabel);

	
		//for the settings, location, text, color, etc of buttons
		JButton addButton = new JButton("ADD");
		addButton.setBackground(Color.WHITE);
		addButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		addButton.setBounds(35, 195, 115, 30);
		leftBluePanel.add(addButton);

		JButton updateButton = new JButton("UPDATE");
		updateButton.setBackground(Color.WHITE);
		updateButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		updateButton.setBounds(35, 235, 115, 30);
		leftBluePanel.add(updateButton);

		JButton deleteButton = new JButton("DELETE");
		deleteButton.setBackground(Color.WHITE);
		deleteButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		deleteButton.setBounds(35, 275, 115, 30);
		leftBluePanel.add(deleteButton);

		JButton sortButton = new JButton("SORT");
		sortButton.setBackground(Color.WHITE);
		sortButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		sortButton.setBounds(35, 315, 115, 30);
		leftBluePanel.add(sortButton);

		JButton searchButton = new JButton("");
		searchButton.setToolTipText("Search");
		searchButton.setIcon(new ImageIcon(Dashboard.class.getResource("/images/search.png")));
		searchButton.setBounds(400, 139, 34, 33);
		contentPane.add(searchButton);

		JButton importButton = new JButton("");
		importButton.setToolTipText("Import");
		importButton.setIcon(new ImageIcon(Dashboard.class.getResource("/images/import.png")));
		importButton.setBounds(1045, 139, 34, 33);
		contentPane.add(importButton);
		importButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		importButton.setBackground(Color.WHITE);

		JButton exportButton = new JButton("");
		exportButton.setIcon(new ImageIcon(Dashboard.class.getResource("/images/export.png")));
		exportButton.setToolTipText("Export");
		exportButton.setBounds(1082, 139, 34, 33);
		contentPane.add(exportButton);
		exportButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		exportButton.setBackground(Color.WHITE);


		JButton logoutBUtton = new JButton("LOG OUT");
		logoutBUtton.setFont(new Font("Tahoma", Font.BOLD, 13));
		logoutBUtton.setForeground(new Color(255, 255, 255));
		logoutBUtton.setBackground(new Color(70, 130, 180));
		logoutBUtton.setBounds(871, 13, 96, 25);
		upperBluePanel.add(logoutBUtton);

		// for the scroll pane of the jtable, in case it is more than the window size
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(215, 185, 911, 402);
		contentPane.add(scrollPane);

		// create table and dont allow edit just by double clicking the table
		// must edit through update button
		table = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		// set columns to this values
		Object[] columns = { "Member No.", "First Name", "Last Name", "Email Address", "Membership Type", "Date Added",
				"Expiration Date" };
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		scrollPane.setViewportView(table); // add table to view


		// set alignment of columns and size
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

		// Add button - for adding new row
		// create Add window and remove window decoration
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddWindow aw = new AddWindow();
				aw.setUndecorated(true);
				aw.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
				aw.setVisible(true);
			}
		});
		// create update window to get ready if used just in case
		UpdateWindow uw = new UpdateWindow();
		uw.setUndecorated(true);
		uw.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		// add mouse click listener to the table to get index for updating and deleting
		// function
		// also if clicked a row, get the values and put it in update window text fields
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// i = the index of the selected row
				int i = table.getSelectedRow();

				try {// try to get value of each row and set it to the textfields if null then catch
					uw.fnameField.setText(model.getValueAt(i, 1).toString());
					uw.lnameField.setText(model.getValueAt(i, 2).toString());
					uw.emailField.setText(model.getValueAt(i, 3).toString());
					uw.textMemtype.setText(model.getValueAt(i, 4).toString());
				} catch (NullPointerException e1) {
				}
			}
		});

		// Update button - for editing the values of the membership info
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow(); // get the nth row
				// if selected show Update Window
				if (i >= 0) {
					uw.setVisible(true);
				} // if null and did not select show this message
				else {
					JOptionPane.showMessageDialog(null, "Please select a row to update.", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		// Delete Button - for deleting entire row, cant delete one column in one row
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// i = the index of the selected row
				int i = table.getSelectedRow();
				// if selected a row then confirm
				if (i >= 0) {
					// confirm if sure to remove a row from jtable
					int response = JOptionPane.showConfirmDialog(rootPane, "Are you sure to delete this row?",
							"Confirm delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response == 0) { // if okey then delete
						((DefaultTableModel) table.getModel()).removeRow(i);
					}
				} // if did not select row show warning message
				else {
					JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		// set the sorter as the sorter of the table
		table.setRowSorter(sorter);

		// Export button - for saving the data to a files
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportWindow ew = new ExportWindow();
				ew.setUndecorated(true);
				ew.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
				ew.setVisible(true);

			}
		});
		// Import button - for getting the data from a file to put in jtable
		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImportWindow iw = new ImportWindow();
				iw.setUndecorated(true);
				iw.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
				iw.setVisible(true);

			}
		});

		// Sort button - uses inbuilt table sorter of java and put it in a Array list
		// to sort the data in ascending order (click columns where it will base the
		// sorting)
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedColumn(); // get the index of column selected
				try {
					// make a sorter

					// make a list then add the sorted data in ascending order
					List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
					sortKeys.add(new RowSorter.SortKey(i, SortOrder.ASCENDING));
					sorter.setSortKeys(sortKeys);
				} catch (IllegalArgumentException e2) {
				}

			}
		});
		
		// search button for the retrieval of data, has two icons and state
		// 1) not search, normal icon look	2)searched, change icon to back icon
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!searched) {
					String searchString = searchField.getText();
					sorter.setRowFilter(RowFilter.regexFilter("(?i)"+ searchString));
					searchButton.setIcon(new ImageIcon(Dashboard.class.getResource("/images/back.png")));
					searchButton.setToolTipText("Go Back");
					searched = true;
				}else {
					String searchString = "";
					sorter.setRowFilter(RowFilter.regexFilter(searchString));
					searchButton.setIcon(new ImageIcon(Dashboard.class.getResource("/images/search.png")));
					searchButton.setToolTipText("Search");
					searched = false;
				}
				

			}
		});

		// Log out button - if user want to quit
		logoutBUtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(rootPane, "Are you sure in Logging out?", "Confirm delete",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				// if okey return to home
				if (response == 0) {
					dispose();
					Home h = new Home();
					h.setVisible(true);
					dispose();
				}

			}
		});
	}

	// validate email address
	public static boolean isEmailValid(String email) {
		String emailRegex = InputValidator.validEmail;
		// using regex check if email is null and
		// if not check if it matches the standard email format
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

	// validate first name
	public static boolean isFirstNameValid(String name) {
		return name.matches(InputValidator.validFirstName);
	}

	// validate last name
	public static boolean isLastNameValid(String name) {
		return name.matches(InputValidator.validLastName);
	}

	// validate if there is no duplication of email or member id
	public static boolean ifNotDuplicate(String memberId, String memEmail) {
		int row = Dashboard.table.getRowCount();
		boolean ifnoduplicate = true;
		String id = "";
		String email = "";
		for (int i = 0; i < row; i++) {
			// get value of email and id on table
			id = Dashboard.table.getValueAt(i, 0).toString().trim();
			email = Dashboard.table.getValueAt(i, 3).toString().trim();
			// check if there is duplicate if yes set 'ifnoduplicate' to false and stop loop
			if (memberId.equals(id) || memEmail.equals(email)) {
				ifnoduplicate = false;
				break;
			}
		}
		return ifnoduplicate;
	}
}
