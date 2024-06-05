package ihm.ville;

import controleur.Controleur;

import javax.swing.*;

import java.awt.BorderLayout;

public class FrameVille extends JFrame
{
	private PanelVille      panelVille;
	private PanelTableVille panelTableVille;

	private Controleur ctrl;

	public FrameVille(Controleur ctrl)
	{
		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;
		this.setTitle   ( "Ville" );
		this.setSize    ( 600,400 );
		this.setLocation(  50, 500 );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.panelVille = new PanelVille(this.ctrl);
		this.panelTableVille = new PanelTableVille(ctrl);

		JPanel panelTest = new JPanel();
		panelTest.setLayout(new BorderLayout());
		panelTest.add(panelVille, BorderLayout.NORTH);
		this.panelTableVille = new PanelTableVille(this.ctrl);

		this.add( panelTableVille, BorderLayout.CENTER);
		this.add ( panelTest, BorderLayout.EAST );

		this.setVisible(true);
	}

	public void rafraichir() { this.panelTableVille.rafraichir(); }
}
