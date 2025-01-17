package com.inkwell.archives.service.impl;

import com.inkwell.archives.model.BookEntity;
import com.inkwell.archives.model.PurchaseEntity;
import com.inkwell.archives.model.UserEntity;
import com.inkwell.archives.repository.PurchaseRepository;
import com.inkwell.archives.service.interfaces.PurchaseService;
import com.inkwell.archives.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
  @Autowired
  private UserService userService;
  private PurchaseRepository purchaseRepository;
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

    Optional<PurchaseEntity> result = purchaseRepository.findById(purchaseId);

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

    Optional<PurchaseEntity> result = purchaseRepository.findByPurchaseDate(purchaseDate);

    PurchaseEntity thePurchaseDate = null;

    if(result.isPresent()) {
      thePurchaseDate = result.get();
    } else {
      throw new RuntimeException("Finding requested purchase id was not possible " + purchaseDate);
    }

    return thePurchaseDate;
  }

  @Override
  public PurchaseEntity save(PurchaseEntity request) {
    // Validations to avoid having data issues when creating purchases

    if(request.getPurchaseDate() == null) {
      throw new IllegalArgumentException("Purchase cannot be null");
    }

    if(request.getPurchaseUser() == null) {
      throw new IllegalArgumentException("Purchase user cannot be null");
    }

    if (request.getBooks().isEmpty()) {
      throw new RuntimeException("Books list cannot be empty");
    }

    if (request.getPurchaseQuantity() <= 0) {
      throw new IllegalArgumentException("The quantity cannot be 0");
    }

    if (request.getPurchaseTotalPrice() <= 0) {
      throw new IllegalArgumentException("The purchase total price cannot be 0");
    }

    // Get the user id by using its repository
    UserEntity userId = userService.findByUserId( request.getPurchaseUser().getId() );

    if (userId  == null) {
      throw new IllegalArgumentException("User with Id " + request.getPurchaseUser().getId() + " was not found");
    }

    // Set the purchase data and user id
    request.setPurchaseUser(userId);
    request.setPurchaseDate(request.getPurchaseDate());
    request.setPurchaseQuantity(request.getPurchaseQuantity());
    request.setPurchaseTotalPrice(request.getPurchaseTotalPrice());
    request.setBooks(request.getBooks());

    PurchaseEntity savedPurchase = purchaseRepository.save(request);

    /*List<BookEntity> books = new ArrayList<>();
    request.setBooks(books);*/

    return savedPurchase;
  }

  @Override
  public void deleteByPurchaseId(int id) {
    purchaseRepository.deleteById(id);
  }
}
