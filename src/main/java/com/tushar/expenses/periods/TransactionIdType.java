package com.tushar.expenses.periods;

import com.tushar.expenses.shared.DomainObjectIdCustomType;
import com.tushar.expenses.shared.DomainObjectIdTypeDescriptor;

public class TransactionIdType extends DomainObjectIdCustomType<TransactionId> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DomainObjectIdTypeDescriptor<TransactionId> TYPE_DESCRIPTOR = new DomainObjectIdTypeDescriptor<>(
    		TransactionId.class, TransactionId::new);

    public TransactionIdType() {
        super(TYPE_DESCRIPTOR);
    }
}

