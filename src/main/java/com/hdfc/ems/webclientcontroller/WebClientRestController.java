package com.hdfc.ems.webclientcontroller;



import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.hdfc.ems.entity.Employee;
import com.hdfc.ems.entity.EmployeeDTO;
import com.hdfc.ems.util.AESDecrypt;
@RestController
@RequestMapping("api/webclient")
public class WebClientRestController {
	@Autowired
	RestTemplate rest;
	String url="https://localhost:9696/api/employee/";
	
	@GetMapping("/getemployeeinfo/{id}")
	public Employee getEmployeeData(@PathVariable long id) throws HttpClientErrorException,Exception{
		
		EmployeeDTO result =rest.getForObject(url+"/findbyemployeeid/"+id, EmployeeDTO.class);

		return getDTO(result);
	
	}
	
	public Employee getDTO(EmployeeDTO empdto) throws Exception{
		Employee emp=new Employee();
		byte[] dob=AESDecrypt.decrypt(empdto.getDateOfBirth());
		LocalDate date=LocalDate.parse(new String(dob,StandardCharsets.UTF_8));
		emp.setEmployeeId(empdto.getEmployeeId());
		emp.setEmployeeName(empdto.getEmployeeName());	
		emp.setDateOfBirth(date);	
		return emp;
	};


}
