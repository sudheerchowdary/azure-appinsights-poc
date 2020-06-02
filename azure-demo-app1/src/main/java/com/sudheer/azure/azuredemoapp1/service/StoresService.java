package com.sudheer.azure.azuredemoapp1.service;

import com.sudheer.azure.azuredemoapp1.entity.StoresEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public interface StoresService {

    /**
     * getStores() will be used to render all stores.
     * @return java.util.List of stores will be returned.
     */
    public List<StoresEntity> getStores();

    /**
     * getStoresById api will be used to get store based on store id.
     * @param id java.lang.{@link Integer} accepts integer value as an input
     * @return {@link StoresEntity} returns store for the given input
     */
    public Optional<StoresEntity> getStoresById(int id);

    /**
     * addStore api will be used to add store for provided input.
     * @param storesEntity {@link StoresEntity} accepts StoresEntity value as an input
     * @return {@link StoresEntity} returns store for the given input
     */
    public StoresEntity addStore(final StoresEntity storesEntity);

    /**
     * deleteStore api will be used to delete store based on store id.
     * @param storesId java.lang.{@link Integer} accepts integer value as an input
     */
    public void deleteStore(final Integer storesId);

    /**
     * updateStore api will be used to update existing store for provided input.
     * @param storesEntity {@link StoresEntity} accepts StoresEntity value as an input
     * @return {@link StoresEntity} returns store for the given input
     */
    public StoresEntity updateStore(final StoresEntity storesEntity);

    /**
     * loadJson api will be used to load stores in bulk
     * @param input {@link InputStream} accepts list of store contains in a JSON
     * @return {@link StoresEntity} returns store for the given input
     */
    public void loadJson(final InputStream input) throws IOException;
}
