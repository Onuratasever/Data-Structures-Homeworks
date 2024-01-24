package homework2_2;

/**
 * This class keeps User data.
 * @author Onur
 *
 */
public class Account {
	private String accountID;
	
	private String username;
	
	private String birthdate;
	
	private String location;
	
	private Boolean LogIn;
	
	//private String[] WhosProfile ;	
	//private ArrayList<String> WhosProfile;
	private LDLinkedList<String> WhosProfile;
	
	//private Post[] posts;
	//private ArrayList<Post> posts;
	private LDLinkedList<Post> posts;
	
	//private Message[] messages_inbox;
	//private ArrayList<Message> messages_inbox;
	private LDLinkedList<Message> messages_inbox;
	
	
	//private Message[] messages_outbox;
	//private ArrayList<Message> messages_outbox;
	LDLinkedList<Message> messages_outbox;
	
	//private String[] following;
	//private ArrayList<String> following;
	private LDLinkedList<String> following;
	
	//private String[] followers;
	//private ArrayList<String> followers;
	private LDLinkedList<String> followers;
	
	//private String[] blocked;
	//private ArrayList<String> blocked;
	private LDLinkedList<String> blocked;
	
	//private Interaction[] interactions;
	//private ArrayList<Interaction> interactions;
	private LDLinkedList<Interaction> interactions;
	
	private LDLinkedList<String> history;
	
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
		
		//followers = new String[0];
		//followers = new ArrayList<String>();
		followers = new LDLinkedList<String>();
		
		//following = new String[0];
		//following = new ArrayList<String>();
		following = new LDLinkedList<String>();
		
		//posts = new Post[0];
		//posts = new ArrayList<Post>();
		posts = new LDLinkedList<Post>();
		
		//messages_inbox = new Message[0];
		//messages_inbox = new ArrayList<Message>();
		messages_inbox = new LDLinkedList<Message>();
		
		//messages_outbox = new Message[0];
		//messages_outbox = new ArrayList<Message>();
		messages_outbox = new LDLinkedList<Message>();
		
		//blocked = new String[0];
		//blocked = new ArrayList<String>();
		blocked = new LDLinkedList<String>();
		
		//interactions = new Interaction[0];
		//interactions = new ArrayList<Interaction>();
		interactions = new LDLinkedList<Interaction>();
		
		//WhosProfile = new String[2];
		//WhosProfile = new ArrayList<String>();
		WhosProfile = new LDLinkedList<String>();
		
		//WhosProfile[0] = "Nobody";
		WhosProfile.add("Nobody");
		
		//WhosProfile[1] = "Nobody";
		WhosProfile.add("Nobody");
		
