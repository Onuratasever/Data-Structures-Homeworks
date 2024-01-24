package homework1;

/**
 * Post class keep details about post.
 * @author Onur
 *
 */
public class Post {

	/**
	 * Post ID
	 */
	private String postID;

	/**
	 * Account ID
	 */
	private String accountID;

	/**
	 * It keeps likes of post
	 */
	private Like[] likes;

	/**
	 * It keeps comments of post
	 */
	private Comment[] comments;

	/**
	 * It is explanation of post
	 */
	private Comment content;

	/**
	 * This is constructor.
	 * @param content It is the content of post.
	 * @param accountID Account ID which is belong to person who is liked or added comment to post.
	 * @param postID The post ID which is liked or added comment.
	 */
	Post(String content, String accountID, int postID)
	{
		this.content = new Comment(content, accountID, Integer.toString(postID), "User", "-1");
		this.accountID = accountID;
		this.postID = Integer.toString(postID);
		likes = new Like[0];
		comments = new Comment[0];
	}
	
	/**
	 * @return It returns content of post.
	 */
	public String getContent()
	{
		return content.getContent();
	}
	
	/**
	 * It display posts.
	 */
	public void display()
	{
		System.out.println("----------------\n(PostID: " + postID + "): " + content.getContent());
	}
	
	/**
	 * It resizes likes array for each liking.
	 */
	public void resizeLike()
	{
		int temp_size = likes.length, i=0;
		
		Like[] temp = new Like[temp_size];
		
		for(i=0; i<temp_size; i++)
		{
			temp[i] = new Like(likes[i].getAccountID(), postID,likes[i].getUsername(), likes[i].getInteractionID());
		}
		
		likes = new Like[temp_size + 1];
		
		for(i=0; i<temp_size; i++)
		{
			likes[i] = new Like(temp[i].getAccountID(), postID,temp[i].getUsername(), temp[i].getInteractionID());
		}
	}
	
	/**
	 * It resizes comments array for each adding comment.
	 */
	public void resizeComment()
	{
		int temp_size = comments.length, i=0;
		
		Comment[] temp = new Comment[temp_size];
		
		for(i=0; i<temp_size; i++)
		{
			temp[i] = new Comment(comments[i].getContent(), comments[i].getAccountID(), comments[i].getPostID(), comments[i].getUsername(), comments[i].getInteractionID());
		}
		
		comments = new Comment[temp_size + 1];
		
		for(i=0; i<temp_size; i++)
		{
			comments[i] = new Comment(temp[i].getContent(), temp[i].getAccountID(), temp[i].getPostID(), temp[i].getUsername(), temp[i].getInteractionID());
		}
	}
	
	/**
	 * It adds like details to likes array.
	 * @param username The person who liked the post.
	 * @param accountID The account id which is belong to person who is liked the post.
	 * @param interactionID Interaction ID.
	 */
	public void like(String username, String accountID, String interactionID)
	{
		resizeLike();
		likes[likes.length - 1] = new Like(accountID, postID, username, interactionID);
	}
	
	/**
	 * It prints details about comment and likes
	 * @param display It can be like or comment to show details.
	 */
	public void displayInteractions(Interaction[] display)
	{
		int i;
		
		for(i=0; i<display.length; i++)
		{
			display[i].displayInfo(i);
		}
		System.out.println("");
	}
	
	/**
	 * It shows interactions
	 */
	public void showInteractions()
	{
		display();
		if(likes.length != 0)
		{
			System.out.printf("The post was liked by the following account(s): ");
		}
		else
		{
			System.out.printf("Post has no likes");
		}
		displayInteractions(likes);
		
		System.out.printf("The post has %d comment(s)... \n", comments.length);
		displayInteractions(comments);
	}
	
	/**
	 * It adds comment details to comment array.
	 * @param username The person who added comment to the post.
	 * @param accountID The account ID which is belongs to the person who added comment to the post.
	 * @param comment This is the comment which is wanted to be added.
	 * @param InteractionID Interaction ID.
	 */
	public void addComment(String username, String accountID, String comment, String InteractionID)
	{
		resizeComment();
		comments[comments.length-1] = new Comment(comment, accountID, postID, username, InteractionID);
	}
	
}
