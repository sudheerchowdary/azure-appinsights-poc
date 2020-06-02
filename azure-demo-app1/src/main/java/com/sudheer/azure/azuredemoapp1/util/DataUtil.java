package com.sudheer.azure.azuredemoapp1.util;

import com.google.gson.Gson;
import com.sudheer.azure.azuredemoapp1.annotation.Util;
import com.sudheer.azure.azuredemoapp1.beans.StoresBean;
import com.sudheer.azure.azuredemoapp1.entity.StoresEntity;
import org.springframework.beans.BeanUtils;

@Util
public class DataUtil {

    public void populate(final StoresEntity entity, final StoresBean storesBean) {
        BeanUtils.copyProperties(storesBean, entity, "id");
        entity.setLocation(new Gson().toJson(storesBean.getLocation().toString().replaceAll("\\\\", "")));
        entity.setServices(new Gson().toJson(storesBean.getServices()));
    }
}
