package homework2;

/**
 * Interaction class.
 * It keeps details of interaction.
 * @author Onur
 *
 */
public class Interaction {
	
	private String InteractionID;
	private String accountID;
	private String postID;
	private String username;
	
	/**
	 * It is superclass.
	 * @param accountID Account ID who is added comment to post.
	 * @param postID The post ID which is added comment.
	 * @param username The person who adds the comment. 
	 * @param interactionID Interaction ID.
	 */
	Interaction(String accountID, String postID, String username, String interactionID)
	{
		this.accountID = accountID;
		this.postID = postID;
		this.username = username;
		this.InteractionID = interactionID;
	}
	
	/**
	 * @return It returns username.
	 */
	public String getUsername()
	{
		return username;
	}
	
	/**
	 * @return It returns Account ID.
	 */
	public String getAccountID()
	{
		return accountID;
	}
	
	/**
	 * @return It returns Post ID
	 */
	public String getPostID()
	{
		return postID;
	}
	
	/**
	 * @return It returns Interaction ID
	 */
	public String getInteractionID()
	{
		return InteractionID;
	}
	
	/**
	 * It display informations of interaction.
	 * @param i This is the number of interaction.
	 */
	public void displayInfo(int i)
	{
		System.out.printf("InteractionID: %s \nAccountID: %s\nPostID: %s\nLiked by: %s", InteractionID, accountID, postID, username);
		
	}
}
