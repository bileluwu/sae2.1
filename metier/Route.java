package metier;

public class Route 
{
	private int troncons;
	private Ville depart, arrivee;

	Route(int tr, Ville d, Ville a)
	{
		this.troncons = tr;
		this.depart   = d;
		this.arrivee  = a;
	}

	public Ville getVilleDep  () { return this.depart  ;}
	public Ville getVilleArr  () { return this.arrivee ;}
	public int   getNbTroncons() { return this.troncons;}
}
