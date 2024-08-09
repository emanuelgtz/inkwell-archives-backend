package com.inkwell.archives.repository;

import com.inkwell.archives.model.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseEntityRepository extends JpaRepository<PurchaseEntity, Integer> {

  Optional<PurchaseEntity> findPurchaseEntityById(int idpurchase);

}
