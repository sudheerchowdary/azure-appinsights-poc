package com.sudheer.azure.azuredemoapp.controller;

import com.microsoft.applicationinsights.TelemetryClient;
import com.sudheer.azure.azuredemoapp.beans.StoresBean;
import com.sudheer.azure.azuredemoapp.entity.StoresEntity;
import com.sudheer.azure.azuredemoapp.util.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sudheer.azure.azuredemoapp.service.StoresService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class StoreController {

    protected final Logger log = LoggerFactory.getLogger(StoreController.class);

    @Autowired
    TelemetryClient telemetryClient;
    @Autowired
    private DataUtil dataUtil;
    @Autowired
    private StoresService storesService;

    @GetMapping("get-stores")
    public ResponseEntity<List<StoresEntity>> getStores() {
        telemetryClient.trackEvent("app-get-stores");
        List<StoresEntity> storesEntities = null;
        try {
            storesEntities = this.storesService.getStores();
            if (storesEntities != null && !storesEntities.isEmpty())
                return new ResponseEntity<>(storesEntities, HttpStatus.resolve(200));
            else
                log.debug("Data not available ..!");
        } catch (Exception e) {
            log.error("Exception during getStores() !! Ex: {}", e.getLocalizedMessage());
        }
        return new ResponseEntity<>(storesEntities, HttpStatus.resolve(500));
    }

    @GetMapping("get-store/{id}")
    public ResponseEntity<StoresEntity> getStore(@PathVariable("id") final Integer id) {
        telemetryClient.trackEvent("app-get-store for id");
        if (id != null) {
            try {
                final Optional<StoresEntity> entity = this.storesService.getStoresById(id);
                if (entity.isPresent())
                    return new ResponseEntity(entity, HttpStatus.resolve(200));
                return new ResponseEntity("Entry not found !!", HttpStatus.resolve(500));
            } catch (Exception e) {
                log.error("Exception during get-store for id. Ex: {}", e.getLocalizedMessage());
                return new ResponseEntity("Problem while get-store for id. Ex: " + e.getLocalizedMessage(), HttpStatus.resolve(500));
            }
        } else
            log.debug("Provided input null/empty ..!");
        return new ResponseEntity("Provides id empty/null. Please verify the input provided!!.", HttpStatus.resolve(500));
    }

    @PostMapping("add-store")
    public ResponseEntity<String> addStore(@RequestBody final StoresBean storesBean) {
        telemetryClient.trackEvent("app-add-store");
        if (storesBean != null) {
            try {
                final StoresEntity storesEntity = new StoresEntity();
                this.dataUtil.populate(storesEntity, storesBean);
                this.storesService.addStore(storesEntity);
                return new ResponseEntity<>("Store added successfully !!", HttpStatus.resolve(200));
            } catch (Exception e) {
                log.error("Exception during add-store. Ex: {}", e.getLocalizedMessage());
                return new ResponseEntity<>("Problem while persisting store. Ex: " + e.getLocalizedMessage(), HttpStatus.resolve(500));
            }
        } else
            log.debug("Provided input null/empty ..!");
        return new ResponseEntity<>("Store details are empty/null. Please verify the input provided!!.", HttpStatus.resolve(500));
    }

    @DeleteMapping("delete-store/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable("id") final Integer id) {
        telemetryClient.trackEvent("app-delete-store for id");
        if (id != null) {
            try {
                this.storesService.deleteStore(id);
                return new ResponseEntity<>("Store deleted successfully !!", HttpStatus.resolve(200));
            } catch (Exception e) {
                log.error("Exception during delete-store. Ex: {}", e.getLocalizedMessage());
                return new ResponseEntity<>("Problem while deleting store. Ex: " + e.getLocalizedMessage(), HttpStatus.resolve(500));
            }
        } else
            log.debug("Provided input null/empty ..!");
        return new ResponseEntity<>("Store details are empty/null. Please verify the input provided!!.", HttpStatus.resolve(500));
    }

    @PutMapping(value = "update-store")
    public ResponseEntity<String> updateStore(@RequestBody final StoresBean storesBean) {
        telemetryClient.trackEvent("app-update-store");
        if (storesBean != null) {
            try {
                final Optional<StoresEntity> entity = this.storesService.getStoresById(storesBean.getId());
                if (entity.isPresent()) {
                    this.dataUtil.populate(entity.get(), storesBean);
                    this.storesService.updateStore(entity.get());
                    return new ResponseEntity<>("Store added successfully !!", HttpStatus.resolve(200));
                } else {
                    log.debug("Entry already present with id: {}", storesBean.getId());
                    return new ResponseEntity<>("Entry already present with id: " + storesBean.getId() + ", please change and try again!!", HttpStatus.resolve(500));
                }
            } catch (Exception e) {
                log.error("Exception during add-store. Ex: {}", e.getLocalizedMessage());
                return new ResponseEntity<>("Problem while persisting store. Ex: " + e.getLocalizedMessage(), HttpStatus.resolve(500));
            }
        } else
            log.debug("Provided input null/empty ..!");
        return new ResponseEntity<>("Store details are empty/null. Please verify the input provided!!.", HttpStatus.resolve(500));
    }

    private void close() {
        this.dataUtil = null;
        this.storesService = null;
    }
}
