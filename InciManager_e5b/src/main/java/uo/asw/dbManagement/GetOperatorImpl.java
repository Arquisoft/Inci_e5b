package uo.asw.dbManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uo.asw.dbManagement.model.Operator;
import uo.asw.dbManagement.repositories.OperatorsRepository;

@Component
public class GetOperatorImpl implements GetOperator {

	@Autowired
	private OperatorsRepository operatorsRepository;
	
	@Override
	public Operator getOperator(String identifier) {
		return operatorsRepository.getOperator(identifier);
	}

}
