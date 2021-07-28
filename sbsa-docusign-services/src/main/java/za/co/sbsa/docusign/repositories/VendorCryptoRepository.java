package za.co.sbsa.docusign.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import za.co.sbsa.docusign.domain.VendorCrypto;

@RepositoryRestResource
public interface VendorCryptoRepository extends CrudRepository<VendorCrypto, Long> {

}
