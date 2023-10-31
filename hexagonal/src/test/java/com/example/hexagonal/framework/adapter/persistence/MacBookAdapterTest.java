package com.example.hexagonal.framework.adapter.persistence;

import com.example.hexagonal.application.port.in.MacBookManagementInPort;
import com.example.hexagonal.application.port.in.dto.BatteryDto;
import com.example.hexagonal.application.port.in.dto.MacBookCreateDto;
import com.example.hexagonal.application.port.in.dto.MacBookDto;
import com.example.hexagonal.domain.entity.MacBook;
import com.example.hexagonal.domain.vo.Battery;
import com.example.hexagonal.framework.adapter.in.web.MacBookController;
import com.example.hexagonal.framework.adapter.in.web.exception.ObjectValidationException;
import com.example.hexagonal.framework.adapter.in.web.validator.ValidDtoAspect;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentScan(basePackageClasses = { ValidDtoAspect.class } )
@WebMvcTest(MacBookController.class)
public class MacBookAdapterTest {

    @MockBean
    private MacBookManagementInPort macBookManagementInPort;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Autowired
    private ValidDtoAspect sampleAspect;

    final String mackBootName = "macBook";
    final String mackBootCode = "AAA111";
    Battery battery;
    MacBook macBook;
    BatteryDto batteryDto;
    MacBookDto macBookDto;

    @BeforeEach
    public void setup() {
        battery = Battery.builder()
                .chargeStatus(true)
                .code("BBB111")
                .build();
        macBook = MacBook.builder()
                .name(mackBootName)
                .code(mackBootCode)
                .battery(battery)
                .build();
        batteryDto = BatteryDto.builder()
                .chargeStatus(true)
                .build();
        macBookDto = MacBookDto.builder()
                .name(mackBootName)
                .battery(batteryDto)
                .build();
    }

    @Test
    @DisplayName("맥북 생성하기")
    void create_macbook() throws Exception {
        // Given
        MacBookCreateDto createDto = MacBookCreateDto.builder().name(mackBootName).code(mackBootCode).build();
        given(macBookManagementInPort.createMacBook(createDto)).willReturn(macBookDto);

        // Then
        mvc.perform(post("/macbook")
                        .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("맥북 생성하기 - 이름을 작성하지 않은 경우")
    void create_macbook_no_name() {
        // Given
        MacBookCreateDto createDto = MacBookCreateDto.builder().code(mackBootCode).build();
        macBookDto = MacBookDto.builder().battery(batteryDto).build();

        ObjectValidationException result = assertThrows(ObjectValidationException.class, () -> {
            sampleAspect.validation(proceedingJoinPoint, createDto);
        });

        assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(422));
    }

//    @Test
//    @DisplayName("맥북 생성하기 - 코드가 중복되는 경우")
//    void create_macbook_code_already_exists() {
//
//    }
}
