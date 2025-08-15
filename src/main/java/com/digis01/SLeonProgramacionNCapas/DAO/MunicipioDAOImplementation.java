package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.ML.Municipio;
import com.digis01.SLeonProgramacionNCapas.ML.Result;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class MunicipioDAOImplementation implements IMunicipioDAO{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result MunicipioByEstado(int IdEstado) {
        
        Result result = new Result();
        
        try{
            
            jdbcTemplate.execute("CALL MunicipioByEstado(?,?)", (CallableStatementCallback<Boolean>) callableStatement -> {

                callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);
                callableStatement.setInt(1, IdEstado);

                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

                result.objects = new ArrayList<>();
                
                while(resultSet.next()){
                    Municipio municipio = new Municipio();
                    
                    municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                    municipio.setNombre(resultSet.getString("Nombre"));
                    
                    result.objects.add(municipio);
                    
                }
                
                result.correct = true;

                return true;
            });
            
            
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
        
        }
        return result;
        
        
    }
    
    
    
    
}
