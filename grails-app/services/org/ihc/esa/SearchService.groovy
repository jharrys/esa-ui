package org.ihc.esa

class SearchService
{
	static scope = "singleton"
	
	static transactional = false
	
	List<Item> searchSimpleAllItems(List<String> keywords)
	{
		log.debug("searchSimpleAllItems called with keywords: " + keywords)
		List<Item> foundItems = Item.search().list
		{
			should
			{
				for (String term in keywords)
				{
					log.debug("adding term: " + term)
					keyword 'name', term
					keyword 'description', term
					keyword 'comments', term
					keyword 'notes.text', term
				} // end for
			} // end should
		} // end search().list closure
		
		log.debug("found Item count: " + foundItems.size())
		return foundItems
	} // end method
}
