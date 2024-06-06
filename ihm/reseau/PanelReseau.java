package ihm.reseau;

import controleur.Controleur;

import javax.swing.*;
import java.awt.event.*;
import java. awt. Toolkit;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

import metier.Route;
import metier.Ville;

public class PanelReseau extends JPanel
{

    private static final int TAILLE_VILLE = 15;
    private Controleur ctrl;

    private Graphics2D g2;


    public PanelReseau(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(null);

        GereSouris gereSouris = new GereSouris();

	    this.addMouseListener      ( gereSouris );
	    this.addMouseMotionListener( gereSouris );
    }

    public void paintComponent(Graphics g)
    {

        g2 = (Graphics2D) g;

        super.paintComponent(g);


        for (Route r : ctrl.getRoutes())
        {

            double distance = Math.sqrt(Math.pow(r.getVilleArr().getX() - r.getVilleDep().getX(), 2) + Math.pow(r.getVilleArr().getY() - r.getVilleDep().getY(), 2));

            double tailleSegment = distance / (r.getNbTroncons() * 2 - 1);

            float[] pattern = {(float) tailleSegment, (float) tailleSegment};

            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, pattern, 0.0f));

            g2.drawLine(r.getVilleDep().getX(), r.getVilleDep().getY(),
                         r.getVilleArr().getX(), r.getVilleArr().getY());
        }

        for (Ville v : ctrl.getVilles())
        {
            g2.setColor(java.awt.Color.RED);
            g2.fillOval( v.getX()-TAILLE_VILLE/2, v.getY()-TAILLE_VILLE/2, TAILLE_VILLE, TAILLE_VILLE);
            g2.setColor(java.awt.Color.BLACK);
            g2.drawString(v.getNom(), v.getX()-TAILLE_VILLE/2, v.getY()-TAILLE_VILLE/2);
        }


    }

    private class GereSouris extends MouseAdapter
	{
		Ville villeActive=null;
		int     posX, posY;

		public void mousePressed (MouseEvent e)
		{
			this.villeActive = PanelReseau.this.ctrl.villeCliquer ( e.getX(), e.getY() );
			this.posX = e.getX();
			this.posY = e.getY();
		}

		public void mouseDragged (MouseEvent e)
		{
			if ( this.villeActive != null )
			{
				PanelReseau.this.ctrl.deplacerVille ( this.villeActive, e.getX()-this.posX, e.getY()-this.posY );

				// Ne pas oublier de mettre à jour les futures anciennes coordonnées de la souris
				this.posX = e.getX();
				this.posY = e.getY();
				
				PanelReseau.this.repaint();
			}
		}
	}
}
