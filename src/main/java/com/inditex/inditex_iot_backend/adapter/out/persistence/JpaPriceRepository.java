package com.inditex.inditex_iot_backend.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaPriceRepository extends JpaRepository<PriceJpaEntity, Long> {

    @Query("""
                select p from PriceJpaEntity p
                where p.brandId = :brandId
                  and p.productId = :productId
                  and :when between p.startDate and p.endDate
                order by p.priority desc, p.startDate desc
            """)
    List<PriceJpaEntity> findApplicable(@Param("brandId") int brandId,
            @Param("productId") long productId,
            @Param("when") LocalDateTime when);
}
