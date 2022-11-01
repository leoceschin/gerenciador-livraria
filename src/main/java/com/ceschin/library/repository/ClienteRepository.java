package com.ceschin.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceschin.library.model.Cliente;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Long>{

}
