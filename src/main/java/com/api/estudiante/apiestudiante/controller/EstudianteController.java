package com.api.estudiante.apiestudiante.controller;

import com.api.estudiante.apiestudiante.model.Estudiante;
import com.api.estudiante.apiestudiante.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public List<Estudiante> listarEstudiantes() {
        return estudianteService.listarEstudiante();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> consultarPorId(@PathVariable Long id) {
        return estudianteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Estudiante crearEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.guardar(estudiante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        return estudianteService.obtenerPorId(id)
                .map(existingEstudiante -> {
                    existingEstudiante.setNombre(estudiante.getNombre());
                    existingEstudiante.setEspecialidad(estudiante.getEspecialidad());
                    existingEstudiante.setGrado(estudiante.getGrado());
                    Estudiante updatedEstudiante = estudianteService.guardar(existingEstudiante);
                    return ResponseEntity.ok(updatedEstudiante);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarEstudiante(@PathVariable Long id) {
        return estudianteService.obtenerPorId(id)
                .map(estudiante -> {
                    estudianteService.eliminar(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
