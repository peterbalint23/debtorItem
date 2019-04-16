package service;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import debtor_item_service.DebtorItem;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class DebtorItemRepository {
	private static final Map<String, List<DebtorItem>> debtorItems = new HashMap<>();
	private static Random rnd = new Random();
	
	@PostConstruct
	public void initData() throws DatatypeConfigurationException {
		
		debtorItems.put("91ec513b-c169-40ca-ad2e-e6297d1a0507", addItemToCustomer("91ec513b-c169-40ca-ad2e-e6297d1a0507", 3));
		debtorItems.put("c6271344-f9fd-443c-b84c-eac2725370cb", addItemToCustomer("c6271344-f9fd-443c-b84c-eac2725370cb", 1));
		debtorItems.put("dffd1493-16ae-49c8-ac61-237a47863a4f", addItemToCustomer("dffd1493-16ae-49c8-ac61-237a47863a4f", 0));
		
	}
	
	public List<DebtorItem> findDebtorItems(String CustomerID) {
		Assert.notNull(CustomerID, "ID must not be null");
		return debtorItems.get(CustomerID);
	}
	
	private DebtorItem createDebtorItem(String CustomerID) throws DatatypeConfigurationException {
		
		DebtorItem debtorItem = new DebtorItem();
		
		debtorItem.setAmount(rnd.nextInt(10) + 1);
		debtorItem.setCustomerID(CustomerID);
		debtorItem.setReferenceNumber(String.valueOf(rnd.nextInt(9999999) + 1));
		
		int year = rnd.nextInt(50) + 2020;
		int month = rnd.nextInt(12) + 1;
		int day = rnd.nextInt(31) + 1;
		GregorianCalendar c = new GregorianCalendar(year, month, day);
		XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		debtorItem.setDueDate(date);
		
		return debtorItem;
	}
	
	private List<DebtorItem> addItemToCustomer(String CustomerID, int number) throws DatatypeConfigurationException{
		
		List<DebtorItem> debtorItemList = new ArrayList<>();
		
		for(int i =0; i < number; i++) {
			debtorItemList.add(createDebtorItem(CustomerID));
		}
		
		return debtorItemList;
	}
	
	
}
