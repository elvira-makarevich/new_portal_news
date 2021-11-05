package by.htp.it.service.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import by.htp.it.dao.DAOProvider;
import by.htp.it.dao.UserDAO;
import by.htp.it.dao.impl.SQLUserDAO;
import by.htp.it.service.validator.UserServiceValidator;

public class UserServiceImplTest {
		
	
	
	@Mock
	private SQLUserDAO userDAO;
	
	@InjectMocks
	private UserServiceImpl userService;

	
	@Test
	public void testMockCreation() {
		assertNotNull(userDAO);
	}

}
