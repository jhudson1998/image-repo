public class User
{
    private String username;
    private String firstName;
    private String lastName;

    public User(String user, String first, String last)
    {
        this.username = user;
        this.firstName = first;
        this.lastName = last;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }
}
