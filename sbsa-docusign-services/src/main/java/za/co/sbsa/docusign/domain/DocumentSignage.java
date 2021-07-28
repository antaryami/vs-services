package za.co.sbsa.docusign.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="DOCUMENT_SIGNAGE")
public class DocumentSignage implements Serializable {

	private static final long serialVersionUID = -1905670006426714383L;
	@EmbeddedId
	private DocumentSignageId documentSignageId;
	
	@Column(name="ELECTRONIC_SIGNATURE",length=20971520)
	@Lob
	@NotNull
	private byte[] electronicSignature;
	
	public DocumentSignageId getDocumentSignageId() {
		return documentSignageId;
	}
	public void setDocumentSignageId(DocumentSignageId documentSignageId) {
		this.documentSignageId = documentSignageId;
	}
	public byte[] getElectronicSignature() {
		return electronicSignature;
	}
	public void setElectronicSignature(byte[] electronicSignature) {
		this.electronicSignature = electronicSignature;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((documentSignageId == null) ? 0 : documentSignageId.hashCode());
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
		DocumentSignage other = (DocumentSignage) obj;
		if (documentSignageId == null) {
			if (other.documentSignageId != null)
				return false;
		} else if (!documentSignageId.equals(other.documentSignageId))
			return false;
		return true;
	}

}
