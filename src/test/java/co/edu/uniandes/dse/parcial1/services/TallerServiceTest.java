package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.TallerEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.TallerRepository;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(TallerService.class)
class TallerServiceTest {
    
    @Autowired
    private TallerService tallerService;

    PodamFactory factory = new PodamFactoryImpl();

    @Test
    void createTallerInvalido() throws IllegalOperationException{

        TallerEntity taller = factory.manufacturePojo(TallerEntity.class);
        taller.setSlogan("No tiene mas 50 car");

        assertThrows(IllegalOperationException.class, () -> {

            tallerService.createTaller(taller);

        });

    }

    @Test
    void createTallerValido() throws IllegalOperationException{

        TallerEntity taller = factory.manufacturePojo(TallerEntity.class);
        taller.setSlogan("Este slogan en efect tiene muchos mas de 50 caracteres en el.");

        assertNotNull(tallerService.createTaller(taller));

    }

}
