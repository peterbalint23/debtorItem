package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import debtor_item_service.DebtorItem;
import debtor_item_service.GetDebtorItemRequest;
import debtor_item_service.GetDebtorItemResponse;

@Endpoint
public class DebtorItemEndpoint {
	private static final String NAMESPACE_URI = "debtor-item-service";
	
	private DebtorItemRepository debtorItemRepository;
	
	@Autowired
	public DebtorItemEndpoint(DebtorItemRepository debtorItemRepository) {
		this.debtorItemRepository = debtorItemRepository;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDebtorItemRequest")
	@ResponsePayload
	public GetDebtorItemResponse getDebtorItems(@RequestPayload GetDebtorItemRequest request) {
		GetDebtorItemResponse response = new GetDebtorItemResponse();
		List<DebtorItem> responseList = response.getDebtorItems();
		responseList.addAll(debtorItemRepository.findDebtorItems(request.getCustomerID()));
		return response;
	}
}
