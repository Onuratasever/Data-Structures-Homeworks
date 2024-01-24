package homework1;

/**
 * This class keeps User data.
 * @author Onur
 *
 */
public class Account {
	/**
	 * Account ID of user
	 */
	private String accountID;
	
	/**
	 * Username of user
	 */
	private String username;
	
	/**
	 * Birth date of user
	 */
	private String birthdate;
	
	/**
	 * Location of user
	 */
	private String location;
	
	/**
	 * It controls if user log in
	 */
	private Boolean LogIn;
	
	/**
	 * It keeps current user viewing whose profile and whose posts.
	 */
	private String[] WhosProfile ;	
	
	/**
	 * It keeps shared posts.
	 */
	private Post[] posts;
	
	/**
	 * It keeps receiving messages.
	 */
	private Message[] messages_inbox;
	
	/**
	 * It keeps sended messages.
	 */
	private Message[] messages_outbox;
	
	/**
	 * It keeps following.
	 */
	private String[] following;
	
	/**
	 * It keeps followers.
	 */
	private String[] followers;
	
	/**
	 * It keeps blocked users list.
	 */
	private String[] blocked;
	
	/**
	 * It keeps interaction (each like and comment).
	 */
	private Interaction[] interactions;
	
	/**
	 * Constructor assigns the parameters to data. And it invokes other constructor to arrangments.
	 * @param username Taken username
	 * @param birthdate Taken birthdate from user
	 * @param location Taken location from user
	 * @param IdNum Account number
	 */
	Account(String username, String birthdate, String location, int IdNum)
	{
		
		this(username, birthdate, location, Integer.toString(IdNum));
		/*System.out.println("Account cagrildi");
		this.accountID = Integer.toString(IdNum);
		this.username = username;
		this.birthdate = birthdate;
		this.location = location;
		
		posts = new Post();
		messages = new Message();*/
	}
	
	/**
	 * Constructor assigns the parameters to data. And it arranges size of arrays.
	 * @param username Taken username
	 * @param birthdate Taken birthdate from user
	 * @param location Taken location from user
	 * @param IdNum Account number
	 */
	Account(String username, String birthdate, String location, String IdNum)
	{
		
		this.accountID = IdNum;
		this.username = username;
		this.birthdate = birthdate;
		this.location = location;
		this.LogIn = false;
		
		followers = new String[0];
		following = new String[0];
		posts = new Post[0];
		messages_inbox = new Message[0];
		messages_outbox = new Message[0];
		blocked = new String[0];
		interactions = new Interaction[0];
		WhosProfile = new String[2];
		WhosProfile[0] = "Nobody";
		WhosProfile[1] = "Nobody";
		System.out.println("An account with username " + getUserName() + " has been created successfully");
	}
	
	/**
	 * Account log in is system is avaliable.
	 * @param isOk Only one user can log in at the same time. This parameter controls it.
	 */
	public void LogIn(int[] isOk)
	{
		if(isOk[0] == 0)
		{
			LogIn = true;
			System.out.println("Logging into an account (username: " + username + ")...");
			isOk[0] = 1;
		}
		else
		{
			System.out.println(username + " you should log out from other account");
		}
	}
	
	/**
	 * Account log out from system.
	 * @param isOk Only one user can log in at the same time. This parameter controls it.
	 */
	public void LogOut(int[] isOk)
	{
		if(isOk[0] == 1)
		{
			LogIn = false;
			isOk[0] = 0;
			System.out.println("Logging out from account '" + username + "'...");
		}
		else
		{
			System.out.println(username + " you should log in before log out");
		}
	}
	
	/**
	 * @return Username
	 */
	public String getUserName()
	{
		return username;
	}
	
	/**
	 * @return Birth Date
	 */
	public String getBirthDate()
	{
		return birthdate;
	}
	
	/**
	 * @return Location
	 */
	public String getLocation()
	{
		return location;
	}
	
	/**
	 * @return Account ID
	 */
	public String getAccountID()
	{
		return accountID;
	}
	
