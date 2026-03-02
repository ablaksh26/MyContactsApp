package phone.tag;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class TagFactory {
	private static final Map<String, Tag> tagPool = new HashMap<>();

	static {
		EnumSet<PredefinedTag> defaultTags = EnumSet.allOf(PredefinedTag.class);
		for(PredefinedTag tag : defaultTags) {
			tagPool.put(tag.name(), new Tag(tag.name()));
		}
	}

	public static Tag getTag(String name) {
		if(name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Tag cannot be empty");
		}
		
		String normalizedName = name.trim().toUpperCase();
		
		return tagPool.computeIfAbsent(normalizedName, Tag::new);
	}
	public static Collection<Tag> getAllAvailableTags() {
		return tagPool.values();
	}
}