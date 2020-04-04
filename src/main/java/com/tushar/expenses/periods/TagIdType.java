package com.tushar.expenses.periods;

import com.tushar.expenses.shared.DomainObjectIdCustomType;
import com.tushar.expenses.shared.DomainObjectIdTypeDescriptor;

public class TagIdType extends DomainObjectIdCustomType<TagId> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DomainObjectIdTypeDescriptor<TagId> TYPE_DESCRIPTOR = new DomainObjectIdTypeDescriptor<>(
    		TagId.class, TagId::new);

    public TagIdType() {
        super(TYPE_DESCRIPTOR);
    }
}
