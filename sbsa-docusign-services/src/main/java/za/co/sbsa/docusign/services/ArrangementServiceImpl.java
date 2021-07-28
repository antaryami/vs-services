package za.co.sbsa.docusign.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.sbsa.docusign.domain.SignatoryArrangementBPPartaking;
import za.co.sbsa.docusign.repositories.ArrangementPartakingRepository;

@Service
public class ArrangementServiceImpl implements ArrangementService {
	
	@Autowired
	private ArrangementPartakingRepository arrangementPartakingRepository;

	@Override
	public List<SignatoryArrangementBPPartaking> getArrangementByBpID(String bpId) {
		return arrangementPartakingRepository.findAllBySignatoryArrangementBPPartakingID_SigningBPId(bpId);
	}

}
