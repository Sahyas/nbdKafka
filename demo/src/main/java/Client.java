public class Client {
    String firstName;
    String lastName;
    String personalID;
    int age;
    boolean isArchive = false;
    float debt = 0;

    public Client(String firstName, String lastName, String personalID, int age, ClientType clientType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalID = personalID;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonalID() {
        return personalID;
    }

    public int getMaxBooks() {
        return ;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }

    public String getTypeInfo() {

    }


}

