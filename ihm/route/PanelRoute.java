package ihm.route;

import metier.Factory;
import javax.swing.*;

import java.awt.GridLayout;

import java.awt.event.*;
import metier.*;
import controleur.Controleur;

public class PanelRoute extends JPanel implements ActionListener
{
	private JTextField txtTroncons;
	private JButton    btnAjouter;
	private JComboBox<String>  comboDepart, comboArrivee;
	private Controleur ctrl;

	public PanelRoute(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(4,2));	
		/* ----------------------- */
		/* Création des composants */
		/* ----------------------- */

		this.btnAjouter  = new JButton   ("Ajouter");
		this.txtTroncons = new JTextField(10);

		this.comboDepart  = new JComboBox<String>();
		this.comboArrivee = new JComboBox<String>();

		this.comboDepart .setSize(10,20);
		this.comboArrivee.setSize(10,20);

		/* ---------------------------- */
		/* Postionnement des composants */
		/* ---------------------------- */

		this.add(new JLabel("Ville Dep:", SwingConstants.RIGHT));
		this.add(this.comboDepart);

		this.add(new JLabel("Ville Arr:", SwingConstants.RIGHT) );
		this.add(this.comboArrivee );

		this.add(new JLabel("Troncons :", SwingConstants.RIGHT));
		this.add(this.txtTroncons        );

		this.add(new JLabel());
		this.add(this.btnAjouter         );

		/* ------------------------- */
		/* Activation des composants */
		/* ------------------------- */

		this.btnAjouter.addActionListener(this);
	}

	public void ajouterCombo(Ville v)
	{
		this.comboDepart.addItem(v.getNom());
		this.comboArrivee.addItem(v.getNom());
	}

	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if(e.getSource() == this.btnAjouter)
			{
				Route nouvelleRoute = Factory.CreerRoute(Integer.parseInt(this.txtTroncons.getText()),this.ctrl.villeParNom((String)this.comboDepart.getSelectedItem()),this.ctrl.villeParNom((String)this.comboArrivee.getSelectedItem()), this.ctrl.getRoutes());
				if (nouvelleRoute != null)
				{
					this.ctrl.ajouter(nouvelleRoute);
					this.txtTroncons.setText("");
				}
			}
		}
		catch(NumberFormatException ex)
		{
				JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs numériques valides pour les coordonnées.", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
