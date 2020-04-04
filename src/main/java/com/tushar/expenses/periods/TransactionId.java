package com.tushar.expenses.periods;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionId  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;

	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o == null || o.getClass() != getClass()) {
			return false;
		}
		return id.equals(((TransactionId) o).id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public String toString() {
		return id;
	}

	public TransactionId(String id) {
		this.id = id;
	}

}
