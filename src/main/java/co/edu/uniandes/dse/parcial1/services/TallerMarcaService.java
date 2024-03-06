package co.edu.uniandes.dse.parcial1.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.MarcaEntity;
import co.edu.uniandes.dse.parcial1.entities.TallerEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.repositories.MarcaRepository;
import co.edu.uniandes.dse.parcial1.repositories.TallerRepository;

@Service
public class TallerMarcaService {

    @Autowired
    private TallerRepository tallerRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Transactional
    public TallerEntity addMarcaTaller(Long idMarca, Long idTaller) throws EntityNotFoundException{

        Optional<MarcaEntity> marca = marcaRepository.findById(idMarca);
        Optional<TallerEntity> taller = tallerRepository.findById(idTaller);

        if(marca.isEmpty()) {

            throw new EntityNotFoundException("La marca a agregar no existe");
            
        }

        if(taller.isEmpty()) {

            throw new EntityNotFoundException("El taller que se quiere editar no existe");

        }

        taller.get().getMarcasTaller().add(marca.get());
        return taller.get();

    }
    
}
