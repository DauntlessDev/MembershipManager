import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

// this window has very similar design as add window but very different workings in behind
public class UpdateWindow extends JFrame implements InputValidator {
	// declare variables need for the update windows
	// set the serial id to default
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextField memberNum;
	JTextField fnameField;
	JTextField lnameField;
	JTextField emailField;
	JTextField textMemtype;

	// constructor
	public UpdateWindow() {
		setAlwaysOnTop(true);
		setType(Type.POPUP); // set to pop up type
		setTitle("Update row");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 353);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		

		// blue panel for modern look design
		JPanel bluePanel = new JPanel();
		bluePanel.setBackground(new Color(0, 0, 128));
		bluePanel.setBounds(0, 0, 590, 53);
		contentPane.add(bluePanel);
		bluePanel.setLayout(null);

		// initialize labels and set string values,dimension and design
		JLabel fnameLabel = new JLabel("First Name:");
		fnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fnameLabel.setBounds(32, 88, 147, 16);
		contentPane.add(fnameLabel);

		JLabel lnameLabel = new JLabel("Last Name:");
		lnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lnameLabel.setBounds(32, 137, 122, 16);
		contentPane.add(lnameLabel);

		JLabel emailLabel = new JLabel("Email Address");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emailLabel.setBounds(32, 186, 117, 16);
		contentPane.add(emailLabel);
		
		JLabel lblMembershipType = new JLabel("Membership Type:");
		lblMembershipType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMembershipType.setBounds(32, 235, 168, 16);
		contentPane.add(lblMembershipType);

		JLabel lblAddAMember = new JLabel("UPDATE MEMBER INFORMATION");
		lblAddAMember.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblAddAMember.setBounds(158, 0, 272, 53);
		lblAddAMember.setForeground(new Color(255, 255, 255));
		bluePanel.add(lblAddAMember);


		// initialize listbox
		JSpinner memtypeSpinner = new JSpinner(new SpinnerListModel(new String[] { "Chapter Member", "Chapter Officer" }));
		memtypeSpinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		memtypeSpinner.setBounds(172, 226, 385, 36);
		contentPane.add(memtypeSpinner);

		// initialize textfields and set string values,dimension and design
		fnameField = new JTextField();
		fnameField.setToolTipText("First name should start with capital letter");
		fnameField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fnameField.setColumns(10);
		fnameField.setBounds(172, 79, 385, 36);
		contentPane.add(fnameField);

		lnameField = new JTextField();
		lnameField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lnameField.setColumns(10);
		lnameField.setBounds(172, 128, 385, 36);
		contentPane.add(lnameField);

		emailField = new JTextField();
		emailField.setToolTipText("Input valid email that follow the standard format");
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emailField.setColumns(10);
		emailField.setBounds(172, 177, 385, 36);
		contentPane.add(emailField);

		// initialize buttons and set string values,dimension and design
		JButton saveButton = new JButton("SAVE");
		saveButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		saveButton.setBackground(new Color(30, 144, 255));
		saveButton.setForeground(Color.WHITE);
		saveButton.setBounds(346, 296, 97, 41);
		contentPane.add(saveButton);

		JButton btnCancel = new JButton("EXIT");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.setBackground(new Color(192, 192, 192));
		btnCancel.setBounds(460, 296, 97, 41);
		contentPane.add(btnCancel);

		// save button
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// i = the index of the selected row
				int i = Dashboard.table.getSelectedRow();
				int response = JOptionPane.showConfirmDialog(rootPane, "Add this to table?", "Confirm addition",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == 0) {
					if (i >= 0) {
						try { // based on selected row check each values if there is an error
							String currentValue = (String) memtypeSpinner.getValue();

							// check if duplicate, if empty or if does not follow the format for name and
							// email, if no error proceed in saving
							if (!Dashboard.ifNotDuplicate("MemberID", emailField.getText().toString())) {
								JOptionPane.showMessageDialog(null, "There is a duplicate email", "Duplicate",
										JOptionPane.ERROR_MESSAGE);
							} else if (fnameField.getText().trim().equals("") || lnameField.getText().equals("")
									|| emailField.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Please input required information.", "Null",
										JOptionPane.ERROR_MESSAGE);
							} else if (!Dashboard.isFirstNameValid(fnameField.getText())) {
								JOptionPane.showMessageDialog(null,
										"Please input a valid First Name that starts with capital letter.",
										"Input Error", JOptionPane.WARNING_MESSAGE);
							} else if (!Dashboard.isLastNameValid(lnameField.getText())) {
								JOptionPane.showMessageDialog(null,
										"Please input a valid Last Name that starts with capital letter.",
										"Input Error", JOptionPane.WARNING_MESSAGE);
							} else if (!Dashboard.isEmailValid(emailField.getText())) {
								JOptionPane.showMessageDialog(null, "Please input a valid Email Address.",
										"Input Error", JOptionPane.WARNING_MESSAGE);
							} else {
								Dashboard.model.setValueAt(fnameField.getText(), i, 1);
								Dashboard.model.setValueAt(lnameField.getText(), i, 2);
								Dashboard.model.setValueAt(emailField.getText(), i, 3);
								Dashboard.model.setValueAt(currentValue, i, 4);
							}
						} catch (NullPointerException e2) {
						}
					} // if did not select a row, show message
					else {
						JOptionPane.showMessageDialog(null, "Please select a row to update.", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		// same as other cancel button
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(rootPane, "Exit Update Window?", "Confirm exit",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == 0) {
					dispose();
				}

			}
		});
	}
}
