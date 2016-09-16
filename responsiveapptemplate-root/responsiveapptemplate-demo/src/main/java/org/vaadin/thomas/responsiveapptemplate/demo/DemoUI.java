package org.vaadin.thomas.responsiveapptemplate.demo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.thomas.responsiveapptemplate.ResponsiveAppTemplate;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("demo")
@Title("MyComponent Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {

		// Initialize our new UI component
		final ResponsiveAppTemplate component = new ResponsiveAppTemplate();

		setContent(component);
	}
}
