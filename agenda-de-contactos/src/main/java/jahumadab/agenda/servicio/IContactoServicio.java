package jahumadab.agenda.servicio;

import java.util.List;

import jahumadab.agenda.modelo.Contacto;

public interface IContactoServicio {

    public List<Contacto> listarContactos();

    public Contacto buscarContactoPorId(Integer idContacto);

    public void guardarContacto(Contacto contacto);

    public void eliminarContacto(Contacto contacto);

}
