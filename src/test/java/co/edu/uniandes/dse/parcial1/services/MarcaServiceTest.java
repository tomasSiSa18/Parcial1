package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.MarcaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(MarcaService.class)
class MarcaServiceTest {

    @Autowired
    private MarcaService marcaService;

    private PodamFactory factory = new PodamFactoryImpl();

    @Test
    void createMarcaInvalida() throws IllegalOperationException {

        MarcaEntity marca = factory.manufacturePojo(MarcaEntity.class);

        marca.setLogo("imagen.png");
        
        assertThrows(IllegalOperationException.class, () -> {

            marcaService.createMarca(marca);

        });

    }

    @Test
    void createMarcaValida() throws IllegalOperationException {

        MarcaEntity marca = factory.manufacturePojo(MarcaEntity.class);

        marca.setLogo("https://imagen.png");

        assertNotNull(marcaService.createMarca(marca));

    }

    
    
}
