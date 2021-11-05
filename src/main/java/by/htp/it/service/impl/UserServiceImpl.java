package by.htp.it.service.impl;

import org.mindrot.jbcrypt.BCrypt;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.dao.DAOProvider;
import by.htp.it.dao.UserDAO;
import by.htp.it.dao.exception.DAOException;
import by.htp.it.dao.exception.DAOExceptionInvalidPassword;
import by.htp.it.dao.exception.DAOExceptionLoginOrEmailExists;
import by.htp.it.service.UserService;
import by.htp.it.service.exception.ServiceException;
import by.htp.it.service.exception.ServiceExceptionInvalidData;
import by.htp.it.service.exception.ServiceExceptionInvalidPassword;
import by.htp.it.service.exception.ServiceExceptionLoginOrEmailExists;
import by.htp.it.service.exception.ServiceExceptionValidationPassword;
import by.htp.it.service.exception.ServiceExceptionValidationUser;
import by.htp.it.service.validator.UserServiceValidator;

public class UserServiceImpl implements UserService {

	private static final DAOProvider daoProvider = DAOProvider.getInstance();
	private static final UserDAO userDAO = daoProvider.getSQLUserDAOImpl();
	private static final UserServiceValidator userServiceValidator = new UserServiceValidator();

	@Override
	public void register(RegistrationInfo info)
			throws ServiceException, ServiceExceptionLoginOrEmailExists, ServiceExceptionValidationUser {

		userServiceValidator.validateByRegistration(info);

		info.setPassword(BCrypt.hashpw(info.getPassword(), BCrypt.gensalt()));

		try {
			userDAO.register(info);
		} catch (DAOExceptionLoginOrEmailExists e) {

			throw new ServiceExceptionLoginOrEmailExists(e);
		} catch (DAOException e) {

			throw new ServiceException(e);
		}

	}

	@Override
	public User signIn(User unauthorizedUser)
			throws ServiceException, ServiceExceptionInvalidData, ServiceExceptionValidationUser {

		User user;

		userServiceValidator.validateByAuthorization(unauthorizedUser);

		try {

			user = userDAO.getUser(unauthorizedUser.getLogin());

			if (user == null) {
				throw new ServiceExceptionInvalidData("Incorrect login and (or) password.");
			}

			if (!checkPass(unauthorizedUser.getPassword(), user.getPassword())) {
				throw new ServiceExceptionInvalidData("Incorrect login and (or) password.");
			}
			user.setPassword(null);
		} catch (DAOException e) {

			throw new ServiceException(e);
		}

		return user;

	}

	@Override
	public void changePassword(RegistrationInfo info)
			throws ServiceException, ServiceExceptionInvalidPassword, ServiceExceptionValidationPassword {

		User user;
		userServiceValidator.validateByChangingPassword(info);

		try {
			user = userDAO.getUser(info.getLogin());

			if (user == null) {
				throw new ServiceExceptionInvalidData("Incorrect login and (or) password.");
			}

			if (!checkPass(info.getPassword(), user.getPassword())) {
				throw new ServiceExceptionInvalidData("Incorrect login and (or) password.");
			}

			info.setNewPassword(BCrypt.hashpw(info.getNewPassword(), BCrypt.gensalt()));
			userDAO.changePassword(info);

		} catch (DAOExceptionInvalidPassword e) {

			throw new ServiceExceptionInvalidPassword(e);

		} catch (DAOException e) {

			throw new ServiceException(e);
		}

	}

	private boolean checkPass(String plainPassword, String hashedPassword) {
		if (BCrypt.checkpw(plainPassword, hashedPassword)) {
			return true;
		} else {
			return false;
		}
	}

}
