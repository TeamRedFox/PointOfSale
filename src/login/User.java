package login;

public class User
{
	private String username;
	private String passwordHash;
	private String firstName;
	private String lastName;
	private boolean isAdmin;
	
	public User(String username)
	{
		this.username = username;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPaswordHash()
	{
		return passwordHash;
	}

	public void setPaswordHash(String paswordHash)
	{
		this.passwordHash = paswordHash;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public boolean isAdmin() 
	{
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}
	
	public String getFullName()
	{
		return firstName + " " + lastName;
	}
	
	//TODO better toString representation
	public String toString()
	{
		return username + ", " + passwordHash + ", " + getFullName() + (isAdmin ? ", admin" : ", normal");
	}
	
}
