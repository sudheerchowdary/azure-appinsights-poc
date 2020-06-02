package com.sudheer.azure.azuredemoapp.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sudheer.azure.azuredemoapp.beans.StoresBean;
import com.sudheer.azure.azuredemoapp.entity.StoresEntity;
import com.sudheer.azure.azuredemoapp.repository.StoresRepository;
import com.sudheer.azure.azuredemoapp.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StoresServiceImpl implements StoresService {

    @Value("${app1.url}")
    public String app1Url;
    @Autowired
    private DataUtil dataUtil;
    @Autowired
    private StoresRepository storesRepository;

    @Override
    public List<StoresEntity> getStores() {
        return new Gson().fromJson(dataUtil.get(app1Url.concat("/store/get-stores")), List.class);
        //return (List<StoresEntity>) this.storesRepository.findAll();
    }

    @Override
    public Optional<StoresEntity> getStoresById(final int id) {
        return new Gson().fromJson(dataUtil.get(app1Url.concat("/store/get-store/" + id)), (Type) StoresEntity.class);
        // return this.storesRepository.findById(id);
    }

    @Override
    public StoresEntity addStore(final StoresEntity storesEntity) throws IOException, InterruptedException {
        return new Gson().fromJson(dataUtil.post(app1Url.concat("/add-store"), new Gson().toJson(storesEntity)), StoresEntity.class);
        //return this.storesRepository.save(storesEntity);
    }

    @Override
    public void deleteStore(final Integer storesId) {
        dataUtil.delete(app1Url.concat("/store/delete-store/") + storesId);
        //this.storesRepository.deleteById(storesId);
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
