package Controler;


import PersonaModel.PersonaModel;
import Services.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
@RequiredArgsConstructor

public class PersonaControler {

    private final PersonaService personaService;

    // Constructor explícito
    public PersonaControler(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/crear")
    public  void insertarPersona(@RequestBody PersonaModel person){
        personaService.createPersona(person);
    }

}
