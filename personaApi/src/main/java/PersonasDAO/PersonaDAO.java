package PersonasDAO;

import PersonaModel.PersonaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaDAO  extends JpaRepository<PersonaModel, Long> {

}
