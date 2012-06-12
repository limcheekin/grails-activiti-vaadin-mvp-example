package org.vaadin.activiti.simpletravel.domain


class TravelInvoiceDecision extends AbstractClonableObject {

	Decision decision
	String motivationOfDecision
	String managerUserId
	Date dateOfDecision

	static constraints = {
		decision nullable: false
		motivationOfDecision nullable: false
		managerUserId nullable: false
		dateOfDecision()
	}


	protected TravelInvoiceDecision() {
	}

	public TravelInvoiceDecision(Decision decision, String motivationOfDecision, String managerUserId, Date dateOfDecision) {
		this.decision = decision
		this.motivationOfDecision = motivationOfDecision
		this.managerUserId = managerUserId
		this.dateOfDecision = dateOfDecision
	}

	public Decision getDecision() {
		return decision
	}

	public String getManagerUserId() {
		return managerUserId
	}

	public String getMotivationOfDecision() {
		return motivationOfDecision
	}

	public Date getDateOfDecision() {
		return dateOfDecision
	}
}
