interface InputValidator {
	// implemented by dashboard to be used by its buttons for input validation
	String validFirstName = "[A-Z][a-zA-Z][a-zA-Z ]*";
	String validLastName = "[a-zA-Z][a-zA-Z ]*";
	String validEmail = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
			+ "A-Z]{2,7}$";

}