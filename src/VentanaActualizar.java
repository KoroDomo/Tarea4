import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class VentanaActualizar extends JFrame {
    private JTextField nombreField, apellidoField, telefonoField, correoField;
    private String usuario;

    public VentanaActualizar(String usuario) {
        this.usuario = usuario;
        setTitle("Actualizar Usuario");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(50, 20, 100, 25);
        add(nombreLabel);

        nombreField = new JTextField();
        nombreField.setBounds(150, 20, 150, 25);
        add(nombreField);

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(50, 60, 100, 25);
        add(apellidoLabel);

        apellidoField = new JTextField();
        apellidoField.setBounds(150, 60, 150, 25);
        add(apellidoField);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(50, 100, 100, 25);
        add(telefonoLabel);

        telefonoField = new JTextField();
        telefonoField.setBounds(150, 100, 150, 25);
        add(telefonoField);

        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(50, 140, 100, 25);
        add(correoLabel);

        correoField = new JTextField();
        correoField.setBounds(150, 140, 150, 25);
        add(correoField);

        JButton actualizarButton = new JButton("Actualizar");
        actualizarButton.setBounds(100, 200, 150, 30);
        add(actualizarButton);

        actualizarButton.addActionListener((ActionEvent e) -> {
            try (Connection connection = BaseDatos.getInstancia().getConexion()) {
                String query = "UPDATE usuarios SET nombre = ?, apellido = ?, telefono = ?, correo = ? WHERE nombre_usuario = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, nombreField.getText());
                statement.setString(2, apellidoField.getText());
                statement.setString(3, telefonoField.getText());
                statement.setString(4, correoField.getText());
                statement.setString(5, usuario);

                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar el usuario: " + ex.getMessage());
            }
        });
    }
}
