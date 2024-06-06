package ihm.reseau;

import javax.swing.*;

import controleur.Controleur;
import metier.*;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class PanelReseau extends JPanel
{
	private static int TAILLE = 10;
	private Graphics2D g2;
	private Controleur ctrl;

	public PanelReseau(Controleur ctrl)
	{
		this.ctrl = ctrl;
	}

	public void paintComponent(Graphics g)
	{
		Ville ville;
		Route route;

		super.paintComponent(g);

		g2 = (Graphics2D) g;
		
		// Dessiner l'ensemble des villeures
		for(int i=0; i<this.ctrl.getNbVille();i++)
		{
			ville = this.ctrl.getVille(i);

			g2.drawRect  (ville.getX(), ville.getY(), PanelReseau.TAILLE, PanelReseau.TAILLE);
			g2.drawString(""+this.ctrl.getVille(i).getNom(), ville.getX(), ville.getY()+25);
		}

		for(int i=0; i<this.ctrl.getNbRoute();i++)
		{
			route = this.ctrl.getRoute(i);
			for(int j=0; j<route.getNbTroncons(); j++)
				g2.drawLine(route.getVilleDep().getX(), route.getVilleDep().getY()/route.getNbTroncons(), route.getVilleArr().getX(), route.getVilleArr().getY()/route.getNbTroncons());
		}
	}

}