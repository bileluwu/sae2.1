package ihm.reseau;

import controleur.Controleur;

import javax.swing.*;
import java.awt.event.*;
import java. awt. Toolkit;
import java.awt.Dimension;

import java.awt.BorderLayout;

public class FrameReseau extends JFrame implements ActionListener
{
	private Controleur ctrl;
	private JMenuItem menuFichierSauvergarder;
	private PanelReseau panelReseau;

	public FrameReseau(Controleur ctrl)
	{
		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;
		this.setTitle   ("Reseau Routier");

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		this.setSize    ( width-100, (int)(height*0.66-50));
		this.setLocation( 50, 50 );

		JMenuBar menubMaBarre = new JMenuBar();

		JMenu menuFichier = new JMenu("Fichier");

		this.panelReseau = new PanelReseau(this.ctrl);

		this.menuFichierSauvergarder = new JMenuItem("Sauvegarder");

		menuFichier.add(this.menuFichierSauvergarder);

		menubMaBarre.add(menuFichier);

		this.setJMenuBar(menubMaBarre);

		this.menuFichierSauvergarder.addActionListener(this);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.add(this.panelReseau, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.menuFichierSauvergarder)
		{
			this.ctrl.sauvegarder();
		}
	}
}

