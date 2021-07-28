package za.co.sbsa.docusign.services;

import java.util.List;

import za.co.sbsa.docusign.domain.SignatoryArrangementBPPartaking;

public interface ArrangementService {

	public List<SignatoryArrangementBPPartaking> getArrangementByBpID(String bpId);
}
