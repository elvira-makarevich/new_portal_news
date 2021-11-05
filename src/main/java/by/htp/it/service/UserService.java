package by.htp.it.service;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.service.exception.ServiceException;
import by.htp.it.service.exception.ServiceExceptionInvalidData;
import by.htp.it.service.exception.ServiceExceptionInvalidPassword;
import by.htp.it.service.exception.ServiceExceptionLoginOrEmailExists;
import by.htp.it.service.exception.ServiceExceptionValidationNews;
import by.htp.it.service.exception.ServiceExceptionValidationPassword;
import by.htp.it.service.exception.ServiceExceptionValidationUser;

public interface UserService {
	
	void register(RegistrationInfo info) throws ServiceException, ServiceExceptionLoginOrEmailExists, ServiceExceptionValidationNews;

	User signIn(User unauthorizedUser) throws ServiceException, ServiceExceptionInvalidData, ServiceExceptionValidationUser;

	void changePassword(RegistrationInfo info) throws ServiceException, ServiceExceptionInvalidPassword, ServiceExceptionValidationPassword;
	
}
