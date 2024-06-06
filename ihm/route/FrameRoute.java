package ihm.route;

import controleur.Controleur;
import metier.Ville;

import java.awt.BorderLayout;
import java. awt. Toolkit;
import java.awt.Dimension;

import javax.swing.*;
public class FrameRoute extends JFrame
{
	private PanelRoute      panelRoute;
	private PanelTableRoute panelTableRoute;

	private Controleur ctrl;

	public FrameRoute( Controleur ctrl)
	{
		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;

		this.setTitle   ( "Route" );
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		this.setSize    ( (int)(width/2)-50,(int)(height*0.33)-50 );
		this.setLocation(  50+ctrl.getVilleSize().width, ctrl.getReseauSize().height+50 );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.panelRoute = new PanelRoute(this.ctrl);
		JPanel panelTest = new JPanel();
		panelTest.setLayout(new BorderLayout());
		panelTest.add(panelRoute, BorderLayout.NORTH);
		this.panelTableRoute = new PanelTableRoute(this.ctrl);

		this.add (panelTableRoute, BorderLayout.CENTER);
		
		this.add (panelTest, BorderLayout.EAST);

		this.setVisible(true);
	}

	public void ajouterCombo(Ville v) { panelRoute.ajouterCombo(v); }

	public void rafraichir() { this.panelTableRoute.rafraichir(); }
}
