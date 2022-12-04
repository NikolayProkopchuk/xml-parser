package com.prokopchuk.xmlparser.service;

import com.prokopchuk.xmlparser.domain.EpaperRequest;
import com.prokopchuk.xmlparser.persistence.entity.EpaperRequestEntity;
import com.prokopchuk.xmlparser.persistence.entity.AppInfo;
import com.prokopchuk.xmlparser.persistence.entity.ScreenInfo;
import com.prokopchuk.xmlparser.persistence.repo.EpaperRequestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EpaperRequestServiceImpl implements EpaperRequestService {

    private final EpaperRequestRepo epaperRequestRepo;

    @Override
    @Transactional
    public EpaperRequestEntity saveEpaperRequest(EpaperRequest epaperRequest, String fileName) {
        var epaperRequestEntity = new EpaperRequestEntity();
        epaperRequestEntity.setFileName(fileName);

        var newspaper = new AppInfo();
        newspaper.setNewspaperName(epaperRequest.getDeviceInfo().getAppInfo().getNewspaperName());
        newspaper.setEpaperRequestEntity(epaperRequestEntity);

        var screenInfo = new ScreenInfo();
        screenInfo.setWidth(epaperRequest.getDeviceInfo().getScreenInfo().getWidth());
        screenInfo.setHeight(epaperRequest.getDeviceInfo().getScreenInfo().getHeight());
        screenInfo.setDpi(epaperRequest.getDeviceInfo().getScreenInfo().getDpi());
        screenInfo.setEpaperRequestEntity(epaperRequestEntity);

        epaperRequestEntity.setAppInfo(newspaper);
        epaperRequestEntity.setScreenInfo(screenInfo);

        return epaperRequestRepo.save(epaperRequestEntity);
    }

    @Override
    public Page<EpaperRequestEntity> findAllEpaperRequests(Pageable pageable) {
        return epaperRequestRepo.findAll(pageable);
    }
}
