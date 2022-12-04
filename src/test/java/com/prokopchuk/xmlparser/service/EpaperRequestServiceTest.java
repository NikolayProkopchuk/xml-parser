package com.prokopchuk.xmlparser.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.prokopchuk.xmlparser.domain.EpaperRequest;
import com.prokopchuk.xmlparser.persistence.entity.AppInfo;
import com.prokopchuk.xmlparser.persistence.entity.EpaperRequestEntity;
import com.prokopchuk.xmlparser.persistence.entity.ScreenInfo;
import com.prokopchuk.xmlparser.persistence.repo.EpaperRequestRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EpaperRequestServiceTest {

    private final EpaperRequestRepo epaperRequestRepo = Mockito.mock(EpaperRequestRepo.class);

    private final EpaperRequestService epaperRequestService = new EpaperRequestServiceImpl(epaperRequestRepo);

    @Test
    void saveEpaperRequest_shouldSaveAndReturnCorrectEntity() {
        EpaperRequest epaperRequest = prepareEpaperRequest();

        when(epaperRequestRepo.save(any())).thenReturn(prepareEntity());

        String fileName = "fileName";

        EpaperRequestEntity epaperRequestEntity = epaperRequestService.saveEpaperRequest(epaperRequest, fileName);

        Assertions.assertEquals(fileName, epaperRequestEntity.getFileName());
        Assertions.assertEquals(epaperRequest.getDeviceInfo().getAppInfo().getNewspaperName(),
          epaperRequestEntity.getAppInfo().getNewspaperName());
        Assertions.assertEquals(epaperRequest.getDeviceInfo().getScreenInfo().getDpi(),
          epaperRequestEntity.getScreenInfo().getDpi());
        Assertions.assertEquals(epaperRequest.getDeviceInfo().getScreenInfo().getHeight(),
          epaperRequestEntity.getScreenInfo().getHeight());
        Assertions.assertEquals(epaperRequest.getDeviceInfo().getScreenInfo().getWidth(),
          epaperRequestEntity.getScreenInfo().getWidth());
    }

    private EpaperRequest prepareEpaperRequest() {
        EpaperRequest epaperRequest = new EpaperRequest();

        EpaperRequest.DeviceInfo deviceInfo = new EpaperRequest.DeviceInfo();

        EpaperRequest.DeviceInfo.AppInfo appInfo = new EpaperRequest.DeviceInfo.AppInfo();
        appInfo.setNewspaperName("newspaperName");

        EpaperRequest.DeviceInfo.ScreenInfo screenInfo = new EpaperRequest.DeviceInfo.ScreenInfo();

        screenInfo.setDpi(10);
        screenInfo.setHeight(20);
        screenInfo.setWidth(30);

        deviceInfo.setAppInfo(appInfo);
        deviceInfo.setScreenInfo(screenInfo);
        epaperRequest.setDeviceInfo(deviceInfo);

        return epaperRequest;
    }

    private EpaperRequestEntity prepareEntity() {
        long sharedId = 1L;
        EpaperRequestEntity epaperRequestEntity = new EpaperRequestEntity();
        epaperRequestEntity.setId(sharedId);
        epaperRequestEntity.setFileName("fileName");

        AppInfo appInfoEntity = new AppInfo();
        appInfoEntity.setId(sharedId);
        appInfoEntity.setNewspaperName("newspaperName");

        ScreenInfo screenInfoEntity = new ScreenInfo();
        screenInfoEntity.setId(sharedId);
        screenInfoEntity.setDpi(10);
        screenInfoEntity.setHeight(20);
        screenInfoEntity.setWidth(30);

        epaperRequestEntity.setAppInfo(appInfoEntity);
        epaperRequestEntity.setScreenInfo(screenInfoEntity);

        return epaperRequestEntity;
    }

}
