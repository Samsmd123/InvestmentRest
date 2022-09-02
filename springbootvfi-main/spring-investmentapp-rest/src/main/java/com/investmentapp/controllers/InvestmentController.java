package com.investmentapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.investmentapp.exceptions.PlanNotFoundException;
import com.investmentapp.model.Investment;
import com.investmentapp.service.IInvestmentService;

//@RestController
public class InvestmentController {
	
	@Autowired
	IInvestmentService investmentService;

	@PostMapping("/investments")
	public void addInvestment(@RequestBody Investment investment) {
		investmentService.addInvestment(investment);
	}
	@PutMapping("/investments")
	public void updateInvestment(@RequestBody Investment investment) {
		investmentService.updateInvestment(investment);
	}
	@DeleteMapping("/investments/{planId}")
	public void deleteInvestment(@PathVariable("planId")int  planId) {
		investmentService.deleteInvestment(planId);
	}
	@GetMapping("/investments/risk/{risk}/term/{term}")
	public List<Investment> getByRiskAndTerm
			(@PathVariable("risk") String risk, @PathVariable("term") int mterm ) {
		
		return investmentService.getByRiskAndTerm(risk, mterm);
	}
	
	@GetMapping("/investments/type/{type}")
	public List<Investment> getByType(@PathVariable("type") String type){
		return investmentService.getByType(type);
	}
	
	@GetMapping("/investments/purpose/{purpose}")
	public List<Investment> getByPurpose(@PathVariable("purpose")String purpose){
		return investmentService.getByPurpose(purpose);
	}
//	http://localhost:8080/investments
	@GetMapping("/investments")
	public List<Investment> getAll(){
		return investmentService.getAll();
	}
	
	@GetMapping("/investments/planId/{id}")
	public Investment getById(@PathVariable("id") int planId) {
		return investmentService.getById(planId);
	}
}
