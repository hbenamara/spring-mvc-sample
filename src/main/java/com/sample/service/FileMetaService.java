package com.sample.service;

import java.util.List;

import com.sample.domain.FileMeta;

/**
 * @author hbenamara
 * The Interface FileMetaService.
 */
public interface FileMetaService {

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the file meta
	 */
	FileMeta findById(long id);

	/**
	 * Find by title.
	 *
	 * @param title the title
	 * @return the file meta
	 */
	FileMeta findByTitle(String title);

	/**
	 * Save.
	 *
	 * @param fileMeta the file meta
	 * @return the file meta
	 */
	FileMeta save(FileMeta fileMeta);

	/**
	 * Update file.
	 *
	 * @param fileMeta the file meta
	 */
	void updateFile(FileMeta fileMeta);

	/**
	 * Delete file by id.
	 *
	 * @param id the id
	 */
	void deleteFileById(long id);

	/**
	 * Find all files.
	 *
	 * @return the list
	 */
	List<FileMeta> findAllFiles();

	/**
	 * Delete all files.
	 */
	void deleteAllFiles();

	/**
	 * Checks if is file exist.
	 *
	 * @param fileMeta the file meta
	 * @return true, if is file exist
	 */
	public boolean isFileExist(FileMeta fileMeta);

}
