import java.sql.Connection;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Aqui se inicia la pueba de base de datos
        Connection connection = BaseDatos.getInstancia().getConexion();
        if (connection != null) {
            System.out.println("Conexión a la base de datos exitosa.");
        } else {
            System.out.println("Error al conectar a la base de datos.");
            return; // se termina el programa si no hay conexion
        }

        // Vetana Login
        SwingUtilities.invokeLater(() -> new VentanaLogin().setVisible(true));
    }
}
