package phonebook2018;

import java.io.Serializable;

public class PhoneNumber implements Serializable {

	private static final long serialVersionUID = 4922256141952711189L;
	
	private String phoneNumber;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws Exception {
            // metoda phoneNumberOk returneaza true de aceea inversam la false
            if(!phoneNumberOk(phoneNumber)) throw new Exception("Numar gresit!");
            this.phoneNumber = phoneNumber;
	}

	public PhoneNumber(String phoneNumber) throws Exception {
		super();
                if(phoneNumber == null || phoneNumber.length() < 0)
                    throw new Exception("Formatul numarului de tel. neacceptat!");
		this.phoneNumber = phoneNumber;
	}
	
        @Override
	public String toString() {
		return phoneNumber;
	}
	// TODO: toString nice format?
        // This method return true
        private static boolean phoneNumberOk(String phoneNumber){
            return phoneNumber != null && phoneNumber.length()>0;
        }
}
