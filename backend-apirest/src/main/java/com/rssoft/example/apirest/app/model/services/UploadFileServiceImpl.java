package com.rssoft.example.apirest.app.model.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {
	
	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	
	private final static String DIR_UPLOAD = "uploads";

	@Override
	public Resource load(String name) throws MalformedURLException {
		Path route = getPath(name);
		Resource resource = new UrlResource(route.toUri());
		
		if(!resource.exists() && !resource.isReadable()) {
			route = Paths.get("src/main/resources/static/img").resolve("person.png").toAbsolutePath();
			resource = new UrlResource(route.toUri());
			log.error("No se pudo cargar la imagen");
		}
		
		return resource;
	}

	@Override
	public String save(MultipartFile file) throws IOException {
		String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
		Path route = getPath(filename);
		log.info(route.toString());
		Files.copy(file.getInputStream(), route);
		return filename;
	}

	@Override
	public boolean delete(String name) {
		if(name != null && name.length() > 0) {
			Path routeExist = Paths.get("uploads").resolve(name).toAbsolutePath();
			File f = routeExist.toFile();
			if(f.exists() && f.canRead()) {
				f.delete();
				return true;
			}
		}
		return false;
	}

	@Override
	public Path getPath(String name) {
		return Paths.get(DIR_UPLOAD).resolve(name).toAbsolutePath();
	}

}
