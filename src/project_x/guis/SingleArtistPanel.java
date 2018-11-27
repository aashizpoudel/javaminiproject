package project_x.guis;

import javax.swing.JPanel;

import project_x.models.Artist;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Dimension;

public class SingleArtistPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5227408996808100142L;
	/**
	 * Create the panel.
	 */
	CustomActionListener listener ;
	SingleArtistPanel self;
	public SingleArtistPanel(Artist artist,CustomActionListener l) {
		listener = l;
		setMaximumSize(new Dimension(220, 40));
		self = this;
		JLabel lblNewLabel = new JLabel(artist.name);
		add(lblNewLabel);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					artist.delete();
					listener.onDeleteSuccess();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					listener.onDeleteFail();
				}
			}
		});
		add(btnDelete);
		
	
	}
	


	public interface CustomActionListener{
		void onDeleteSuccess();
		void onDeleteFail();
	}

}
