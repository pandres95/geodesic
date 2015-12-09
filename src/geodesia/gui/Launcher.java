package geodesia.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Launcher window = new Launcher();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Launcher() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAa = new JLabel("Programas de Geodesia");
		lblAa.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblAa.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblAa, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton elipsInfo = new JButton("Información de Elipsoide");
		elipsInfo.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent e) {
				new EllipsoidInfo().setVisible(true);;
			}
		});
		panel.add(elipsInfo);
		
		JButton calcCoord = new JButton("Calculo de coordenadas");
		calcCoord.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent e) {
				new Calculations().setVisible(true);
			}
		});
		panel.add(calcCoord);
		
		JButton biscTrisc = new JButton("Bisección y trisección");
		biscTrisc.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(biscTrisc);
		
		JButton nivlGeodD = new JButton("Nivelación Geodésica Diferencial");
		nivlGeodD.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(nivlGeodD);
		
		JPanel bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		JButton buttonExt = new JButton("Salir");
		buttonExt.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bottomPanel.add(buttonExt);
	}

}
