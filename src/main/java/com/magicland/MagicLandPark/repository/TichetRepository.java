package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.Tichet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface TichetRepository extends JpaRepository<Tichet, Long> {

//    Optional<Tichet> findByDenumire(String denumire);


    Tichet findByBonId(Long id);

    List<Tichet> findByStocGreaterThan(Long stoc);

    @Query("SELECT t FROM tichete t JOIN t.categorie_tichet ct")
   List<Tichet> getTicheteWithCategorie_tichet();

    @Query("SELECT t.stoc FROM tichete t WHERE t.id = :id")
    Long getStocById(@Param("id") Long id);
}
