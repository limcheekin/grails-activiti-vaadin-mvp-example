package org.vaadin.activiti.simpletravel.domain

class TravelRequestDecision extends AbstractClonableObject {

	Decision decision
	String motivationOfDecision
	String managerUserId
	Date dateOfDecision

	static constraints = {
		decision nullable: false
		motivationOfDecision nullable: false, minSize: 3
		managerUserId nullable: false
		dateOfDecision()
	}

	protected TravelRequestDecision() {
	}

	public TravelRequestDecision(Decision decision, String motivationOfDecision, String managerUserId, Date dateOfDecision) {
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
