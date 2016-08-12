package com.scout.aws.model;

public class GenericResult {

	public String htmlVersion;
	public String pageTitle;
	public String headings;
	public String links;
	public String linksWorking;
	public String login;

	public int statusCode;
	public String statusMessage;

	public String getHtmlVersion() {
		return htmlVersion;
	}

	public void setHtmlVersion(String htmlVersion) {
		this.htmlVersion = htmlVersion;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getHeadings() {
		return headings;
	}

	public void setHeadings(String headings) {
		this.headings = headings;
	}

	public String getLinks() {
		return links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	public String getLinksWorking() {
		return linksWorking;
	}

	public void setLinksWorking(String linksWorking) {
		this.linksWorking = linksWorking;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}