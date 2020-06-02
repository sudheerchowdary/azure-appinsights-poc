package com.sudheer.azure.azuredemoapp2;

import com.microsoft.applicationinsights.TelemetryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BookController {
    @Autowired
    TelemetryClient telemetryClient;

    @GetMapping("get-books")
    public ResponseEntity<String> getBooks() {
        telemetryClient.trackEvent("app2-get-books");
        return new ResponseEntity<>("[{\"id\":1,\"book\":\"Book1\"}, {\"id\":2,\"book\":\"Book2\"}]", HttpStatus.resolve(200));
    }
}
