/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.ML.Result;

/**
 *
 * @author digis
 */
public interface IUsuarioJPADAO {
    
    Result GetAll();
    
    Result ADD(com.digis01.SLeonProgramacionNCapas.ML.Usuario usuarioML);
    
    Result Update(com.digis01.SLeonProgramacionNCapas.ML.Usuario usuarioML);
    
    Result Delete(int IdUsuario);
    
    Result GetById(int IdUsuario);
    
    Result BajaLogica(int IdUsuario);
    
}
