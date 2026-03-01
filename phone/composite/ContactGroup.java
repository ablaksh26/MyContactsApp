package phone.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import userfunction.model.User;

public class ContactGroup implements ContactComponent{
	private final List<ContactComponent> components = new ArrayList<>();

	public void addComponent(ContactComponent component) {
		components.add(component);
	}

	public int getSize() {
		return components.size();
	}

	@Override
	public void addTag(String tag) {
		components.forEach(c -> c.addTag(tag));
	}

	@Override
	public String exportToCSV() {
		return components.stream()
						 .map(ContactComponent::exportToCSV)
						 .collect(Collectors.joining("\n"));
	}

	@Override
	public void performBulkSoftDelete(User actveUser){
		new ArrayList<>(components).forEach(c -> c.performBulkSoftDelete(actveUser));
	}
}