package com.amby.rest.controller;

import com.amby.rest.entity.Owner;
import com.amby.rest.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collection;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/owners")
public class OwnerRestController {

	@Autowired
	private ClinicService clinicService;

	//@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/*/lastname/{lastName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Owner>> getOwnersList(@PathVariable("lastName") String ownerLastName) {
		if (ownerLastName == null) {
			ownerLastName = "";
		}
		Collection<Owner> owners = this.clinicService.findOwnerByLastName(ownerLastName);
		if (owners.isEmpty()) {
			return new ResponseEntity<Collection<Owner>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Owner>>(owners, HttpStatus.OK);
	}

    //@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Owner>> getOwners() {
		Collection<Owner> owners = this.clinicService.findAllOwners();
		if (owners.isEmpty()) {
			return new ResponseEntity<Collection<Owner>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Owner>>(owners, HttpStatus.OK);
	}

    //@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{ownerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Owner> getOwner(@PathVariable("ownerId") int ownerId) {
		Owner owner = null;
		owner = this.clinicService.findOwnerById(ownerId);
		if (owner == null) {
			return new ResponseEntity<Owner>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Owner>(owner, HttpStatus.OK);
	}

    //@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Owner> addOwner(@RequestBody @Valid Owner owner, BindingResult bindingResult,
                                          UriComponentsBuilder ucBuilder) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (owner == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Owner>(headers, HttpStatus.BAD_REQUEST);
		}
		this.clinicService.saveOwner(owner);
		headers.setLocation(ucBuilder.path("/api/owners/{id}").buildAndExpand(owner.getId()).toUri());
		return new ResponseEntity<Owner>(owner, headers, HttpStatus.CREATED);
	}

    //@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{ownerId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Owner> updateOwner(@PathVariable("ownerId") int ownerId, @RequestBody @Valid Owner owner,
                                             BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (owner == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Owner>(headers, HttpStatus.BAD_REQUEST);
		}
		Owner currentOwner = this.clinicService.findOwnerById(ownerId);
		if (currentOwner == null) {
			return new ResponseEntity<Owner>(HttpStatus.NOT_FOUND);
		}
		currentOwner.setAddress(owner.getAddress());
		currentOwner.setCity(owner.getCity());
		currentOwner.setFirstName(owner.getFirstName());
		currentOwner.setLastName(owner.getLastName());
		currentOwner.setTelephone(owner.getTelephone());
		this.clinicService.saveOwner(currentOwner);
		return new ResponseEntity<Owner>(currentOwner, HttpStatus.NO_CONTENT);
	}

    //@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{ownerId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Transactional
	public ResponseEntity<Void> deleteOwner(@PathVariable("ownerId") int ownerId) {
		Owner owner = this.clinicService.findOwnerById(ownerId);
		if (owner == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		this.clinicService.deleteOwner(owner);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
