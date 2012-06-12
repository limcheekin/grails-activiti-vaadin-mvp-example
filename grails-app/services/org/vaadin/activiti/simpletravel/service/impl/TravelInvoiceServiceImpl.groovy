package org.vaadin.activiti.simpletravel.service.impl

import org.activiti.engine.impl.identity.Authentication
import org.activiti.engine.runtime.ProcessInstance
import org.activiti.engine.task.Task
import org.vaadin.activiti.simpletravel.domain.Decision
import org.vaadin.activiti.simpletravel.domain.TravelInvoice
import org.vaadin.activiti.simpletravel.domain.TravelInvoiceDecision
import org.vaadin.activiti.simpletravel.domain.TravelRequest
import org.vaadin.activiti.simpletravel.domain.validation.ValidationUtil
import org.vaadin.activiti.simpletravel.identity.Groups
import org.vaadin.activiti.simpletravel.identity.RequireGroup
import org.vaadin.activiti.simpletravel.service.TravelInvoiceService
import org.vaadin.activiti.simpletravel.service.TravelRequestService


class TravelInvoiceServiceImpl extends AbstractServiceImpl implements TravelInvoiceService {

	TravelRequestService requestService

	@RequireGroup(Groups.GROUP_EMPLOYEES)
	public TravelInvoice findInvoiceForRequest(TravelRequest request) {
		return TravelInvoice.findByRequest(request)
	}

	@RequireGroup(Groups.GROUP_EMPLOYEES)
	public TravelInvoice saveTravelInvoice(TravelInvoice invoice) {
		ValidationUtil.validateAndThrow(validator, invoice)
		invoice.save(failOnError: true)
		final ProcessInstance processInstance = getProcessInstanceForInvoice(invoice)
		final Task task = getEnterExpencesTask(processInstance)
		taskService.complete(task.getId())
		return invoice
	}

	@RequireGroup(Groups.GROUP_EMPLOYEES)
	public TravelInvoice findTravelInvoiceByProcessInstanceId(String processInstanceId) {
		TravelRequest request = requestService.findTravelRequestByProcessInstanceId(processInstanceId)
		if (request == null) {
			return null
		} else {
			return findInvoiceForRequest(request)
		}
	}

	@RequireGroup(Groups.GROUP_PAYROLLADMINS)
	public void payExpences(TravelInvoice invoice) {
		ValidationUtil.validateAndThrow(validator, invoice)
		final ProcessInstance processInstance = getProcessInstanceForInvoice(invoice)
		final Task task = getPayExpencesTask(processInstance)
		invoice.setPaid(true)
		invoice.save(failOnError: true)
		taskService.complete(task.getId())
	}

	private ProcessInstance getProcessInstanceForInvoice(TravelInvoice invoice) {
		return runtimeService.createProcessInstanceQuery().
		processInstanceBusinessKey(invoice.getRequest().getId().toString(), "simple-travel").
		singleResult()
	}

	private Task getEnterExpencesTask(ProcessInstance processInstance) {
		return findTaskByDefinitionKey(processInstance, "enterExpenses")
	}

	private Task getPayExpencesTask(ProcessInstance processInstance) {
		return findTaskByDefinitionKey(processInstance, "payout")
	}

	@RequireGroup(Groups.GROUP_MANAGERS)
	public void approveTravelInvoice(TravelInvoice invoice, String motivation) {
		final TravelInvoiceDecision decision = new TravelInvoiceDecision(Decision.APPROVED, motivation, Authentication.getAuthenticatedUserId(), new Date())
		setDecisionAndSave(invoice, decision)
	}

	@RequireGroup(Groups.GROUP_MANAGERS)
	public void rejectTravelInvoice(TravelInvoice invoice, String motivation) {
		final TravelInvoiceDecision decision = new TravelInvoiceDecision(Decision.DENIED, motivation, Authentication.getAuthenticatedUserId(), new Date())
		setDecisionAndSave(invoice, decision)
	}

	private void setDecisionAndSave(TravelInvoice invoice, TravelInvoiceDecision decision) {
		invoice.setDecision(decision)
		ValidationUtil.validateAndThrow(validator, invoice)
		invoice.save(failOnError: true)
		final ProcessInstance processInstance = getProcessInstanceForInvoice(invoice)
		final Task expensesApprovalTask = getExpensesApprovalTask(processInstance)
		runtimeService.setVariable(processInstance.getProcessInstanceId(), "invoice", invoice)
		taskService.complete(expensesApprovalTask.getId())
	}

	private Task getExpensesApprovalTask(ProcessInstance processInstance) {
		return findTaskByDefinitionKey(processInstance, "approveExpenses")
	}
}
