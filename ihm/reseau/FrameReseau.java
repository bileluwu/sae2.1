package ihm.reseau;

import controleur.Controleur;

import javax.swing.*;
import java.awt.event.*;

import java.awt.BorderLayout;

public class FrameReseau extends JFrame implements ActionListener
{
	private Controleur ctrl;
	private JMenuItem menuFichierSauvergarder;

	public FrameReseau(Controleur ctrl)
	{
		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;
		this.setTitle   ("Reseau Routier");
		this.setSize    (400,300 );
		this.setLocation( 50, 50 );

		JMenuBar menubMaBarre = new JMenuBar();

		JMenu menuFichier = new JMenu("Fichier");

		this.menuFichierSauvergarder = new JMenuItem("Sauvegarder");

		menuFichier.add(this.menuFichierSauvergarder);

		menubMaBarre.add(menuFichier);

		this.setJMenuBar(menubMaBarre);

		this.menuFichierSauvergarder.addActionListener(this);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//this.add(new PanelReseau());
		//this.pack();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.menuFichierSauvergarder)
		{
			this.ctrl.sauvegarder();
		}
	}
}

