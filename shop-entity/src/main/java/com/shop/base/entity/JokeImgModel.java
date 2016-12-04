package com.shop.base.entity;

import java.io.Serializable;

public class JokeImgModel extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1073560563797425481L;

	private Integer id;

	private String articleTitle;

	private String articleContent;

	private String articleAuthor;

	private String articlePublishTime;

	private String articleAvatar;

	private String articleCategories;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle == null ? null : articleTitle.trim();
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent == null ? null : articleContent.trim();
	}

	public String getArticleAuthor() {
		return articleAuthor;
	}

	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor == null ? null : articleAuthor.trim();
	}

	public String getArticlePublishTime() {
		return articlePublishTime;
	}

	public void setArticlePublishTime(String articlePublishTime) {
		this.articlePublishTime = articlePublishTime == null ? null : articlePublishTime.trim();
	}

	public String getArticleAvatar() {
		return articleAvatar;
	}

	public void setArticleAvatar(String articleAvatar) {
		this.articleAvatar = articleAvatar == null ? null : articleAvatar.trim();
	}

	public String getArticleCategories() {
		return articleCategories;
	}

	public void setArticleCategories(String articleCategories) {
		this.articleCategories = articleCategories == null ? null : articleCategories.trim();
	}

}