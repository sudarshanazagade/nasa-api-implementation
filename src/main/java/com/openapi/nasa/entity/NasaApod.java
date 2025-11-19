package com.openapi.nasa.entity;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "apod")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NasaApod {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Size(min = 1, message = "is required")
	@Column(name = "copyright")
	private String copyright;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Column(name = "date")
	private String date;

	@Column(columnDefinition = "Text")
	@Lob
	@NotNull(message = "is required")
	@Size(min = 1, message = " is required")
	private String explanation;

	@NotNull(message = " is required")
	@Size(min = 1, message = "is required")
	@Column(name = "hdurl")
	private String hdurl;

	@Size(min = 1, message = " is required")
	@NotNull(message = "is required")
	@Column(name = "title")
	private String title;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Column(name = "url")
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyyright) {
		this.copyright = copyright;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getHdurl() {
		return hdurl;
	}

	public void setHdurl(String hdurl) {
		this.hdurl = hdurl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "NasaApod [id=" + id + ", copyright=" + copyright + ", date=" + date + ", explanation=" + explanation
				+ ", hdurl=" + hdurl + ", title=" + title + ", url=" + url + "]";
	}

}
