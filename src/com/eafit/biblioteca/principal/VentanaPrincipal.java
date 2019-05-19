package com.eafit.biblioteca.principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import com.eafit.biblioteca.dto.Libro;
import com.eafit.biblioteca.dto.LibroDAO;
import com.eafit.biblioteca.dto.LibroDAOMySQL;
import com.eafit.biblioteca.dto.Usuario;
import com.eafit.biblioteca.dto.UsuarioDAO;
import com.eafit.biblioteca.dto.UsuarioDAOMySLQL;

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
import java.awt.Font;

import javax.swing.UIManager;


public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ImageIcon  imagen;
	

	
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtContraseña;
	
	Connection conexion=null; 
	PreparedStatement ps= null;
	Connection conn = null;

	LibroDAO libro = new LibroDAOMySQL();
	Libro variables = new Libro();
	List<Libro> libros = new ArrayList<>();
	UsuarioDAO usuarioDao = new UsuarioDAOMySLQL();
	boolean inicioSesion = false;	
	
	private JLabel lblNewLabel;
	private JLabel lblIngreso;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					VentanaPrincipal frame = new VentanaPrincipal();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setTitle("LIBROS\r\n");
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 354);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("INFORMACION\r\n");
		panel.setBorder(UIManager.getBorder("CheckBox.border"));
		panel.setBounds(54, 102, 339, 173);
		panel.setBackground(new Color(204, 204, 255));
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(44, 29, 254, 27);
		txtUsuario.setColumns(10);
		panel.add(txtUsuario);
		
		txtContraseña = new JTextField();
		txtContraseña.setBounds(44, 88, 254, 20);
		txtContraseña.setColumns(10);
		panel.add(txtContraseña);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String user = txtUsuario.getText();
				String password = txtContraseña.getText();
				Usuario usuario = new Usuario(user, password);
				final VentanaLibro  w= new VentanaLibro();

				

				try {
					usuarioDao.iniciarSesion(usuario);
					w.setVisible(true);
					dispose();
				

				}catch (Exception e) {

					JOptionPane.showMessageDialog(null, "Datos incorrectos");
				}
				inicioSesion = true;
			}
						
			
		});
		btnLogin.setBounds(124, 127, 89, 23);
		btnLogin.setBackground(new Color(153, 153, 153));
		panel.add(btnLogin);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(21, 40, 16, 16);
		lblNewLabel_1.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imag/usuario-hombre.png")));
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("\r\n");
		lblNewLabel_2.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imag/llave-de-una-casa.png")));
		lblNewLabel_2.setBounds(21, 90, 20, 16);
		panel.add(lblNewLabel_2);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imag/bloquear.png")));
		lblNewLabel.setBounds(193, 11, 64, 64);
		contentPane.add(lblNewLabel);
		
		lblIngreso = new JLabel("INGRESO");
		lblIngreso.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 16));
		lblIngreso.setBounds(193, 72, 79, 19);
		contentPane.add(lblIngreso);
		
		ImageIcon fot = new ImageIcon("/imag/libreria");
		this.repaint();
		this.repaint();
	}
}
