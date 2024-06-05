package metier;

import java.util.List;

public abstract class Factory 
{
	public static Route CreerRoute(int nbTroncons,Ville depart, Ville arrivee, List<Route> lstRoutes)
	{
		if (nbTroncons < 0 || nbTroncons > 10) return null;
		if (depart == null || arrivee == null) return null;
		if (depart == arrivee)                 return null;
		for (Route route : lstRoutes)
			if (route.getVilleDep() == depart && route.getVilleArr() == arrivee) return null;

		return new Route(nbTroncons, depart, arrivee);
	}

	public static Ville CreerVille(String nom, int x, int y, List<Ville> lstVilles)
	{
		if (x < 0 || x > 1000) return null;
		if (y < 0 || y > 800 ) return null;
		for (Ville ville : lstVilles)
		{
			if (ville.getNom().equals(nom)            ) return null;
			if (ville.getX() == x && ville.getY() == y) return null;
		}
		return new Ville(nom, x, y);
	}
}
