package homework2_1;


/**
 * It extends Interaction class.
 * It keeps the content and comment of post.
 * @author Onur
 *
 */
public class Comment extends Interaction{

	private String content;
	
	/**
	 * It invokes super class's constructor.
	 * @param content The content of post or comment.
	 * @param AccountID Account ID who is added comment to post.
	 * @param postID The post ID which is added comment.
	 * @param Username The person who adds the comment. 
	 * @param interactionID Interaction ID.
	 */
	Comment(String content, String AccountID, String postID, String Username, String interactionID)
	{
		super(AccountID, postID, Username, interactionID);
		this.content = content;
	}
	
	/**
	 * @return content or comment.
	 */
	public String getContent()
	{
		return content;
	}
	
	@Override
	/**
	 * It is overrided function. It prints details of comment.
	 * @param i It is the number of comment.
	 */
	public void displayInfo(int i) {
		
		System.out.printf("Comment %d: '%s' said '%s' \n", i+1, getUsername(), content);
	}
}
