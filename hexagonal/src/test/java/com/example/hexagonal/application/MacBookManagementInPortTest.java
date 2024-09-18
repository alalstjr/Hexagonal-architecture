package com.example.hexagonal.application;

import com.example.hexagonal.application.exception.CommonServiceException;
import com.example.hexagonal.application.port.in.MacBookManagementInPort;
import com.example.hexagonal.application.port.in.dto.MacBookCreateDto;
import com.example.hexagonal.application.port.in.dto.MacBookDto;
import com.example.hexagonal.application.port.out.MacBookManagementOutPort;
import com.example.hexagonal.domain.MacBook;
import com.example.hexagonal.domain.Battery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MacBookManagementInPortTest {

    @InjectMocks
    private MacBookManagementInPort macBookManagementInPort;

    @Mock
    private MacBookManagementOutPort macBookManagementOutPort;

    final String mackBootName = "macBook";
    final String mackBootCode = "AAA111";
    Battery battery;
    MacBook macBook;

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
    }

    @Test
    @DisplayName("맥북 생성하기")
    void create_macbook() {
        // Given
        String mackBootName = "macBook";
        Battery battery = Battery.builder()
                .chargeStatus(true)
                .code("BBB111")
                .build();
        MacBook macBook = MacBook.builder()
                .name(mackBootName)
                .code("AAA111")
                .battery(battery)
                .build();

        // When
        // Mock 데이터에 만약의 데이터를 저장하는 것입니다.
        // 만약 outPort 무언가를 저장한다면 어떠한 결과를 반환한다. 이는 DB 연결이 안되어 있어서 가상의 데이터를 넣는것
        when(macBookManagementOutPort.save(any())).thenReturn(Optional.of(macBook));

        MacBookCreateDto createDto = MacBookCreateDto.builder().name(mackBootName).code(mackBootCode).build();
        MacBookDto resultDto = macBookManagementInPort.createMacBook(createDto);

        // Then
        // Verify that the moduleManagementOutPort.save method was called with the correct argument
        verify(macBookManagementOutPort, times(1)).save(any());

        // Verify that the resultDto is not null
        assertNotNull(resultDto);

        // Add more assertions based on the behavior of your code
        // For example, you can assert specific properties of the resultDto
        assertEquals(mackBootName, resultDto.name());
        assertEquals(true, resultDto.battery().chargeStatus());
    }

    @Test
    @DisplayName("맥북 생성하기 - 코드가 중복되는 경우")
    void create_macbook_code_already_exists() {
        // Given - precondition or setup
        // 만약 서버에서 코드를 조건으로 맥북을 조회한다면 결과로 맥북을 반환한다.
        // 이는 이미 코드로 저장된 맥북이 있는지 확인하려고 하는 역할이다.
        given(macBookManagementOutPort.findByCode(macBook.getCode())).willReturn(Optional.of(macBook));

        // When -  action or the behaviour that we are going test
        // given 에서 이미 오류가 발생될 수 있도록 설정하였으므로 오류가 발생했을 때 과정을 추가한다.
        // 만약 MacBookCreateDto 의 코드값이 달라지면 생성이 되므로 테스트 통과가 안됩니다.
        CommonServiceException result = assertThrows(CommonServiceException.class, () -> {
            MacBookCreateDto createDto = MacBookCreateDto.builder().name(mackBootName).code(mackBootCode).build();
            macBookManagementInPort.createMacBook(createDto);
        });

        // Then
        // 코드가 중복되는 경우 409 오류를 반환하는지 확인
        assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(409));
    }
}
