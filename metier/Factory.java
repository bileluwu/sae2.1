package metier;

import java.awt.Dimension;
import java.util.List;

public abstract class Factory 
{
	public static Route CreerRoute(int nbTroncons,Ville depart, Ville arrivee, List<Route> lstRoutes)
	{
		if (nbTroncons < 0 || nbTroncons > 10) return null;
		if (depart == null || arrivee == null) return null;
		if (depart == arrivee)                 return null;
		for (Route route : lstRoutes)
		{
			if (route.getVilleDep() == depart && route.getVilleArr() == arrivee) return null;
			if (route.getVilleDep() == arrivee && route.getVilleArr() == depart) return null;
		}

		return new Route(nbTroncons, depart, arrivee);
	}

	public static Ville CreerVille(String nom, int x, int y, List<Ville> lstVilles, Dimension dim)
	{
		if (x < 0 || x > dim.getWidth ()-17) return null;
		if (y < 0 || y > dim.getHeight()-63) return null;
		for (Ville ville : lstVilles)
		{
			if (ville.getNom().equals(nom)            ) return null;
			if (ville.getX() == x && ville.getY() == y) return null;
		}
		return new Ville(nom, x, y);
	}

	public static void setX(Ville ville, int x, Dimension dim)
	{
		if (x < 0 || x > dim.getWidth ()-17) return;
		ville.setX(x);
	}

	public static void setY(Ville ville, int y, Dimension dim)
	{
		if (y < 0 || y > dim.getHeight()-63) return;
		ville.setY(y);
	}

}
