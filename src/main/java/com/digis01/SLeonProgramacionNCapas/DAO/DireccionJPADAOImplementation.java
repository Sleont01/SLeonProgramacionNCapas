/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.JPA.Direccion;
import com.digis01.SLeonProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class DireccionJPADAOImplementation implements IDireccionJPADAO {
    
    
    @Autowired
    private EntityManager entityManager;
    
    
    
    @Transactional
    @Override
    public Result ADD(com.digis01.SLeonProgramacionNCapas.ML.Usuario usuarioML) {
        Result result = new Result();

        try {

            Direccion direccionJPA = new Direccion(usuarioML);

            entityManager.persist(direccionJPA);
            
            result.correct = true; 

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
@Transactional
@Override
public Result Update(com.digis01.SLeonProgramacionNCapas.ML.Usuario usuarioML) {
       
        Result result = new Result();
        try {
            Direccion direccionJPA = new Direccion(usuarioML);
            entityManager.merge(direccionJPA);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
}
    

    @Transactional
    @Override
    public Result Delete(int IdDireccion) {

    Result result = new Result();

    try {
       
        Direccion direccionJPA = entityManager.find(Direccion.class, IdDireccion);

        if (direccionJPA == null) {
            result.correct = false;
            result.errorMessage = "La dirección no existe en la base de datos.";
            return result;
        }

       
        entityManager.remove(direccionJPA);

        result.correct = true;

    } catch (Exception ex) {
        result.correct = false;
        result.errorMessage = ex.getLocalizedMessage();
        result.ex = ex;
    }

    return result;
}
        
    }




