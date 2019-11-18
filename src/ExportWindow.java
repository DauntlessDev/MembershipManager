import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

// pop up for exporting
public class ExportWindow extends JFrame {
	// set serial id to default and declare variables
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField pathField;

	// constructor
	public ExportWindow() {
		setAlwaysOnTop(true);
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 63);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		// textfield for getting the file path input
		pathField = new JTextField();
		pathField.setBounds(12, 13, 290, 36);
		contentPane.add(pathField);
		pathField.setColumns(10);

		// initialize button : export and cancel
		JButton exportButton = new JButton("EXPORT");
		exportButton.setForeground(Color.WHITE);
		exportButton.setBackground(new Color(30, 144, 255));
		exportButton.setBounds(302, 13, 88, 36);
		contentPane.add(exportButton);

		JButton cancelButton = new JButton("EXIT");
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBackground(Color.GRAY);
		cancelButton.setBounds(390, 13, 88, 36);
		contentPane.add(cancelButton);

		// for exporting button
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(rootPane, "Are you sure to Export?", "Confirm action",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == 0) {
					{ // if ok, then create a bfw object set the path from the input got from
						// textfield textpath
						BufferedWriter bfw;

						String filePath = pathField.getText();
						// filePath = filePath.replace('\\', '/');
						try {
							bfw = new BufferedWriter(new FileWriter(filePath));
							// then check the column count and transfer to file as the first row
							for (int i = 0; i < Dashboard.table.getColumnCount(); i++) {
								bfw.write(Dashboard.table.getColumnName(i));
								bfw.write(",");
							}
							// then check the rows and put it below the columns separated by ','
							for (int i = 0; i < Dashboard.table.getRowCount(); i++) {
								bfw.newLine();
								for (int j = 0; j < Dashboard.table.getColumnCount(); j++) {
									bfw.write((String) (Dashboard.table.getValueAt(i, j)));
									bfw.write(",");
									;
								}
							}
							// close if done for security
							bfw.close();

						} // if input path file error then catch and show error message
						catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "File path not found.");
						} catch (IOException e1) {
							// catch block
							e1.printStackTrace();
						}

						// if done then show message
						JOptionPane.showMessageDialog(null, "Data Exported");
					}
					;

				}

			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(rootPane, "Exit Export?", "Confirm exit",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == 0) {
					dispose();
				}

			}
		});
	}
}
