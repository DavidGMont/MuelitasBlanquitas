package com.clinicaodontologica.MuelitasBlanquitas.repository;

import com.clinicaodontologica.MuelitasBlanquitas.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {}