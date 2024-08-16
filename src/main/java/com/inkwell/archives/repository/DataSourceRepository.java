package com.inkwell.archives.repository;

import com.inkwell.archives.model.DataSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataSourceRepository extends JpaRepository<DataSourceEntity, Integer> {

  Optional<DataSourceEntity> findBySourceName(String sourceName);

}
