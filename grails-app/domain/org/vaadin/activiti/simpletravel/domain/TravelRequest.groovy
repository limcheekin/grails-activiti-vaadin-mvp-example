package org.vaadin.activiti.simpletravel.domain

class TravelRequest extends AbstractClonableObject {

	public static final String PROP_DEPARTURE_DATE = "departureDate"
	public static final String PROP_RETURN_DATE = "returnDate"
	public static final String PROP_COUNTRY = "country"
	public static final String PROP_DESCRIPTION = "description"
	String requesterUserId
	String requesterUserName
	Date departureDate
	Date returnDate
	Country country
	String description
	TravelRequestDecision decision

	static constraints = {
		requesterUserId nullable: false
		requesterUserName nullable: false
		departureDate nullable: false
		returnDate nullable: false
		country nullable: false
		depadescription nullable: false, minSize: 3
		decision nullable: false
	}
	
	static transients = ['decisionPending']

	/**
	 * Returns a clone of the travel request decision.
	 */
	public TravelRequestDecision getDecision() {
		return decision != null ? (TravelRequestDecision) decision.clone() : null
	}

	public void setDecision(TravelRequestDecision decision) {
		this.decision = decision != null ? (TravelRequestDecision) decision.clone() : null
	}

	public boolean isDecisionPending() {
		return this.decision == null
	}
}
