package ihm.route;

import controleur.Controleur;

import java.awt.BorderLayout;

import javax.swing.*;



public class PanelTableRoute extends JPanel
{
	private Controleur ctrl;
	private JTable     tblGrilleDonnees;

	public PanelTableRoute (Controleur ctrl)
	{
		this.ctrl = ctrl;

		JScrollPane spGrilleDonnees;

		this.setLayout( new BorderLayout());
		
		// Creation des composants
		this.tblGrilleDonnees = new JTable ( new GrilleDonneesModel(ctrl) );
		this.tblGrilleDonnees.setFillsViewportHeight(false);

		spGrilleDonnees   = new JScrollPane( this.tblGrilleDonnees );

		// positionnement des composants
		this.add ( spGrilleDonnees, BorderLayout.CENTER);
		this.add ( new JLabel("  "), BorderLayout.SOUTH);
	}

	public void rafraichir()
	{
		this.tblGrilleDonnees.setModel ( new GrilleDonneesModel(ctrl) );
	}

	public int getLigneSelectionnee()
	{
		return this.tblGrilleDonnees.getSelectedRow();
	}

}