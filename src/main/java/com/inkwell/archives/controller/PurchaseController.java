package com.inkwell.archives.controller;

import com.inkwell.archives.model.PurchaseEntity;
import com.inkwell.archives.service.interfaces.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/purchases")
@CrossOrigin(origins = "http://localhost:5173")
public class PurchaseController {
  private final PurchaseService purchaseService;

  @Autowired
  public PurchaseController(PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  // Create and Consult purchase
  @PostMapping
  public ResponseEntity<PurchaseEntity> createPurchase(@RequestBody PurchaseEntity purchaseEntity) {

    // Validation
    if(purchaseEntity == null) {
      return ResponseEntity.badRequest().build();
    }

    PurchaseEntity createdPurchase = purchaseService.save(purchaseEntity);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdPurchase.getId())
            .toUri();
    return ResponseEntity.created(location).body(createdPurchase);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PurchaseEntity> consultPurchaseById(@PathVariable int id) {
    PurchaseEntity purchase = purchaseService.findByPurchaseId(id);
    // Validation
    if(purchase == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(purchase);
  }

  @GetMapping
  public ResponseEntity<List<PurchaseEntity>> consultAllPurchases() {
    List<PurchaseEntity> purchases = purchaseService.findAll();

    // Validation
    if(purchases.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(purchases);
  }
}
