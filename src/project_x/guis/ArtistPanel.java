package project_x.guis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import project_x.guis.SingleArtistPanel.CustomActionListener;
import project_x.models.Artist;
import java.awt.FlowLayout;

public class ArtistPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9183300087707141779L;
	private JLabel artist;
	private JButton btnAddNewArtist;
	private JButton btnLoadArtists;
	private JPanel artistList;
	
	JFrame frame;
	/**
	 * Create the panel.
	 */
	public ArtistPanel(JFrame frame) {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		this.frame = frame;
		
		
		JPanel artistPanel = this;
//		add(artistPanel);
		artistPanel.setLayout(new BoxLayout(artistPanel, BoxLayout.Y_AXIS));
		
		artist = new JLabel("Artist");
		artistPanel.add(artist);
		
		btnAddNewArtist = new JButton("Add new Artist");
		btnAddNewArtist.setActionCommand("add_artist");
		artistPanel.add(btnAddNewArtist);
		btnAddNewArtist.addActionListener(this);
		
		btnLoadArtists = new JButton("Load Artists");
		btnLoadArtists.setActionCommand("load_artists");
		btnLoadArtists.addActionListener(this);
		artistPanel.add(btnLoadArtists);
		
		
		artistList = new JPanel();;
		JScrollPane artistScroll = new JScrollPane(artistList);
		artistList.setLayout(new BoxLayout(artistList, BoxLayout.PAGE_AXIS));
		artistScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		artistPanel.add(artistScroll);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String cmd=arg0.getActionCommand();
		if(cmd == "add_artist") {
			AddArtistDialog dialog= new AddArtistDialog();
			
//			this.frame.setVisible(false);
			dialog.setListener(new AddArtistDialog.ActionListener() {
				
				@Override
				public void onCancel() {
					// TODO Auto-generated method stub
					
					dialog.setVisible(false);
					
				}
				
				@Override
				public void onAdd(String text) {
					// TODO Auto-generated method stub
//					System.out.println(text);
					try {
						Artist.create(text);
							JOptionPane.showMessageDialog(frame,"Successfully created",
									"Success", JOptionPane.INFORMATION_MESSAGE);
							loadArtists();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frame,e.getMessage(),"Error in creation", JOptionPane.ERROR_MESSAGE);
					}
					dialog.setVisible(false);
				}
			});
			dialog.setVisible(true);
		}else if(cmd=="load_artists") {
		
			loadArtists();
			
		}
		
	}
	
	public void loadArtists() {
		artistList.removeAll();
		artistList.updateUI();
		try {
			for (Artist artist :Artist.loadAll()) {
				artistList.add(new SingleArtistPanel(artist,new CustomActionListener() {

					@Override
					public void onDeleteSuccess() {
						// TODO Auto-generated method stub
						JOptionPane.showMessageDialog(frame, "Successfully Deleted!!", "Message", JOptionPane.INFORMATION_MESSAGE);
						loadArtists();
					}

					@Override
					public void onDeleteFail() {
						// TODO Auto-generated method stub
						
					}
					
				}));
				artistList.updateUI();
			}
			JOptionPane.showMessageDialog(this, "Successfully loaded!!", "Message", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error in loading", JOptionPane.ERROR_MESSAGE);
		}
	}

}
