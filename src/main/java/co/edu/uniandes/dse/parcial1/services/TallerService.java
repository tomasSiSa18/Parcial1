package co.edu.uniandes.dse.parcial1.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.TallerEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.TallerRepository;

@Service
public class TallerService {

    @Autowired
    private TallerRepository tallerRepository;

    @Transactional
    public TallerEntity createTaller(TallerEntity taller) throws IllegalOperationException{

        if(taller.getSlogan().length() < 50) {

            throw new IllegalOperationException("El taller debe tener por lo menos un slogan de 50 caracteres.");

        }

        return tallerRepository.save(taller);

    }


}
