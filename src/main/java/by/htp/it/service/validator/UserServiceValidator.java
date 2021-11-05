package by.htp.it.service.validator;

import java.util.ArrayList;
import java.util.List;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.service.exception.ServiceExceptionValidationPassword;
import by.htp.it.service.exception.ServiceExceptionValidationUser;

public class UserServiceValidator {

	public static final String PATTERN_EMAIL = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
	public static final String PATTERN_LOGIN = "([a-zA-Zа-яА-яёЁ!_$^]{6,20}$)";
	public static final String PATTERN_PASSWORD = "((?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,})";
	public static final String PATTERN_NAME_OR_SURNAME = "([a-zA-Z]{2,20}$)|([а-яА-яёЁ]{2,20}$)";

	public static final String REQUIREMENTS_TO_NAME = "Имя должно содержать латинские или русские буквы, от 2 до 20 символов.";
	public static final String REQUIREMENTS_TO_SURNAME = "Фамилия должна содержать латинские или русские буквы, от 2 до 20 символов.";
	public static final String REQUIREMENTS_TO_LOGIN = "Логин должен содержать от 6 до 20 символов латинского и (или) русского алфавита, в том числе может содержать символы ! _ $ ^.";
	public static final String REQUIREMENTS_TO_EMAIL = "Введенный email не существует.";
	public static final String REQUIREMENTS_TO_PASSWORD = "Пароль должен содержать:" + "\n" + " - хотя бы одно число;"
			+ "\n" + " - хотя бы один спецсимвол [!@#$%^&*];" + "\n"
			+ " - хотя бы одну латинскую букву в нижнем регистре;" + "\n"
			+ " - хотя бы одну латинскую букву в верхнем регистре;" + "\n"
			+ "- состоит не менее, чем из 8 вышеупомянутых символов.";

	// для имени/фамилии
	// -либо латинскими, либо кириллицей от 2 до 20 символов

	// для логина
	// -от 6 до 20 (латинские и (или) кириллицей) символов, в том числе может
	// содержать ! _ $ ^

	// пароль содержит
	// - хотя бы одно число;
	// - хотя бы один спецсимвол [!@#$%^&*];
	// - хотя бы одну латинскую букву в нижнем регистре;
	// - хотя бы одну латинскую букву в верхнем регистре;
	// - состоит не менее, чем из 8 вышеупомянутых символов.

	public void validateByRegistration(RegistrationInfo info) throws ServiceExceptionValidationUser {

		List<String> errors = new ArrayList<>();

		if (!info.getName().matches(PATTERN_NAME_OR_SURNAME)) {
			errors.add(REQUIREMENTS_TO_NAME);
		}

		if (!info.getSurname().matches(PATTERN_NAME_OR_SURNAME)) {
			errors.add(REQUIREMENTS_TO_SURNAME);
		}

		if (!info.getLogin().matches(PATTERN_LOGIN)) {
			errors.add(REQUIREMENTS_TO_LOGIN);
		}

		if (!info.getPassword().matches(PATTERN_PASSWORD)) {
			errors.add(REQUIREMENTS_TO_PASSWORD);
		}

		if (!info.getEmail().matches(PATTERN_EMAIL)) {
			errors.add(REQUIREMENTS_TO_EMAIL);
		}

		if (!errors.isEmpty()) {
			// на самом деле сами ошибки потом не выводятся, выводится список правил,
			// прописанных в локали
			throw new ServiceExceptionValidationUser(errors);

		}

	}

	public void validateByAuthorization(User unauthorizedUser) throws ServiceExceptionValidationUser {

		List<String> errors = new ArrayList<>();

		if (!unauthorizedUser.getLogin().matches(PATTERN_LOGIN)) {
			errors.add(REQUIREMENTS_TO_LOGIN);
		}

		if (!unauthorizedUser.getPassword().matches(PATTERN_PASSWORD)) {
			errors.add(REQUIREMENTS_TO_PASSWORD);
		}

		if (!errors.isEmpty()) {
			// на самом деле сами ошибки потом не выводятся, выводится список правил,
			// прописанных в локали
			throw new ServiceExceptionValidationUser(errors);

		}

	}

	public void validateByChangingPassword(RegistrationInfo info) throws ServiceExceptionValidationPassword {

		if (!info.getNewPassword().matches(PATTERN_PASSWORD)) {
			throw new ServiceExceptionValidationPassword("New password does not meet security requirements.");
		}

	}

}
