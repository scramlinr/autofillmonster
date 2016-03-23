import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Component;
import java.awt.SystemColor;
public class autoGui {

	private JFrame frmAutoFill;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					autoGui window = new autoGui();
					window.frmAutoFill.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public autoGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAutoFill = new JFrame();
		frmAutoFill.setType(Type.UTILITY);
		frmAutoFill.setTitle("Auto Fill ");
		frmAutoFill.setFont(new Font("Consolas", Font.PLAIN, 12));
		frmAutoFill.setForeground(Color.WHITE);
		frmAutoFill.setBackground(Color.RED);
		frmAutoFill.getContentPane().setBackground(new Color(192, 192, 192));
		frmAutoFill.setBounds(100, 100, 711, 482);
		frmAutoFill.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAutoFill.getContentPane().setLayout(null);
		
		JLabel imgLabel = new JLabel("");
		imgLabel.setIcon(new ImageIcon("C:\\Users\\Sog\\Desktop\\pika.jpg"));
		Image img = new ImageIcon(this.getClass().getResource("/pika.jpg")).getImage();
		imgLabel.setBounds(10, 11, 254, 337);
		frmAutoFill.getContentPane().add(imgLabel);
	}
}
