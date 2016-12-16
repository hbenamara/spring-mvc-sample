package com.sample.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.domain.FileMeta;
import com.sample.repository.FileMetaRepository;

/**
 * @author hbenamara
 * The Class FileMetaServiceImpl.
 */
@Service("fileService")
public class FileMetaServiceImpl implements FileMetaService {

	/** The file metas. */
	private static List<FileMeta> fileMetas;

	/** The file meta repository. */
	@Autowired
	private FileMetaRepository fileMetaRepository;

	/* (non-Javadoc)
	 * @see com.sample.service.FileMetaService#findAllFiles()
	 */
	public List<FileMeta> findAllFiles() {
		return (List<FileMeta>) fileMetaRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.sample.service.FileMetaService#findById(long)
	 */
	public FileMeta findById(long id) {
		return fileMetaRepository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.sample.service.FileMetaService#findByTitle(java.lang.String)
	 */
	public FileMeta findByTitle(String title) {
		for (FileMeta fileMeta : fileMetas) {
			if (fileMeta.getTitle().equalsIgnoreCase(title)) {
				return fileMeta;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sample.service.FileMetaService#save(com.sample.domain.FileMeta)
	 */
	public FileMeta save(FileMeta fileMeta) {
		// fileMeta.setId(counter.incrementAndGet());
		FileMeta file = fileMetaRepository.save(fileMeta);
		return file;
	}

	/* (non-Javadoc)
	 * @see com.sample.service.FileMetaService#updateFile(com.sample.domain.FileMeta)
	 */
	public void updateFile(FileMeta fileMeta) {
		int index = fileMetas.indexOf(fileMeta);
		fileMetas.set(index, fileMeta);
	}

	/* (non-Javadoc)
	 * @see com.sample.service.FileMetaService#deleteFileById(long)
	 */
	public void deleteFileById(long id) {

		for (Iterator<FileMeta> iterator = fileMetas.iterator(); iterator.hasNext();) {
			FileMeta fileMeta = iterator.next();
			if (fileMeta.getId() == id) {
				iterator.remove();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.sample.service.FileMetaService#isFileExist(com.sample.domain.FileMeta)
	 */
	public boolean isFileExist(FileMeta fileMeta) {
		return findByTitle(fileMeta.getTitle()) != null;
	}

	/* (non-Javadoc)
	 * @see com.sample.service.FileMetaService#deleteAllFiles()
	 */
	public void deleteAllFiles() {
		fileMetas.clear();
	}

}
