package parser;

import java.awt.EventQueue; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private File firstFile = null;
	private File privacyFile = null;
	private File scriptFile = null;
	private String restultFileAbsolutePath = "";
	private static final String EXTENSION = ".z151";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
	
	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("Pick files");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnFirstFile = new JButton("Select your file");
		btnFirstFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFirstFile.setBounds(12, 29, 149, 35);
		contentPane.add(btnFirstFile);
		
		JButton btnSecondFile = new JButton("Select privacy file");
		btnSecondFile.setBounds(12, 93, 149, 35);
		contentPane.add(btnSecondFile);
		
		JLabel lblFirstFile = new JLabel("");
		lblFirstFile.setBounds(209, 29, 438, 35);
		contentPane.add(lblFirstFile);
		
		JLabel lblSecondFile = new JLabel("");
		lblSecondFile.setBounds(209, 93, 438, 35);
		contentPane.add(lblSecondFile);
		
		JButton btnMerge = new JButton("Merge");
		btnMerge.setBounds(274, 309, 97, 25);
		contentPane.add(btnMerge);
		
		JButton btnResultFile = new JButton("Select Result File");
		btnResultFile.setBounds(12, 155, 149, 35);
		contentPane.add(btnResultFile);
		
		JLabel lblResultFile = new JLabel("");
		lblResultFile.setBounds(206, 155, 441, 35);
		contentPane.add(lblResultFile);
		
		JButton btnScript = new JButton("Connection Script");
		btnScript.setBounds(12, 209, 149, 35);
		contentPane.add(btnScript);
		
		JLabel lblScript = new JLabel("");
		lblScript.setBounds(209, 209, 438, 35);
		contentPane.add(lblScript);
	
		btnFirstFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(true);
				int option = chooser.showOpenDialog(GUI.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					firstFile = chooser.getSelectedFile();			
					lblFirstFile.setText(firstFile.getAbsolutePath());
				}
				
			}
		});
		
		btnSecondFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(true);
				int option = chooser.showOpenDialog(GUI.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					privacyFile = chooser.getSelectedFile();			
					lblSecondFile.setText(privacyFile.getAbsolutePath());
				}
				
			}
		});
		
		btnResultFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(true);
				int option = chooser.showOpenDialog(GUI.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					restultFileAbsolutePath = chooser.getSelectedFile().getAbsolutePath();
					if (getExtension(restultFileAbsolutePath).equals("")){
						restultFileAbsolutePath += EXTENSION;
						lblResultFile.setText(restultFileAbsolutePath);
					}
					else
						lblResultFile.setText(restultFileAbsolutePath);
				}
			}
		});
		
		btnScript.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(true);
				int option = chooser.showOpenDialog(GUI.this);
				if (option == JFileChooser.APPROVE_OPTION){
					scriptFile = chooser.getSelectedFile();
					lblScript.setText(scriptFile.getAbsolutePath());
				}
			}
		});
		
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				if (firstFile == null || privacyFile == null || restultFileAbsolutePath.isEmpty()){
					JOptionPane.showMessageDialog(null, "Please select the files", "Message", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ParseXML newParser = new ParseXML();
				newParser.mergeFiles(firstFile, privacyFile, scriptFile, restultFileAbsolutePath);
				if (!newParser.getWrongNodes().isEmpty()){
					JOptionPane.showMessageDialog(null, "SAME ID, Check the wrong list in ERROR FILE", "Message", JOptionPane.ERROR_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Finish", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	private String getExtension(String filePath){
		int i = filePath.lastIndexOf(".");
		if (i < 0)
			return "";
		return filePath.substring(i+1);
	}
}
