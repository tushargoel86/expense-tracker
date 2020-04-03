package com.tushar.expenses.user;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

import lombok.Data;

@Data
public class UserId  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String id;

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o == null || o.getClass() != getClass()) {
			return false;
		}
		return id == (((UserId) o).id);
	}

	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return id;
	}
}