	/**
	 * It shares post with given content.
	 * @param content Explaination from user.
	 */
	public void sharePost(String content)
	{
		if(LogIn)
		{
			resizePost();
			
			posts[posts.length - 1] = new Post(content, accountID, posts.length);
			System.out.println("Post is shared");
	
		}
		else
		{
			System.out.println(username + ", you should log in to perform action!");
		}
	}
	
	/**
	 * It resize Post array when new post will be shared.
	 */
	private void resizePost()
	{
		int temp_size = posts.length, i=0;
		
		Post[] temp = new Post[temp_size];
		
		for(i=0; i<temp_size; i++)
		{
			temp[i] = new Post(posts[i].getContent(), accountID, i+1);
		}
		
		posts = new Post[temp_size + 1];
		
		for(i=0; i<temp_size; i++)
		{
			posts[i] = new Post(temp[i].getContent(), accountID, i+1);
		}
	}
	
	/**
	 * It shows each post.
	 */
	private void displayPosts()
	{
		int i;
		//System.out.println("\n---------------\nAccountID: " + accountID);
		System.out.println("Viewing " + username + "'s posts");
		for(i=0; i<posts.length; i++)
		{
			posts[i].display();
		}
	}
	
	/**
	 * It resize follower and following arrays.
	 * @param resizeArray This is the array which is wanted to resize.
	 * @return It return resized array.
	 */
	private String[] resizeFollow(String[] resizeArray)
	{
		int temp_size = resizeArray.length, i=0;
		
		String[] temp = new String[temp_size];
		
		for(i=0; i<temp_size; i++)
		{
			temp[i] = new String(resizeArray[i]);
		}
		
		String[] resizeArray1 = new String[temp_size + 1];
		
		for(i=0; i<temp_size; i++)
		{
			resizeArray1[i] = new String(temp[i]);
		}
		return resizeArray1;
	}
	
	/**
	 * It adds person who wanted to be followed current user's following array and it adds current user to other person's follower array.
	 * @param user The person who wanted to be followed.
	 */
	public void follow(Account user)
	{
		if(LogIn)
		{
			if(!isBlocked(user))
			{
				setFollowing(user.getUserName());
				user.setFollowers(getUserName());				
			}
			else
			{
				System.out.println("User can not found");
			}
		}
		else
		{
			System.out.println(username + ", you should log in to perform action!");
		}
	}	
	
	/**
	 * It resizes followers array and adds follower user to array.
	 * @param username The person who wants to follow.
	 */
	private void setFollowers(String username)
	{
		followers = resizeFollow(followers);
		followers[followers.length - 1] = new String(username);
	}
	
	/**
	 * It resizes following array and adds following user to array.
	 * @param username The person who is wanted to be followed.
	 */
	private void setFollowing(String username)
	{
		following = resizeFollow(following);
		following[following.length - 1] = new String(username);
		System.out.println("Following " + username + "...");
	}
	
	/**
	 * It shows user's follower and following lists.
	 */
	public void displayFollow()
	{
		getFollowers();
		getFollowing();
	}
	
	/**
	 * It prints follower's names.
	 */
	private void getFollowers()
	{
		int i;
		System.out.println(username + " has " + followers.length + " followers");
		
		if(followers.length != 0)
		{
			System.out.printf("The followers of %s are:" , username );
			
			for(i=0; i<followers.length; i++)
			{
				System.out.printf("%s ", followers[i]);
			}
			System.out.println("");
		}
	}
	
	/**
	 * It prints following list.
	 */
	private void getFollowing()
	{
		int i;
		System.out.println(username + " is following " + following.length + " account(s)");
		
		if(following.length != 0)
		{
			System.out.printf("%s is following :" , username );
			
			for(i=0; i<following.length; i++)
			{
				System.out.printf("%s ", following[i]);
			}
			System.out.println("");
		}
	}
	
	/**
	 * If user that you want to see did not blocked the current user, it views profile and it saves the last profile viewed.
	 * @param user the person whose profile you want to view.
	 */
	public void viewProfile(Account user)
	{
		if(LogIn)
		{
			if(!isBlocked(user))
			{
				user.viewprofile();
				WhosProfile[0] = user.getUserName();				
			}
			else
			{
				System.out.println("User can not found");
			}
		}
		else
		{
			System.out.println(username + ", you should log in to perform action!");
		}
	}
	
