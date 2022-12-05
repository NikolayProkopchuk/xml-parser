package com.prokopchuk.xmlparser.controller;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import java.io.IOException;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.prokopchuk.xmlparser.domain.EpaperRequest;
import com.prokopchuk.xmlparser.persistence.entity.EpaperRequestEntity;
import com.prokopchuk.xmlparser.service.EpaperRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

@RestController
@RequestMapping("/epaper")
@Slf4j
@RequiredArgsConstructor
public class EpaperRequestController {

    private final EpaperRequestService epaperRequestService;

    private final Validator validator;

    @PostMapping
    public EpaperRequestEntity uploadXml(@RequestParam("file") MultipartFile file) throws IOException, SAXException {
        Source xmlFile = new StreamSource(file.getInputStream());
        validator.validate(xmlFile);

        XmlMapper xmlMapper = new XmlMapper();

        var epaperRequest = xmlMapper.readValue(file.getInputStream(), EpaperRequest.class);
        return epaperRequestService.saveEpaperRequest(epaperRequest, file.getOriginalFilename());
    }

    @GetMapping
    public Page<EpaperRequestEntity> findAllEpaperRequests(Pageable pageable) {
        return epaperRequestService.findAllEpaperRequests(pageable);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleParseException(SAXException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
