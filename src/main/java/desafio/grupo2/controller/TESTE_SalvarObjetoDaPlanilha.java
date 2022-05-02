package desafio.grupo2.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import desafio.grupo2.models.Produto;
import desafio.grupo2.services.DownloadFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Controller
public class TESTE_SalvarObjetoDaPlanilha {
	
	//@Autowired
	//ProdutoDAO dao;
	
	
	@GetMapping("/s3")
	public String TESTE() throws IOException { 
		
		
		ResponseInputStream<GetObjectResponse> inputStream = DownloadFile.getFile();
		
		Produto p = DownloadFile.readFileAndCreateObject(inputStream);
		//dao.save(p);
		return "Tudo certooo";
	}
}
