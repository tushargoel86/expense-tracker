package com.tushar.expenses.user.shared;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
import org.springframework.lang.NonNull;

/**
 * Hibernate custom type for a {@link DomainObjectId} subtype. You need this to be able to use {@link DomainObjectId}s
 * as primary keys. You have to create one subclass per {@link DomainObjectId} subtype.
 *
 * @param <ID> the ID type.
 * @see DomainObjectIdTypeDescriptor
 */
public abstract class DomainObjectIdCustomType<ID> extends AbstractSingleColumnStandardBasicType<ID> {

    /**
     * Creates a new {@code DomainObjectIdCustomType}. In your subclass, you should create a default constructor and
     * invoke this constructor from there.
     *
     * @param domainObjectIdTypeDescriptor the {@link DomainObjectIdTypeDescriptor} for the ID type.
     */
    public DomainObjectIdCustomType(@NonNull DomainObjectIdTypeDescriptor<ID> domainObjectIdTypeDescriptor) {
        super(VarcharTypeDescriptor.INSTANCE, domainObjectIdTypeDescriptor);
    }

    @Override
    public String getName() {
        return getJavaTypeDescriptor().getJavaType().getSimpleName();
    }
}
