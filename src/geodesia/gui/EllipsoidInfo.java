package geodesia.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import geodesia.data.Datum;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class EllipsoidInfo extends JFrame implements ListSelectionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	JLabel lblArea, lblVolumen, lblAre, lblVolume;
	JList<String> list;

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
	
	/**
	 * Create the application.
	 */
	public EllipsoidInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("Información de Elipsoide");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblArea = new JLabel("Area");
		lblArea.setBounds(46, 67, 61, 16);
		panel.add(lblArea);
		
		lblVolumen = new JLabel("Volumen");
		lblVolumen.setBounds(46, 95, 61, 16);
		panel.add(lblVolumen);
		
		lblAre = new JLabel("");
		lblAre.setBounds(119, 67, 250, 16);
		panel.add(lblAre);
		
		lblVolume = new JLabel("");
		lblVolume.setBounds(119, 95, 250, 16);
		panel.add(lblVolume);
		
		list = new JList<String>();
		list.setModel(new AbstractListModelExtension());
		list.addListSelectionListener(this);
		getContentPane().add(list, BorderLayout.WEST);
	}
	
	@Override public void valueChanged(ListSelectionEvent e) {
		Datum datum = datums[list.getSelectedIndex()];
		lblAre.setText(String.format("%f", datum.getArea()));
		lblVolume.setText(String.format("%f", datum.getVolume()));
	}
	
	
}
