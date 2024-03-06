package co.edu.uniandes.dse.parcial1.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.MarcaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.MarcaRepository;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Transactional
    public MarcaEntity createMarca(MarcaEntity marca) throws IllegalOperationException{

        if(!marca.getLogo().startsWith("https://")) {

            throw new IllegalOperationException("El link de la imagen es invalida.");

        }

        return marcaRepository.save(marca);

    }



}
