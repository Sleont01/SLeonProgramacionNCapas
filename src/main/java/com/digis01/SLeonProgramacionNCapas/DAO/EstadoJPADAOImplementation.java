/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.DAO;


import com.digis01.SLeonProgramacionNCapas.JPA.Estado;
import com.digis01.SLeonProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoJPADAOImplementation implements IEstadoJPADAO {
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result EstadoByPais(int IdPais) {

        Result result = new Result();

        try {

            TypedQuery<Estado> queryEstado = entityManager.createQuery("FROM Estado WHERE Pais.IdPais = :IdPais", Estado.class);       
            queryEstado.setParameter("IdPais", IdPais);

            List<Estado> estados = queryEstado.getResultList();

            result.objects = new ArrayList<>();
            
            for (Estado estado : estados) {
                result.objects.add(estado);
            }
            
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
    
    
    
}
