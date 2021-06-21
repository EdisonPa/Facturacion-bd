package edu.ucacue.factura.controlador.persona;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.factura.infraestructura.repositorio.PersonaRepository;
import edu.ucacue.factura.modelo.Persona;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.persistence.PostLoad;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
@Controller
public class PersonaUI extends JInternalFrame {
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	
	JButton btnEliminar;
	JButton btnActualizar;
	Persona personaActualizar;
	
	boolean banderaActualizar = false;
	
	PersonaItemModel personaModel;

	@Autowired
	PersonaRepository personaRepositorio;

	private JTable tablePersona;
	private JLabel lblNewLabel_2;
	private JTextField txtBuscarApellido;
	private JLabel lblNewLabel_4;

	public PersonaUI() {

		this.setMaximizable(true); // maximize
		this.setIconifiable(true); // set minimize
		this.setClosable(true); // set closed
		this.setResizable(true); // set resizable
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 421);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel_3 = new JLabel("Nombre:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 87, 74, 24);
		contentPane.add(lblNewLabel_3);
		txtNombre = new JTextField();
		txtNombre.setBounds(70, 88, 122, 24);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		JLabel lblNewLabel_1 = new JLabel("Apellido:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 122, 81, 24);
		contentPane.add(lblNewLabel_1);
		txtApellido = new JTextField();
		txtApellido.setBounds(70, 123, 122, 24);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		JLabel lblNewLabel = new JLabel("Cédula:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(234, 92, 96, 14);
		contentPane.add(lblNewLabel);
		txtCedula = new JTextField();
		txtCedula.setBounds(307, 88, 122, 24);
		contentPane.add(txtCedula);
		txtCedula.setColumns(10);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(SystemColor.textHighlight);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardar.setBounds(485, 102, 121, 35);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persona p;
				if (banderaActualizar == true) {
					p = personaActualizar;
					p.setApellido(txtApellido.getText());
					p.setNombre(txtNombre.getText());
					p.setTelefono(txtTelefono.getText());
					p.setCedula(txtCedula.getText());
					banderaActualizar = false;
				} else {
					p = new Persona(txtNombre.getText(), txtApellido.getText(), txtTelefono.getText(),txtCedula.getText());
				}
				personaRepositorio.save(p);
				limpiarInterfaz();
				generarTabla(personaRepositorio.findAll());
			}
		});
		contentPane.add(btnGuardar);
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(SystemColor.textHighlight);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persona pEliminar = personaModel.getPersonaAt(tablePersona.getSelectedRow());
				personaRepositorio.delete(pEliminar);
				generarTabla(personaRepositorio.findAll());

			}
		});
		btnEliminar.setBounds(193, 352, 89, 23);
		contentPane.add(btnEliminar);
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setBackground(SystemColor.textHighlight);
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personaActualizar = personaModel.getPersonaAt(tablePersona.getSelectedRow());
				txtCedula.setText(personaActualizar.getCedula());
				txtNombre.setText(personaActualizar.getNombre());
				txtApellido.setText(personaActualizar.getApellido());
				txtTelefono.setText(personaActualizar.getTelefono());
				banderaActualizar = true;
			}
		});
		btnActualizar.setEnabled(false);
		btnActualizar.setBounds(324, 352, 112, 23);
		contentPane.add(btnActualizar);
		
		lblNewLabel_2 = new JLabel("Teléfono:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(235, 127, 62, 14);
		contentPane.add(lblNewLabel_2);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(307, 125, 122, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);

		txtBuscarApellido = new JTextField();
		txtBuscarApellido.setBounds(168, 183, 259, 20);
		contentPane.add(txtBuscarApellido);
		txtBuscarApellido.setColumns(10);

		lblNewLabel_4 = new JLabel("Buscar por apellido:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(26, 186, 132, 14);
		contentPane.add(lblNewLabel_4);

		JButton btnBuscar = new JButton("Buscar..");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setBackground(SystemColor.textHighlight);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String apellido= txtBuscarApellido.getText();
				List<Persona> personasFiltro= personaRepositorio.buscarPorApellidoLike(apellido);
				generarTabla(personasFiltro);
			}
		});
		btnBuscar.setBounds(437, 182, 89, 23);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 214, 507, 128);
		contentPane.add(scrollPane);
		tablePersona = new JTable();
		tablePersona.setBackground(Color.WHITE);
		scrollPane.setViewportView(tablePersona);
		tablePersona.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Apellido", "Cedula", "Telefono"
			}
		));
		
		JLabel lblIngresarDatos = new JLabel("Ingresar Datos");
		lblIngresarDatos.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblIngresarDatos.setBounds(146, 11, 283, 39);
		contentPane.add(lblIngresarDatos);
		tablePersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnEliminar.setEnabled(true);
				btnActualizar.setEnabled(true);
				//System.out.println(tablePersona.getSelectedRow());
				//System.out.println(personaModel.getPersonaAt(tablePersona.getSelectedRow()));
			}
		});

	}

	public void limpiarInterfaz() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtCedula.setText("");
		txtTelefono.setText("");
	}
	
	public void generarTabla(List<Persona> personas) {
		personaModel = new PersonaItemModel(personas);

		tablePersona.setModel(personaModel);
		
		btnEliminar.setEnabled(false);
		btnActualizar.setEnabled(false);
		// personaRepositorio.findAll();
	}
}