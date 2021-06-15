package edu.ucacue.factura.controlador;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import edu.ucacue.factura.infraestructura.repositorio.ProductoRepository;
import edu.ucacue.factura.modelo.Producto;
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
@Controller
public class ProductoGui extends JInternalFrame {
	
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtPrecio;
	private JTextField txtStock;
	
	JButton btnEliminar;
	JButton btnActualizar;
	Producto productoActualizar;
	
	boolean banderaActualizar = false;
	
	ProductoItemModel productoModel;

	@Autowired
	ProductoRepository productoRepositorio;

	private JTable tablePersona;
	//private JLabel lblNewLabel_2;
	private JTextField txtBuscarCodigo;
	private JLabel lblNewLabel_4;
	private JScrollPane scrollPane;

	public ProductoGui() {

		this.setMaximizable(true); // maximize
		this.setIconifiable(true); // set minimize
		this.setClosable(true); // set closed
		this.setResizable(true); // set resizable
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 421);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel_3 = new JLabel("Nombre");
		lblNewLabel_3.setBounds(130, 11, 74, 24);
		contentPane.add(lblNewLabel_3);
		txtNombre = new JTextField();
		txtNombre.setBounds(234, 11, 122, 24);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setBounds(130, 46, 81, 24);
		contentPane.add(lblNewLabel_1);
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(234, 46, 122, 24);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		JLabel lblNewLabel = new JLabel("Precio");
		lblNewLabel.setBounds(130, 86, 96, 14);
		contentPane.add(lblNewLabel);
		txtPrecio = new JTextField();
		txtPrecio.setBounds(234, 81, 122, 24);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		JLabel lblNewLabel_2 = new JLabel("Stock");
		lblNewLabel_2.setBounds(130, 117, 46, 14);
		contentPane.add(lblNewLabel_2);
		txtStock = new JTextField();
		txtStock.setBounds(234, 124, 122, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(407, 65, 121, 35);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto p;
				if (banderaActualizar == true) {
					p = productoActualizar;
					p.setDescripcion(txtDescripcion.getText());
					p.setNombre(txtNombre.getText());
					p.setPrecio(txtPrecio.getText());
					p.setStock(txtStock.getText());
					banderaActualizar = false;
				} else {
					p = new Producto(txtNombre.getText(), txtDescripcion.getText(), txtPrecio.getText(), txtStock.getText());
				}
				productoRepositorio.save(p);
				limpiarInterfaz();
				generarTabla(productoRepositorio.findAll());
			}
		});
		contentPane.add(btnGuardar);
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto pEliminar = productoModel.getPersonaAt(tablePersona.getSelectedRow());
				productoRepositorio.delete(pEliminar);
				generarTabla(productoRepositorio.findAll());

			}
		});
		btnEliminar.setBounds(193, 352, 89, 23);
		contentPane.add(btnEliminar);
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productoActualizar = productoModel.getPersonaAt(tablePersona.getSelectedRow());
				txtNombre.setText(productoActualizar.getNombre());
				txtDescripcion.setText(productoActualizar.getDescripcion());
				txtPrecio.setText(productoActualizar.getPrecio());
				txtStock.setText(productoActualizar.getStock());
				banderaActualizar = true;
			}
		});
		btnActualizar.setEnabled(false);
		btnActualizar.setBounds(324, 352, 112, 23);
		contentPane.add(btnActualizar);
		
		

		txtBuscarCodigo = new JTextField();
		txtBuscarCodigo.setBounds(177, 186, 259, 20);
		contentPane.add(txtBuscarCodigo);
		txtBuscarCodigo.setColumns(10);

		lblNewLabel_4 = new JLabel("Buscar por Nombre");
		lblNewLabel_4.setBounds(58, 189, 109, 14);
		contentPane.add(lblNewLabel_4);

		JButton btnBuscar = new JButton("Buscar..");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre= txtBuscarCodigo.getText();
				List<Producto> personasFiltro= productoRepositorio.buscarPorNombreLike(nombre);
				generarTabla(personasFiltro);
			}
		});
		btnBuscar.setBounds(439, 182, 89, 23);
		contentPane.add(btnBuscar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 214, 545, 125);
		contentPane.add(scrollPane);
		tablePersona = new JTable();
		scrollPane.setViewportView(tablePersona);
		tablePersona.setCellSelectionEnabled(true);
		tablePersona.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Producto", "Codigo", "Precio", "Cantidad"
			}
		));
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
		txtDescripcion.setText("");
		txtPrecio.setText("");
		txtStock.setText("");
	}
	
	public void generarTabla(List<Producto> productos) {
		productoModel = new ProductoItemModel(productos);

		tablePersona.setModel(productoModel);
		
		btnEliminar.setEnabled(false);
		btnActualizar.setEnabled(false);
		productoRepositorio.findAll();
	}
}
	