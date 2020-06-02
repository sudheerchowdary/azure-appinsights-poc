package com.sudheer.azure.azuredemoapp1.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sudheer.azure.azuredemoapp1.beans.StoresBean;
import com.sudheer.azure.azuredemoapp1.entity.StoresEntity;
import com.sudheer.azure.azuredemoapp1.repository.StoresRepository;
import com.sudheer.azure.azuredemoapp1.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StoresServiceImpl implements StoresService {

    @Autowired
    private DataUtil dataUtil;
    @Autowired
    private StoresRepository storesRepository;

    @Override
    public List<StoresEntity> getStores() {
        return (List<StoresEntity>) this.storesRepository.findAll();
    }

    @Override
    public Optional<StoresEntity> getStoresById(final int id) {
        return this.storesRepository.findById(id);
    }

    @Override
    public StoresEntity addStore(final StoresEntity storesEntity) {
        return this.storesRepository.save(storesEntity);
    }

    @Override
    public void deleteStore(final Integer storesId) {
        this.storesRepository.deleteById(storesId);
    }

    @Override
    public StoresEntity updateStore(final StoresEntity storesEntity) {
        return this.storesRepository.save(storesEntity);
    }

    public void loadJson(final InputStream input) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final List<StoresBean> beans = Arrays.asList(mapper.readValue(input, StoresBean[].class));
        for (StoresBean bean : beans) {
            final StoresEntity storesEntity = new StoresEntity();
            this.dataUtil.populate(storesEntity, bean);
            this.storesRepository.save(storesEntity);
        }
    }

    @PreDestroy
    private void clean() {
        this.dataUtil = null;
        this.storesRepository = null;
    }
}
