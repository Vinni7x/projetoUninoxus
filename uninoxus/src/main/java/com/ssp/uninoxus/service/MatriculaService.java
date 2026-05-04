package com.ssp.uninoxus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.repositories.MatriculaRepository;

@Service
public class MatriculaService {
	
	@Autowired 
	private MatriculaRepository matriculaRepository;

}
