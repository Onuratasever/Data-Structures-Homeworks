package homework1;

/**
 * Test Class 2
 */
public class TestClass2 {

	/**
	 * Test Class 2
	 * @param args Arguman from user
	 */
	public static void main(String[] args) {
		
		int[] isOk= {0};
		int numOfUser=0;
		Account gizemsungu = new Account("gizemsungu","24.02.2002", "Kocaeli", ++numOfUser);
		Account sibelgulmez = new Account("sibelgulmez","10.03.1995", "Istanbul", ++numOfUser);
		Account gokhankaya = new Account("gokhankaya","24.02.2002", "Kocaeli", ++numOfUser);
		
		sibelgulmez.LogIn(isOk);
		sibelgulmez.sharePost("I like Java.\n");
		sibelgulmez.sharePost("Java the coffe...\n");
		sibelgulmez.follow(gizemsungu);
		sibelgulmez.follow(gokhankaya);
		sibelgulmez.LogOut(isOk);
		gokhankaya.LogIn(isOk);
		gokhankaya.viewProfile(sibelgulmez);
		gokhankaya.viewPosts(sibelgulmez);
		gokhankaya.likePost(sibelgulmez, 1);
		gokhankaya.addComment(sibelgulmez, 1, "me too!");
		gokhankaya.follow(sibelgulmez);
		gokhankaya.follow(gizemsungu);
		gokhankaya.sendMessage(gizemsungu, "this homework is too easy(!)");
		gokhankaya.LogOut(isOk);
		gizemsungu.LogIn(isOk);
		gizemsungu.checkOutbox();
		gizemsungu.checkInbox();
		gizemsungu.viewInbox();
		gizemsungu.viewProfile(sibelgulmez);
		gizemsungu.viewPosts(sibelgulmez);
		gizemsungu.showInteractions(sibelgulmez);
		gizemsungu.likePost(sibelgulmez, 1);
		gizemsungu.likePost(sibelgulmez, 2);
		gizemsungu.showInteractions(sibelgulmez);
		
		gizemsungu.LogIn(isOk);
		gizemsungu.sharePost("Post1\n");
		gizemsungu.sharePost("Post2\n");
		gizemsungu.LogOut(isOk);
		sibelgulmez.LogIn(isOk);
		sibelgulmez.viewProfile(gizemsungu);
		sibelgulmez.likePost(gizemsungu, 1);
		sibelgulmez.LogOut(isOk);
		gokhankaya.LogIn(isOk);
		gokhankaya.viewProfile(gizemsungu);
		gokhankaya.addComment(gizemsungu, 2, "Ex: Nice!");
		gokhankaya.sendMessage(gizemsungu, "Ex: Hello!");
		gokhankaya.LogOut(isOk);
		gizemsungu.LogIn(isOk);
		gizemsungu.viewProfile(gizemsungu);
		gizemsungu.checkInbox();
		gizemsungu.viewInbox();
			
	}

}
