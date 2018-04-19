package phonebook2018;

import phonebook2018.Person;
import phonebook2018.PhoneNumber;
import phonebook2018.Phonebook;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Main {

	private static final String DEFAULT_PHONEBOOK_NAME = "phonebook.txt";
	
	public static void main(String[] args) {
            
            Person.test1();
		 
		int number = 0;
		
		Phonebook phonebook;
		
		if(!phonebookExists()) {
			System.out.println("No phonebook found, generating default phonebook.");
			phonebook = generatePhonebook();
		} else {
			phonebook = Phonebook.load(DEFAULT_PHONEBOOK_NAME);
		}
		
		 

		while(number != 3) {
			System.out.println("Pick an option: \n\t1. Generate new phonebook \n\t2. Look up a person\n\t3. Exit");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			try {
				number = Integer.parseInt(br.readLine());
			} 
			catch (IOException ioe) {
				System.err.println("IO error trying to read your choice!");
				System.exit(1);
			}
			catch(NumberFormatException nfe) {
				number = 0; // will be handled by default case
			}
			
			switch (number) {
			case 1:
				System.out.println("Generating phonebook...");
				phonebook = generatePhonebook();
				break;
			case 2:
				while(number != 4) {
					System.out.println("Look up by:\n\t1. Last name\n\t2. Phone Number\n\t3. Full Name\n\t4. Back");
					try {
						number = Integer.parseInt(br.readLine());
					} catch (IOException ioe) {
						System.err.println("IO error trying to read your choice!");
						System.exit(1);
					} catch (NumberFormatException nfe) {
						number = 0; // will be handled by default case
					}
					switch (number) {
					case 1:
						System.out.print("Please enter a last name to search for: ");
						String lastNameToFind = "";
						try {
							lastNameToFind = br.readLine().trim();
						} catch (IOException ioe) {
							System.err.println("IO error trying to read your choice!");
							System.exit(1);
						}
						
						List<Person> peopleWithLastname = phonebook.findPeopleWithLastName(lastNameToFind);
						
						if(peopleWithLastname != null) {
							for(Person person : peopleWithLastname) {
								System.out.println(person);
							}
						} else {
							System.out.println("Couldn't find anyone with a last name of '" + lastNameToFind + "'");
						}
						
						break;
					case 2:
						System.out.print("Please enter a phone number to search for: ");
						String phoneNumber = "";
						try {
							phoneNumber = br.readLine().trim();
						} catch (IOException ioe) {
							System.err.println("IO error trying to read your choice!");
							System.exit(1);
						}
						
						Person personFromPhoneNumber = phonebook.findPersonWithPhoneNumber(new PhoneNumber(phoneNumber));
						
						if(personFromPhoneNumber != null) {
							System.out.println(personFromPhoneNumber);
						} else {
							System.out.println("Couldn't find anyone with a phone number of '" + phoneNumber + "'");
						}
						
						break;
					case 3:
						System.out.print("Please enter the person's first and last names separated by a space: ");
						String personFullName = "";
						try {
							personFullName = br.readLine().trim();
						} catch (IOException ioe) {
							System.err.println("IO error trying to read your choice!");
							System.exit(1);
						}
						
						if(personFullName == null || personFullName == "") {
							System.err.println("invalid name, try again");
						}
						
						String firstName = personFullName.split(" ")[0];
						String lastName = personFullName.split(" ")[1];
						
						List<Person> matchingPeople = phonebook.findPhoneNumbersOfPerson(new Person(firstName, lastName));
						
						if(matchingPeople != null) {
							for(Person person : matchingPeople) {
								System.out.println(person.getFirstName() + " " + person.getLastName() + ":");
								System.out.println("\t" + person.getPhoneNumbers() + "\n");
							}
							
						} else {
							System.out.println("Couldn't find anyone matching '" + firstName + " " + lastName + "'");
						}
						
						break;
					case 4:	break;
					default:
						System.out.println("Please enter a number between 1 and 4");
						break;
					}
				}
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("Please enter a number between 1 and 3");
				break;
			}
		}
	}

	private static boolean phonebookExists() {
		return new File(DEFAULT_PHONEBOOK_NAME).exists();
	}

	private static Phonebook generatePhonebook() {
		Phonebook phonebook = Phonebook.generateRandomizedPhonebook(5000);

		System.out.println("Sample:");
		for(int i=0; i < 5; i++) {
			System.out.println("#" + (i+1) + " - " + phonebook.get(i));
		}
		System.out.println();
		
		phonebook.save(DEFAULT_PHONEBOOK_NAME);
		return phonebook;
	}

	

}
