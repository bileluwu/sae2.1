package metier;

public class Ville
{
	private static int         nbVille=0;
	protected        int         numVille;

	private        String      nom;
	private        int         x, y;
	
	//private        List<Route> lstRoute;

	Ville(String nom, int x, int y)
	{
		this.numVille = ++Ville.nbVille;
		this.nom      = nom;
		this.x        = x;
		this.y        = y;
		//this.lstRoute = new ArrayList<Route>();
	}

	protected void   setX(int x) { this.x = x     ;}
	protected void   setY(int y) { this.y = y     ;}

	public int    getX     () { return this.x  ;}
	public int    getY     () { return this.y  ;}
	public String getNom   () { return this.nom;}
	public int    getNumero() { return this.numVille;}
}