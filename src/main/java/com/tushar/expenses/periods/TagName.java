package com.tushar.expenses.periods;

import java.util.Objects;

public class TagName {

	private final String tagName;

	public TagName(String tagName) {
		this.tagName = tagName;
	}

	public String name() {
		return tagName;
	}
	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o == null || o.getClass() != getClass()) {
			return false;
		}
		return tagName.equals(((TagName) o).tagName);
	}

	public int hashCode() {
		return Objects.hash(tagName);
	}
	
}
