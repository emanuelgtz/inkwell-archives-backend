package com.inkwell.archives.controller;

import com.inkwell.archives.model.DataSourceEntity;
import com.inkwell.archives.service.interfaces.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/data-source")
public class DataSourceController {
  private final DataSourceService dataSourceService;

  @Autowired
  public DataSourceController(DataSourceService dataSourceService) {
    this.dataSourceService = dataSourceService;
  }

  // Actions: Create a new data source.
  @PostMapping
  public ResponseEntity<DataSourceEntity> createDataSource(@RequestBody DataSourceEntity dataSourceEntity) {

    // Validation
    if(dataSourceEntity == null) return ResponseEntity.badRequest().build();

    // Actions: Create
    DataSourceEntity ds = dataSourceService.save(dataSourceEntity);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(ds.getId())
            .toUri();
    return ResponseEntity.created(location).body(ds);
  }

}
