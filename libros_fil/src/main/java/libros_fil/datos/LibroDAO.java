package libros_fil.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import static libros_fil.conexion.Conexion.getConexion;

import libros_fil.dominio.Libro;

public class LibroDAO implements ILibroDAO {

    @Override
    public boolean agregarLibro(Libro libro) {
        // TODO Auto-generated method stub
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO libros (titulo, autor, disponible) VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setBoolean(3, libro.isDisponible());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar libro: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }   
        return false;
    }

    @Override
    public boolean buscarLibroPorId(Libro libro) {
        // TODO Auto-generated method stub
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM libros WHERE id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, libro.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setDisponible(rs.getBoolean("disponible"));
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error al buscar libro por ID: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarLibro(Libro libro) {
        // TODO Auto-generated method stub
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM libros WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, libro.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar libro: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public List<Libro> listarLibros() {
        // TODO Auto-generated method stub
        List<Libro> libros = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM libros ORDER BY id";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Libro libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setDisponible(rs.getBoolean("disponible"));
                libros.add(libro);
            }


        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error al listar libros: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }


        return libros;
    }

    @Override
    public boolean modificarLibro(Libro libro) {
        // TODO Auto-generated method stub
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE libros SET titulo = ?, autor = ?, disponible = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setBoolean(3, libro.isDisponible());
            ps.setInt(4, libro.getId());
            ps.execute();

            return true;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error al modificar libro: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }


    public static void main(String[] args) {
        
        ILibroDAO libroDAO = new LibroDAO();
        
    /*     Libro libro = new Libro();
        libro.setId(6);
        boolean eliminado = libroDAO.eliminarLibro(libro);
        if(eliminado){
            System.out.println("Libro eliminado correctamente" + libro);
        }else{
            System.out.println("Error al eliminar el libro"+ libro);
    } */

    Libro nuevLibro = new Libro("El principito", "Antoine de Saint-Exupéry", true);
    boolean agregado = libroDAO.agregarLibro(nuevLibro);
    if(agregado){
        System.out.println("Libro agregado correctamente");
    }else{
        System.out.println("Error al agregar el libro");
    }

    nuevLibro.setDisponible(false);

    /* boolean modificado = libroDAO.modificarLibro(nuevLibro);
    if(modificado){
        System.out.println("Libro modificado correctamente");
    }else{
        System.out.println("Error al modificar el libro");
    } */

    List<Libro> libros = libroDAO.listarLibros();
        libros.forEach(System.out::println);

}

}
