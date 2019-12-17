package com.chico.cursomc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.chico.cursomc.services.exceptions.FileException;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multiPartFile) {
		try {
			String fileName = multiPartFile.getOriginalFilename();
			InputStream is = multiPartFile.getInputStream();
			String contentType = multiPartFile.getContentType();
			return uploadFile(is, fileName, contentType);
			
		} catch (IOException e) {
			throw new FileException("Erro de IO: " + e.getMessage());
		}
		
	}

	public URI uploadFile(InputStream is, String fileName, String contentType) {

		try {
			
			ObjectMetadata metaData = new ObjectMetadata();
			metaData.setContentType(contentType);
			LOG.info("Iniciando Upload");
			s3client.putObject(bucketName, fileName, is, metaData);
			LOG.info("Upload Finalizado");

			return s3client.getUrl(bucketName, fileName).toURI();
			
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URL para URI");
		}

	}

}
