package project_x.guis;

import javax.swing.JPanel;

import project_x.models.Song;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Dimension;

public class SingleSongPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5227408996808100142L;
	/**
	 * Create the panel.
	 */
	CustomActionListener listener ;
	SingleSongPanel self;
	public SingleSongPanel(Song song,CustomActionListener l) {
		listener = l;
		setMaximumSize(new Dimension(220, 80));
		self = this;
		JLabel lblNewLabel = new JLabel(song.songName);
		add(lblNewLabel);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					song.delete();
					listener.onDeleteSuccess();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					listener.onDeleteFail();
				}
			}
		});
		add(btnDelete);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String msg = "SongName: %name% ,AlbumName: %album%, ArtistName: %artist%";
				msg =  msg.replace("%name%", song.songName);
				msg =  msg.replace("%album%", song.songAlbum.albumName);
				msg =  msg.replace("%artist%", song.songAlbum.albumArtist.name);

				JOptionPane.showMessageDialog(self, msg,"details", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(btnView);
		
	}
	


	public interface CustomActionListener{
		void onDeleteSuccess();
		void onDeleteFail();
	}

}
