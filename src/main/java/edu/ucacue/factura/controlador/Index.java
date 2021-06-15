package edu.ucacue.factura.controlador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Controller
public class Index extends JFrame {

	//private JPanel contentPane;
	
	JDesktopPane desktopPanel;
	
	@Autowired
	ProductoGui pUI;
	
	@Autowired
	PrincipalGUI pGUI;
	
	/*@Autowired
	ProductoModificarUI pModificarUI;*/
	
	
	/**
	 * Create the frame.
	 */
	public Index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		desktopPanel= new JDesktopPane();
		getContentPane().add(desktopPanel);
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem miPNuevo = new JMenuItem("Producto");
		miPNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pUI.setVisible(true);
				desktopPanel.add(pUI);

			}
		});
		mnNewMenu.add(miPNuevo);
		
		JMenuItem miPeNuevo = new JMenuItem("Persona");
		miPeNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pGUI.setVisible(true);
				desktopPanel.add(pGUI);

			}
		});
		mnNewMenu.add(miPeNuevo);
		
		
		
		/*JMenuItem miPModificar = new JMenuItem("Modificar");
		miPModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pModificarUI.setVisible(true);
				desktopPanel.add(pModificarUI);
			}
		});
		mnNewMenu.add(miPModificar);*/
		
		/*JMenuItem miPEliminar = new JMenuItem("Eliminar");
		miPEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		/*mnNewMenu.add(miPEliminar);
		
		JMenuItem miPListar = new JMenuItem("Listar");
		mnNewMenu.add(miPListar);*/
		//contentPane = new JPanel();
		//setContentPane(contentPane);
		
	}

}