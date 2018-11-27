package project_x.guis;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import project_x.models.DatabaseOperator;

public class MainGui {

	public JFrame frmMusicSearcher;
	private AlbumPanel albumPanel;
	private SongPanel SongPanel;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public MainGui() {
		initialize();
		try {
			
			DatabaseOperator.initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMusicSearcher = new JFrame();
		frmMusicSearcher.setTitle("Music Searcher");
		frmMusicSearcher.setBounds(100, 100, 627, 500);
		frmMusicSearcher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMusicSearcher.getContentPane().setLayout(new BoxLayout(frmMusicSearcher.getContentPane(), BoxLayout.X_AXIS));
		
		ArtistPanel artistPanel = new ArtistPanel(frmMusicSearcher);
		frmMusicSearcher.getContentPane().add(artistPanel);
		
		
		albumPanel = new AlbumPanel(frmMusicSearcher);
		frmMusicSearcher.getContentPane().add(albumPanel);
		
		SongPanel = new SongPanel(frmMusicSearcher);
		frmMusicSearcher.getContentPane().add(SongPanel);
		
		SongPanel.loadSongs();
		albumPanel.loadAlbums();
		artistPanel.loadArtists();
//		artistList.setLayout(new ScrollPaneLayout());
		
		
	}

	

}
