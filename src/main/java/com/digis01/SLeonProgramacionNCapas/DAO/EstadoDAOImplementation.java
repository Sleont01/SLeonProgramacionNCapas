
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.ML.Estado;
import com.digis01.SLeonProgramacionNCapas.ML.Result;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class EstadoDAOImplementation implements IEstadoDAO{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Result EstadoByPais(int IdPais){
        
        Result result = new Result();
        
        try {

            jdbcTemplate.execute("CALL EstadoByPais(?,?)", (CallableStatementCallback<Boolean>) callableStatement -> {

                callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);
                callableStatement.setInt(1, IdPais);

                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

                result.objects = new ArrayList<>();
                
                while(resultSet.next()){
                    Estado estado = new Estado();
                    
                    estado.setIdEstado(resultSet.getInt("IdEstado"));
                    estado.setNombre(resultSet.getString("Nombre"));
                    
                    result.objects.add(estado);
                    
                }
                
                result.correct = true;

                return true;
            });
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
        }

        return result;

    }


}
