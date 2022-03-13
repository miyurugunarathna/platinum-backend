package com.example.demo.repository;

import com.example.demo.model.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChequeRepository extends JpaRepository<Cheque, Integer> {
}
