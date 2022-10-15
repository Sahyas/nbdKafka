public class Client {
    private String firstName;
    private String lastName;
    private String personalID;
    private int age;
    boolean isArchive = false;
    private float debt = 0;
    ClientType clientType = new ClientType() {
        @Override
        public int getMaxBooks() {
            return super.getMaxBooks();
        }

        @Override
        public String getTypeInfo() {
            return super.getTypeInfo();
        }

        @Override
        public int getMaxDays() {
            return super.getMaxDays();
        }

        @Override
        public float getPenalty() {
            return super.getPenalty();
        }
    };

    public Client() {
    }

    public Client(String firstName, String lastName, String personalID, int age, ClientType clientType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalID = personalID;
        this.age = age;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("personalID", personalID)
                .append("age", age)
                .append("isArchive", isArchive)
                .append("debt", debt)
                .toString();
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


    public void setDebt(float debt) {
        this.debt = debt;
    }



}

