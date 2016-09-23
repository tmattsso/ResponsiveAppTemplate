package org.vaadin.thomas.responsiveapptemplate.demo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.thomas.responsiveapptemplate.ResponsiveAppTemplate;
import org.vaadin.thomas.responsiveapptemplate.ResponsiveAppTemplate.MenuClickHandler;
import org.vaadin.thomas.responsiveapptemplate.ResponsiveMenuItem;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("MyComponent Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {
	private final class FooView extends VerticalLayout implements View {

		public FooView(String text) {
			setMargin(true);
			addComponent(new Label(text));
		}

		@Override
		public void enter(ViewChangeEvent event) {
		}
	}

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.thomas.responsiveapptemplate.demo.Responsiveapptemplate_demoWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	ResponsiveAppTemplate sideMenu = new ResponsiveAppTemplate();

	@Override
	protected void init(VaadinRequest request) {
		setContent(sideMenu);
		final Navigator navigator = new Navigator(this, sideMenu);
		setNavigator(navigator);
		navigator.addView("", new FooView("Initial view"));
		navigator.addView("Foo", new FooView("Foo!"));

		sideMenu.setMenuCaption("My App Menu");
		sideMenu.setMenuCaptionIcon(FontAwesome.LAPTOP);

		// sideMenu.setMenuCaption(new Button("Custom menu!"));

		// Navigation examples
		sideMenu.addNavigation("Initial View", "");
		sideMenu.addNavigation("Foo View", FontAwesome.AMBULANCE, "Foo");

		sideMenu.setMenuCaptionClickHandler(new MenuClickHandler() {

			@Override
			public void click() {
				Notification.show("Clicked");
			}
		});

		// Arbitrary method execution
		final ResponsiveMenuItem item2 = sideMenu.addMenuItem("My Menu Entry",
				new MenuClickHandler() {
					@Override
					public void click() {
						final VerticalLayout content = new VerticalLayout();
						content.setMargin(true);
						content.addComponent(new Label("A layout"));
						sideMenu.setContent(content);
					}
				});
		item2.setBadgeText("123", "#e61e6d");

		final ResponsiveMenuItem item = sideMenu.addMenuItem("Entry With Icon",
				FontAwesome.ANDROID, new MenuClickHandler() {
					@Override
					public void click() {
						final VerticalLayout content = new VerticalLayout();
						content.setMargin(true);
						content.addComponent(new Label("Another layout"));
						sideMenu.setContent(content);
					}
				});

		item.setBadgeText("42");

		// User menu controls
		sideMenu.addMenuItem("Show/Hide user menu", FontAwesome.USER,
				new MenuClickHandler() {
					@Override
					public void click() {
						sideMenu.setUserMenuVisible(!sideMenu
								.isUserMenuVisible());
					}
				});

		setUser("Guest", FontAwesome.MALE);
	}

	private void setUser(String name, Resource icon) {
		sideMenu.setUserName(name);
		sideMenu.setUserIcon(icon);

		sideMenu.clearUserMenu();
		sideMenu.addUserMenuItem("Settings", FontAwesome.WRENCH,
				new MenuClickHandler() {
					@Override
					public void click() {
						Notification.show("Showing settings",
								Type.TRAY_NOTIFICATION);
					}
				});
		sideMenu.addUserMenuItem("Sign out", new MenuClickHandler() {
			@Override
			public void click() {
				Notification.show("Logging out..", Type.TRAY_NOTIFICATION);
			}
		});
	}
}
