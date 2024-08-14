package com.inkwell.archives.service.interfaces;

import com.inkwell.archives.model.PurchaseEntity;

import java.util.Date;
import java.util.List;

public interface PurchaseService {
  List<PurchaseEntity> findAll();
  PurchaseEntity findByPurchaseId(int purchaseId);
  PurchaseEntity findByPurchaseDate(Date purchaseDate);
  PurchaseEntity createPurchase(PurchaseEntity request);
  PurchaseEntity save(PurchaseEntity thePurchase);
  void deleteByPurchaseId(int id);
}
