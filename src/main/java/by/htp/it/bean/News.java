package by.htp.it.bean;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String brief;
	private String content;
	private Date publishingDate;
	private int idUser;
	private NewsStatus status;

	public News() {

	}

	public News(String title, String content, Date publishingDate) {
		super();
		this.title = title;
		this.content = content;
		this.publishingDate = publishingDate;
	}

	public News(String title, String brief, String content, int idUser) {
		super();
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.idUser = idUser;
	}

	public News(int id, String title, String brief) {
		this.id = id;
		this.title = title;
		this.brief = brief;

	}

	public News(int id) {

		this.id = id;
	}

	public News(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public News(int id, String title, String brief, String content) {
		this.id = id;
		this.title = title;
		this.brief = brief;
		this.content = content;
	}

	public News(int id, String title, String brief, NewsStatus status) {
		this.id = id;
		this.title = title;
		this.brief = brief;
		this.status = status;
	}

	public News(String title, String brief, String content) {
		this.title = title;
		this.brief = brief;
		this.content = content;
	}

	public News(int idNews, int idUser) {
		this.id = idNews;
		this.idUser = idUser;
	}

	public News(int id, String title, String brief, Date publishingDate ) {
		this.id = id;
		this.title = title;
		this.brief = brief;
		this.publishingDate = publishingDate;
		
	}

	public News(int idNews, String title, String brief, String content, Date publishingDate) {
		this.id = idNews;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.publishingDate = publishingDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public NewsStatus getStatus() {
		return status;
	}

	public void setStatus(NewsStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + id;
		result = prime * result + idUser;
		result = prime * result + ((publishingDate == null) ? 0 : publishingDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		if (brief == null) {
			if (other.brief != null)
				return false;
		} else if (!brief.equals(other.brief))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		if (idUser != other.idUser)
			return false;
		if (publishingDate == null) {
			if (other.publishingDate != null)
				return false;
		} else if (!publishingDate.equals(other.publishingDate))
			return false;
		if (status != other.status)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [id=" + id + ", title=" + title + ", brief=" + brief + ", content=" + content
				+ ", publishingDate=" + publishingDate + ", idUser=" + idUser + ", status=" + status + "]";
	}
}