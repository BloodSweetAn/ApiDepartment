package com.upc.api_examen_parcial_202120796.controllers;

import com.upc.api_examen_parcial_202120796.dtos.DepartamentDTO;
import com.upc.api_examen_parcial_202120796.entities.Department;
import com.upc.api_examen_parcial_202120796.services.DepartamentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/GuevaraCueva/api")
public class DepartamentController {

    @Autowired
    private DepartamentService gcDepartamentService;

    @PostMapping("/department")
    public ResponseEntity<DepartamentDTO> save (@RequestBody DepartamentDTO gcdepartamentDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Department gcDepartament = modelMapper.map(gcdepartamentDTO, Department.class);
        try {
            gcDepartamentService.save(gcDepartament);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha podido guardar el departamento");
        }
        gcdepartamentDTO = modelMapper.map(gcDepartament, DepartamentDTO.class);
        return new ResponseEntity<>(gcdepartamentDTO, HttpStatus.OK);
    }

    private DepartamentDTO ConverToList(Department list) {
        ModelMapper modelMapper = new ModelMapper();
        DepartamentDTO departamentDTO = modelMapper.map(list , DepartamentDTO.class);
        return departamentDTO;
    }


    @GetMapping("/departments")
    public ResponseEntity<List<DepartamentDTO>> list() {
        List<Department> l;
        l = gcDepartamentService.list();
        List<DepartamentDTO> departamentDTOS = l.stream().map(this::ConverToList).toList();
        return new ResponseEntity<>(departamentDTOS , HttpStatus.OK);
    }

    @PutMapping("/department")
    public ResponseEntity<DepartamentDTO> actualizarDepartment(@RequestBody DepartamentDTO gcdepartamentDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Department gcDepartament = modelMapper.map(gcdepartamentDTO, Department.class);
        //gcDepartament.setGcId(id);
        try {
            gcDepartament = gcDepartamentService.actualizarDepartament(gcDepartament);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
        gcdepartamentDTO = modelMapper.map(gcDepartament, DepartamentDTO.class);
        return new ResponseEntity<>(gcdepartamentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/department/{codigo}")
    public ResponseEntity<DepartamentDTO> borrarDepartment(@PathVariable(value = "codigo") Long codigo){
        Department department;
        DepartamentDTO departamentDTO;
        try {
            department = gcDepartamentService.borrarDepartament(codigo);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
        departamentDTO = ConverToList(department);
        return new ResponseEntity<>(departamentDTO, HttpStatus.OK);
    }

    @GetMapping("/department/{codigo}")
    public ResponseEntity<DepartamentDTO> obtenerEntidad(@PathVariable(value = "codigo") Long codigo){
        Department department;
        DepartamentDTO departamentDTO;
        try {
            department = gcDepartamentService.searchId(codigo);
            departamentDTO = ConverToList(department);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mi mensaje");
        }
        return new ResponseEntity<>(departamentDTO, HttpStatus.OK);
    }
}
