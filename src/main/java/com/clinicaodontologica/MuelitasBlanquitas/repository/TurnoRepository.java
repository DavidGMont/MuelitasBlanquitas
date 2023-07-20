package com.clinicaodontologica.MuelitasBlanquitas.repository;

import com.clinicaodontologica.MuelitasBlanquitas.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {}
