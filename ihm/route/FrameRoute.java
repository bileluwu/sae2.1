package ihm.route;

import controleur.Controleur;
import metier.Ville;

import java.awt.BorderLayout;

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
		this.setSize    ( 600,400 );
		this.setLocation(  1000, 500 );
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
