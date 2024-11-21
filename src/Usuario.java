public class Usuario {
    private int id;
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String contrasena;

    public Usuario(String nombreUsuario, String nombre, String apellido, String telefono, String correo, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasena = contrasena;
    }
 // Getters y Setters

    // Getters
    public String getNombreUsuario() { return nombreUsuario; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public String getContrasena() { return contrasena; }

    // Setters
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}