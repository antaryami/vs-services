package za.co.sbsa.docusign.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import za.co.sbsa.docusign.domain.SignatoryArrangementBPPartaking;
import za.co.sbsa.docusign.domain.SignatoryArrangementBPPartakingID;

public interface ArrangementPartakingRepository extends CrudRepository<SignatoryArrangementBPPartaking, SignatoryArrangementBPPartakingID> {

	public List<SignatoryArrangementBPPartaking> findAllBySignatoryArrangementBPPartakingID_SigningBPId(String signingBPId);
}
