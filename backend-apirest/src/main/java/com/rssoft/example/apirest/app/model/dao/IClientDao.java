package com.rssoft.example.apirest.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rssoft.example.apirest.app.model.entities.Client;
import com.rssoft.example.apirest.app.model.entities.Region;

public interface IClientDao extends JpaRepository<Client, Long> {
	
	@Query("from Region")
	public List<Region> findAllRegions();

}
