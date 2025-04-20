package datos; // Define el paquete donde se encuentra esta clase.

import java.sql.PreparedStatement; // Importa la clase para ejecutar consultas SQL con parámetros.
import java.sql.ResultSet; // Importa la clase para manejar los resultados de una consulta SQL.
import java.sql.SQLException; // Importa la clase para manejar excepciones SQL.
import basedatos.Conexion; // Importa la clase que maneja la conexión con la base de datos.
import Cru.CrudSimpleInterface; // Importa la interfaz genérica para CRUD.
import entidades.Client;  // Importa la clase Client desde el paquete entidades.
import java.util.ArrayList; // Importa la clase ArrayList para manejar listas dinámicas.
import java.util.List; // Importa la interfaz List para almacenar múltiples objetos.
import javax.swing.JOptionPane; // Importa JOptionPane para mostrar mensajes emergentes.

public class DatosUnihotelDAO implements CrudSimpleInterface<Client> { // Declara la clase y especifica que implementa la interfaz CRUD.
    private final Conexion CON; // Variable para la conexión a la base de datos.
    private PreparedStatement ps; // Variable para ejecutar sentencias SQL con parámetros.
    private ResultSet rs; // Variable para almacenar los resultados de una consulta SQL.
    private boolean resp; // Variable para almacenar el resultado de las operaciones.

    // Constructor de la clase que inicializa la conexión con la base de datos.
    public DatosUnihotelDAO() {
        CON = Conexion.getInstancia(); // Obtiene una instancia de la conexión.
    }

    // Método que lista los clientes que coinciden con un texto en su nombre.
    @Override
    public List<Client> listar(String texto) {
        List<Client> registros = new ArrayList<>(); // Crea una lista vacía para almacenar los clientes.
        try {
            // Prepara la consulta SQL con un parámetro para filtrar por nombre.
            ps = CON.conectar().prepareStatement("SELECT * FROM categoria WHERE nombre LIKE ?");
            ps.setString(1, "%" + texto + "%"); // Reemplaza el parámetro con el texto ingresado.
            rs = ps.executeQuery(); // Ejecuta la consulta y almacena los resultados.

            // Itera sobre los resultados y crea objetos Client.
            while (rs.next()) {
                registros.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
            }
            ps.close(); // Cierra el PreparedStatement.
            rs.close(); // Cierra el ResultSet.
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage()); // Muestra un mensaje si ocurre un error SQL.
        } finally {
            ps = null; // Libera la memoria de ps.
            rs = null; // Libera la memoria de rs.
            CON.desconectar(); // Cierra la conexión con la base de datos.
        }
        return registros; // Retorna la lista de clientes encontrados.
    }

    // Método para insertar un nuevo cliente en la base de datos.
    @Override
    public boolean insertar(Client obj) {
        resp = false; // Inicializa la respuesta en falso.
        try {
            // Prepara la consulta SQL para insertar un nuevo cliente.
            ps = CON.conectar().prepareStatement("INSERT INTO categoria (nombre, descripcion, activo) VALUES (?, ?, 1)");
            ps.setString(1, obj.getNombre()); // Asigna el nombre del cliente.
            ps.setString(2, obj.getDescripcion()); // Asigna la descripción.

            // Ejecuta la consulta y verifica si se insertó correctamente.
            if (ps.executeUpdate() > 0) {
                resp = true; // Si se insertó al menos un registro, la respuesta es verdadera.
            }
            ps.close(); // Cierra el PreparedStatement.
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage()); // Muestra un mensaje si ocurre un error SQL.
        } finally {
            ps = null; // Libera la memoria de ps.
            CON.desconectar(); // Cierra la conexión con la base de datos.
        }
        return resp; // Retorna si la inserción fue exitosa o no.
    }

    // Método para actualizar los datos de un cliente.
    @Override
    public boolean actualizar(Client obj) {
        resp = false; // Inicializa la respuesta en falso.
        try {
            // Prepara la consulta SQL para actualizar el cliente.
            ps = CON.conectar().prepareStatement("UPDATE categoria SET nombre=?, descripcion=? WHERE id=?");
            ps.setString(1, obj.getNombre()); // Asigna el nuevo nombre.
            ps.setString(2, obj.getDescripcion()); // Asigna la nueva descripción.
            ps.setInt(3, obj.getId()); // Asigna el ID del cliente.

            // Ejecuta la consulta y verifica si se actualizó correctamente.
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close(); // Cierra el PreparedStatement.
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage()); // Muestra un mensaje si ocurre un error SQL.
        } finally {
            ps = null; // Libera la memoria de ps.
            CON.desconectar(); // Cierra la conexión con la base de datos.
        }
        return resp; // Retorna si la actualización fue exitosa o no.
    }

    // Método para desactivar un cliente (cambiar su estado a inactivo).
    @Override
    public boolean desactivar(int id) {
        resp = false; // Inicializa la respuesta en falso.
        try {
            ps = CON.conectar().prepareStatement("UPDATE categoria SET activo=0 WHERE id=?");
            ps.setInt(1, id); // Asigna el ID del cliente a desactivar.

            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp; // Retorna si la operación fue exitosa o no.
    }

    // Método para activar un cliente (cambiar su estado a activo).
    @Override
    public boolean activar(int id) {
        resp = false; // Inicializa la respuesta en falso.
        try {
            ps = CON.conectar().prepareStatement("UPDATE categoria SET activo=1 WHERE id=?");
            ps.setInt(1, id); // Asigna el ID del cliente a activar.

            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp; // Retorna si la operación fue exitosa o no.
    }

    // Método que cuenta cuántos clientes existen en la base de datos.
    @Override
    public int total() {
        int totalRegistros = 0; // Inicializa el contador en 0.
        try {
            ps = CON.conectar().prepareStatement("SELECT COUNT(id) FROM categoria");
            rs = ps.executeQuery();

            if (rs.next()) { // Si hay resultados en la consulta...
                totalRegistros = rs.getInt(1); // Obtiene el número total de clientes.
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return totalRegistros; // Retorna el total de clientes.
    }

    // Método que verifica si un cliente ya existe en la base de datos.
    @Override
    public boolean existe(String texto) {
        resp = false; // Inicializa la respuesta en falso.
        try {
            ps = CON.conectar().prepareStatement("SELECT nombre FROM categoria WHERE nombre=?");
            ps.setString(1, texto); // Asigna el nombre del cliente.

            rs = ps.executeQuery(); // Ejecuta la consulta.

            if (rs.next()) { // Si hay resultados, significa que el cliente ya existe.
                resp = true;
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return resp; // Retorna si el cliente existe o no.
    }
}
