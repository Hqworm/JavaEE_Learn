package service;

import java.util.List;

import pojo.AdministratorDao;

public interface IAdminService {

	public List<AdministratorDao> getListALL(AdministratorDao administratorDao);
	public int isExist(String name, String pwd) ;
}
