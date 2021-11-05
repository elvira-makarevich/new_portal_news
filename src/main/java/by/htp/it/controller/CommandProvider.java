package by.htp.it.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.it.controller.impl.SignIn;
import by.htp.it.controller.impl.AddComment;
import by.htp.it.controller.impl.AddNews;
import by.htp.it.controller.impl.AddToFavorites;
import by.htp.it.controller.impl.ApprovePublication;
import by.htp.it.controller.impl.ChangeLocale;
import by.htp.it.controller.impl.ChangePassword;
import by.htp.it.controller.impl.DeleteFromFavorites;
import by.htp.it.controller.impl.DeleteNews;
import by.htp.it.controller.impl.DenyToPublish;
import by.htp.it.controller.impl.EditNews;
import by.htp.it.controller.impl.GoToAddNewsPage;
import by.htp.it.controller.impl.GoToAuthorizationPage;
import by.htp.it.controller.impl.GoToChangePasswordPage;
import by.htp.it.controller.impl.GoToCheckOfferedNewsPage;
import by.htp.it.controller.impl.GoToEditNewsPage;
import by.htp.it.controller.impl.GoToMainPage;
import by.htp.it.controller.impl.GoToOfferNewsPage;
import by.htp.it.controller.impl.GoToRegistrationPage;
import by.htp.it.controller.impl.LogOut;
import by.htp.it.controller.impl.OfferNews;
import by.htp.it.controller.impl.OpenProfile;
import by.htp.it.controller.impl.ReadAllNews;
import by.htp.it.controller.impl.ReadNews;
import by.htp.it.controller.impl.RegistrationNewUser;
import by.htp.it.controller.impl.ViewFavoriteNews;
import by.htp.it.controller.impl.ViewUserOfferedNews;
import by.htp.it.controller.impl.ViewOfferedNews;
import by.htp.it.controller.impl.UnknownCommand;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.GO_TO_AUTHORIZATION_PAGE, new GoToAuthorizationPage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.UNKNOWN_COMMAND, new UnknownCommand());
		commands.put(CommandName.SIGN_IN, new SignIn());
		commands.put(CommandName.REGISTRATION_NEW_USER, new RegistrationNewUser());
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.LOG_OUT, new LogOut());
		commands.put(CommandName.GO_TO_ADD_NEWS_PAGE, new GoToAddNewsPage());
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
		commands.put(CommandName.ADD_NEWS, new AddNews());
		commands.put(CommandName.DELETE_NEWS, new DeleteNews());
		commands.put(CommandName.READ_NEWS, new ReadNews());
		commands.put(CommandName.ADD_TO_FAVORITES, new AddToFavorites());
		commands.put(CommandName.DELETE_FROM_FAVORITES, new DeleteFromFavorites());
		commands.put(CommandName.GO_TO_EDIT_NEWS_PAGE, new GoToEditNewsPage());
		commands.put(CommandName.EDIT_NEWS, new EditNews());
		commands.put(CommandName.READ_ALL_NEWS, new ReadAllNews());
		commands.put(CommandName.OPEN_PROFILE, new OpenProfile());
		commands.put(CommandName.VIEW_FAVORITE_NEWS, new ViewFavoriteNews());
		commands.put(CommandName.ADD_COMMENT, new AddComment());
		commands.put(CommandName.OFFER_NEWS, new OfferNews());
		commands.put(CommandName.GO_TO_OFFER_NEWS_PAGE, new GoToOfferNewsPage());
		commands.put(CommandName.VIEW_USER_OFFERED_NEWS, new ViewUserOfferedNews());
		commands.put(CommandName.VIEW_OFFERED_NEWS, new ViewOfferedNews());
		commands.put(CommandName.DENY_TO_PUBLISH, new DenyToPublish());
		commands.put(CommandName.GO_TO_CHECK_OFFERED_NEWS_PAGE, new GoToCheckOfferedNewsPage());
		commands.put(CommandName.APPROVE_PUBLICATION, new ApprovePublication());
		commands.put(CommandName.GO_TO_CHANGE_PASSWORD_PAGE, new GoToChangePasswordPage());
		commands.put(CommandName.CHANGE_PASSWORD, new ChangePassword());
		
		
	}

	public Command findCommand(String name) {

		if (name == null) {
			name = CommandName.UNKNOWN_COMMAND.toString();
		}

		CommandName commandName;

		try {
			commandName = CommandName.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException e) {
			// logging
			commandName = CommandName.UNKNOWN_COMMAND;
		}
		Command command = commands.get(commandName);
		return command;
	}

}
