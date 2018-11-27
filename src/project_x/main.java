package project_x;

import java.awt.EventQueue;

import project_x.guis.MainGui;
import project_x.models.Artist;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("App Launched");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frmMusicSearcher.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
