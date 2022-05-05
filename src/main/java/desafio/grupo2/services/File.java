package desafio.grupo2.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import desafio.grupo2.dao.ProdutoDAO;
import desafio.grupo2.models.Produto;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Component
public class File {
	
    @Autowired
	private ProdutoDAO dao;
	
	public ArrayList<Produto> getProductsFromS3File(String fileName) throws IOException { 
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
			if (!line.contains("Nome")) { 
				String[] productData = line.split(","); 
				Double preco = Double.parseDouble(productData[3]);
				Integer quantity = Integer.parseInt(productData[2]);
				
				Long datetime = System.currentTimeMillis();
				Timestamp timestamp = new Timestamp(datetime);
				Produto p = new Produto(productData[0], productData[1], quantity, preco, timestamp, 1);
				pList.add(p);
			} 
		}
		reader.close();
		this.dao.saveAll(pList);
		return (ArrayList<Produto>) pList; 
	}	
}