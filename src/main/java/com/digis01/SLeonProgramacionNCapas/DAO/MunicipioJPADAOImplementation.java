/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.JPA.Municipio;
import com.digis01.SLeonProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class MunicipioJPADAOImplementation implements IMunicipioJPADAO{
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result MunicipioByEstado(int IdEstado) {

        Result result = new Result();

        try {

            TypedQuery<Municipio> queryMunicipio = entityManager.createQuery("FROM Municipio WHERE Estado.IdEstado = :IdEstado", Municipio.class);       
            queryMunicipio.setParameter("IdEstado", IdEstado);

            List<Municipio> municipios = queryMunicipio.getResultList();

            result.objects = new ArrayList<>();
            
            for (Municipio municipio : municipios) {
                result.objects.add(municipio);
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
