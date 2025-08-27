package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.ML.Direccion;
import com.digis01.SLeonProgramacionNCapas.ML.Result;
import com.digis01.SLeonProgramacionNCapas.ML.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class DireccionDAOImplementation implements IDireccionDAO {

    @Autowired //inyeccion de dependencias 
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result Add(Direccion direccion) {

        Result result = new Result();

        try {
            result.correct = jdbcTemplate.execute("CALL DireccionAdd(?,?,?,?,?)", (CallableStatementCallback<Boolean>) callablestatement -> {

                callablestatement.setString(1, direccion.getCalle());
                callablestatement.setString(2, direccion.getNumeroExterior());
                callablestatement.setString(3, direccion.getNumeroInterior());
                callablestatement.setInt(4, direccion.Colonia.getIdColonia());
                Usuario usuario = new Usuario();
                callablestatement.setInt(5, usuario.getIdUsuario());
                int isCorrect = callablestatement.executeUpdate();

                if (isCorrect == -1) {
                    return true;
                }

                return false;
            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result Update(Direccion direccion) {

        Result result = new Result();

        try {
            result.correct = jdbcTemplate.execute("CALL DireccionUpdate(?,?,?,?,?,?)", (CallableStatementCallback<Boolean>) callablestatement -> {

                callablestatement.setInt(1, direccion.getIdDireccion());
                callablestatement.setString(2, direccion.getCalle());
                callablestatement.setString(3, direccion.getNumeroExterior());
                callablestatement.setString(4, direccion.getNumeroInterior());
                callablestatement.setInt(5, direccion.getColonia().getIdColonia());
                Usuario usuario = new Usuario();
                callablestatement.setInt(6, usuario.getIdUsuario());

                int isCorrect = callablestatement.executeUpdate();

                if (isCorrect == -1) {
                    return true;
                }

                return false;
            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

}
