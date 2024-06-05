package ihm.ville;

import controleur.Controleur;
import metier.Ville;

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

		Ville ville;
		List<Ville> lstVilles = ctrl.getVilles();

		tabDonnees = new Object[lstVilles.size()][4];

		for ( int lig=0; lig<lstVilles.size(); lig++)
		{
			ville = lstVilles.get(lig);

			tabDonnees[lig][0] = ville.getNumero();
			tabDonnees[lig][1] = ville.getNom   ();
			tabDonnees[lig][2] = ville.getX     ();
			tabDonnees[lig][3] = ville.getY     ();
		}

		this.tabEntetes = new String[]   {   "Numero"  , "Nom"     , "X", "Y"  };

	}

	public int    getColumnCount()                 { return this.tabEntetes.length;      }
	public int    getRowCount   ()                 { return this.tabDonnees.length;      }
	public String getColumnName (int col)          { return this.tabEntetes[col];        }
	public Object getValueAt    (int row, int col) { return this.tabDonnees[row][col];   }
	public Class  getColumnClass(int c)            { return getValueAt(0, c).getClass(); }

	public boolean isCellEditable(int row, int col)
	{
		return col == 2 || col == 3;
	}

	public void setValueAt(Object value, int row, int col)
	{

		boolean bRet = false;

		if ( col == 2 )
		{
			bRet = this.ctrl.majX ( row, (int)value );
			bRet = bRet && (int)value >= 0 && (int)value <= 1000;
			if ( bRet )
			{
				this.tabDonnees[row][col] = value;
				this.fireTableCellUpdated(row, col);
			}
		}

		if ( col == 3 )
		{
			bRet = this.ctrl.majY ( row, (int)value );
			bRet = bRet && (int)value >= 0 && (int)value <= 800;
			if ( bRet )
			{
				this.tabDonnees[row][col] = value;
				this.fireTableCellUpdated(row, col);
			}
		}

	}


}