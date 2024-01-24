package homework2_1;

import java.util.LinkedList;

/**
 * Post class keep details about post.
 * @author Onur
 *
 */
public class Post {

	private String postID;
	private String accountID;
	//private Like[] likes;
	//private ArrayList<Interaction> likes;
	private LinkedList<Interaction> likes;
	
	//private Comment[] comments;
	//private ArrayList<Interaction> comments;
	private LinkedList<Interaction> comments;
	
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
		likes = new LinkedList<Interaction>();
		comments = new LinkedList<Interaction>();
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
	 * It adds like details to likes array.
	 * @param username The person who liked the post.
	 * @param accountID The account id which is belong to person who is liked the post.
	 * @param interactionID Interaction ID.
	 */
	public void like(String username, String accountID, String interactionID)
	{
		//resizeLike();
		//likes[likes.length - 1] = new Like(accountID, postID, username, interactionID);
		likes.add(new Like(accountID, postID, username, interactionID));
	}
	
	/**
	 * It prints details about comment and likes
	 * @param display It can be like or comment to show details.
	 */
	public void displayInteractions(LinkedList<Interaction> display)
	{
		int i;
		
		for(i=0; i<display.size(); i++)
		{
			display.get(i).displayInfo(i);
		}
		System.out.println("");
	}
	
	/**
	 * It shows interactions
	 */
	public void showInteractions()
	{
		display();
		if(likes.size() != 0)
		{
			System.out.printf("The post was liked by the following account(s): ");
		}
		else
		{
			System.out.printf("Post has no likes");
		}
		displayInteractions(likes);
		
		System.out.printf("The post has %d comment(s)... \n", comments.size());
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
		//resizeComment();
		//comments[comments.length-1] = new Comment(comment, accountID, postID, username, InteractionID);
		comments.add(new Comment(comment, accountID, postID, username, InteractionID));
	}
	
	public void removeLike(Account user)
	{
		int index;
		boolean isLiked = false;
		for(index = 0; index < likes.size(); index++)
		{
			if(likes.get(index).getUsername().equals(user.getUserName()))
			{
				isLiked = true;
				break;
			}
		}
		if(isLiked)
		{
			likes.remove(index);
			System.out.printf("Post %s is unliked\n", postID);
		}
		else
		{
			System.out.println("Before unlike a post, you should have liked");
		}
	}
	
	public void removeComment(Account user)
	{
		int index;
		boolean isComment = false;
		for(index = 0; index < comments.size(); index++)
		{
			if(comments.get(index).getUsername().equals(user.getUserName()))
			{
				isComment = true;
				break;
			}
		}
		if(isComment)
		{
			comments.remove(index);
			System.out.printf("Post %s is uncommented\n", postID);
		}
		else
		{
			System.out.println("Before uncomment a post, you should add comment");
		}
	}
	
}
