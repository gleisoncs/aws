package com.scout.aws;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scout.aws.service.UrlService;

@Controller
public class UrlController {

	@Inject
	UrlService urlService;

	@RequestMapping(value = "/processUrl", method = RequestMethod.POST)
	public String search(@RequestParam String url, Model model) {
		model.addAttribute("result", urlService.processUrl(url));
		model.addAttribute("url", url);
		return "index";
	}
}