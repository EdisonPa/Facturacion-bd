package edu.ucacue.factura.controlador.factura;


import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import edu.ucacue.factura.infraestructura.repositorio.ProductoRepository;
import java.awt.Font;
import javax.swing.JTextField;
import edu.ucacue.factura.modelo.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.factura.infraestructura.repositorio.FacturaCabeceraRepository;
import edu.ucacue.factura.infraestructura.repositorio.PersonaRepository;
import edu.ucacue.factura.modelo.DetalleFactura;
import edu.ucacue.factura.modelo.FacturaCabecera;
import edu.ucacue.factura.modelo.Persona;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JSeparator;

@Controller
public class FacturaUI extends JInternalFrame {
	
	private JTextField txtNumFactuta;
	private JTextField txtCedula;
	private JLabel lblFecha;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblCedula ;
	private Persona p;


	@Autowired
	PersonaRepository personaRepositorio;
	
	@Autowired
	FacturaCabeceraRepository fCR;
	
	@Autowired
	ProductoRepository productoRepository;
	
	List<DetalleFactura> detallesFacturas; 

	FacturaCabecera fc;
	
	Producto producto;


	private JButton btnGuardar;
	private JTable tableDetalleFactura;
	DetalleFacturaItemModel modelo;
	private JTextField txtProducto;
	private JButton btnBuscarProducto;
	private JLabel lblNombreProducto;
	private JLabel lblNewLabel_4;
	private JTextField txtCantidad;
	private JButton btnAgregar;
	private JLabel lblNombreEncontrado;
	private JLabel lblApellidoEncontrado;
	private JLabel lblCedulaEncontrada;
	private JSeparator separator_2;
	

	public FacturaUI() {
		getContentPane().setBackground(SystemColor.activeCaption);
		setBackground(Color.LIGHT_GRAY);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		detallesFacturas=new ArrayList<>();
		producto=new Producto();

		modelo=new DetalleFacturaItemModel(detallesFacturas);
		fc=new FacturaCabecera();
		setBounds(100, 100, 657, 431);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sistema de Facturación Integral");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel.setBounds(32, -2, 326, 45);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Número Factura");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 11));
		lblNewLabel_1.setBounds(398, 54, 85, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtNumFactuta = new JTextField();
		txtNumFactuta.setBounds(493, 51, 86, 20);
		getContentPane().add(txtNumFactuta);
		txtNumFactuta.setColumns(10);
		
		lblFecha = new JLabel("");
		lblFecha.setForeground(SystemColor.inactiveCaptionText);
		lblFecha.setFont(new Font("Segoe UI", Font.BOLD, 10));
		lblFecha.setBounds(398, 11, 202, 14);
		getContentPane().add(lblFecha);
		lblFecha.setText(new Date().toString());
		
		txtCedula = new JTextField();
		txtCedula.setBounds(147, 52, 86, 20);
		getContentPane().add(txtCedula);
		txtCedula.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setBackground(SystemColor.textHighlight);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedula=txtCedula.getText();
				p = personaRepositorio.findByCedula(cedula); 
				
				lblCedula.setText(p.getCedula());
				lblNombre.setText(p.getNombre());
				lblApellido.setText(p.getApellido());
				
				
			}
		});
		btnBuscar.setBounds(243, 52, 85, 20);
		getContentPane().add(btnBuscar);
		
		JLabel lblNewLabel_2 = new JLabel("Ingrese La Cédula:");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 11));
		lblNewLabel_2.setBounds(32, 54, 105, 14);
		getContentPane().add(lblNewLabel_2);
		
		lblNombre = new JLabel("");
		lblNombre.setBounds(110, 79, 112, 14);
		getContentPane().add(lblNombre);
		
		lblApellido = new JLabel("");
		lblApellido.setBounds(110, 104, 100, 14);
		getContentPane().add(lblApellido);
		
		lblCedula = new JLabel("");
		lblCedula.setBounds(111, 129, 111, 14);
		getContentPane().add(lblCedula);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(SystemColor.textHighlight);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setPersona(p);
				fc.setNumeroFactura(Integer.parseInt(txtNumFactuta.getText()));
				fc.setFechaEmision(new Date());
				fc.setDetallesFacturas(detallesFacturas);
				fc.setDetallesFacturas(detallesFacturas);
				fCR.save(fc);
			}
		});
		btnGuardar.setBounds(293, 367, 89, 23);
		getContentPane().add(btnGuardar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 217, 495, 143);
		getContentPane().add(scrollPane);

		tableDetalleFactura = new JTable();
		scrollPane.setViewportView(tableDetalleFactura);
		tableDetalleFactura.setModel(modelo);

		JLabel lblNewLabel_3 = new JLabel("Producto:");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 11));
		lblNewLabel_3.setBounds(30, 186, 66, 20);
		getContentPane().add(lblNewLabel_3);

		txtProducto = new JTextField();
		txtProducto.setBounds(99, 186, 86, 20);
		getContentPane().add(txtProducto);
		txtProducto.setColumns(10);

		btnBuscarProducto = new JButton("Buscar");
		btnBuscarProducto.setForeground(Color.WHITE);
		btnBuscarProducto.setBackground(SystemColor.textHighlight);
		btnBuscarProducto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnBuscarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				producto=productoRepository.findById(Integer.parseInt(txtProducto.getText())).get();
				lblNombreProducto.setText(producto.getNombre());
			}
		});
		btnBuscarProducto.setBounds(208, 183, 89, 23);
		getContentPane().add(btnBuscarProducto);

		lblNombreProducto = new JLabel("");
		lblNombreProducto.setBounds(110, 161, 178, 14);
		getContentPane().add(lblNombreProducto);

		lblNewLabel_4 = new JLabel("Cantidad:");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 11));
		lblNewLabel_4.setBounds(336, 186, 64, 20);
		getContentPane().add(lblNewLabel_4);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(397, 186, 86, 20);
		getContentPane().add(txtCantidad);
		txtCantidad.setColumns(10);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setBackground(SystemColor.textHighlight);
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cantidad= Integer.parseInt(txtCantidad.getText());
				DetalleFactura dF = new DetalleFactura(cantidad, producto, fc);
				detallesFacturas.add(dF);
				generarTabla();
			}
		});
		btnAgregar.setBounds(490, 183, 89, 23);
		getContentPane().add(btnAgregar);
		
		lblNombreEncontrado = new JLabel("Nombre:");
		lblNombreEncontrado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombreEncontrado.setBounds(32, 79, 68, 14);
		getContentPane().add(lblNombreEncontrado);
		
		lblApellidoEncontrado = new JLabel("Apellido:");
		lblApellidoEncontrado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblApellidoEncontrado.setBounds(30, 104, 70, 14);
		getContentPane().add(lblApellidoEncontrado);
		
		lblCedulaEncontrada = new JLabel("Cédula:");
		lblCedulaEncontrada.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCedulaEncontrada.setBounds(32, 129, 69, 14);
		getContentPane().add(lblCedulaEncontrada);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(381, 0, 0, 43);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(381, 41, 192, 2);
		getContentPane().add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(570, -2, 3, 45);
		getContentPane().add(separator_2);

	}

	public void generarTabla() {

		modelo = new DetalleFacturaItemModel(detallesFacturas);

		tableDetalleFactura.setModel(modelo);

	}
}