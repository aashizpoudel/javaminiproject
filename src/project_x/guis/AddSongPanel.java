package project_x.guis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project_x.models.Album;
import project_x.models.Artist;
import project_x.models.Song;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;

public class AddSongPanel extends JPanel {
	private JTextField textField;
	ArrayList<Album> albums = new ArrayList<>();
	JComboBox comboBox;
	JDialog parent;
	/**
	 * Create the panel.
	 */
	public AddSongPanel(JDialog s) {
		parent = s;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		javax.swing.JLabel lbl = new javax.swing.JLabel("SongName");
		add(lbl);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JLabel lblArtist = new JLabel("Album");
		add(lblArtist);
		
	 comboBox = new JComboBox();
		add(comboBox);
		
		try {
			albums = (ArrayList<Album>) Album.loadAll();
			for(Album a:albums) {
				comboBox.addItem(a.albumName);;
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
						Song.create(textField.getText(),albums.get(comboBox.getSelectedIndex()).id);
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
