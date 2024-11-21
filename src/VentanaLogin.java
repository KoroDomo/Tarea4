import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnEntrar, btnRegistrar;

    public VentanaLogin() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(10, 10, 80, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(100, 10, 160, 25);
        add(txtUsuario);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(10, 40, 80, 25);
        add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(100, 40, 160, 25);
        add(txtContrasena);

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(10, 80, 100, 25);
        add(btnEntrar);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(120, 80, 100, 25);
        add(btnRegistrar);

        // Boton Registrar
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaRegistro().setVisible(true);
                dispose();
            }
        });


        btnEntrar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String contrasena = new String(txtContrasena.getPassword());

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, llena todos los campos.");
                return;
            }

            boolean validado = BaseDatos.getInstancia().validarLogin(usuario, contrasena);

            if (validado) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
                new VentanaPrincipal().setVisible(true);
                dispose(); // Cierra la ventana de login
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
            }
        });


    }
}
