package com.tushar.expenses.user;

import com.tushar.expenses.user.shared.DomainObjectIdCustomType;
import com.tushar.expenses.user.shared.DomainObjectIdTypeDescriptor;

public class UserIdType extends DomainObjectIdCustomType<UserId> {

    private static final DomainObjectIdTypeDescriptor<UserId> TYPE_DESCRIPTOR = new DomainObjectIdTypeDescriptor<>(
    		UserId.class, UserId::new);

    public UserIdType() {
        super(TYPE_DESCRIPTOR);
    }
}

