import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;


public class VentanaPrincipal extends JFrame {
    private JTable userTable;

    public VentanaPrincipal() {
        setTitle("Usuarios Registrados");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Usuarios Registrados");
        titleLabel.setBounds(200, 10, 200, 25);
        add(titleLabel);

        userTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(50, 50, 500, 250);
        add(scrollPane);

        cargarUsuarios();

        JButton cerrarSesionButton = new JButton("Cerrar Sesión");
        cerrarSesionButton.setBounds(400, 320, 150, 30);
        add(cerrarSesionButton);

        cerrarSesionButton.addActionListener((ActionEvent e) -> {
            new VentanaLogin().setVisible(true);
            dispose();
        });

        JButton actualizarButton = new JButton("Actualizar Usuario");
        actualizarButton.setBounds(50, 320, 150, 30);
        add(actualizarButton);

        JButton eliminarButton = new JButton("Eliminar Usuario");
        eliminarButton.setBounds(225, 320, 150, 30);
        add(eliminarButton);

// Acción para actualizar un usuario
        actualizarButton.addActionListener((ActionEvent e) -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String usuario = userTable.getValueAt(selectedRow, 0).toString();
                new VentanaActualizar(usuario).setVisible(true); // Abre la ventana para actualizar
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario.");
            }
        });

// Acción para eliminar un usuario
        eliminarButton.addActionListener((ActionEvent e) -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String usuario = userTable.getValueAt(selectedRow, 0).toString();
                try (Connection connection = BaseDatos.getInstancia().getConexion()) {
                    String query = "DELETE FROM usuarios WHERE nombre_usuario = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, usuario);
                    statement.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
                    cargarUsuarios(); // Refresca la tabla despues de eliminar
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el usuario: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario.");
            }
        });

    }



    private void cargarUsuarios() {
        try (Connection connection = BaseDatos.getInstancia().getConexion()) { // Obtén una nueva conexión
            String query = "SELECT nombre_usuario, nombre, apellido, telefono, correo FROM usuarios";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"Usuario", "Nombre", "Apellido", "Teléfono", "Correo"}, 0);

            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getString("nombre_usuario"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("telefono"),
                        resultSet.getString("correo")
                });
            }
            userTable.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los usuarios: " + e.getMessage());
        }
    }




}
