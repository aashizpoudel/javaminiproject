package project_x.guis;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import project_x.guis.SingleSongPanel.CustomActionListener;
import project_x.models.Album;
import project_x.models.Artist;
import project_x.models.Song;

import javax.swing.ScrollPaneConstants;

public class SongPanel extends JPanel implements ActionListener,SingleSongPanel.CustomActionListener{

	/**
	 * Create the panel.
	 */
	JPanel panel ;
	JFrame frame;
	public SongPanel(JFrame frame) {
		this.frame = frame;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JLabel lblSongs = new JLabel("Songs");
		add(lblSongs);
		
		JButton btnAddNewSong = new JButton("Add New Song");
		btnAddNewSong.setActionCommand("add");
		btnAddNewSong.addActionListener(this);
		add(btnAddNewSong);
		
		JButton btnLoadAll = new JButton("Load all ");
		btnLoadAll.setActionCommand("load");
		btnLoadAll.addActionListener(this);
		add(btnLoadAll);
		
		JButton btnSearch= new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String search =JOptionPane.showInputDialog(frame, "Enter search string","Search", JOptionPane.INFORMATION_MESSAGE);
				addToPane(Song.searchByName(search));
			}

	
		});
		add(btnSearch);
		
		panel = new JPanel();

		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String cmd = arg0.getActionCommand();
		if(cmd=="load") {
			loadSongs();
		}
		if(cmd=="add") {
			JDialog s = new JDialog(frame,"add song");
			s.setLocationRelativeTo(frame);
			s.setSize(600, 200);
			s.setModalityType(ModalityType.APPLICATION_MODAL);
			s.setContentPane(new AddSongPanel(s));
			s.setVisible(true);
			loadSongs();
		}
	}
	
	public void loadSongs() {
		panel.removeAll();
		panel.updateUI();
		try {
			addToPane(Song.loadAll());
			JOptionPane.showMessageDialog(this, "Successfully loaded!!", "Message", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error in loading", JOptionPane.ERROR_MESSAGE);

		};
	}

	@Override
	public void onDeleteSuccess() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(frame,"Success in deletion","Success in creation", JOptionPane.INFORMATION_MESSAGE);

	}
	
	@Override
	public void onDeleteFail() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(frame,"Error in deletion","Error in Deletion", JOptionPane.ERROR_MESSAGE);

	}
	
	private void addToPane(List<Song> searchByName) {
		// TODO Auto-generated method stub
		if(searchByName.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "No Data found", "Empty",JOptionPane.ERROR_MESSAGE);
			return;
		}
		panel.removeAll();
		for(Song s:searchByName) {
		
			panel.add(new SingleSongPanel(s, this));
			panel.updateUI();
		}
		
	}

}
