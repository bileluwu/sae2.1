package ihm.route;

import controleur.Controleur;
import metier.Route;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class GrilleDonneesModel extends AbstractTableModel
{
	private Controleur ctrl;

	private String[]   tabEntetes;
	private Object[][] tabDonnees;

	public GrilleDonneesModel (Controleur ctrl)
	{
		this.ctrl = ctrl;

		Route route;
		List<Route> lstRoutes = this.ctrl.getRoutes();

		tabDonnees = new Object[lstRoutes.size()][3];

		for ( int lig=0; lig<lstRoutes.size(); lig++)
		{
			route = lstRoutes.get(lig);

			tabDonnees[lig][0] = route.getVilleDep  ().getNom();
			tabDonnees[lig][1] = route.getVilleArr  ().getNom();
			tabDonnees[lig][2] = route.getNbTroncons();
		}

		this.tabEntetes = new String[]   {   "Ville Dep"  , "Ville Arr"     , "nbTroncons"  };

	}

	public int    getColumnCount()                 { return this.tabEntetes.length;      }
	public int    getRowCount   ()                 { return this.tabDonnees.length;      }
	public String getColumnName (int col)          { return this.tabEntetes[col];        }
	public Object getValueAt    (int row, int col) { return this.tabDonnees[row][col];   }
	public Class  getColumnClass(int c)            { return getValueAt(0, c).getClass(); }


}