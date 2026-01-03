package gm.zona_fit.servicio;

import java.util.List;

import gm.zona_fit.modelo.Cliente;

public interface IClienteServicio {

    public List<Cliente> listarClientes();

    public void guardarCliente(Cliente cliente);

    public Cliente buscarClientePorId(Integer id);

    public Cliente eliminarClientePorId(Cliente cliente);

    
    
}
