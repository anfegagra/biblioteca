package com.eafit.biblioteca.principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.eafit.biblioteca.dto.Libro;
import com.eafit.biblioteca.dto.LibroDAO;
import com.eafit.biblioteca.dto.LibroDAOMySQL;
import com.eafit.biblioteca.dto.Prestamo;
import com.eafit.biblioteca.dto.PrestamoDAO;
import com.eafit.biblioteca.dto.PrestamoDAOMySQL;
import com.eafit.biblioteca.dto.Usuario;
import com.eafit.biblioteca.excepcion.LibroExistenteException;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Panel;
import javax.swing.ListSelectionModel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrestamos extends JFrame {

	private JPanel contentPane;

	private JTextField txtID;
	private JTextField txtUsuario;
	private JTextField txtFecha;
	private JTextField txtFechaFin;

	Connection conexion = null;
	PreparedStatement ps = null;

	LibroDAO libro = new LibroDAOMySQL();
	PrestamoDAO pres = new PrestamoDAOMySQL();
	List<Libro> libros = new ArrayList<>();
	private JTable table;
	private JTable table_1;
	List<Prestamo> prestamos = new ArrayList<>();

	/**
	 * Launch the application .
	 */

	

	public void mostrar() {

		try {
			prestamos = pres.consultarPrestamos();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			prestamos = pres.consultarPrestamos();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String mat[][] = new String[prestamos.size()][4];
		for (int i = 0; i < prestamos.size(); i++) {
			mat[i][0] = prestamos.get(i).getId().toString();
			mat[i][1] = prestamos.get(i).getUsuario();
			mat[i][2] = prestamos.get(i).getFechaInicio();
			mat[i][3] = prestamos.get(i).getFechaFin();

		}
		
		
	
			



		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 315, 452, 427);
		contentPane.add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);

		table_1.setModel(new DefaultTableModel(mat, new String[] { "Id", "Usuario", "Fecha inicio", "Fecha fin" }));

	}

	/**
	 * Create the frame.
	 */
	public VentanaPrestamos() {
		setTitle("PRESTAMOS\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(UIManager.getBorder("CheckBox.border"));
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion Prestamos"));
		
		panel.setBounds(0, 75, 265, 173);
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblId = new JLabel("ID Libro");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(10, 31, 60, 14);
		panel.add(lblId);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBackground(new Color(0, 0, 0));
		lblUsuario.setForeground(new Color(0, 0, 0));
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(10, 56, 60, 14);
		panel.add(lblUsuario);

		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setBackground(new Color(0, 0, 0));
		lblFechaInicio.setForeground(new Color(0, 0, 0));
		lblFechaInicio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFechaInicio.setBounds(10, 81, 76, 14);
		panel.add(lblFechaInicio);

		JLabel lblFechaFin = new JLabel("Fecha fin:\r\n");
		lblFechaFin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFechaFin.setBounds(10, 117, 60, 14);
		panel.add(lblFechaFin);

		txtID = new JTextField();
		txtID.setBounds(96, 25, 155, 20);
		panel.add(txtID);
		txtID.setColumns(10);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(96, 54, 155, 20);
		panel.add(txtUsuario);

		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(96, 79, 155, 20);
		panel.add(txtFecha);

		txtFechaFin = new JTextField();
		txtFechaFin.setColumns(10);
		txtFechaFin.setBounds(96, 115, 155, 20);
		panel.add(txtFechaFin);

	
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(51, 0, 255));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(UIManager.getBorder("CheckBox.border"));
		panel_1.setBounds(22, 259, 430, 45);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnAgregar = new JButton(new ImageIcon(VentanaPrestamos.class.getResource("/img/add_icon-icons.com_74429.png")));
		btnAgregar.setText("PRESTAR");
		btnAgregar.setBackground(new Color(255, 255, 204));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Libro libro2 = null;
				String nombre = JOptionPane.showInputDialog(null, "Nombre del libro a prestar:");

				try {
					libro2 = libro.obtenerPorNombre(nombre);
					if (libro2 != null) {
						String user = JOptionPane.showInputDialog(null, "Nombre del usuario:");
						pres.prestarLibro(libro2, user);

						JOptionPane.showMessageDialog(null, "Prestamo solicitado");
						mostrar();
					} else {

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				;
			}

		});
		btnAgregar.setBounds(0, 0, 131, 44);
		panel_1.add(btnAgregar);

		JButton btnModificar = new JButton(new ImageIcon(VentanaPrestamos.class.getResource("/img/note-task-comment-message-edit-write_108613.png")));
		btnModificar.setBackground(new Color(255, 255, 204));
		btnModificar.setText("RENOVAR");
		btnModificar.addActionListener(new ActionListener() {
			private Object presta;

			public void actionPerformed(ActionEvent e) {

				Libro libro2 = null;
				String nombre = JOptionPane.showInputDialog(null, "Nombre del Libro a renovar");
			
				Prestamo prestamo2 = null;
				

				try {
					libro2 = libro.obtenerPorNombre(nombre);
					if (libro2 != null ) {
						String user = JOptionPane.showInputDialog(null, "Ingrese su nombre");
						String fechafin = JOptionPane.showInputDialog(null, "Ingrese fecha devolucion ");

					
					

					
						 pres.renovarPrestamo(libro2, user, fechafin);
						
						

						JOptionPane.showMessageDialog(null, "Prestamo solicitado");
						mostrar();
					} else {

					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				;

			}
		});
		btnModificar.setBounds(282, 0, 148, 44);
		panel_1.add(btnModificar);

		JButton btnEliminar = new JButton(new ImageIcon(VentanaPrestamos.class.getResource("/img/delete_40623.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Libro libro2 = null;
				String cadenaid = JOptionPane.showInputDialog(null, "Id del Libro a retirar");

				int id = Integer.parseInt(cadenaid);

				

				try {
					libro2 = libro.obtenerPorId(id);
					if (libro2 != null) {
						String user = JOptionPane.showInputDialog(null, "Nombre del usuario");
						pres.devolverLibro(libro2, user);

						JOptionPane.showMessageDialog(null, "Devolucion realizada");
						mostrar();
					} else {

					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				;
			}
		});
		btnEliminar.setBounds(141, 0, 131, 44);
		panel_1.add(btnEliminar);
		btnEliminar.setBackground(new Color(255, 255, 204));
		btnEliminar.setText("DEVOLVER");
		panel_1.setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { btnModificar, btnAgregar, btnEliminar }));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(UIManager.getBorder("CheckBox.border"));
		panel_2.setBounds(0, 11, 473, 53);
		panel_2.setForeground(new Color(0, 0, 0));
		panel_2.setBackground(new Color(204, 153, 51));
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("\r\n");
		lblNewLabel.setIcon(new ImageIcon(VentanaPrestamos.class.getResource("/img/biblioteca_header.jpg")));

		lblNewLabel.setBounds(0, 0, 463, 53);
		panel_2.add(lblNewLabel);

		ImageIcon fot = new ImageIcon("/imag/libreria");
		Icon icono = new ImageIcon(
				fot.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_DEFAULT));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBorder(UIManager.getBorder("CheckBox.border"));
		panel_3.setBorder(javax.swing.BorderFactory.createTitledBorder("Libros disponibles"));
		panel_3.setBounds(275, 75, 196, 173);
		contentPane.add(panel_3);

		JButton btnPrestamos = new JButton("");
		btnPrestamos.setBackground(null);
		btnPrestamos.setBorder(null);
		btnPrestamos.setIcon(new ImageIcon(VentanaPrestamos.class.getResource("/img/libro.png")));
		btnPrestamos.setBounds(10, 22, 176, 140);
		btnPrestamos.setFont(new Font("Baskerville Old Face", Font.BOLD, 12));
		
	
		btnPrestamos.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				
               
				
				final VentanaLibro  w= new VentanaLibro();
				w.setVisible(true);
				dispose();

			}
		});
		panel_3.setLayout(null);
		panel_3.add(btnPrestamos);

		try {
			prestamos = pres.consultarPrestamos();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String mat[][] = new String[prestamos.size()][4];
		for (int i = 0; i < prestamos.size(); i++) {
			mat[i][0] = prestamos.get(i).getId().toString();
			mat[i][1] = prestamos.get(i).getUsuario();
			mat[i][2] = prestamos.get(i).getFechaInicio();
			mat[i][3] = prestamos.get(i).getFechaFin();

		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 315, 452, 427);
		contentPane.add(scrollPane);

		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			

		
		});
		scrollPane.setViewportView(table_1);
		
		

		table_1.setModel(new DefaultTableModel(mat, new String[] { "Id Prestamo", "Usuario", "Fecha inicio", "Fecha fin" }));

	}

}
