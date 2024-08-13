package com.inkwell.archives.service.impl;

import com.inkwell.archives.model.BookEntity;
import com.inkwell.archives.model.PurchaseEntity;
import com.inkwell.archives.model.UserEntity;
import com.inkwell.archives.repository.PurchaseRepository;
import com.inkwell.archives.service.interfaces.PurchaseService;
import com.inkwell.archives.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

  private PurchaseRepository purchaseRepository;

  @Autowired
  private UserService userService;
  @Autowired
  public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
    this.purchaseRepository = purchaseRepository;
  }

  @Override
  public List<PurchaseEntity> findAll() {
    return purchaseRepository.findAll();
  }

  @Override
  public PurchaseEntity findByPurchaseId(int purchaseId) {
    Optional<PurchaseEntity> result = purchaseRepository.findByPurchaseEntityId(purchaseId);

    PurchaseEntity thePurchaseId = null;

    if(result.isPresent()) {
      thePurchaseId = result.get();
    } else {
      throw new RuntimeException("Finding requested purchase id was not possible " + purchaseId);
    }

    return thePurchaseId;
  }

  @Override
  public PurchaseEntity findByPurchaseDate(Date purchaseDate) {

    Optional<PurchaseEntity> result = purchaseRepository.findByPurchaseEntityDate(purchaseDate);

    PurchaseEntity thePurchaseDate = null;

    if(result.isPresent()) {
      thePurchaseDate = result.get();
    } else {
      throw new RuntimeException("Finding requested purchase id was not possible " + purchaseDate);
    }

    return thePurchaseDate;
  }

  @Override
  public PurchaseEntity createPurchase(PurchaseEntity request) {
    // Validations to avoid having data issues when creating purchases

    if(request.getPurchaseDate() == null) {
      throw new IllegalArgumentException("Purchase date cannot be null or negative");
    }

    if(request.getPurchaseUser() == null) {
      throw new IllegalArgumentException("User cannot be null");
    }

    UserEntity user = userService.findByUserId(request.getPurchaseUser().getId());

    if(user == null) {
      throw new IllegalArgumentException("User with the Id: " + request.getPurchaseUser().getId());
    }

    if(request.getPurchaseDate() == null) {
      request.setPurchaseDate(new Date());
    }

    PurchaseEntity savedPurchase = purchaseRepository.save(request);

    List<BookEntity> books = new ArrayList<>();

    request.setBooks(books);

    return savedPurchase;
  }

  @Override
  public PurchaseEntity save(PurchaseEntity thePurchase) {
    return purchaseRepository.save(thePurchase);
  }

  @Override
  public void deleteByPurchaseId(int id) {
    purchaseRepository.deleteById(id);
  }
}
