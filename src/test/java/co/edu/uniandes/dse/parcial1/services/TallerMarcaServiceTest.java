package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.MarcaEntity;
import co.edu.uniandes.dse.parcial1.entities.TallerEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import({ TallerMarcaService.class, TallerService.class})
public class TallerMarcaServiceTest {
    
    @Autowired
    private TallerMarcaService tallerMarcaService;
    
    @Autowired
    private TallerService tallerService;

    @Autowired
	private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private MarcaEntity marcaAAgregar = new MarcaEntity();


    @BeforeEach
    void setUp() {

        marcaAAgregar = factory.manufacturePojo(MarcaEntity.class);
        marcaAAgregar.setLogo("https://");

        entityManager.persist(marcaAAgregar);

    }

    @Test
    void agregarMarcaATallerInexistente() throws EntityNotFoundException {

        assertThrows(EntityNotFoundException.class, () -> {

            tallerMarcaService.addMarcaTaller(marcaAAgregar.getId(), 0L);

        });

    }

    @Test
    void agregarMarcaInexistenteATaller() throws EntityNotFoundException {

        assertThrows(EntityNotFoundException.class, () -> {

            TallerEntity taller = factory.manufacturePojo(TallerEntity.class);
            entityManager.persist(taller);

            tallerMarcaService.addMarcaTaller(0L, taller.getId());

        });

    }

    @Test
    void agregarMarcaATallerValido() throws EntityNotFoundException {

        TallerEntity taller = factory.manufacturePojo(TallerEntity.class);
        entityManager.persist(taller);

        assertFalse(taller.getMarcasTaller().contains(marcaAAgregar));
        tallerMarcaService.addMarcaTaller(marcaAAgregar.getId(), taller.getId());
        assertTrue(taller.getMarcasTaller().contains(marcaAAgregar));

    }
 

}
