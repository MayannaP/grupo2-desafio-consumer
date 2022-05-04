package desafio.grupo2.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import desafio.grupo2.dao.ProdutoDAO;
import desafio.grupo2.models.Produto;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class File {
	
	public static ArrayList<Produto> getProductsFromS3File(String fileName) throws IOException { 
		String bucketName = "grupo2-bucket";
		
		
		AwsCredentialsProvider credentialsProvider = new AwsCredentialsProvider() {
	        @Override
	        public AwsCredentials resolveCredentials() {
	            return new AwsCredentials() {
	                @Override
	                public String accessKeyId() {
	                    return System.getenv("AWS_ACCESS_KEY");
	                }
	    
	                @Override
	                public String secretAccessKey() {
	                    return System.getenv("AWS_SECRET_KEY");
	                }
	            };
	        }
	    };
		
		S3Client client = S3Client.builder()
					.region(Region.US_EAST_1)
					.credentialsProvider(credentialsProvider)
					.build();
		
		GetObjectRequest request = GetObjectRequest.builder()
				.bucket(bucketName)
				.key(fileName)
				.build();
	
		ResponseInputStream<GetObjectResponse> inputStream = client.getObject(request); 
		
		BufferedReader reader = new BufferedReader((new InputStreamReader(inputStream)));
		
		String line;   
		List<Produto> pList = new ArrayList<Produto>(); 
		while ((line = reader.readLine()) != null) {            
			if (!line.contains("id")) { //Para ignorar a primeira linha
				String[] productData = line.split(","); 
				Produto p = new Produto(productData[0], productData[1], 2);
				pList.add(p);
			} 
		}
		reader.close();
		return (ArrayList<Produto>) pList; 
	}	
	
	
	@Autowired
	ProdutoDAO dao;
	
	public ResponseEntity<String> saveProductsToDB(String fileName) throws IOException { 
		
		ArrayList<Produto> p = File.getProductsFromS3File(fileName);
		dao.saveAll(p);
		
		return ResponseEntity.ok("Produto cadastrado no DB.");
	}
}
