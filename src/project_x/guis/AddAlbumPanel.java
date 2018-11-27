package project_x.guis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project_x.models.Album;
import project_x.models.Artist;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;

public class AddAlbumPanel extends JPanel {
	private JTextField textField;
	ArrayList<Artist> artists = new ArrayList<>();
	JComboBox comboBox;
	JDialog parent;
	/**
	 * Create the panel.
	 */
	public AddAlbumPanel(JDialog s) {
		parent = s;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		javax.swing.JLabel lbl = new javax.swing.JLabel("AlbumName");
		add(lbl);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JLabel lblArtist = new JLabel("Artist");
		add(lblArtist);
		
	 comboBox = new JComboBox();
		add(comboBox);
		
		try {
			artists = (ArrayList<Artist>) Artist.loadAll();
			for(Artist a:artists) {
				comboBox.addItem(a.name);;
			}
		}catch(Exception ex) {
			
		}
		
		JButton btnAdd = new JButton("Add");
		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(textField.getText().length() != 0) {
					try {
						Album.create(textField.getText(),artists.get(comboBox.getSelectedIndex()).id);
						JOptionPane.showMessageDialog(parent,"Successfully created,please load again", "Success",JOptionPane.INFORMATION_MESSAGE);;

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(parent, e.getMessage(), "Failed",JOptionPane.WARNING_MESSAGE);;
					}
					parent.setVisible(false);
				}
			}
			
		});
		
	}

}
