package phone.tag;

import java.util.Objects;

public class Tag {
	private final String name;

	public Tag(String name) {
		if(name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Tag name cannot be empty");
		}
		
		this.name = name.trim().toUpperCase();
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		Tag tag = (Tag) o;
		
		return name.equals(tag.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}