		history = new LDLinkedList<String>();
		System.out.println("\nAn account with username " + getUserName() + " has been created successfully");
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
			System.out.println("-------------------\nLogging into an account (username: " + username + ")...");
			isOk[0] = 1;
		}
		else
		{
			System.out.println(username + " you should log out to log in");
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
			System.out.println("--------------\nLogging out from account '" + username + "'...");
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
			//resizePost();
			
			//posts[posts.length - 1] = new Post(content, accountID, posts.length);
			
			posts.add(new Post(content, accountID, posts.size() + 1));
			System.out.println("-------------\nPost is shared");
			setHistory("You shared Post with ID" + (posts.size() + 1));
		}
		else
		{
			System.out.println(username + ", you should log in to perform action!");
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
		for(i=0; i<posts.size(); i++)
		{
			posts.get(i).display();
		}
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
				setHistory("You followed " + user.getUserName());
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
		//followers = resizeFollow(followers);
		//followers[followers.length - 1] = new String(username);
		followers.add(username);
	}
	
	/**
	 * It resizes following array and adds following user to array.
	 * @param username The person who is wanted to be followed.
	 */
	private void setFollowing(String username)
	{
		//following = resizeFollow(following);
		//following[following.length - 1] = new String(username);
		following.add(username);
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
		System.out.println(username + " has " + followers.size() + " followers");
		
		if(followers.size() != 0)
		{
			System.out.printf("The followers of %s are:" , username );
			
			for(i=0; i<followers.size(); i++)
			{
				System.out.printf("%s ", followers.get(i));
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
		System.out.println(username + " is following " + following.size() + " account(s)");
		
		if(following.size() != 0)
		{
			System.out.printf("following size: %d\n%s is following :" ,following.size(), username );
			
			for(i=0; i<following.size(); i++)
			{
				System.out.printf("%s ", following.get(i));
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
				//WhosProfile[0] = user.getUserName();
				WhosProfile.set(0, user.getUserName());
				setHistory("You viewed " + user.getUserName() + "'s profile");
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
		System.out.printf("-------------\nViewing %s's profile...", username);
		System.out.println("\n----------------");
		System.out.println("User ID:" + accountID);
		System.out.println("Username: " + username);
		System.out.println("Location: " + location);
		System.out.println("Birthdate: " + birthdate);
		getFollowers();
		getFollowing();
		System.out.println(username + " has " + posts.size() + " posts.");
	}
	
	/**
	 * If current user in the other users profile, it views posts and it saves the last person name whose posts are viewed.
	 * @param user the person whose posts you want to view.
	 */
	public void viewPosts(Account user)
	{
		if(LogIn)
		{
			if(WhosProfile.get(0).equals(user.getUserName()))
			{
				user.displayPosts();
				WhosProfile.set(1, user.getUserName());
				setHistory("You viewed " + user.getUserName() + "'s posts");
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
			if(WhosProfile.get(1).equals(User.getUserName()))
			{
				if(User.posts.size() != 0)
				{
					User.addInteraction(User.getAccountID(), Integer.toString(postNumber), User.getUserName());
					User.like(username, accountID, postNumber);
					System.out.printf("-------------\nLiking %d. post of %s\n", postNumber, User.getUserName());
					setHistory("You liked " + User.getUserName() + "'s post ID: " + postNumber);
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
		if(PostNumber>0 && PostNumber <= posts.size())
		{
			//posts[PostNumber-1].like(username, accountID, Integer.toString(interactions.length));
			posts.get(PostNumber-1).like(username, accountID, Integer.toString(interactions.size()));
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
			if(WhosProfile.get(0).equals(user.getUserName()))
			{
				int i;
				System.out.printf("-------------\n%s 's post Interactions...\n",user.getUserName());

				for(i=0; i<user.posts.size(); i++)
				{
					user.posts.get(i).showInteractions();
				}
				setHistory("You showed interactions of " + user.getUserName());
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
			if(WhosProfile.get(1).equals(user.getUserName()))
			{
				if(postNumber>0 && postNumber <= user.posts.size())
				{
					user.addInteraction(user.getAccountID(), Integer.toString(postNumber), user.getUserName());
					//user.posts[postNumber-1].addComment(username, accountID, comment, Integer.toString(interactions.length));
					user.posts.get(postNumber - 1).addComment(username, accountID, comment, Integer.toString(interactions.size()));
					System.out.printf("-------------\nAdding a comment %d. post of %s\n", postNumber, user.getUserName());;
					setHistory("You added comment '" + comment + "' " + user.getUserName()+"'s post ID: " + postNumber);
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
	 * It controls if following user.
	 * @param user The person who is wanted to be controlled.
	 * @return If current user is following the user it returns true, else false.
	 */
	private boolean isFollowing(Account user)
	{
		int i;
		for(i=0; i<following.size(); i++)
		{
			if(user.getUserName().equals(following.get(i)))
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
					setHistory("You sent message to " + user.getUserName());
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
		//messages_outbox = resizeMessage(messages_outbox);
		//messages_outbox[messages_outbox.length - 1] = new Message(Integer.toString(messages_outbox.length-1), this, user, content);
		messages_outbox.add(new Message(Integer.toString(messages_outbox.size()), this, user, content));
	}
	
	/**
	 * It resize inbox and add message to inbox.
	 * @param user Message sender.
	 * @param content the message.
	 */
	private void receivedMessage(Account user, String content)// gönderen kişinin bilgileri İNBOX
	{
		//messages_inbox = resizeMessage(messages_inbox);
		//messages_inbox[messages_inbox.length-1] = new Message(Integer.toString(messages_inbox.length-1), user, this, content);
		messages_inbox.add(new Message(Integer.toString(messages_inbox.size()), user, this, content));
	}
	
	/**
	 * It checks outbox. And print details.
	 */
	public void checkOutbox()
	{
		if(LogIn)
		{
			System.out.println("...Checking Outbox...");
			System.out.println("There is/are " + messages_outbox.size() + " in the outbox.");			
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
			System.out.println("There is/are " + messages_inbox.size() + " in the inbox.");			
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
			if(messages_inbox.size() == 0)
			{
				System.out.println("There is no message to show.");
			}
			else
			{
				for(i=0; i<messages_inbox.size(); i ++)
				{
					//messages_inbox[i].display();
					messages_inbox.get(i).display();
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
			if(messages_outbox.size() == 0)
			{
				System.out.println("There is no message to show.");
			}
			else
			{
				for(i=0; i<messages_outbox.size(); i ++)
				{
					//messages_outbox[i].display();
					messages_outbox.get(i).display();
				}
			}			
		}
		else
		{
			System.out.println(username + ", you should log in to perform action!");
		}
	}
	
	/**
	 * When an user is blocked, follower list get updated.
	 * @param user The person who is wanted to be deleted.
	 */
	private void deleteFollower(Account user)
	{
		int index;
		boolean isFollower = false;
		for(index=0; index < followers.size(); index++)
		{
			if(followers.get(index).equals(user.getUserName()))
			{
				isFollower = true;
				break;
			}
		}
		
		if(isFollower)
		{
			followers.remove(index);
		}
	}
	
	/**
	 * When an user is blocked, following list get updated.
	 * @param user The person who is wanted to be deleted.
	 */
	private void deleteFollowing(Account user)
	{
		int index;
		boolean isFollower = false;
		for(index=0; index < following.size(); index++)
		{
			if(following.get(index).equals(user.getUserName()))
			{
				isFollower = true;
				break;
			}
		}
		
		if(isFollower)
		{
			following.remove(index);
		}
	}
	
	/**
	 * It adds the user block list and it invokes function to delete their names from their following and follower lists.
	 * @param user The person who is wanted to be blocked.
	 */
	public void block(Account user)
	{
		//user.resizeBlocked();

		//user.blocked[user.blocked.length - 1] = new String(getUserName());
		user.blocked.add(new String(getUserName()));
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
		for(i=0; i<blocked.size(); i++)
		{
			if(blocked.get(i).equals(user.getUserName()))
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
		//resizeInteractions();
		//interactions[interactions.length-1] = new Interaction(AccountID, PostID, Username, Integer.toString(interactions.length));
		interactions.add(new Interaction(AccountID, PostID, Username, Integer.toString(interactions.size() + 1)));
	}
	
	/**
	 * It removes like of user decided post
	 * @param user
	 * @param PostID
	 */
	public void unlike(Account user, int PostID)
	{
		if(LogIn)
		{
			if(WhosProfile.get(1).equals(user.getUserName()))
			{
				if(user.posts.size() != 0)
				{
					user.removeLike(this, PostID);
					setHistory("You unliked " + user.getUserName() + "'s post id " + PostID);
				}
			}
			else
			{
				System.out.println("To like post you should enter the profile and then posts. Then you can like");
			}
			
		}
		else
			System.out.println(username + ", you should log in to perform action!");
	}
	
	/**
	 * It removes like of user decided post
	 * @param user
	 * @param PostID
	 */
	private void removeLike(Account user, int PostID)
	{
		posts.get(PostID - 1).removeLike(user);
		setHistory("You unliked " + user.getUserName() + "'s post id " + PostID);
	}
	
	/**
	 * It removes comment of user decided post
	 * @param user
	 * @param PostID
	 */
	public void uncomment(Account user, int PostID)
	{
		if(LogIn)
		{
			if(WhosProfile.get(1).equals(user.getUserName()))
			{
				if(user.posts.size() != 0)
				{

					user.removeComment(this, PostID);	
					setHistory("You removed your comment " + user.getUserName() + "'s post id " + PostID);
				}
			}
			else
			{
				System.out.println("To like post you should enter the profile and then posts. Then you can like");
			}
		}
		else
			System.out.println(username + ", you should log in to perform action!");
	}
	
	/**
	 * It removes comment of user decided post
	 * @param user
	 * @param PostID
	 */
	private void removeComment(Account user, int PostID)
	{
		posts.get(PostID - 1).removeComment(user);
		setHistory("You uncommented " + user.getUserName() + "'s post id " + PostID);
	}
	
	/**
	 * It set Follower list according to user
	 * @param user
	 */
	private void removeFollowers(Account user)
	{
		int index;
		
		for(index=0; index<followers.size(); index++)
		{
			if(followers.get(index).equals(user.getUserName()))
			{
				break;
			}
		}
		
		followers.remove(index);
	}
	
	/**
	 * It removes user from following list and user's follower list
	 * @param user to unfollow
	 */
	public void unfollow(Account user)
	{
		if(LogIn)
		{
			int index;
			if(isFollowing(user))
			{ 
				for(index = 0; index < following.size(); index++)
				{
					
					if(following.get(index).equals(user.getUserName()))
					{
						following.remove(index);
						//System.out.println("GEliyomumumumumummumuSF:SDG:DSFH:");
						user.removeFollowers(this);
						setHistory("You unfollow " + user.getUserName());
						break;
					}
				}
			}
			else
			{
				System.out.println("You should follow, before unfollow");
			}
		}
		else
			System.out.println(username + ", you should log in to perform action!");
	}
	
	/**
	 * It cancel block if user is blocked
	 * @param user whose block is going to remove
	 */
	public void unblock(Account user)
	{
		if(LogIn)
		{
			user.removeBlock(this);	
			setHistory("You unblocked " + user.getUserName());
		}
		else
			System.out.println(username + ", you should log in to perform action!");
	}
	
	/**
	 * It cancel block if user is blocked
	 * @param user whose block is going to remove
	 */
	private void removeBlock(Account user)
	{
		int index;
		
		if(isBlocked(user))
		{
			for(index = 0; index < blocked.size(); index++)
			{
				if(blocked.get(index).equals(user.getUserName()))
				{
					blocked.remove(index);
					System.out.println(getUserName() + " is unblocked");
					break;
				}
			}
		}
		else
		{
			System.out.println("You did not blocked");
		}
	}
	
	/**
	 * It set History for each action of user
	 * @param action action performed by user
	 */
	private void setHistory(String action)
	{
		history.add(action);
	}
	
	/**
	 * It shows History for each action of user
	 */
	public void showHistory()
	{
		int i;
		System.out.println("\n-----------------\nShowing History\n");
		for(i=0; i<history.size(); i++)
		{
			System.out.println(history.get(i));
		}
	}
}