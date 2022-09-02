package com.investmentapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.investmentapp.exceptions.PlanNotFoundException;
import com.investmentapp.model.Investment;
import com.investmentapp.repository.IInvestmentRepository;
import com.investmentapp.service.IInvestmentService;

@RestController
public class InvestmentResponseController {
	
	@Autowired
	IInvestmentService investmentService;

	@PostMapping("/investments")
	public ResponseEntity<Void>addInvestment(@RequestBody Investment investment) {
		investmentService.addInvestment(investment);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	@PutMapping("/investments")
	public ResponseEntity<String> updateInvestment(@RequestBody Investment investment) {
		investmentService.updateInvestment(investment);
		return ResponseEntity.accepted().body("Updated");
	}
	@DeleteMapping("/investments/{planId}")
	public ResponseEntity<Void> deleteInvestment(@PathVariable("planId")int  planId) {
		investmentService.deleteInvestment(planId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@GetMapping("/investments/risk/{risk}/term/{term}")
	public ResponseEntity<List<Investment>> getByRiskAndTerm(@PathVariable("risk") String risk,@PathVariable("term")int term) {
		List<Investment> investments = investmentService.getByRiskAndTerm(risk, term);
		HttpHeaders headers=new HttpHeaders();
		headers.add("desc", "All investment risk and terms");
		headers.add("Info","Getting investments frm db");
		return ResponseEntity.status(HttpStatus.OK).body(investments);
	}
	
	@GetMapping("/investments/type")
	public List<Investment> getByType(@RequestParam("type") String type){
		return investmentService.getByType(type);
	}
	
	@GetMapping("/investments/purpose/{purpose}")
	public ResponseEntity<List<Investment>> getByPurpose(@PathVariable("purpose")String purpose){
		List<Investment> investments=investmentService.getByPurpose(purpose);
		HttpHeaders headers=new HttpHeaders();
		headers.add("desc", " Get All Inestments By Purpose");
		headers.add("info","Info RST API by purpose ");
		
		
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(investments);
	}
//	http://localhost:8080/investments
	@GetMapping("/investments")
	public ResponseEntity<List<Investment>>getAll() {
		List<Investment> investments=investmentService.getAll();
		HttpHeaders headers=new HttpHeaders();
		headers.add("desc", " All Inestments");
		headers.add("info","Getting investments frm db");
		
		ResponseEntity<List<Investment>> responseEntity=new ResponseEntity<>(investments,headers,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/investments/planId/{id}")
	public  ResponseEntity<Investment> getById(@PathVariable("id") int planId) {
		Investment investments=investmentService.getById(planId);
		HttpHeaders headers=new HttpHeaders();
		headers.add("desc", " Get All Inestments By Purpose");
		headers.add("info","Info RST API by purpose ");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(investments);
	}
	@GetMapping("/investments/planId/{id}/amount/{amount}")
	public  ResponseEntity<Investment> getById(@PathVariable("id") int planId,@PathVariable("amount") double amount) {
		investmentService.updateInvestmentAmount(planId, amount);
		HttpHeaders headers=new HttpHeaders();
		headers.add("desc", " Get All Inestments By Purpose");
		headers.add("info","Info RST API by purpose ");
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
}
