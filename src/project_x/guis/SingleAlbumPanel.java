package project_x.guis;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import project_x.models.Album;



public class SingleAlbumPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7547531226179084541L;
	/**
	 * Create the panel.
	 */
	CustomActionListener listener ;
	SingleAlbumPanel self;
	public SingleAlbumPanel(Album album,CustomActionListener customActionListener) {
		System.out.println(album.albumName);
		listener = customActionListener;
		setMaximumSize(new Dimension(220, 80));
		self = this;
		JLabel lblNewLabel = new JLabel(album.albumName);
		add(lblNewLabel);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					album.delete();
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
				String msg = "AlbumName: %album%, ArtistName: %artist%";
//				msg =  msg.replace("%name%", artist.songName);
				msg =  msg.replace("%album%", album.albumName);
				msg =  msg.replace("%artist%", album.albumArtist.name);

				JOptionPane.showMessageDialog(self, msg,"details", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(btnView);
		
	}

	interface CustomActionListener {
		void onDeleteSuccess();
		void onDeleteFail();
	}
}
