package com.invillia.acme.store.controllers;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.store.domain.Store;
import com.invillia.acme.store.dto.StoreDTO;
import com.invillia.acme.store.exception.BadRequestException;
import com.invillia.acme.store.service.StoreService;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * Controller do microservice de Store
 * 
 * @author erick.oliveira
 *
 */
@RestController
@RequestMapping("/stores")
public class StoreController {
	
	private final StoreService storeService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}

	/**
	 * Consultar Store por parametros
	 * 
	 * @param FilterStoreDTO
	 * @return ResponseEntity
	 */
	@GetMapping("/find")
	@ApiOperation(notes = "Recuperar uma Store por parametros", value = "Filter", response = ResponseEntity.class)
    @Secured("ROLE_ADMIN")
	public ResponseEntity<List<StoreDTO>> getStore(@RequestParam(required = false) String nome,
			@RequestParam(required = false) String endereco) {
		return ResponseEntity.status(HttpStatus.OK).body(storeService.findStore(nome,endereco).stream()
				.map(this::toDTO).collect(Collectors.toList()));	}

	/**
	 * Metodo responsavel por salvar uma Store
	 * 
	 * @param StoreDTO
	 * @return ResponseEntity
	 */
	@PostMapping("/save")
	@ApiOperation(notes = "Salvar os dados de uma Store", value = "Store", response = ResponseEntity.class)
    @Secured("ROLE_ADMIN")
	public ResponseEntity<StoreDTO> save(@Validated @RequestBody StoreDTO dto) {
		Store store = modelMapper.map(dto, Store.class);
		store = storeService.save(store);
		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(store, StoreDTO.class));
	}

	/**
	 * Metodo responsavel por atualizar uma Store
	 * 
	 * @param StoreDTO
	 * @return ResponseEntity
	 */
	@PutMapping("/update/{id}")
	@ApiOperation(notes = "Atualiar os dados de uma Store", value = "Store", response = ResponseEntity.class)
    @Secured("ROLE_ADMIN")
	public ResponseEntity<StoreDTO> update(@Validated @RequestBody StoreDTO dto,@PathVariable Long id)
			throws URISyntaxException {
		if (id == null) {
			throw new BadRequestException("Informe o id");
		}
		Store store = modelMapper.map(dto, Store.class);
		store = storeService.update(store,id);
		return ResponseEntity.ok().body(modelMapper.map(store, StoreDTO.class));
	}

	/**
	 * Metodo responsavel por fazer o parse de Store para StoreDTO
	 * 
	 * @param store
	 * @return
	 */
	public StoreDTO toDTO(Store store) {
		if (store == null) {
			return null;
		} else {
			StoreDTO storeDTO = StoreDTO.builder().build();
			storeDTO = modelMapper.map(store, StoreDTO.class);
			return storeDTO;
		}
	}
}
