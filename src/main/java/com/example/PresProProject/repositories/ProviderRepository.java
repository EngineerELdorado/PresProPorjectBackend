package com.example.PresProProject.repositories;

import com.example.PresProProject.entities.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

    @Query(value = "SELECT * from providers ORDER BY location <-> ST_MakePoint(?2, ?1) limit 50",nativeQuery = true)
    Collection<Provider> findClosest(Double longitude, Double latitude);

    @Override
    @Query(value = "SELECT * FROM providers order by id desc",nativeQuery = true)
    Page<Provider> findAll(Pageable pageable);
}