	/**
	 * It prints all details of user.
	 */
	private void viewprofile()
	{
		System.out.printf("Viewing %s's profile...", username);
		System.out.println("\n----------------");
		System.out.println("User ID:" + accountID);
		System.out.println("Username: " + username);
		System.out.println("Location: " + location);
		System.out.println("Birthdate: " + birthdate);
		getFollowers();
		getFollowing();
		System.out.println(username + " has " + posts.length + " posts.");
	}
	
	/**
	 * If current user in the other users profile, it views posts and it saves the last person name whose posts are viewed.
	 * @param user the person whose posts you want to view.
	 */
	public void viewPosts(Account user)
	{
		if(LogIn)
		{
			if(WhosProfile[0].equals(user.getUserName()))
			{
				user.displayPosts();
				WhosProfile[1] = user.getUserName();
			}
			else
			{
				System.out.println("To view post, first of all you should view the profile!");
			}
		}
		else
		{
			System.out.println(username + ", you should log in to perform action!");
		}
	}
	
	/**
	 * If the user is currently in the posts of the person whose posts want to be liked, it likes the post and adds an interaction to interactions array.
	 * @param User The person whose post is wanted to be liked.
	 * @param postNumber Post number that want to be liked
	 */
	public void likePost(Account User, int postNumber)
	{
		if(LogIn)
		{
			if(WhosProfile[1].equals(User.getUserName()))
			{
				if(User.posts.length != 0)
				{
					User.addInteraction(User.getAccountID(), Integer.toString(postNumber), User.getUserName());
					User.like(username, accountID, postNumber);
					System.out.printf("Liking %d. post of %s\n", postNumber, User.getUserName());
				}
			}
			else
			{
				System.out.println("To like post you should enter the profile and then posts. Then you can like");
			}
		}
		else
		{
			System.out.println(username + ", you should log in to perform action!");
		}
	}
	
	/**
	 * It adds like to user's posts.
	 * @param username Taken username.
	 * @param accountID Taken Account ID.
	 * @param PostNumber Post number wanted to be like.
	 */
	private void like(String username, String accountID, int PostNumber)//postu beğenilen kişinin
	{
		if(PostNumber>0 && PostNumber <= posts.length)
		{
			posts[PostNumber-1].like(username, accountID, Integer.toString(interactions.length));
		}
	}
	
	/**
	 * If the last profile matches with the person you want to show, it prints all interactions with details.
	 * @param user The person whose interactions you want to show.
	 */
	public void showInteractions(Account user)
	{
		if(LogIn)
		{
			if(WhosProfile[0].equals(user.getUserName()))
			{
				int i;
				System.out.printf("%s 's post Interactions...\n",user.getUserName());

				for(i=0; i<user.posts.length; i++)
				{
					user.posts[i].showInteractions();
				}
			}
			else
			{
				System.out.println("To show interactions you should enter the profile and then posts.");
			}
		}
		else
		{
			System.out.println(username + ", you should log in to perform action!");
		}
	}
	
	/**
	 * It adds a comment to users post, if the current user is in the profile.
	 * @param user The person who wants to add comment
	 * @param postNumber Number of post which is wanted to be added comment.
	 * @param comment Comment of user
	 */
	public void addComment(Account user, int postNumber, String comment)
	{
		if(LogIn)
		{
			if(WhosProfile[1].equals(user.getUserName()))
			{
				if(postNumber>0 && postNumber <= user.posts.length)
				{
					user.addInteraction(user.getAccountID(), Integer.toString(postNumber), user.getUserName());
					user.posts[postNumber-1].addComment(username, accountID, comment, Integer.toString(interactions.length));
					System.out.printf("Adding a comment %d. post of %s\n", postNumber, user.getUserName());;
				}
			}
			else
			{
				System.out.println("To add comment post you should enter the profile and then posts. Then you can add comment");
			}
		}
		else
		{
			System.out.println(username + ", you should log in to perform action!");
		}
	}
	
