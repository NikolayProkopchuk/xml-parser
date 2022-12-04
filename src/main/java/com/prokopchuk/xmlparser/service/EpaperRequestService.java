package com.prokopchuk.xmlparser.service;

import com.prokopchuk.xmlparser.domain.EpaperRequest;
import com.prokopchuk.xmlparser.persistence.entity.EpaperRequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EpaperRequestService {

    EpaperRequestEntity saveEpaperRequest(EpaperRequest epaperRequest, String fileName);

    Page<EpaperRequestEntity> findAllEpaperRequests(Pageable pageable);
}
