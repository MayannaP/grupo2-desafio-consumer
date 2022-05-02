package desafio.grupo2.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import desafio.grupo2.models.Produto;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class DownloadFile {
	
	public static ResponseInputStream<GetObjectResponse> getFile() throws IOException { 
		String bucketName = "grupo2-bucket";
		String keyName = "Banana-e-maca.csv";
		
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
				.key(keyName)
				.build();
	
		ResponseInputStream<GetObjectResponse> inputStream = client.getObject(request); 
		
		inputStream.close();
		return inputStream;
	}
	

	
	public static Produto readFileAndCreateObject(ResponseInputStream<GetObjectResponse> file) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(file));
		
		String line;            
		Produto p = null; 
		while ((line = reader.readLine()) != null) {            
			if (!line.contains("id")) { //Para ignorar a primeira linha
				String[] productData = line.split(","); 
				p = new Produto(0, productData[0], productData[1], 0, line);
				System.out.println(p.getDescricao());	 
			} 
		}
		return p; 
	}

	
}
