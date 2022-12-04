package com.prokopchuk.xmlparser.persistence.repo;

import com.prokopchuk.xmlparser.persistence.entity.EpaperRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EpaperRequestRepo extends PagingAndSortingRepository<EpaperRequestEntity, Long>, JpaRepository<EpaperRequestEntity, Long> {
}
