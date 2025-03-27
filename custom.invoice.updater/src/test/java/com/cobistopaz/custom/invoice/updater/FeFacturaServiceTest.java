package com.cobistopaz.custom.invoice.updater;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cobistopaz.custom.invoice.updater.repositories.IFeFacturaRepository;
import com.cobistopaz.custom.invoice.updater.service.FeFacturaService;

@ExtendWith(MockitoExtension.class)
public class FeFacturaServiceTest {
	  @Mock
	    private IFeFacturaRepository feFacturaRepository;

	    @InjectMocks
	    private FeFacturaService feFacturaService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testActualizarFactura() {
	        when(feFacturaRepository.actualizarFactura("003", 10)).thenReturn(1);
	        feFacturaService.actualizarFactura("003", 10);
	        verify(feFacturaRepository, times(1)).actualizarFactura("003", 10);
	    }
}