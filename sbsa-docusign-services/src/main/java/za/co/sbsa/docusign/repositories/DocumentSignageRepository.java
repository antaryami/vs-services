package za.co.sbsa.docusign.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import za.co.sbsa.docusign.domain.Document;
import za.co.sbsa.docusign.domain.DocumentSignage;
import za.co.sbsa.docusign.domain.DocumentSignageId;

/**
 * Created by Jan-Rudolph on 7/18/2017.
 */
@RepositoryRestResource
public interface DocumentSignageRepository extends CrudRepository<DocumentSignage, DocumentSignageId> {
}
