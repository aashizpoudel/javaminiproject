package project_x.guis;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import project_x.guis.SingleAlbumPanel.CustomActionListener;
import project_x.models.Album;
import javax.swing.ScrollPaneConstants;

public class AlbumPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5574626729161082982L;
	/**
	 * Create the panel.
	 */
	JPanel panel ;
	JFrame frame;
	public AlbumPanel(JFrame frame) {
		this.frame = frame;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JLabel lblAlbums = new JLabel("Albums");
		add(lblAlbums);
		
		JButton btnAddNewAlbum = new JButton("Add New Album");
		btnAddNewAlbum.setActionCommand("add");
		btnAddNewAlbum.addActionListener(this);
		add(btnAddNewAlbum);
		
		JButton btnLoadAll = new JButton("Load all ");
		btnLoadAll.setActionCommand("load");
		btnLoadAll.addActionListener(this);
		add(btnLoadAll);
		
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
			loadAlbums();
		}
		if(cmd=="add") {
			JDialog s = new JDialog(frame,"add album");
			s.setLocationRelativeTo(frame);
			s.setSize(600, 200);
			s.setModalityType(ModalityType.APPLICATION_MODAL);
			s.setContentPane(new AddAlbumPanel(s));
			s.setVisible(true);
			loadAlbums();
		}
	}
	
	public void loadAlbums() {
		panel.removeAll();
		panel.updateUI();
		try {
			for(Album album:Album.loadAll()){
				panel.add(new SingleAlbumPanel(album, new CustomActionListener() {
					
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
				}));
				panel.updateUI();
			}
			JOptionPane.showMessageDialog(this, "Successfully loaded!!", "Message", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error in loading", JOptionPane.ERROR_MESSAGE);

		};
	}

}
