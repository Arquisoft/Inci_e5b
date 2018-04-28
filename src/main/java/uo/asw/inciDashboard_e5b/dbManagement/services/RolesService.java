package uo.asw.inciDashboard_e5b.dbManagement.services;

import org.springframework.stereotype.Service;

@Service
public class RolesService {
	
	String[] roles = {"ROLE_OPERATOR"};
		
	public String[] getRoles() {
		return roles;
	}
}
