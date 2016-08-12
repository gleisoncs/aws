package com.scout.aws.service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.inject.Named;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.scout.aws.IndexController;
import com.scout.aws.UrlController;
import com.scout.aws.model.GenericResult;
import com.scout.aws.utils.Constants;

/**
 * Main service that implement a way to analyze a URL <tt>String</tt> parameter.
 *
 * <p>
 * The <tt>processUrl</tt>is the main method implementation.
 *
 * <p>
 * Each <tt>UrlService</tt> instanced has a <i>capacity</i> to analyze a whole
 * URL.
 *
 * @author Gleison Caetano
 * @see IndexController
 * @see UrlController
 * @see GenericResult
 * @since 1.0
 */
@Named
public class UrlService {

	/**
	 * Method responsible to start processing url.
	 * 
	 * @see GenericResult
	 * 
	 * @param url
	 * 
	 */
	public GenericResult processUrl(String url) {
		GenericResult result = new GenericResult();
		try {
			if (isUrlValid(url, result)) {
				Document doc = init(url);

				extractHtmlVersion(result, doc);

				extractTitle(result, doc);

				extractInternalExternalLinks(result, doc);

				extractLinksNotWorking(result, doc);

				extractHeadings(result, doc);

				extractForm(result, doc);
			}

		} catch (IOException e) {
			result.setStatusCode(0);
			result.setStatusMessage("Oops something wrong!");
		}
		return result;
	}

	/**
	 * Method responsible for initialize the <b>Document<b> element.
	 * 
	 * @see GenericResult
	 * @see Document
	 * 
	 * @param result
	 * @param doc
	 * 
	 */
	private Document init(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		return doc;
	}

	/**
	 * Method responsible for count how many links are accessible. URL are
	 * faster than Jsoup in this case.
	 * 
	 * @see GenericResult
	 * @see Document
	 * 
	 * @param result
	 * @param doc
	 * 
	 */
	private void extractLinksNotWorking(GenericResult result, Document doc) {
		int counter = 0;
		Elements links = doc.select("a[href]");
		Elements media = doc.select("[src]");
		Elements imports = doc.select("link[href]");

		for (Element element : links) {
			try {
				URL url = new URL(element.attr("abs:href"));
				URLConnection conn = url.openConnection();
				conn.connect();
			} catch (Exception e) {
				counter++;
			}
		}

		for (Element element : media) {
			try {
				URL url = new URL(element.attr("abs:src"));
				URLConnection conn = url.openConnection();
				conn.connect();
			} catch (Exception e) {
				counter++;
			}
		}

		for (Element element : imports) {
			try {
				URL url = new URL(element.attr("abs:href"));
				URLConnection conn = url.openConnection();
				conn.connect();
			} catch (Exception e) {
				counter++;
			}
		}
		result.setLinksWorking(String.valueOf(counter));
	}

	/**
	 * Method responsible for return how many heading are in URL.
	 * 
	 * @see GenericResult
	 * @see Document
	 * 
	 * @param result
	 * @param doc
	 * 
	 */
	private void extractHeadings(GenericResult result, Document doc) {
		Elements links = doc.body().select("h1,h2,h3,h4,h5,h6");
		result.setHeadings(String.valueOf(links.size()));
	}

	/**
	 * Method responsible for return how many forms are in URL. Specifically
	 * this method, does not return which one is a login form, cause its
	 * necessary to review the requirements
	 * 
	 * @see GenericResult
	 * @see Document
	 * 
	 * @param result
	 * @param doc
	 * 
	 */
	private void extractForm(GenericResult result, Document doc) {
		Elements links = doc.select("form");
		result.setLogin(String.valueOf(links.size()));
	}

	/**
	 * Method responsible for return how many links are in URL.
	 * 
	 * @see GenericResult
	 * @see Document
	 * 
	 * @param result
	 * @param doc
	 * 
	 */
	private void extractInternalExternalLinks(GenericResult result, Document doc) {
		Elements links = doc.select("a[href]");
		Elements media = doc.select("[src]");
		Elements imports = doc.select("link[href]");

		int qtdeLinks = media.size() + imports.size() + links.size();
		result.setLinks(String.valueOf(qtdeLinks));
	}

	/**
	 * Method responsible for populate the version of HTML page.
	 * 
	 * @see GenericResult
	 * @see Document
	 * 
	 * @param result
	 * @param doc
	 * 
	 */
	private void extractHtmlVersion(GenericResult result, Document doc) {
		String version = "";
		List<Node> nods = doc.childNodes();
		for (Node node : nods) {
			if (node instanceof DocumentType) {
				DocumentType documentType = (DocumentType) node;
				if (Constants.HTML5.equalsIgnoreCase(documentType.toString())) {
					version = "HTML 5";
				} else {
					version = documentType.toString().substring(documentType.toString().indexOf("//DTD") + 6,
							documentType.toString().indexOf("//EN"));
				}
				break;
			}
		}
		result.setHtmlVersion(version);
	}

	/**
	 * Method responsible for extract the title inside doc and set to a
	 * GenericResult
	 * 
	 * @see GenericResult
	 * @see Document
	 * 
	 * @param result
	 * @param doc
	 * 
	 */
	private void extractTitle(GenericResult result, Document doc) {
		result.setPageTitle(doc.title());
	}

	/**
	 * Method responsible for looking and validate the URL
	 * 
	 * @see GenericResult
	 * @see Document
	 * 
	 * @param url
	 * @param result
	 * 
	 */
	public boolean isUrlValid(String url, GenericResult result) {
		Connection.Response response = null;

		try {
			response = Jsoup.connect(url).timeout(Constants.TIMEOUT).execute();

			result.setStatusCode(response.statusCode());
			result.setStatusMessage(response.statusMessage());

			if (response.statusCode() == 200)
				return true;
			else {
				return false;
			}
		} catch (Exception e) {
			result.setStatusCode(0);
			result.setStatusMessage("Oops something wrong!");
			return false;
		}
	}
}