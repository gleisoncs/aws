/**
 * 
 */
package com.scout.core.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.scout.aws.model.GenericResult;
import com.scout.aws.service.UrlService;

/**
 * Class designed to create tests from UrlService
 * 
 * @author admin
 *
 */
public class ProcessUrl {

	/**
	 * Method responsible to test the processUrl at UrlService
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testProcessUrl() throws Exception {
		UrlService service = new UrlService();
		GenericResult result = service.processUrl("http://www.amazon.com/");
		//FIXME bug to access the internet via Junit
		assertEquals(0, result.getStatusCode());
	}
}
