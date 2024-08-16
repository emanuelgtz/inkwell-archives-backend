package com.inkwell.archives.service.impl;

import com.inkwell.archives.model.DataSourceEntity;
import com.inkwell.archives.repository.DataSourceRepository;
import com.inkwell.archives.service.interfaces.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataSourceServiceImpl implements DataSourceService {

  private DataSourceRepository dataSourceRepository;

  @Autowired
  public DataSourceServiceImpl(DataSourceRepository dataSourceRepository) {
    this.dataSourceRepository = dataSourceRepository;
  }

  @Override
  public List<DataSourceEntity> findAll() {
    return dataSourceRepository.findAll();
  }

  @Override
  public DataSourceEntity findById(int id) {
    // Ready to face a runtimeException if id is not found
    Optional<DataSourceEntity> result = dataSourceRepository.findById(id);

    DataSourceEntity theDataSourceId = null;

    if(result.isPresent()) {
      theDataSourceId = result.get();
    } else {
      throw new RuntimeException("Finding the requested data source was not possible " + id );
    }
    return theDataSourceId;
  }

  @Override
  public DataSourceEntity findByDataSourceName(String sourceName) {
    Optional<DataSourceEntity> result = dataSourceRepository.findBySourceName(sourceName);

    DataSourceEntity theDataSourceName = null;

    if(result.isPresent()) {
      theDataSourceName = result.get();
    } else {
      throw new RuntimeException("Finding the requested data source name was not possible " + sourceName);
    }
    return theDataSourceName;
  }
}
