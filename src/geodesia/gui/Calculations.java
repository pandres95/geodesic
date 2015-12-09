package geodesia.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import geodesia.data.Angle;
import geodesia.data.Datum;
import geodesia.data.calculations.DirectCalculation;
import geodesia.data.calculations.ReverseCalculation;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Calculations extends JFrame implements ListSelectionListener, ActionListener {
	
	JLabel lblArea, lblVolumen, lblAre, lblVolume, lblX, lblY, lblZ, lblPhi, lblLambda, lblH, txtPhi, txtLambda, txtH;
	private JLabel lblPh, txtXD, txtYD, txtZD;
	JList<String> list;
	JRadioButton rdbtnDirecta;
	JTextField txtX, txtY, txtZ, txtPhiDH, txtLambdaDH, txtHD;
	JButton btnCalcular, btnCalcularD;

	String[] values = new String[] {
			"WSG84",
			"Internacional",
			"Bogota",
			"Airy 1830",
			"Modified Airy",
			"Australian National",
			"Delambre 1800",
			"Modified Fischer 1960",
			"Helmert 1906",
			"Indonesian 1974",
			"Krassovsky 1940",
			"GRS 80",
			"South American 1969",
			"Struve 1924",
			"Wallbeck 1819"
		};
		Datum datums[] = new Datum[]{
			new Datum.WGS84(),
			new Datum.International(),
			new Datum.Bogota(),
			new Datum.Airy1830(),
			new Datum.ModifAiry(),
			new Datum.AuNational(),
			new Datum.Delambre1800(),
			new Datum.MoFi1960(),
			new Datum.Helmert1906(),
			new Datum.Indonesian1974(),
			new Datum.Krassovsky1940(),
			new Datum.GRS80(),
			new Datum.SouthAm1969(),
			new Datum.Struve1924(),
			new Datum.Wallbeck1819()
		};
	Datum actualDatum = new Datum.WGS84();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton rdbtnInversa;
	private JPanel panelDirect, panelReverse;
	private JTextField txtPhiDM;
	private JTextField txtPhiDS;
	private JTextField txtLambdaDM;
	private JTextField txtLambdaDS;

	
	private final class AbstractListModelExtension extends AbstractListModel<String> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public int getSize() {
			return values.length;
		}

		public String getElementAt(int index) {
			return values[index];
		}
	}

	/**
	 * Create the application.
	 */
	public Calculations() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 600, 450);
		
		JLabel lblNewLabel = new JLabel("Cálculo de Coordenadas");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		list = new JList<String>();
		list.setModel(new AbstractListModelExtension());
		list.addListSelectionListener(this);
		getContentPane().add(list, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		rdbtnInversa = new JRadioButton("Inversa");
		rdbtnInversa.setSelected(true);
		rdbtnInversa.setBounds(16, 6, 141, 23);
		rdbtnInversa.addActionListener(this);
		panel.add(rdbtnInversa);
		
		panelReverse = new JPanel();
		panelReverse.setBounds(6, 41, 588, 146);
		panel.add(panelReverse);
		panelReverse.setLayout(null);
		
		lblX = new JLabel("x");
		lblX.setBounds(18, 11, 8, 16);
		panelReverse.add(lblX);
		
		txtX = new JTextField();
		txtX.setBounds(48, 5, 134, 28);
		txtX.setColumns(10);
		panelReverse.add(txtX);
		
		lblY = new JLabel("y");
		lblY.setBounds(18, 45, 7, 16);
		panelReverse.add(lblY);
		
		txtY = new JTextField();
		txtY.setBounds(48, 39, 134, 28);
		txtY.setColumns(10);
		panelReverse.add(txtY);

		lblZ = new JLabel("z");
		lblZ.setBounds(19, 73, 7, 16);
		panelReverse.add(lblZ);
		
		txtZ = new JTextField();
		txtZ.setBounds(48, 71, 134, 28);
		txtZ.setColumns(10);
		panelReverse.add(txtZ);
		
		lblPhi = new JLabel("φ");
		lblPhi.setBounds(225, 11, 11, 16);
		panelReverse.add(lblPhi);
		
		txtPhi = new JLabel("");
		txtPhi.setBounds(254, 11, 110, 16);
		panelReverse.add(txtPhi);
		
		lblLambda = new JLabel("λ");
		lblLambda.setBounds(228, 45, 8, 16);
		panelReverse.add(lblLambda);
		
		txtLambda = new JLabel("");
		txtLambda.setBounds(248, 45, 110, 16);
		panelReverse.add(txtLambda);
		
		lblH = new JLabel("h");
		lblH.setBounds(228, 73, 8, 16);
		panelReverse.add(lblH);
		
		txtH = new JLabel("");
		txtH.setBounds(254, 73, 110, 16);
		panelReverse.add(txtH);
		
		btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(6, 111, 95, 29);
		btnCalcular.addActionListener(this);
		panelReverse.add(btnCalcular);
		
		rdbtnDirecta = new JRadioButton("Directa");
		rdbtnDirecta.addActionListener(this);
		rdbtnDirecta.setBounds(16, 199, 141, 23);
		panel.add(rdbtnDirecta);
		
		panelDirect = new JPanel();
		panelDirect.setBounds(6, 234, 588, 167);
		panelDirect.setEnabled(false);
		panelDirect.setLayout(null);
		panel.add(panelDirect);
		
		lblPhi = new JLabel("φ");
		lblPhi.setBounds(18, 11, 18, 16);
		panelDirect.add(lblPhi);
		
		txtPhiDH = new JTextField();
		txtPhiDH.setBounds(48, 5, 35, 28);
		txtPhiDH.setColumns(10);
		panelDirect.add(txtPhiDH);
		
		lblLambda = new JLabel("λ");
		lblLambda.setBounds(18, 45, 18, 16);
		panelDirect.add(lblLambda);
		
		txtLambdaDH = new JTextField();
		txtLambdaDH.setBounds(48, 39, 35, 28);
		txtLambdaDH.setColumns(10);
		panelDirect.add(txtLambdaDH);

		lblH = new JLabel("h");
		lblH.setBounds(19, 73, 18, 16);
		panelDirect.add(lblH);
		
		txtHD = new JTextField();
		txtHD.setBounds(48, 71, 134, 28);
		txtHD.setColumns(10);
		panelDirect.add(txtHD);
		
		lblX = new JLabel("x");
		lblX.setBounds(225, 11, 11, 16);
		panelDirect.add(lblX);
		
		txtXD = new JLabel("");
		txtXD.setBounds(254, 11, 110, 16);
		panelDirect.add(txtXD);
		
		lblY = new JLabel("y");
		lblY.setBounds(228, 45, 8, 16);
		panelDirect.add(lblY);
		
		txtYD = new JLabel("");
		txtYD.setBounds(248, 45, 110, 16);
		panelDirect.add(txtYD);
		
		lblZ = new JLabel("z");
		lblZ.setBounds(228, 73, 8, 16);
		panelDirect.add(lblZ);
		
		txtZD = new JLabel("");
		txtZD.setBounds(254, 73, 110, 16);
		panelDirect.add(txtZD);
		
		btnCalcularD = new JButton("Calcular");
		btnCalcularD.setBounds(6, 111, 95, 29);
		btnCalcularD.addActionListener(this);
		panelDirect.add(btnCalcularD);
		
		txtPhiDM = new JTextField();
		txtPhiDM.setColumns(10);
		txtPhiDM.setBounds(95, 5, 35, 28);
		panelDirect.add(txtPhiDM);
		
		txtPhiDS = new JTextField();
		txtPhiDS.setColumns(10);
		txtPhiDS.setBounds(142, 5, 35, 28);
		panelDirect.add(txtPhiDS);
		
		txtLambdaDM = new JTextField();
		txtLambdaDM.setColumns(10);
		txtLambdaDM.setBounds(95, 39, 35, 28);
		panelDirect.add(txtLambdaDM);
		
		txtLambdaDS = new JTextField();
		txtLambdaDS.setColumns(10);
		txtLambdaDS.setBounds(142, 39, 35, 28);
		panelDirect.add(txtLambdaDS);
		
	}
	
	@Override public void valueChanged(ListSelectionEvent e) {
		actualDatum = datums[list.getSelectedIndex()];
	}

	@Override public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnCalcular)){
			Object[] reverse = new ReverseCalculation(actualDatum).calculate(
					Double.valueOf(txtX.getText()), 
					Double.valueOf(txtY.getText()), 
					Double.valueOf(txtZ.getText())
			);
			txtPhi.setText(((Angle)reverse[0]).toString());
			txtLambda.setText(((Angle)reverse[1]).toString());
			txtH.setText(((Double)reverse[2]).toString());
		}
		
		if(e.getSource().equals(btnCalcularD)){
			double[] direct = new DirectCalculation(actualDatum).calculate(
					new Angle(Integer.valueOf(txtPhiDH.getText()), Integer.valueOf(txtPhiDM.getText()), Double.valueOf(txtPhiDS.getText())),
					new Angle(Integer.valueOf(txtLambdaDH.getText()), Integer.valueOf(txtLambdaDM.getText()), Double.valueOf(txtLambdaDS.getText())),
					Double.valueOf(txtHD.getText())
			);
			txtXD.setText(String.format("%f", direct[0]));
			txtYD.setText(String.format("%f", direct[1]));
			txtZD.setText(String.format("%f", direct[2]));
		}
		
		if(e.getSource().equals(rdbtnDirecta)){
			rdbtnInversa.setSelected(false);
			panelReverse.setEnabled(false);
			rdbtnDirecta.setSelected(true);
			panelDirect.setEnabled(true);
		}
		
		if(e.getSource().equals(rdbtnInversa)){
			rdbtnDirecta.setSelected(false);
			panelDirect.setEnabled(false);
			rdbtnInversa.setSelected(true);
			panelReverse.setEnabled(true);
		}
	}
}
