package com.tushar.expenses.user;

import com.tushar.expenses.periods.TransactionId;
import com.tushar.expenses.shared.DomainObjectIdCustomType;
import com.tushar.expenses.shared.DomainObjectIdTypeDescriptor;

public class UserIdType extends DomainObjectIdCustomType<UserId> {

    private static final DomainObjectIdTypeDescriptor<UserId> TYPE_DESCRIPTOR = new DomainObjectIdTypeDescriptor<>(
    		UserId.class, UserId::new);

    public UserIdType() {
        super(TYPE_DESCRIPTOR);
    }
}

