package za.co.sbsa.docusign.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import za.co.sbsa.docusign.domain.SbsaCrypto;

@RepositoryRestResource
public interface SBSACryptoRepository extends CrudRepository<SbsaCrypto, Long> {

	public List<SbsaCrypto> findBySigningBPId(String signingBPId);
}
