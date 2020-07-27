 package com.rssoft.example.apirest.app.model.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rssoft.example.apirest.app.model.entities.Client;
import com.rssoft.example.apirest.app.model.entities.Region;

public interface IClientService {
	
	public List<Client> findAll();
	
	public Page<Client> findAll(Pageable pageable);
	
	public Client findById(Long id);
	
	public Client save(Client client);
	
	public void delete(Long id);
	
	public List<Region> findAllRegions();

}
