package controleur;

import ihm.route.*;
import ihm.ville.*;
import ihm.reseau.*;
import metier.*;

import java.util.Scanner;
import java.io.FileInputStream;
import iut.algo.Decomposeur;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import java.util.List;
import java.util.ArrayList;

public class Controleur 
{
	private FrameVille  frameVille ;
	private FrameRoute  frameRoute ;
	private FrameReseau frameReseau;

	private List<Ville> lstVilles = new ArrayList<Ville>();
	private List<Route> lstRoutes = new ArrayList<Route>();
	private List<Route> lstRoutesAvantSauvegarde = new ArrayList<Route>();
	
	public Controleur()
	{
		this.frameVille = new FrameVille(this);
		this.frameRoute = new FrameRoute(this);
		this.frameReseau = new FrameReseau(this);
		this.initTab();
		this.frameRoute.rafraichir();
		this.frameVille.rafraichir();
	}

	public List<Ville> getVilles       () { return new ArrayList<Ville>(lstVilles); }
	public List<Route> getRoutes       () { return new ArrayList<Route>(lstRoutes); }
	public Ville       getVille (int ind) { return lstVilles.get(ind)             ; }
	public Route       getRoute (int ind) { return lstRoutes.get(ind)             ; }

	public int getNbVille() { return lstVilles.size();}
	public int getNbRoute() { return lstRoutes.size();}

	private void initTab()
	{
		Scanner     scFic;
		Decomposeur dec;

		char   type;

		String nom;
		int    x, y;

		int nbTroncons ;
		int numVilleDep;
		int numVilleArr;

		Ville villeDep = null;
		Ville villeArr = null;

		try
		{
			scFic = new Scanner ( new FileInputStream ( "../data/donnees.data" ), "UTF8" );

			while ( scFic.hasNextLine() )
			{

				dec = new Decomposeur ( scFic.nextLine() );

				type = dec.getChar(0);

				switch (type) {
					case 'V':
						nom = dec.getString(1);
						x   = dec.getInt(2);
						y   = dec.getInt(3);
						Ville nouvelleVille = Factory.CreerVille( nom, x, y , this.getVilles());
						if (nouvelleVille != null)
						{
							this.lstVilles.add (nouvelleVille);
							this.ajouterCombo(nouvelleVille);
						}
						break;
				
					case 'R':
						nbTroncons  = dec.getInt(1);
						numVilleDep = dec.getInt(2);
						numVilleArr = dec.getInt(3);

						for (Ville v : lstVilles)
						{
							if (v.getNumero() == numVilleDep)
								villeDep = v;
							
							if (v.getNumero() == numVilleArr)
								villeArr = v;
						}

						Route nouvelleRoute = Factory.CreerRoute(nbTroncons, villeDep, villeArr,this.lstRoutes);
						if (nouvelleRoute != null)
						{
							this.lstRoutes.add(nouvelleRoute) ;
						}
						break;
				}

			}

			scFic.close();
		}
		catch (Exception e){ e.printStackTrace(); }
		this.lstRoutesAvantSauvegarde = new ArrayList<Route>(lstRoutes);
	}

	public void ajouterCombo(Ville v) { frameRoute.ajouterCombo(v); }

	public void sauvegarder()
	{
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("../data/donnees.data") );

			for (Ville ville : lstVilles) 
				pw.println("V\t" + ville.getNom() + "\t" + ville.getX() + "\t" +ville.getY());

			for (Route route : lstRoutes)
				pw.println("R\t" + route.getNbTroncons() + "\t" + route.getVilleDep().getNumero() + "\t" + route.getVilleArr().getNumero());
			
			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

	public void modifier()
	{
		try
		{
			PrintWriter pw = new PrintWriter(new FileOutputStream("../data/donnees.data"));

			for (Ville ville : lstVilles)
				pw.println("V\t" + ville.getNom() + "\t" + ville.getX() + "\t" + ville.getY());
			for (Route route : lstRoutesAvantSauvegarde)
				pw.println("R\t" + route.getNbTroncons() + "\t" + route.getVilleDep().getNumero() + "\t"
						+ route.getVilleArr().getNumero());
			pw.close();
		} catch (Exception e){ e.printStackTrace();}
	}

	public void ajouter(Ville v)
	{
		this.lstVilles.add(v);
		this.ajouterCombo(v);
		this.frameVille.rafraichir();
	}

	public void ajouter(Route r)
	{
		this.lstRoutes.add(r);
		this.frameRoute.rafraichir();
	}


	public Ville villeParNumero(int num)
	{
		return this.lstVilles.get(num-1);
	}

	public Ville villeParNom(String nom)
	{
		for (Ville  ville: lstVilles) 
			if (ville.getNom().equals(nom)) return ville;
		return null;
	}

	public Ville villeParCoord(int x, int y)
	{
		for (Ville ville : lstVilles) 
			if (ville.getX() == x && ville.getY() == y) return ville;
		return null;
	}

	public boolean majX( int ligne, int val )
	{
		if(villeParCoord(val, this.lstVilles.get(ligne).getY( )) == null) 
		{
			this.lstVilles.get(ligne).setX(val);
			return true;
		}

		return false;
	}

	public boolean majY( int ligne, int val)
	{
		if(villeParCoord(this.lstVilles.get(ligne).getX(), val ) == null) 
		{
			this.lstVilles.get(ligne).setY(val);
			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		new Controleur();
	}
}
