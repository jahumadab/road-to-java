package jahumadab.agenda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import jahumadab.agenda.modelo.Contacto;

public interface ContactoRepositorio extends JpaRepository<Contacto, Integer> {

}
