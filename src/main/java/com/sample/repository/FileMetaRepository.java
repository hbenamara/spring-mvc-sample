package com.sample.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.sample.domain.FileMeta;

/**
 * @author hbenamara
 * The Interface FileMetaRepository.
 */
@Transactional
public interface FileMetaRepository extends CrudRepository<FileMeta, Long> {
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<FileMeta> findAll(Pageable pageable);
}
