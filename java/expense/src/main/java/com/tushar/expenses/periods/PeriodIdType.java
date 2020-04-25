package com.tushar.expenses.periods;

import com.tushar.expenses.shared.DomainObjectIdCustomType;
import com.tushar.expenses.shared.DomainObjectIdTypeDescriptor;

public class PeriodIdType extends DomainObjectIdCustomType<PeriodId> {

    private static final DomainObjectIdTypeDescriptor<PeriodId> TYPE_DESCRIPTOR = new DomainObjectIdTypeDescriptor<>(
    		PeriodId.class, PeriodId::new);

    public PeriodIdType() {
        super(TYPE_DESCRIPTOR);
    }
}
