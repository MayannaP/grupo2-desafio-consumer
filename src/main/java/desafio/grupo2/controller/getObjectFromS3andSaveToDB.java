package desafio.grupo2.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import desafio.grupo2.dao.ProdutoDAO;
import desafio.grupo2.models.Produto;
import desafio.grupo2.services.File;

@Controller
public class getObjectFromS3andSaveToDB {
	
	@Autowired
	ProdutoDAO dao;
	
	
	@GetMapping("/s3")
	public ResponseEntity<String> saveProductsToDB() throws IOException { 
		
		ArrayList<Produto> p = File.getProductsFromS3File("Banana_e_pera.csv");
		dao.saveAll(p);
		
		return ResponseEntity.ok("Produto cadastrado no DB.");
	}
}