	/**
	 * It resizes inbox and outbox arrays each times.
	 * @param resizeArray The array which is wanted to be changed.
	 * @return resized new array.
	 */
	private Message[] resizeMessage(Message[] resizeArray)
	{
		int temp_size = resizeArray.length, i=0;
		
		Message[] temp = new Message[temp_size];
		
		for(i=0; i<temp_size; i++)
		{
			temp[i] = new Message(resizeArray[i].getMessageID(), resizeArray[i].getSender(), resizeArray[i].getReceiver(), resizeArray[i].getContent() );
		}
		
		Message[] resizeArray1 = new Message[temp_size + 1];
		
		for(i=0; i<temp_size; i++)
		{
			resizeArray1[i] = new Message(temp[i].getMessageID(), temp[i].getSender(), temp[i].getReceiver(), temp[i].getContent());
		}
		return resizeArray1;
	}
	
	/**
	 * It controls if following user.
	 * @param user The person who is wanted to be controlled.
	 * @return If current user is following the user it returns true, else false.
	 */
	private boolean isFollowing(Account user)
	{
		int i;
		for(i=0; i<following.length; i++)
		{
			if(user.getUserName().equals(following[i]))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * It sends message if the user is not blocked and if user is following him/her.
	 * @param user The person who is wanted to send message.
	 * @param message_content The message which is wanted to be sended. 
	 */
	public void sendMessage(Account user, String message_content)
	{
		if(LogIn)
		{
			if(!isBlocked(user))
			{
				if(isFollowing(user))
				{
					sendedMessage(user, message_content);
					user.receivedMessage(this, message_content);	
					System.out.printf("Sending a message to %s...\n", user.getUserName());
				}
				else
				{
					System.out.println("You should follow the user to send message");
				}
			}
			else
			{
				System.out.println("User can not found");
			}
			
		}
		else
		{
			System.out.println(username + ", you should log in to perform action!");
		}
	}
	
	/**
	 * It resize outbox and add message to outbox.
	 * @param user Message recipient.
	 * @param content the message.
	 */
	private void sendedMessage(Account user, String content)// gönderdiği kişinin bilgileri OUTBOX
	{
		messages_outbox = resizeMessage(messages_outbox);
		messages_outbox[messages_outbox.length - 1] = new Message(Integer.toString(messages_outbox.length-1), this, user, content);
	}
	
	/**
	 * It resize inbox and add message to inbox.
	 * @param user Message sender.
	 * @param content the message.
	 */
	private void receivedMessage(Account user, String content)// gönderen kişinin bilgileri İNBOX
	{
		messages_inbox = resizeMessage(messages_inbox);
		messages_inbox[messages_inbox.length-1] = new Message(Integer.toString(messages_inbox.length-1), user, this, content);
	}
	
	/**
	 * It checks outbox. And print details.
	 */
	public void checkOutbox()
	{
		if(LogIn)
		{
			System.out.println("...Checking Outbox...");
			System.out.println("There is/are " + messages_outbox.length + " in the outbox.");			
		}
		else
		{
			System.out.println(username + ", you should log in to perform action!");
		}
	}
	
	/**
	 * It checks inbox. And print details.
	 */
	public void checkInbox()
	{
		if(LogIn)
		{
			System.out.println("...Checking Inbox...");
			System.out.println("There is/are " + messages_inbox.length + " in the inbox.");			
		}
		else
		{
			System.out.println(username + ", you should log in to perform action!");
		}
	}
	
	/**
	 * If there is message in inbox to show, it invokes display function.
	 */
	public void viewInbox()
	{
		if(LogIn)
		{			
			int i;
			System.out.println("...Viewin Inbox...");
			if(messages_inbox.length == 0)
			{
				System.out.println("There is no message to show.");
			}
			else
			{
				for(i=0; i<messages_inbox.length; i ++)
				{
					messages_inbox[i].display();
				}
			}
		}
		else
		{
			System.out.println(username + ", you should log in to perform action!");
		}
	}
	
	/**
	 * If there is message in outbox to show, it invokes display function.
	 */
	public void viewOutbox()
	{
		if(LogIn)
		{
			int i;
			System.out.println("...Viewin Outbox...");
			if(messages_outbox.length == 0)
			{
				System.out.println("There is no message to show.");
			}
			else
			{
				for(i=0; i<messages_outbox.length; i ++)
				{
					messages_outbox[i].display();
				}
			}			
		}
		else
		{
			System.out.println(username + ", you should log in to perform action!");
		}
	}
	
	/**
	 * It resizes blocked array each time.
	 */
	private void resizeBlocked()
	{
		int i;
		String[] accountTemp = new String[blocked.length];
		
		for(i=0; i<blocked.length; i++)
		{
			accountTemp[i] = new String(blocked[i]);
		}
		
		blocked = new String[blocked.length + 1];
		for(i=0; i<blocked.length - 1; i++)
		{
			blocked[i] = new String(accountTemp[i]);
		}	
	}
	
	/**
	 * When an user is blocked, follower list get updated.
	 * @param user The person who is wanted to be deleted.
	 */
	private void deleteFollower(Account user)
	{
		int index, j;
		boolean isFollower = false;
		for(index=0; index < followers.length; index++)
		{
			if(followers[index].equals(user.getUserName()))
			{
				isFollower = true;
				break;
			}
		}
		
		if(isFollower)
		{
			String[] temp1 = new String[followers.length-1];
			for(j=0; j<followers.length - 1; j++)
			{
				if(j < index)
				{
					temp1[j] = new String(followers[j]);						
				}
				else if(j >= index)
				{
					temp1[j] = new String(followers[j+1]);
				}
			}
			
			followers = new String[followers.length - 1];
			for(j=0; j<followers.length; j++)
			{
				followers[j] = new String(temp1[j]);
			}
		}
	}
	
	/**
	 * When an user is blocked, following list get updated.
	 * @param user The person who is wanted to be deleted.
	 */
	private void deleteFollowing(Account user)
	{
		int index, j;
		boolean isFollower = false;
		for(index=0; index < following.length; index++)
		{
			if(following[index].equals(user.getUserName()))
			{
				isFollower = true;
				break;
			}
		}
		
		if(isFollower)
		{
			String[] temp1 = new String[following.length-1];
			for(j=0; j<following.length - 1; j++)
			{
				if(j < index)
				{
					temp1[j] = new String(following[j]);						
				}
				else if(j >= index)
				{
					temp1[j] = new String(following[j+1]);
				}
			}
			
			following = new String[following.length - 1];
			for(j=0; j<following.length; j++)
			{
				following[j] = new String(temp1[j]);
			}
		}
	}
	
	/**
	 * It adds the user block list and it invokes function to delete their names from their following and follower lists.
	 * @param user The person who is wanted to be blocked.
	 */
	public void block(Account user)
	{
		user.resizeBlocked();

		user.blocked[user.blocked.length - 1] = new String(getUserName());
		System.out.println(username + " has blocked " + user.getUserName());
		deleteFollower(user);
		deleteFollowing(user);
		user.deleteFollower(this);
		user.deleteFollowing(this);
	}
	
	/**
	 * It controls if user is blocked
	 * @param user The person who is wanted to be controlled.
	 * @return If the user is blocked it returns true, else false.
	 */
	public boolean isBlocked(Account user)
	{
		int i;
		for(i=0; i<blocked.length; i++)
		{
			if(blocked[i].equals(user.getUserName()))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * For each like and comment it adds informations to interactions array
	 * @param AccountID Account ID which is belong to person who liked  or added comment.
	 * @param PostID The post ID which is liked or added comment.
	 * @param Username The name of user who is liked or added comment.
	 */
	private void addInteraction(String AccountID, String PostID, String Username)
	{
		resizeInteractions();
		interactions[interactions.length-1] = new Interaction(AccountID, PostID, Username, Integer.toString(interactions.length));
	}
	
	/**
	 * It resizes interaction array for each interaction acting.
	 */
	private void resizeInteractions()
	{
		int i;
		Interaction[] temp = new Interaction[interactions.length];
		
		for(i=0; i<temp.length; i++)
		{
			temp[i] = new Interaction(interactions[i].getAccountID(), interactions[i].getPostID(), interactions[i].getUsername(), interactions[i].getInteractionID());
		}
		
		interactions = new Interaction[interactions.length + 1];
		
		for(i=0; i<temp.length; i++)
		{
			interactions[i] = new Interaction(temp[i].getAccountID(), temp[i].getPostID(), temp[i].getUsername(), temp[i].getInteractionID());
		}
	}
	
}
