package com.upc.api_examen_parcial_202120796.services;

import com.upc.api_examen_parcial_202120796.entities.Department;
import com.upc.api_examen_parcial_202120796.respositories.DepartamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentService {
    @Autowired
    private DepartamentRepository departamentRepository;

    public Department actualizarDepartament(Department author) throws Exception {
        departamentRepository.findById(author.getGcId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return departamentRepository.save(author);
    }

    public Department borrarDepartament(Long codigo) throws Exception{
        Department department = departamentRepository.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        departamentRepository.delete(department);
        return department;
    }

    public Department searchId(Long id){
        return departamentRepository.findById(id).orElse(null);
    }
    public Department save(Department gcDepartament) {
        return departamentRepository.save(gcDepartament);
    }
    public List<Department> list() {
        return departamentRepository.findAll();
    }
}
