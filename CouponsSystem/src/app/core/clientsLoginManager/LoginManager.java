package app.core.clientsLoginManager;

public class LoginManager {
	
	private static LoginManager instance;
	
	

	public LoginManager() {
		super();
		// TODO Auto-generated constructor stub
	}




	public LoginManager getInstance() {
		if(instance == null) {
			instance = new LoginManager();
			return instance;
		}
		return instance;
	}
	
	

}
