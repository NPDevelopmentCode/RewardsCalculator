package github.assessment.rewards.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.assessment.rewards.dto.RewardsReport;
import github.assessment.rewards.models.Transaction;
import github.assessment.rewards.services.TransactionService;
import github.assessment.rewards.utils.RewardsCalculator;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping(path = "/rewards")
public class RewardsController {
	
    @Value("${rewards.service}")
    private String serviceClassName;
    
	private TransactionService transactionService;
	
    @PostConstruct
    public void create() {
    	transactionService = createTransactionService(serviceClassName);
    	transactionService.initService();
    }
    
    public TransactionService createTransactionService(String serviceClassName) {
    	TransactionService transactionService = null;
    	
	    try {
	    	Class<?> serviceClass = Class.forName(serviceClassName);
	    	Object serviceInstance = serviceClass.getDeclaredConstructor().newInstance();
	    	
	    	transactionService = (TransactionService) serviceInstance;
	    } catch (ClassNotFoundException e) {
	        System.err.println("Class not found: " + e.getMessage());
	    } catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	    
	    return transactionService;
    }
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<RewardsReport> getRewardsForUser(@PathVariable String id) {
		List<Transaction> userTransactions = transactionService.getTransactionsForUser(id);
		
		if ( userTransactions != null ) {
			
			int rewards = RewardsCalculator.calculateRewards(userTransactions);
			
			RewardsReport report = new RewardsReport(id,rewards);
			
			return ResponseEntity.status(HttpStatus.OK).body(report);
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping(path = "/{id}/{year}/{month}")
	public ResponseEntity<RewardsReport> getRewardsForUserByMonth(@PathVariable String id, @PathVariable int year , @PathVariable int  month) {
		List<Transaction> userTransactions = transactionService.getTransactionsForUserByMonth(id, year, month);
		
		if ( userTransactions != null ) {
			
			int rewards = RewardsCalculator.calculateRewards(userTransactions);
			
			RewardsReport report = new RewardsReport(id,rewards);
			
			return ResponseEntity.status(HttpStatus.OK).body(report);
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}

	public void setServiceClassName(String serviceClassName) {
		this.serviceClassName = serviceClassName;
	}
}
