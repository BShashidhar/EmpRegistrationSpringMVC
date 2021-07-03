package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.EmployeeDao;
import com.springmvc.model.Employee;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	@Transactional
	public Employee findById(int id) {
		return employeeDao.findById(id);
	}
	@Transactional
	public void saveEmployee(Employee employee) {
		employeeDao.saveEmployee(employee);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with proper
	 * values within transaction. It will be updated in db once transaction ends.
	 */
	@Transactional
	public void updateEmployee(Employee employee) {
		Employee entity = employeeDao.findById(employee.getId());
		if (entity != null) {
			entity.setName(employee.getName());
			entity.setJoiningDate(employee.getJoiningDate());
			entity.setSalary(employee.getSalary());
			entity.setSsn(employee.getSsn());
		}
	}
	
	@Transactional
	public void deleteEmployeeBySsn(String ssn) {
		employeeDao.deleteEmployeeBySsn(ssn);
	}
	@Transactional
	public List<Employee> findAllEmployees() {
		return employeeDao.findAllEmployees();
	}
	@Transactional
	public Employee findEmployeeBySsn(String ssn) {
		return employeeDao.findEmployeeBySsn(ssn);
	}
	@Transactional
	public boolean isEmployeeSsnUnique(Integer id, String ssn) {
		Employee employee = findEmployeeBySsn(ssn);
		return (employee == null || ((id != null) && (employee.getId() == id)));
	}

}
