package tacos_jpa.repositories;

import org.springframework.data.repository.CrudRepository;
import tacos_jpa.entities.Taco;

public interface TacoRepository
        extends CrudRepository<Taco, Long>
{
}
