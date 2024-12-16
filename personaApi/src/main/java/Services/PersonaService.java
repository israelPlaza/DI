package Services;

import PersonaModel.PersonaModel;
import PersonasDAO.PersonaDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor


public class PersonaService {


    private final PersonaDAO personaDAO;

    public PersonaService(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

        public void createPersona(PersonaModel person){

            personaDAO.save(person);
        }
}
