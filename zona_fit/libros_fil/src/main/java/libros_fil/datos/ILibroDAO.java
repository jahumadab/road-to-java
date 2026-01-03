package libros_fil.datos;

import java.util.List;

import libros_fil.dominio.Libro;

public interface ILibroDAO {

    List <Libro> listarLibros();
    boolean buscarLibroPorId(Libro libro);
    boolean agregarLibro(Libro libro);
    boolean modificarLibro(Libro libro);
    boolean eliminarLibro(Libro libro);
    

}
