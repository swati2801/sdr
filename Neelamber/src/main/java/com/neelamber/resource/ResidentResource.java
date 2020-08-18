package com.neelamber.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.neelamber.bean.Resident;
import com.neelamber.bean.ResidentDTO;
import com.neelamber.service.ResidentService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@OpenAPIDefinition(info = @Info(title = "Neelamber Apartment", version = "1.0.0", description = "Software to track monthly subscription of all the residents."))
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:5000", "http://localhost:8000", "https://sdrgoldindia.netlify.app/" })
@RestController
@RequestMapping("/resident")
@Tag(name = "Resident create, update, delete rest endpoints")
public class ResidentResource {
	
	@Autowired
	private ResidentService service;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ResidentResource.class);
	
	@GetMapping(path = "hello")
	public void hello() {		
		LOGGER.info("Hello Swati..!! Yooo!..!! This is test method..!!");
	}

	@Operation(summary = "List All residents", description = "This API gets all the residents record availible in DB", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")) })
	@GetMapping(path = "allResidents")
	public List<Resident> listProducts() {
		return service.getAllProducts();
	}

	
	@Operation(summary = "List Resident by Flat No", description = "This API gets all the resident details by flat No", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")) })
	@GetMapping(path = "residentByFlatNo/{flatNo}")
	public ResidentDTO getResidentByFlatNo(@NotNull @Parameter(description = "Enter Flat No", required = true) @Valid @PathVariable String flatNo) {
		return service.getResidentByFlatNo(flatNo);
	}
	
	@Operation(summary = "Add Resident to DB", description = "This API adds Resident into the DB", responses = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json")) })
	@PostMapping(path = "/addResident", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createResident(
			@RequestBody ResidentDTO resident) {
		Resident newResident = service.addResident(new Resident(resident.getName(), resident.getFlatNo(), resident.getTelephone1(), resident.getTelephone2(), resident.getCarNo(), resident.getEmail()));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newResident.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@Operation(summary = "Update Resident", description = "This API updats Resident into the DB", responses = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json")) })
	@PutMapping(path = "/updateResident/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Resident updateResident(
			@RequestBody ResidentDTO resident, @PathVariable Integer id) {
		return service.updateResident(id, resident);
	}
	
}
