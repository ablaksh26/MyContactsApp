package phone.composite;

import userfunction.model.User;

public interface ContactComponent {

	void addTag(String tag);
	
	String exportToCSV();

	void performBulkSoftDelete(User activeUser);
}