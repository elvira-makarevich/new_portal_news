package by.htp.it.service.validator;

import by.htp.it.bean.Comment;
import by.htp.it.service.exception.ServiceExceptionValidationComment;

public class CommentServiceValidator {

	public static final String[] BAD_WORDS = { "ss", "ee", "ff", "kk" };
	public static final String DELIMETERS = "[[ ]*|[,]*|[\\.]*|[:]*|[/]*|[-]*|[$]*|[#]*|[!]*|[?]*|[\"]*|[+]*]+";

	public void validateAddComment(Comment comment) throws ServiceExceptionValidationComment {

		String contentComment = comment.getContent();
		String[] arrayContent = contentComment.split(DELIMETERS);

		if (isThereBadWords(arrayContent)) {

			throw new ServiceExceptionValidationComment(
					"There are objectionable words in your comment. It won't be published.");
		}

	}

	private static boolean isThereBadWords(String[] arrayForChecking) {

		for (int i = 0; i < arrayForChecking.length; i++) {
			for (int j = 0; j < BAD_WORDS.length; j++) {
				if (arrayForChecking[i].toLowerCase().equals(BAD_WORDS[j])) {
					return true;
				}
			}
		}
		return false;
	}

}
