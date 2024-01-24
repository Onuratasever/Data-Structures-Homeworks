package homework2_2;

/**
 * It extends from interaction class.
 * It keeps details of like interactions.
 * @author Onur
 *
 */
public class Like extends Interaction{

	/**
	 * It invokes super class's constructor.
	 * @param accountID  Account ID who is added comment to post.
	 * @param postID The post ID which is added comment.
	 * @param username The person who adds the comment. 
	 * @param interactionID Interaction ID.
	 */
	Like(String accountID, String postID, String username, String interactionID)
	{
		super(accountID, postID, username, interactionID);
	}

	@Override
	/**
	 * It is overrided function. It prints details of like.
	 * @param i It is nothing for this overrided function.
	 */
	public void displayInfo(int i) {
		
		System.out.printf(" %s ", getUsername());
	}
	
	
}