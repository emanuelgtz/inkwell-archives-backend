package com.inkwell.archives.service.interfaces;

import com.inkwell.archives.model.DataSourceEntity;

import java.util.List;

public interface DataSourceService {

  // We just added these methods because data source were going to be created manually, because of the amount of available options. We just want to know them, we neither want to delete them nor create them.
  List<DataSourceEntity> findAll();
  DataSourceEntity findById(int id);
  DataSourceEntity findByDataSourceName(String sourceName);
  DataSourceEntity save(DataSourceEntity dataSourceEntity);

}
