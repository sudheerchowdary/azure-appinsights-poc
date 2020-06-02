package com.sudheer.azure.azuredemoapp1.repository;

import com.sudheer.azure.azuredemoapp1.entity.StoresEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoresRepository extends CrudRepository<StoresEntity, Integer> {
}
