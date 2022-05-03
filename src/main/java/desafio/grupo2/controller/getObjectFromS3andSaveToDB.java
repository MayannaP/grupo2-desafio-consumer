package desafio.grupo2.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import desafio.grupo2.dao.ProdutoDAO;
import desafio.grupo2.models.Produto;
import desafio.grupo2.services.DownloadFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Controller
public class getObjectFromS3andSaveToDB {
	
	@Autowired
	ProdutoDAO dao;
	
	
	@GetMapping("/s3")
	public String teste() throws IOException { 
		
		ResponseInputStream<GetObjectResponse> inputStream = DownloadFile.getFile("Banana-e-maca.csv");
		ArrayList<Produto> p = DownloadFile.readFileAndCreateObject(inputStream);
		dao.saveAll(p);
		
		return "suave na nave";
	}
}
