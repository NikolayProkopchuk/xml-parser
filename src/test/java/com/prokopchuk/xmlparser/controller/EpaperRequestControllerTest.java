package com.prokopchuk.xmlparser.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import javax.xml.validation.Validator;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.prokopchuk.xmlparser.XmlParserApplication;
import com.prokopchuk.xmlparser.service.EpaperRequestService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(controllers = EpaperRequestController.class)
class EpaperRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EpaperRequestService epaperRequestService;

    @MockBean
    private Validator validator;

    private XmlMapper xmlMapper = Mockito.mock(XmlMapper.class);

    @Test
    void evaluatesPageableParameter() throws Exception {

        mockMvc.perform(get("/epaper")
            .param("page", "5")
            .param("size", "10")
            .param("sort", "id,desc")
            .param("sort", "screenInfo.width,asc"))
          .andExpect(status().isOk());

        ArgumentCaptor<Pageable> pageableCaptor =
          ArgumentCaptor.forClass(Pageable.class);
        verify(epaperRequestService).findAllEpaperRequests(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();

        PageableAssert.assertThat(pageable).hasPageNumber(5)
          .hasPageSize(10)
          .hasSort("screenInfo.width", Sort.Direction.ASC)
          .hasSort("id", Sort.Direction.DESC);
    }

//    @Test
//    void findAllEpaperRequests_whenValidInputReturns200() throws Exception {
//
//        when(epaperRequestService.findAllEpaperRequests(Pageable.unpaged())).thenReturn()
//
//        var mvcResult = mockMvc.perform(get("/epaper"))
//          .andExpect(status().isOk())
//          .andReturn();
//    }
}
