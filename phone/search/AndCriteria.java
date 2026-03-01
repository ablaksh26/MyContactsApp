package phone.search;

import java.util.List;

import phone.model.Contact;

public class AndCriteria implements SearchCriteria{
	private final List<SearchCriteria> criteriaChain;

	public AndCriteria(List<SearchCriteria> criteriaChain) {
		this.criteriaChain = criteriaChain;
	}

	@Override
	public boolean test(Contact contact) {
		for(SearchCriteria criteria : criteriaChain) {
			if(!criteria.test(contact)) {
				return false;
			}
		}
		
		return true;
	}
}