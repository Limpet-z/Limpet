package com.limpet1.repository;

import com.limpet1.model.BinanceAccount;
import com.limpet1.model.Ico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcoRepository extends JpaRepository<Ico, Integer> {

    List<Ico> findAll();
}
