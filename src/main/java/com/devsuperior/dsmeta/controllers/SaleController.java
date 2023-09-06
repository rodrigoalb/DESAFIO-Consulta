package com.devsuperior.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<?> getReport() {
		// TODO
		return null;
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleMinDTO>> getSummary(
			@RequestParam(name = "minDate") String minDate,
			@RequestParam(name = "maxDate") String maxDate,
			@RequestParam(name = "name") String name,
			Pageable pageable) {

		Page<SaleMinDTO> dto = service.searchPagedSalesWithInitialDateFinalDateAnSellerPartialName(
				minDate, maxDate, name, pageable);

		return ResponseEntity.ok(dto);
	}
}
