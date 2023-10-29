package com.example.hexagonal.application;

import com.example.hexagonal.application.port.in.MacBookManagementInPort;
import com.example.hexagonal.application.port.in.dto.MacBookCreateDto;
import com.example.hexagonal.application.port.in.dto.MacBookDto;
import com.example.hexagonal.application.port.out.MacBookManagementOutPort;
import com.example.hexagonal.domain.entity.MacBook;
import com.example.hexagonal.domain.vo.Battery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MacBookManagementInPortTest {

    @InjectMocks
    private MacBookManagementInPort macBookManagementInPort;

    @Mock
    private MacBookManagementOutPort macBookManagementOutPort;

    @Test
    @DisplayName("맥북 생성하기")
    void create_macbook() {
        // Given
        Battery battery = Battery.builder()
                .chargeStatus(true)
                .code("BBB111")
                .build();
        MacBook macBook = MacBook.builder()
                .name("macBook")
                .code("AAA111")
                .battery(battery)
                .build();

        // When
        when(macBookManagementOutPort.save(any())).thenReturn(Optional.of(macBook));

        MacBookCreateDto createDto = MacBookCreateDto.builder().name("macBookTest").code("AAA222").build();

        MacBookDto resultDto = macBookManagementInPort.createMacBook(createDto);

        // Then
        // Verify that the moduleManagementOutPort.save method was called with the correct argument
        verify(macBookManagementOutPort, times(1)).save(any());

        // Verify that the resultDto is not null
        assertNotNull(resultDto);

        // Add more assertions based on the behavior of your code
        // For example, you can assert specific properties of the resultDto
        assertEquals("macBookTest", resultDto.name());
        assertEquals(true, resultDto.battery().chargeStatus());
    }
}
