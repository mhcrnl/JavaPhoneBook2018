package phonebook2018;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {

	private static final long serialVersionUID = 1513862870479192109L;

	// constructors
	public Person(String firstName, String lastName) {
		this(firstName, lastName, new ArrayList<PhoneNumber>());
	}
	
	public Person(String firstName, String lastName, List<PhoneNumber> phoneNumbers) {
		super();
		
		if(firstName == null
		|| lastName == null) {
			throw new IllegalArgumentException("Firstname and lastname are required");
		}
			
		// you can still be a person without phone numbers
		if(phoneNumbers == null) { 
			phoneNumbers = new ArrayList<PhoneNumber>();
		}
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumbers = phoneNumbers;
	}
	
	// fields
	private String firstName;
	private String lastName;
	private List<PhoneNumber> phoneNumbers;
	
	// getters and setters
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) throws Exception {
            if(lastName==null || lastName.length()>0) 
                throw new Exception("Introduceti un nume!");
		this.lastName = lastName;
	}
	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
	// public methods
        @Override
	public String toString() {
		String personString = String.format("Person: %s %s - [", getFirstName(), getLastName());
		
		List<PhoneNumber> phoneNums = getPhoneNumbers(); 
		for(int i=0; i < phoneNums.size(); i++) {
			personString += phoneNums.get(i).toString();
			if(i != phoneNums.size() -1) {
				personString += ", ";
			}
		}
		personString += "]";
		
		return personString;
	}

}
