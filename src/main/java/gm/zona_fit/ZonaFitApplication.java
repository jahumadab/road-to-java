package gm.zona_fit;

import java.util.List;
import java.util.Scanner;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.classic.Logger;
import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner{

	//inyección de dependencia de servicio a capa presentacion
	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger logger = 
			(Logger) LoggerFactory.getLogger(ZonaFitApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando aplicacion.");
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("Aplicacion finalizada correctamente.");

		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		zonaFitApp();
	}

	private void zonaFitApp() {
		// TODO Auto-generated method stub
		logger.info(nl+"*** Aplicacion de Zona Fit iniciada ***" +nl);
		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir){
			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(nl);
		}
		
	}


	private int mostrarMenu(Scanner consola) {
		// TODO Auto-generated method stub
		logger.info("Seleccione una opcion: "+nl
				+"1. Listar clientes"+nl
				+"2. Buscar cliente"+nl
				+"3. Agregar cliente por ID"+nl
				+"4. Modificar cliente por ID"+nl
				+"5. Eliminar Cliente de la aplicacion"+nl
				+"6. Salir de la aplicacion"+nl
				+"Opcion: ");
		var opcion = Integer.parseInt(consola.nextLine());
		return opcion;

}

	private boolean ejecutarOpciones(Scanner consola, int opcion) {
		// TODO Auto-generated method stub
		boolean salir = false;
		switch(opcion) {
		case 1:
			logger.info("Listar clientes");
			List<Cliente> clientes = clienteServicio.listarClientes();
			clientes.forEach(cliente -> {
				logger.info(cliente.toString());
			});
			break;
		case 2:
			logger.info("Buscar cliente");
			var idCliente = Integer.parseInt(consola.nextLine());
			Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
			if(cliente != null) {
				logger.info("Cliente encontrado: "+cliente.toString());
			}else {
				logger.info("Cliente no encontrado");
			}
			break;
		case 3:
			logger.info("Agregar cliente por ID");
			Cliente nuevoCliente = new Cliente();
			logger.info("Ingrese el nombre del cliente: ");
			nuevoCliente.setNombre(consola.nextLine());
			logger.info("Ingrese el apellido del cliente: ");
			nuevoCliente.setApellido(consola.nextLine());
			logger.info("Ingrese membresi del cliente: ");
			nuevoCliente.setMembresia(Integer.parseInt(consola.nextLine()));
	

			clienteServicio.guardarCliente(nuevoCliente);
			logger.info("Cliente agregado: "+nuevoCliente.toString());
			break;
		case 4:
			logger.info("Modificar cliente por ID");
			var idModCliente = Integer.parseInt(consola.nextLine());
			Cliente modCliente = clienteServicio.buscarClientePorId(idModCliente);
			if(modCliente != null) {
				logger.info("Ingrese el nuevo nombre del cliente: ");
				modCliente.setNombre(consola.nextLine());
				logger.info("Ingrese el nuevo apellido del cliente: ");
				modCliente.setApellido(consola.nextLine());
				logger.info("Ingrese nueva membresia del cliente: ");
				modCliente.setMembresia(Integer.parseInt(consola.nextLine()));
				clienteServicio.guardarCliente(modCliente);
				logger.info("Cliente modificado: "+modCliente.toString());
			}else {
				logger.info("Cliente no encontrado");
			}
			break;
		case 5:
			logger.info("Eliminar Cliente de la aplicacion");
			var idDelCliente = Integer.parseInt(consola.nextLine());
			Cliente delCliente = clienteServicio.buscarClientePorId(idDelCliente);
			if(delCliente != null) {
				clienteServicio.eliminarClientePorId(delCliente);
				logger.info("Cliente eliminado: "+delCliente.toString());
			}else {
				logger.info("Cliente no encontrado");
			}
			
			break;
		case 6:
			logger.info("Salir de la aplicacion");
			salir = true;
			break;
		default:
			logger.info("Opcion no valida");
		}
		return salir;
	}



}
