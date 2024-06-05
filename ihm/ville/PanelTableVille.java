package ihm.ville;

import controleur.Controleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class PanelTableVille extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JTable     tblGrilleDonnees;
	private JButton    btnModifier;

	public PanelTableVille (Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout ( new BorderLayout() );

		JScrollPane spGrilleDonnees;
		
		// CrÃ©ation des composants
		this.tblGrilleDonnees = new JTable ( new GrilleDonneesModel(ctrl) );
		this.tblGrilleDonnees.setFillsViewportHeight(false);
		this.btnModifier = new JButton("Modifier");

		spGrilleDonnees   = new JScrollPane( this.tblGrilleDonnees );

		// positionnement des composants
		this.add ( spGrilleDonnees, BorderLayout.CENTER );
		this.add(btnModifier, BorderLayout.SOUTH);

		this.btnModifier.addActionListener(this);
	}

	public void rafraichir()
	{
		this.tblGrilleDonnees.setModel ( new GrilleDonneesModel(ctrl) );
	}

	public int getLigneSelectionnee()
	{
		return this.tblGrilleDonnees.getSelectedRow();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnModifier)
			this.ctrl.modifier();
	}

}