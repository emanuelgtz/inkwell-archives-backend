package com.inkwell.archives.service.interfaces;

import com.inkwell.archives.model.BookEntity;
import com.inkwell.archives.model.PurchaseEntity;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

public interface PurchaseService {
  List<PurchaseEntity> findAll();
  PurchaseEntity findByPurchaseId(int purchaseId);
  PurchaseEntity findByPurchaseDate(Date purchaseDate);
  PurchaseEntity save(PurchaseEntity request);
  void deleteByPurchaseId(int id);
}
