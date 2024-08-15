package com.inkwell.archives.repository;

import com.inkwell.archives.model.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer> {

  Optional<PurchaseEntity> findByPurchaseEntityId(int purchaseId);

  Optional<PurchaseEntity> findByPurchaseEntityDate(Date date);

}