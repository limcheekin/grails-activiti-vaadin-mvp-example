package org.vaadin.activiti.simpletravel.domain

import com.github.peholmst.stuff4vaadin.clone.CloneUtil


public abstract class AbstractClonableObject implements java.io.Serializable, Cloneable {

	public AbstractClonableObject clone() {
		try {
			return CloneUtil.deepClone((AbstractClonableObject) super.clone())
		} catch (CloneNotSupportedException ex) {
			throw new InternalError(ex.getMessage())
		}
	}
}
