package project_x.guis;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddArtistDialog extends JDialog  implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8587193963199402233L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtArtistName;
	private ActionListener listener;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AddArtistDialog dialog = new AddArtistDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AddArtistDialog() {
		
		setTitle("Add Artist Dialog");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 361, 140);
		
	
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblArtistName = new JLabel("Artist Name");
			contentPanel.add(lblArtistName);
		}
		{
			txtArtistName = new JTextField();
			contentPanel.add(txtArtistName);
			txtArtistName.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Add");
				okButton.setActionCommand("Add");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
	}
	
	public void setListener(ActionListener l) {
		listener = l ;
	}
	interface ActionListener {
		void onAdd(String text);
		void onCancel();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(listener==null) {
			return;
		}
		if(arg0.getActionCommand() == "Add") {
			if(txtArtistName.getText().length()==0) {
				return;
			}
			listener.onAdd(txtArtistName.getText());
		}else if(arg0.getActionCommand()=="Cancel") {
			listener.onCancel();
		}
		
		
	}
}
