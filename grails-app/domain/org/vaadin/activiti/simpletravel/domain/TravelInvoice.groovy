package org.vaadin.activiti.simpletravel.domain

class TravelInvoice extends AbstractClonableObject {

	// TODO Add custom validation: invoice cannot be paid unless it has been approved

	TravelRequest request

	Set<Expense> expenses = new HashSet<Expense>()
	TravelInvoiceDecision decision
	boolean paid = false
	
	static transients = ['totalExpenses', 'decisionPending']

	protected TravelInvoice() {
	}

	public TravelInvoice(TravelRequest request) {
		this.request = request
	}


	/**
	 * Returns a clone of the travel invoice decision.
	 */
	public TravelInvoiceDecision getDecision() {
		return decision != null ? (TravelInvoiceDecision) decision.clone() : null
	}

	public void setDecision(TravelInvoiceDecision decision) {
		this.decision = decision != null ? (TravelInvoiceDecision) decision.clone() : null
	}

	/**
	 * Returns a set of clones of the <code>Expense</code> objects.
	 * Any changes made to these objects will not be propagated to
	 * the invoice. Use {@link #addExpence(org.vaadin.activiti.simpletravel.domain.Expence) } and
	 * {@link #removeExpence(org.vaadin.activiti.simpletravel.domain.Expence) } to make changes
	 * to the set of expenses.
	 */
	public Collection<Expense> getExpenses() {
		HashSet<Expense> expenseClones = new HashSet<Expense>()
		for (Expense expense : expenses) {
			expenseClones.add((Expense) expense.clone())
		}
		return expenseClones
	}

	public void setExpenses(Collection<Expense> expenses) {
		this.expenses = new HashSet<Expense>()
		if (expenses != null) {
			for (Expense expense : expenses) {
				this.expenses.add((Expense) expense.clone())
			}
		}
	}

	public void addExpense(Expense expenseToAdd) {
		expenses.add((Expense) expenseToAdd.clone())
	}

	public void removeExpense(Expense expenseToRemove) {
		expenses.remove(expenseToRemove)
	}

	public BigDecimal getTotalExpenses() {
		BigDecimal total = BigDecimal.ZERO

		for (Expense expense : expenses) {
			total = total.add(expense.getTotal())
		}

		return total
	}

	public boolean isDecisionPending() {
		return this.decision == null
	}
}
