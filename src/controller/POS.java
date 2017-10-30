package controller;


	import java.util.HashMap;

	import model.InvalidUsernameOrPasswordException;
	import model.User;

	public class POS {

		//CREATE A HASHING CODE METHOD SO NONE OVERLAP
		//
		//
		//
		//
		//
		//
		//

		private HashMap<String, User> users;
		private int totalUsers;
		private User currentUser;

		
		
		public POS(){
			this.users = new HashMap<String,User>();
			this.totalUsers = 0;
		}

		public User getCurrentUser() {
			return currentUser;
		}

		public void setCurrentUser(User currentUser) {
			this.currentUser = currentUser;
		}

		public HashMap<String, User> getUsers() {
			return users;
		}

		public void setUsers(HashMap<String, User> users) {
			this.users = users;
		}

		public int getTotalUsers() {
			return totalUsers;
		}

		public void setTotalUsers(int totalUsers) {
			this.totalUsers = totalUsers;
		}
		
		

		//methods used in GUI
		public boolean usernameInUse(String username) {
			return users.containsKey(username);
		}

		public boolean emailInUse(String email) {
			return users.values()
					    .parallelStream()
					    .anyMatch(user -> user.getEmail().equals(email));
		}

		//check exception
		//
		//
		//
		//
		//
		public User login(String username, String password) {
			User currentUser = new User();
			if (username.isEmpty()) {
				throw new InvalidUsernameOrPasswordException("empty");
			}
			else if (password.isEmpty()) {
				throw new InvalidUsernameOrPasswordException("empty");
			}
			else if (!usernameInUse(username)) {
				throw new InvalidUsernameOrPasswordException("username");
			}
			else if (usernameInUse(username) & !users.get(username).getPassword().equals(password)) {
				throw new InvalidUsernameOrPasswordException("password");
			}
			else if (usernameInUse(username) & users.get(username).getPassword().equals(password)) {
				currentUser = users.get(username);
			}
			return currentUser;
		}

		public void addUser(String username, String password, String firstname, String lastname, String email, String role) {
			if (username.equals("") | password.equals("") | firstname.equals("") | lastname.equals("") | email.equals("")) {
				throw new IllegalArgumentException("One or more fields is empty.");
			}
			if (usernameInUse(username)) {
				throw new IllegalArgumentException("Entered username: '" + username + "' is currently in use.");
			}
			if (emailInUse(email)) {
				throw new IllegalArgumentException("Entered email: '" + email + "' is currently in use.");
			}
			users.put(username, new User(username, password, firstname, lastname, email, role));
			totalUsers += 1;
		}

	}


