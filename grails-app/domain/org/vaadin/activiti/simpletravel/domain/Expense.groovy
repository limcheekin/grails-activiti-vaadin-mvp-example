package org.vaadin.activiti.simpletravel.domain

class Expense extends AbstractClonableObject {

  String description
  Integer quantity = 1
  BigDecimal price = BigDecimal.ZERO

	static constraints = {
		description blank: false
		quantity blank: false
		price blank: false
	}
	
	static transients = ['total']

	BigDecimal getTotal() {
		return price.multiply(new BigDecimal(quantity))
	}
}
