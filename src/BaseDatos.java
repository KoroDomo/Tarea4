import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BaseDatos {
    private static BaseDatos instancia;
    private Connection conexion;

    private BaseDatos() {
        try {
            conexion = DriverManager.getConnection( // Linea para inicializar conexion con el user y passwords definidos
                    "jdbc:mysql://localhost:3306/usuarios_db", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BaseDatos getInstancia() {
        if (instancia == null) {
            instancia = new BaseDatos();
        }
        return instancia;
    }

    public Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) { // Verifica si la conexion se cerro
                conexion = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/usuarios_db", "root", "root");
                System.out.println("Conexión activa: " + !conexion.isClosed());
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la conexión: " + e.getMessage());
        }
        return conexion;
    }



    public boolean validarLogin(String usuario, String contrasena) {
        String query = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
        try (Connection conexion = getConexion(); // Abre una nueva conexion para la operacion
             PreparedStatement statement = conexion.prepareStatement(query)) {

            statement.setString(1, usuario);
            statement.setString(2, contrasena);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Devuelve true si hay un resultado
        } catch (Exception e) {
            System.out.println("Error al validar el login: " + e.getMessage());
            return false;
        }
    }


}