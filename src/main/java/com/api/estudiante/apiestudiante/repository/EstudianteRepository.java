package com.api.estudiante.apiestudiante.repository;

import com.api.estudiante.apiestudiante.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
