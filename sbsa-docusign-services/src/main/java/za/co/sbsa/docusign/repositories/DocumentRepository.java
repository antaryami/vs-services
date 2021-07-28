package za.co.sbsa.docusign.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import za.co.sbsa.docusign.domain.Document;

@RepositoryRestResource
public interface DocumentRepository extends CrudRepository<Document, Long> {

	public List<Document> findAllByArrangementId(List<Long> arrangementIds);
}
