package com.planetsystems.monitoring.client.managers;

import java.util.ArrayList;


import java.util.List;

import com.planetsystems.monitoring.model.User;


public class UsersManager {
	private static UsersManager instance=new UsersManager();
	public List<User> usersList=new ArrayList<User>();
	private UsersManager(){
		
	}
	public static UsersManager getInstance() {
		return instance;
	}
	public List<User> searchUsers(String searchParameter){
		List<User> list=new ArrayList<User>();
		try{
			if(searchParameter==null){
				list=usersList;
			}else{
			for(User user:usersList){
				if(user.getUsername().equalsIgnoreCase("administrator")){
					list.add(user);
				}else{
				if (( user.getUsername().toLowerCase().startsWith(searchParameter.toLowerCase())
						|| user.getfName().toLowerCase().startsWith(searchParameter.toLowerCase())
						|| user.getlName().toLowerCase().startsWith(searchParameter.toLowerCase())
						|| user.getEmail().toLowerCase().startsWith(searchParameter.toLowerCase())
						|| user.getPhoneNumber().toLowerCase().startsWith(searchParameter.toLowerCase())
						//|| user.getUserCatergory().name().toLowerCase().startsWith(searchParameter.toLowerCase())
						|| user.getDivision().getDivisionCode().toLowerCase().startsWith(searchParameter.toLowerCase())
						|| user.getDivision().getDivisionName().toLowerCase().startsWith(searchParameter.toLowerCase())
						|| user.getDepartment().getDepartmentCode().toLowerCase().startsWith(searchParameter.toLowerCase())
						|| user.getDepartment().getDepartmentName().toLowerCase().startsWith(searchParameter.toLowerCase())
						)) {
					list.add(user);
					System.out.println("list.size()==>>"+list.size());
	            } else {
	            }
				}
			}
		}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return list;
	}
	
	

}
