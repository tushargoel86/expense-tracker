package com.tushar.expenses.periods;

import com.tushar.expenses.shared.DomainObjectIdCustomType;
import com.tushar.expenses.shared.DomainObjectIdTypeDescriptor;

public class TransactionDateType extends DomainObjectIdCustomType<TransactionDate> {

    private static final DomainObjectIdTypeDescriptor<TransactionDate> TYPE_DESCRIPTOR = new DomainObjectIdTypeDescriptor<>(
    		TransactionDate.class, TransactionDate::new);

    public TransactionDateType() {
        super(TYPE_DESCRIPTOR);
    }
}
