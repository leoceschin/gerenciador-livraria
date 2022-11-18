package com.ceschin.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceschin.library.model.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, UUID>{
	
}
