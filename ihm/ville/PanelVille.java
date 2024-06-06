package ihm.ville;

import metier.Factory;

import javax.swing.*;

//import ihm.route.*;
import metier.Ville;
import controleur.Controleur;

import java.awt.event.*;
import java.awt.GridLayout;

public class PanelVille extends JPanel implements ActionListener
{
	private JTextField txtNom, txtX, txtY;
	private JButton    btnAjouter;
	private Controleur ctrl;

	public PanelVille(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(4,2));
		/* ----------------------- */
		/* Création des composants */
		/* ----------------------- */

		this.btnAjouter = new JButton   ("Ajouter");
		this.txtNom     = new JTextField(10);
		this.txtX       = new JTextField(10);
		this.txtY       = new JTextField(10);

		/* ---------------------------- */
		/* Postionnement des composants */
		/* ---------------------------- */
		this.add(new JLabel("Nom :", SwingConstants.RIGHT) );
		this.add(this.txtNom        );

		this.add(new JLabel(" X :", SwingConstants.RIGHT) );
		this.add(this.txtX          );

		this.add(new JLabel(" Y :", SwingConstants.RIGHT) );
		this.add(this.txtY          );

		this.add(new JLabel());
		this.add(this.btnAjouter    );

		/* ------------------------- */
		/* Activation des composants */
		/* ------------------------- */

		this.btnAjouter.addActionListener(this);
	}
	

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnAjouter)
		{
			try
			{
				Ville nouvelleVille = Factory.CreerVille(
					this.txtNom.getText(), Integer.parseInt(this.txtX.getText()), Integer.parseInt(this.txtY.getText()), this.ctrl.getVilles(), this.ctrl.getReseauSize());
				
				if (nouvelleVille == null) {
					JOptionPane.showMessageDialog(this, "Un problème s'est produit lors de la création de la ville\n" +
														"Rappel: Deux villes ne peuvent pas voir le meme nom\n" +
													    "Les coordonnées de la ville doivent etre dans ces valeurs :\n" +
														(int)(this.ctrl.getReseauSize().getWidth ()- 40) + " pour le x et\n" +
														(int)(this.ctrl.getReseauSize().getHeight()- 40) + " pour le y", 
														"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}

				this.ctrl.ajouter(nouvelleVille);
				this.txtNom.setText("");
				this.txtX.setText("");
				this.txtY.setText("");
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs numériques valides pour les coordonnées.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
