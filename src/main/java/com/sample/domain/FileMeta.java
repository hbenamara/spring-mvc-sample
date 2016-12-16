package com.sample.domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author hbenamara
 * The Class FileMeta.
 */
@Entity
@Table(name = "filemeta")
public class FileMeta {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/** The title. */
	@NotNull
	private String title;

	/** The description. */
	private String description;

	/** The creation date. */
	@NotNull
	private Date creationDate;

	/** The download link. */
	@NotNull
	private String downloadLink;

	/**
	 * Instantiates a new file meta.
	 */
	public FileMeta() {
		id = 0;
	}

	/**
	 * Instantiates a new file meta.
	 *
	 * @param title the title
	 * @param description the description
	 * @param creationDate the creation date
	 * @param downloadLink the download link
	 */
	public FileMeta(String title, String description, Date creationDate, String downloadLink) {
		this.title = title;
		this.description = description;
		this.creationDate = creationDate;
		this.downloadLink = downloadLink;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate the new creation date
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Gets the download link.
	 *
	 * @return the download link
	 */
	public String getDownloadLink() {
		return downloadLink;
	}

	/**
	 * Sets the download link.
	 *
	 * @param downloadLink the new download link
	 */
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FileMeta))
			return false;
		FileMeta other = (FileMeta) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileMeta [id=" + id + ", title=" + title + ", description=" + description + ", creationDate="
				+ creationDate + "downloadLink=" + downloadLink + "]";
	}

}
