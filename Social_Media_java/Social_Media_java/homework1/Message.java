package homework1;

/**
 * It keeps details of message.
 * @author Onur
 *
 */
public class Message {

	/**
	 * Message ID
	 */
	private String messageID;
	
	/**
	 * Account information of sender
	 */
	private Account sender;

	/**
	 * Account information of receiver
	 */
	private Account receiver;

	/**
	 * Message content
	 */
	private String content;
	
	/**
	 * Message class's constructor.
	 * @param messageID Message ID.
	 * @param sender The person who sended message.
	 * @param receiver The person who received message.
	 * @param content The message
	 */
	Message(String messageID, Account sender, Account receiver, String content)
	{
		this.messageID = messageID;
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
	}
	
	/**
	 * @return It returns sender account ID.
	 */
	public String getSenderID()
	{
		return sender.getAccountID();
	}
	
	/**
	 * @return It returns receiver account ID.
	 */
	public String getReceivedID()
	{
		return receiver.getAccountID();
	}
	
	/**
	 * @return It returns sender name.
	 */
	public String getSenderName()
	{
		return sender.getUserName();
	}
	
	/**
	 * @return It returns receiver name.
	 */
	public String getReceiverName()
	{
		return receiver.getUserName();
	}
	
	/**
	 * @return It returns message.
	 */
	public String getContent()
	{
		return content;	
	}
	
	/**
	 * @return It returns message ID.
	 */
	public String getMessageID()
	{
		return messageID;
	}
	
	/**
	 * @return It returns sender.
	 */
	public Account getSender()
	{
		return sender;
	}
	
	/**
	 * @return It returns receiver.
	 */
	public Account getReceiver()
	{
		return receiver;
	}
	
	/**
	 * It show details of message.
	 */
	public void display()
	{
		System.out.println("-----------------\nMessage ID: " + messageID + "\nFrom: " + getSenderName()+ "\nTo: " + getReceiverName() +"\nMessage: " + content);
	}
	
}
