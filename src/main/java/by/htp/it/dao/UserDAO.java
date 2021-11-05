package by.htp.it.dao;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.dao.exception.DAOException;
import by.htp.it.dao.exception.DAOExceptionInvalidPassword;
import by.htp.it.dao.exception.DAOExceptionLoginOrEmailExists;

public interface UserDAO {

	void register(RegistrationInfo info) throws DAOException, DAOExceptionLoginOrEmailExists;
	
	User getUser(String login) throws DAOException;

	void changePassword(RegistrationInfo info) throws DAOException, DAOExceptionInvalidPassword;

}
