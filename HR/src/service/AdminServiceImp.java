package service;

import java.util.List;

import dao.AdministratorDaoImp;
import dao.IAdministratorDao;
import pojo.AdministratorDao;
import pojo.DaoFactory;

public class AdminServiceImp implements IAdminService {

	// 通过工厂获取管理员对象 只创建一个 减少了空间的开销
	AdministratorDao administrator = DaoFactory.createAdministratorDao();
	// 通过多态来实现获取对象
	IAdministratorDao adminDao = new AdministratorDaoImp();

	@Override
	public  List<AdministratorDao> getListALL(AdministratorDao administratorDao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int isExist(String name, String pwd) {
		
		return adminDao.isExist(name, pwd);
	}

}
