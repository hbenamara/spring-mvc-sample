package com.sample.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sample.domain.FileMeta;
import com.sample.service.FileMetaService;

/**
 * @author hbenamara
 * The Class FileMetaController.
 */
@Controller
public class FileMetaController {

	/** The env. */
	@Autowired
	private Environment env;

	/** The file service. */
	@Autowired
	FileMetaService fileService;

	/**
	 * List all files.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/file/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FileMeta>> listAllFiles() {
		List<FileMeta> fileMetas = fileService.findAllFiles();
		if (fileMetas.isEmpty()) {
			return new ResponseEntity<List<FileMeta>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FileMeta>>(fileMetas, HttpStatus.OK);
	}

	/**
	 * The Class EmptyJsonResponse.
	 */
	@JsonSerialize
	public class EmptyJsonResponse {
	}

	/**
	 * Upload file.
	 *
	 * @param files the files
	 * @return the response entity
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/uploadFile/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("kartik-input-700[]") MultipartFile[] files) {
		try {
			for (MultipartFile multipart : files) {
				BufferedOutputStream stream = null;
				String filename = multipart.getOriginalFilename();
				String directory = env.getProperty("dotsub.paths.uploadedFiles");
				String filepath = Paths.get(directory, filename).toString();
				File file = new File(filepath);
				stream = new BufferedOutputStream(new FileOutputStream(file));
				stream.write(multipart.getBytes());
				stream.close();
				saveFileMetaData(multipart);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
	}

	/**
	 * Save file meta data.
	 *
	 * @param multipart the multipart
	 */
	private void saveFileMetaData(MultipartFile multipart) {
		try {
			String filename = multipart.getOriginalFilename();
			String directory = env.getProperty("dotsub.paths.uploadedFiles");
			String filepath = Paths.get(directory, filename).toString();
			File file = new File(filepath);
			BasicFileAttributes attrs = Files.readAttributes(Paths.get(file.getAbsolutePath()),
					BasicFileAttributes.class);
			FileMeta fileMetaData = new FileMeta();
			fileMetaData.setTitle(file.getName());
			fileMetaData.setCreationDate(new Date(attrs.creationTime().to(TimeUnit.MILLISECONDS)));
			fileMetaData.setDescription(multipart.getContentType());
			fileMetaData.setDownloadLink(file.getAbsolutePath());

			FileMeta inserted = fileService.save(fileMetaData);
			System.out.println(inserted.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Download file.
	 *
	 * @param fileId the file id
	 * @return the response entity
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@RequestMapping(value = "/file/{fileId}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> downloadFile(@PathVariable int fileId) throws IOException {

		FileMeta fileMeta = fileService.findById(fileId);
		File file = new File(fileMeta.getDownloadLink());

		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.parseMediaType(fileMeta.getDescription()));
		respHeaders.setContentDispositionFormData("attachment", "fileNameIwant.pdf");

		InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
		return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
	}
}
