package service;

import java.util.List;

import dao.AdministratorDaoImp;
import dao.IAdministratorDao;
import pojo.AdministratorDao;
import pojo.DaoFactory;

public class AdminServiceImp implements IAdminService {

	// ͨ��������ȡ����Ա���� ֻ����һ�� �����˿ռ�Ŀ���
	AdministratorDao administrator = DaoFactory.createAdministratorDao();
	// ͨ����̬��ʵ�ֻ�ȡ����
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
