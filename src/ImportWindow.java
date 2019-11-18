import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


// pop up for importing
public class ImportWindow extends JFrame {

	// set serial id to default and declare variables
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextField pathField;

	// constructor
	public ImportWindow() {
		setAlwaysOnTop(true);
		setType(Type.POPUP);
		// set layout and settings
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 63);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		// declare textfield path

		pathField = new JTextField();
		pathField.setBounds(12, 13, 290, 36);
		contentPane.add(pathField);
		pathField.setColumns(10);

		// initialize buttons : import, cancel
		JButton importButton = new JButton("IMPORT");
		importButton.setForeground(Color.WHITE);
		importButton.setBackground(new Color(30, 144, 255));
		importButton.setBounds(302, 13, 88, 36);
		contentPane.add(importButton);

		JButton cancelButton = new JButton("EXIT");
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBackground(Color.GRAY);
		cancelButton.setBounds(390, 13, 88, 36);
		contentPane.add(cancelButton);

		// Import button
		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCountBefore = Dashboard.model.getRowCount();
				int response = JOptionPane.showConfirmDialog(rootPane, "Are you sure to Import?", "Confirm Action",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == 0) {
					// if yes then get path from textfield textpath then create file
					String filePath = pathField.getText();
					File file = new File(filePath);

					try {// create br object
						BufferedReader br = new BufferedReader(new FileReader(file));
						// get the first line
						// get the columns name from the first line
						// set columns name to the jtable model
						String firstLine = br.readLine().trim();
						String[] columnsName = firstLine.split(",");
						Dashboard.model.setColumnIdentifiers(columnsName);

						// get lines from txt file
						Object[] tableLines = br.lines().toArray();

						// extratct data from lines
						// set data to jtable model

						// if unique then save

						for (int i = 0; i < tableLines.length; i++) {

							String line = tableLines[i].toString().trim();
							String[] dataRow = line.split(",");
							Dashboard.model.addRow(dataRow);

						}
						br.close();

					} // if path file input does not exist then show error message
					catch (FileNotFoundException ex) {
						JOptionPane.showMessageDialog(null, "File Path not found.");
					} catch (IOException ex) {

					} catch (ArrayIndexOutOfBoundsException ex) {
						JOptionPane.showMessageDialog(null, "Error: Too many import in one sesson try again later.");
					}

					finally {
						// checks if the imported is not duplicate with the jtable values (memberid and
						// email) if not unique show error message

						int rowCountAfter = Dashboard.model.getRowCount();
						outerloop: for (int k = 0; k < rowCountAfter; k++) {
							Object obj = Dashboard.model.getValueAt(k, 0);
							Object obj1 = Dashboard.model.getValueAt(k, 3);
							for (int j = 0; j < rowCountAfter; j++) {
								if (k == j)
									continue;
								else {
									if (obj.equals(Dashboard.model.getValueAt(j, 0))
											|| obj1.equals(Dashboard.model.getValueAt(j, 3))) {
										for (int n = rowCountAfter; n > rowCountBefore; n--)
											((DefaultTableModel) Dashboard.table.getModel()).removeRow(n - 1);
										JOptionPane.showMessageDialog(null, "Error: duplicate member id or e-mail.");
										break outerloop;
									}
								}
							}

						}
						// every time you try to import, it will change the width of the column 3 so its
						// required to set it to 120 again.
						Dashboard.table.getColumnModel().getColumn(3).setPreferredWidth(120);
					}

				}
			}

		});
		// same as other cancel buttons
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(rootPane, "Exit Import?", "Confirm exit",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == 0) {
					dispose();
				}

			}
		});
	}

}
