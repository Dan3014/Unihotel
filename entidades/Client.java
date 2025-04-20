package entidades;

public class Client {
    private int id_client;
    private String nombre;
    private String apellido;
    private String email;
    private int telefono;
    private String direccion;
    private String fecha_regi;
    private boolean activo;

    // Constructores
    public Client() {
    }

    public Client(int id_client, String nombre, String apellido, String email, int telefono, String direccion, String fecha_regi, boolean activo) {
        this.id_client = id_client;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fecha_regi = fecha_regi;
        this.activo = activo;
    }

    public Client(int aInt, String string, String string0, boolean aBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getter y setter
    public int getId() {
        return id_client;
    }

    public void setId(int id_client) {
        this.id_client = id_client;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha_regi() {
        return fecha_regi;
    }

    public void setFecha_regi(String fecha_regi) {
        this.fecha_regi = fecha_regi;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Método toString
    @Override
    public String toString() {
        return "Client{" + "id_client=" + id_client + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", telefono=" + telefono + ", direccion=" + direccion + ", fecha_regi=" + fecha_regi + ", activo=" + activo + '}';
    }

    public String getDescripcion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
