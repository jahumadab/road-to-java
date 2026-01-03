package libros_fil.presentacion;

import java.util.Scanner;

import libros_fil.datos.ILibroDAO;
import libros_fil.datos.LibroDAO;
import libros_fil.dominio.Libro;


public class librosAPP {

    public static void main(String[] args) {
        librosApp();
    }

    private static void librosApp() {
        
        boolean salir = false;
        Scanner consola = new Scanner(System.in);
        ILibroDAO libroDAO = new LibroDAO();
        
        while(!salir){
            try {
                int opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, libroDAO);
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
            System.out.println();
        }

}

private static int mostrarMenu(Scanner consola) {
    System.out.println("----- Menu Libros -----");
    System.out.println("1. Listar libros");
    System.out.println("2. Agregar libro");
    System.out.println("3. Modificar libro");
    System.out.println("4. Eliminar libro");
    System.out.println("5. Salir");
    System.out.print("Seleccione una opcion: ");
    
    return Integer.parseInt(consola.nextLine());

}

private static boolean ejecutarOpciones(Scanner consola, int opcion, ILibroDAO libroDAO) {
    boolean salir = false;
    switch(opcion){
        case 1:
            System.out.println("Listado de libros:");
            libroDAO.listarLibros().forEach(System.out::println);
            break;
            
        case 2:
            System.out.println("Agregar un nuevo libro:");
            // Lógica para agregar un libro
            System.out.println("Introduzca el titulo:");
            String titulo = consola.nextLine();
            System.out.println("Introduzca el autor:");
            String autor = consola.nextLine();
            System.out.println("Esta disponible? (true/false):");
            boolean disponible = Boolean.parseBoolean(consola.nextLine());
            Libro nuevoLibro = new Libro(titulo, autor, disponible);
            boolean agregado = libroDAO.agregarLibro(nuevoLibro);
            if(agregado){
                System.out.println("Libro agregado correctamente" + nuevoLibro);
            }else{
                System.out.println("Error al agregar el libro"+ nuevoLibro);
            }
            break;
           
        case 3:
            System.out.println("Modificar un libro existente:");
            // Lógica para modificar un libro
            System.out.println("Introduzca el ID del libro a modificar:");
            int id = Integer.parseInt(consola.nextLine());
            System.out.println("Introduzca el nuevo titulo:");
            String nuevoTitulo = consola.nextLine();
            System.out.println("Introduzca el nuevo autor:");
            String nuevoAutor = consola.nextLine();
            System.out.println("Esta disponible? (true/false):");
            boolean nuevoDisponible = Boolean.parseBoolean(consola.nextLine());
            Libro libroModificado = new Libro(id, nuevoTitulo, nuevoAutor, nuevoDisponible);
            boolean modificado = libroDAO.modificarLibro(libroModificado);
            if(modificado){
                System.out.println("Libro modificado correctamente"+ libroModificado);
            }else{
                System.out.println("Error al modificar el libro"+ libroModificado);
            }
            break;
            
        case 4:
            System.out.println("Eliminar un libro:");
            System.out.println("Introduzca el ID del libro a eliminar:");
            int idEliminar = Integer.parseInt(consola.nextLine());
            Libro libroAEliminar = new Libro(idEliminar);
            boolean eliminado = libroDAO.eliminarLibro(libroAEliminar);
            if(eliminado){
                System.out.println("Libro eliminado correctamente");
            }else{
                System.out.println("Error al eliminar el libro");
            }
            break;
            
        case 5:
            System.out.println("Hasta pronto!!");
            salir = true;
        default:
            System.out.println("Opcion no valida: " + opcion);
    }
    return salir;
}

}
