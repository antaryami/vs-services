package za.co.sbsa.docusign.domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Access (AccessType.FIELD)
public class DocumentSignageId implements Serializable {

	private static final long serialVersionUID = -4832200820479912648L;
	@Column(name="DOCUMENT_ID")
	@NotNull
	private Long documentId;
	@Column(name="SINGING_BPID")
	@NotNull
	private String singingBPId;
	
	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	public String getSingingBPId() {
		return singingBPId;
	}
	public void setSingingBPId(String singingBPId) {
		this.singingBPId = singingBPId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());
		result = prime * result + ((singingBPId == null) ? 0 : singingBPId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentSignageId other = (DocumentSignageId) obj;
		if (documentId == null) {
			if (other.documentId != null)
				return false;
		} else if (!documentId.equals(other.documentId))
			return false;
		if (singingBPId == null) {
			if (other.singingBPId != null)
				return false;
		} else if (!singingBPId.equals(other.singingBPId))
			return false;
		return true;
	}	
	
}
