package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projections.SaleMinProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true, value = "SELECT tb_sales.id, tb_sales.date, tb_sales.amount, " +
            "tb_seller.name " +
            "FROM tb_sales " +
            "INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id " +
            "WHERE tb_sales.date >= :minDate " +
            "  AND tb_sales.date <= :maxDate " +
            "  AND tb_seller.name LIKE CONCAT('%', :name, '%')")
    Page<SaleMinProjection> searchByInitialDateFinalDateAndSellerPartialName
            (@Param("minDate") LocalDate minDate,
             @Param("maxDate") LocalDate maxDate,
             @Param("name") String name, Pageable pageable);
}
