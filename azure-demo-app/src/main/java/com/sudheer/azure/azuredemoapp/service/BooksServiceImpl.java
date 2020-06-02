package com.sudheer.azure.azuredemoapp.service;

import com.sudheer.azure.azuredemoapp.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BooksServiceImpl implements BooksService {

    @Value("${app2.url}")
    public String app2Url;
    @Autowired
    private DataUtil dataUtil;

    @Override
    public String getBooks() {
        return dataUtil.get(app2Url.concat("/get-books"));
    }
}
