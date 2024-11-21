import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentanaRegistro extends JFrame {
    private JTextField nombreField, apellidoField, telefonoField, correoField, usuarioField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton registrarButton, regresarButton;

    public VentanaRegistro() {
        setTitle("Registro de Usuario");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setBounds(50, 20, 100, 25);
        add(usuarioLabel);

        usuarioField = new JTextField();
        usuarioField.setBounds(150, 20, 150, 25);
        add(usuarioField);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(50, 60, 100, 25);
        add(nombreLabel);

        nombreField = new JTextField();
        nombreField.setBounds(150, 60, 150, 25);
        add(nombreField);

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(50, 100, 100, 25);
        add(apellidoLabel);

        apellidoField = new JTextField();
        apellidoField.setBounds(150, 100, 150, 25);
        add(apellidoField);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(50, 140, 100, 25);
        add(telefonoLabel);

        telefonoField = new JTextField();
        telefonoField.setBounds(150, 140, 150, 25);
        add(telefonoField);

        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(50, 180, 100, 25);
        add(correoLabel);

        correoField = new JTextField();
        correoField.setBounds(150, 180, 150, 25);
        add(correoField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(50, 220, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 220, 150, 25);
        add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirmar:");
        confirmPasswordLabel.setBounds(50, 260, 100, 25);
        add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(150, 260, 150, 25);
        add(confirmPasswordField);

        registrarButton = new JButton("Registrar");
        registrarButton.setBounds(50, 300, 120, 30);
        add(registrarButton);

        regresarButton = new JButton("Regresar");
        regresarButton.setBounds(200, 300, 120, 30);
        add(regresarButton);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        regresarButton.addActionListener(e -> {
            new VentanaLogin().setVisible(true);
            dispose();
        });
    }

    private void registrarUsuario() {
        String usuario = usuarioField.getText();
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String telefono = telefonoField.getText();
        String correo = correoField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Usuario es obligatorio.");
            return;
        }
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Nombre es obligatorio.");
            return;
        }
            // Repite esto para los demás campos


        try (Connection connection = BaseDatos.getInstancia().getConexion()) {
            String query = "INSERT INTO usuarios (nombre_usuario, nombre, apellido, telefono, correo, contrasena) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, usuario);
            statement.setString(2, nombre);
            statement.setString(3, apellido);
            statement.setString(4, telefono);
            statement.setString(5, correo);
            statement.setString(6, password);

            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
            new VentanaLogin().setVisible(true);
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar el usuario: " + ex.getMessage());
        }

    }
}
