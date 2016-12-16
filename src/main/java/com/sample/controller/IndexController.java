package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author hbenamara
 * The Class IndexController.
 */
@Controller
@RequestMapping("/")
public class IndexController {

	/**
	 * Gets the index page.
	 *
	 * @return the index page
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getIndexPage() {
		return "index.html";
	}

}