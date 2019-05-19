package com.eafit.biblioteca.principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.eafit.biblioteca.bd.Conexion;
import com.eafit.biblioteca.dto.Libro;
import com.eafit.biblioteca.dto.LibroDAO;
import com.eafit.biblioteca.dto.LibroDAOMySQL;
import com.eafit.biblioteca.excepcion.LibroExistenteException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaLibro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ImageIcon imagen;

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNombre;
	private JTextField txtGenero;
	private JTextField txtDescripcion;
	private JTextField txtAutor;
	private JTable table_1;

	Connection conexion = null;
	PreparedStatement ps = null;
	Connection conn = null;

	LibroDAO libro = new LibroDAOMySQL();
	Libro variables = new Libro();
	List<Libro> libros = new ArrayList<>();

	private JTable table_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
	/**
	 * 7
	 * 
	 */

	public void mostrar() {
		try {
			libros = libro.obtenerTodosConEstado();
			libros.get(0).isPrestado();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Object mat[][] = new Object[libros.size()][6];
		for (int i = 0; i < libros.size(); i++) {
			mat[i][0] = libros.get(i).getId().toString();
			mat[i][1] = libros.get(i).getNombre();
			mat[i][2] = libros.get(i).getDescripcion();
			mat[i][3] = libros.get(i).getAutor();
			mat[i][4] = libros.get(i).getGenero();
			mat[i][5] = libros.get(i).isPrestado();

		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 428, 634, 197);
		contentPane.add(scrollPane);

		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {

		});
		scrollPane.setViewportView(table_1);

		table_2 = new JTable();
		scrollPane.setViewportView(table_1);

		table_1.setModel(new DefaultTableModel(mat,
				new String[] { "Id Libro", "Nombre", "Descripcion", "Autor", "Genero", "Prestado" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { true, true, true, true, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public VentanaLibro() {

		super("Centrar JFrame");

		setTitle("Biblioteca\r\n");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel PanelInfoLibro = new JPanel();
		PanelInfoLibro.setToolTipText("");
		PanelInfoLibro.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		PanelInfoLibro.setBounds(0, 129, 426, 277);
		PanelInfoLibro.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion Libro"));

		PanelInfoLibro.setBackground(new Color(255, 255, 255));
		contentPane.add(PanelInfoLibro);
		PanelInfoLibro.setLayout(null);

		JLabel lblId = new JLabel("Id");
		lblId.setBackground(new Color(0, 0, 0));
		lblId.setForeground(new Color(0, 0, 0));
		lblId.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		lblId.setBounds(10, 28, 13, 19);
		PanelInfoLibro.add(lblId);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBackground(new Color(0, 0, 0));
		lblNombre.setForeground(new Color(0, 0, 0));
		lblNombre.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		lblNombre.setBounds(10, 53, 60, 14);
		PanelInfoLibro.add(lblNombre);

		JLabel lblDescr = new JLabel("Descripcion:");
		lblDescr.setBackground(new Color(0, 0, 0));
		lblDescr.setForeground(new Color(0, 0, 0));
		lblDescr.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		PanelInfoLibro.add(lblDescr);

		JLabel lblAut = new JLabel("Autor:\r\n");
		lblAut.setBackground(new Color(0, 0, 0));
		lblAut.setForeground(new Color(0, 0, 0));
		lblAut.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		lblAut.setBounds(10, 133, 60, 14);
		PanelInfoLibro.add(lblAut);

		JLabel lblGene = new JLabel("Genero:");
		lblGene.setBackground(new Color(0, 0, 0));
		lblGene.setForeground(new Color(0, 0, 0));
		lblGene.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		lblGene.setBounds(10, 172, 70, 14);
		PanelInfoLibro.add(lblGene);

		txtID = new JTextField();
		txtID.setBounds(96, 26, 155, 20);
		PanelInfoLibro.add(txtID);
		txtID.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(96, 51, 155, 20);
		PanelInfoLibro.add(txtNombre);

		txtGenero = new JTextField();
		txtGenero.setColumns(10);
		txtGenero.setBounds(96, 170, 155, 20);
		PanelInfoLibro.add(txtGenero);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(96, 78, 155, 41);
		PanelInfoLibro.add(txtDescripcion);

		txtAutor = new JTextField();
		txtAutor.setColumns(10);
		txtAutor.setBounds(96, 131, 155, 20);
		PanelInfoLibro.add(txtAutor);

		JButton btnBuscarAutor = new JButton("");
		btnBuscarAutor.setIcon(new ImageIcon(VentanaLibro.class.getResource("/imag/archivo (1).png")));

		btnBuscarAutor.setBounds(259, 127, 19, 20);
		PanelInfoLibro.add(btnBuscarAutor);

		JButton btnGenero = new JButton("");
		btnGenero.setIcon(new ImageIcon(VentanaLibro.class.getResource("/imag/archivo (1).png")));
		btnGenero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String genero = JOptionPane.showInputDialog(null, "Genero del libro a buscar");

				try {
					libros = libro.obtenerPorGenero(genero);
					String autor = libros.get(0).getAutor();
					String des = libros.get(0).getDescripcion();
					String nombre = libros.get(0).getNombre();
				

					txtNombre.setText(autor);
					txtDescripcion.setText(des);
					txtNombre.setText(nombre);
					txtGenero.setText(genero);

					JOptionPane.showMessageDialog(null, "Libro encontrado");

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "libro no encontrado");
					e1.printStackTrace();
				}

			}
		});

		btnGenero.setBounds(259, 172, 19, 20);
		PanelInfoLibro.add(btnGenero);

		JButton btnBuscarNombre = new JButton("");
		btnBuscarNombre.setIcon(new ImageIcon(VentanaLibro.class.getResource("/imag/archivo (1).png")));
		btnBuscarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * ( new DialogoAgregarLibro( ) ).setVisible( true );
				 */

				Libro libro2 = null;
				String nombre = JOptionPane.showInputDialog(null, "Nombre del Libro a buscar");
				try {
					libro2 = libro.obtenerPorNombre(nombre);
					if (libro2 != null) {
						String autor = libro2.getAutor();
						String descripcio = libro2.getDescripcion();
						String nombre2 = libro2.getNombre();
						String genero = libro2.getGenero();

						txtAutor.setText(autor);
						txtDescripcion.setText(descripcio);
						txtNombre.setText(nombre2);
						txtGenero.setText(genero);

						JOptionPane.showMessageDialog(null, "libro encontrado");
					} else {
						JOptionPane.showMessageDialog(null, "libro no encontrado");
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnBuscarNombre.setBounds(259, 53, 19, 20);
		PanelInfoLibro.add(btnBuscarNombre);

		JButton btnAgregar = new JButton();
		btnAgregar.setIcon(new ImageIcon(VentanaLibro.class.getResource("/imag/add_icon-icons.com_74429.png")));
		btnAgregar.setBounds(0, 233, 131, 33);
		PanelInfoLibro.add(btnAgregar);
		btnAgregar.setText("AGREGAR");
		btnAgregar.setBackground(new Color(255, 255, 204));

		JButton btnEliminar = new JButton();
		btnEliminar.setIcon(new ImageIcon(VentanaLibro.class.getResource("/imag/delete_40623.png")));
		btnEliminar.setBounds(141, 233, 131, 33);
		PanelInfoLibro.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Libro libro2 = null;
				String nombre = JOptionPane.showInputDialog(null, "Nombre del Libro a retirar");

				try {
					libro2 = libro.obtenerPorNombre(nombre);
					if (libro2 != null) {
						libro.retirar(libro2);
						JOptionPane.showMessageDialog(null, "Libro retirado exitosamente");

					} else {
						JOptionPane.showMessageDialog(null, "Libro no existe");
						
					}
					
					

					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnEliminar.setBackground(new Color(204, 153, 51));
		btnEliminar.setText("Retirar");

		JButton btnModificar = new JButton();
		btnModificar.setIcon(
				new ImageIcon(VentanaLibro.class.getResource("/imag/note-task-comment-message-edit-write_108613.png")));
		btnModificar.setBounds(282, 233, 136, 33);
		PanelInfoLibro.add(btnModificar);
		btnModificar.setBackground(new Color(204, 153, 51));
		btnModificar.setText("MODIFICAR");

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(VentanaLibro.class.getResource("/imag/archivo (1).png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Libro libro2 = null;
				String cadenaid = JOptionPane.showInputDialog(null, "Id del libro a buscar");

				int id = Integer.parseInt(cadenaid);
				try {
					libro2 = libro.obtenerPorId(id);

					if (libro2 != null) {
						int id2 = libro2.getId();
						String cadenaid2 = Integer.toString(id2);
						String autor = libro2.getAutor();
						String descripcio = libro2.getDescripcion();
						String nombre2 = libro2.getNombre();
						String genero = libro2.getGenero();

						txtAutor.setText(autor);
						txtDescripcion.setText(descripcio);
						txtNombre.setText(nombre2);
						txtGenero.setText(genero);
						txtID.setText(cadenaid2);

						JOptionPane.showMessageDialog(null, "libro encontrado");
					} else {
						JOptionPane.showMessageDialog(null, "libro no encontrado");
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		btnNewButton.setBounds(259, 26, 19, 25);
		PanelInfoLibro.add(btnNewButton);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Libro libro2 = null;
				
				String cadenaid = JOptionPane.showInputDialog(null, "Identifacion del Libro a modificar");

				
				
				int id = Integer.parseInt(cadenaid);
			
					
					

				try {
					libro2 = libro.obtenerPorId(id);
					if (libro2 != null )
					 {
						String nombre = JOptionPane.showInputDialog(null, "Nombre del libro");
						String des= JOptionPane.showInputDialog(null, "Descripcion del libro");
						
						
						VentanaLibro frame = new VentanaLibro();
						frame.invalidate();
						frame.validate();
						frame.repaint();
						
					
					
						
						

						libro2.setNombre(nombre);
						libro2.setDescripcion(des);
						libro.modificar(libro2);
						JOptionPane.showMessageDialog(null, "Libro actulizado exitosamente");
						final VentanaLibro w = new VentanaLibro();
						w.setVisible(true);
						dispose();

					} else {

						JOptionPane.showMessageDialog(null, "valor invalido");

					}
					
					

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String nombre = txtNombre.getText();
				String genero = txtGenero.getText();
				String autor = txtAutor.getText();
				Libro libro2 = null;
				try {

					libro2 = libro.obtenerPorNombre(nombre);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if (nombre == " ") {

						JOptionPane.showMessageDialog(null, "Nombre invalido", "Error", JOptionPane.ERROR_MESSAGE);
					} else if (autor == "") {
						JOptionPane.showMessageDialog(null, "Autor no valido", "Error", JOptionPane.ERROR_MESSAGE);

					} else if (libro2 != null) {

						JOptionPane.showMessageDialog(null, "Libro ya existe");
					} else {
						JOptionPane.showMessageDialog(null, "Libro agregado exitosamente");
						libro.agregar(new Libro(txtNombre.getText(), txtDescripcion.getText(), txtAutor.getText(),
								txtGenero.getText()));
						mostrar();

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block

					e.printStackTrace();
				}
			}
		});
		btnBuscarAutor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				List<Libro> libros = new ArrayList<>();

				String autor = JOptionPane.showInputDialog(null, "Autor del libro a buscar");

				try {
					libros = libro.obtenerPorAutor(autor);
					String nombre = libros.get(0).getNombre();
					String des = libros.get(0).getDescripcion();
					String gen = libros.get(0).getGenero();

					txtNombre.setText(nombre);
					txtDescripcion.setText(des);
					txtGenero.setText(gen);
					txtAutor.setText(autor);

					JOptionPane.showMessageDialog(null, "Libro encontrado");

				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "libro no encontrado");
					e.printStackTrace();
				}

			}

		});

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(UIManager.getBorder("CheckBox.border"));
		panel_2.setBounds(0, 11, 705, 107);
		panel_2.setForeground(new Color(0, 0, 0));
		panel_2.setBackground(new Color(204, 153, 51));
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		ImageIcon fot = new ImageIcon("/imag/libreria");

		JLabel lblHeader = new JLabel("New label");
		lblHeader.setIcon(new ImageIcon(VentanaLibro.class.getResource("/imag/biblioteca_header.jpg")));
		lblHeader.setBounds(0, 0, 940, 235);
		panel_2.add(lblHeader);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBorder(UIManager.getBorder("ComboBox.border"));
		panel_3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Prestamos"));
		panel_3.setBounds(458, 129, 197, 183);
		contentPane.add(panel_3);

		JButton btnPrestamos = new JButton("");
		btnPrestamos.setBackground(new Color(255, 255, 255));
		btnPrestamos.setIcon(new ImageIcon(VentanaLibro.class.getResource("/imag/prestamo2.png")));
		btnPrestamos.setBounds(10, 29, 156, 143);
		btnPrestamos.setFont(new Font("Baskerville Old Face", Font.BOLD, 12));

		btnPrestamos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				final VentanaPrestamos w = new VentanaPrestamos();
				dispose();

				w.setVisible(true);
				dispose();

			}
		});
		panel_3.setLayout(null);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				final VentanaPrestamos w = new VentanaPrestamos();
				w.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(VentanaLibro.class.getResource("/imag/libreria.png")));
		btnNewButton_1.setBounds(10, 35, 221, 137);
		panel_3.add(btnNewButton_1);

		try {
			libros = libro.obtenerTodosConEstado();
			libros.get(0).isPrestado();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Object mat[][] = new Object[libros.size()][6];
		for (int i = 0; i < libros.size(); i++) {
			mat[i][0] = libros.get(i).getId().toString();
			mat[i][1] = libros.get(i).getNombre();
			mat[i][2] = libros.get(i).getDescripcion();
			mat[i][3] = libros.get(i).getAutor();
			mat[i][4] = libros.get(i).getGenero();
			mat[i][5] = libros.get(i).isPrestado();

		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 428, 634, 197);
		contentPane.add(scrollPane);

		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {

		});
		scrollPane.setViewportView(table_1);

		table_2 = new JTable();
		scrollPane.setViewportView(table_1);

		table_1.setModel(new DefaultTableModel(mat,
				new String[] { "Id Libro", "Nombre", "Descripcion", "Autor", "Genero", "Prestado" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { true, true, true, true, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

	}
	
	

}
