package login;

public class User
{
	private String username;
	private String paswordHash;
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
		return paswordHash;
	}

	public void setPaswordHash(String paswordHash)
	{
		this.paswordHash = paswordHash;
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
	
}
