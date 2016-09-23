package org.vaadin.thomas.responsiveapptemplate;

import com.vaadin.ui.Button;

public class ResponsiveMenuItem extends Button {

	private static final long serialVersionUID = -3573683131530701986L;
	private String realCaption;

	public ResponsiveMenuItem(String text, ClickListener clickListener) {
		super(text, clickListener);
	}

	@Override
	public void setCaption(String caption) {
		realCaption = caption;
		super.setCaption(caption);
	}

	public void setBadgeText(String text, String color) {
		if (text == null) {
			setCaptionAsHtml(false);
			setCaption(realCaption);
		} else {
			final StringBuilder html = new StringBuilder();
			html.append(realCaption == null ? "" : realCaption);
			html.append("<span class=\"badge\"");
			if (color != null) {
				html.append(" style=\"color: " + color + ";\"");
			}
			html.append(">");
			html.append(text);
			html.append("</span>");

			setCaptionAsHtml(true);
			setCaption(html.toString());
		}

	}

	public void setBadgeText(String text) {

		setBadgeText(text, null);
	}

}
