package za.co.sbsa.docusign.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="DOCUMENT")
public class Document implements Serializable {

	private static final long serialVersionUID = -8988708980277554417L;
	@Id
	@Column(name = "DOCUMENT_ID")
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long documentId;
	@Column(name = "ARRANGEMENT_ID")
	@NotNull
	private Long arrangementId;
	@Column(name = "DOCUMENT_BLOB",length=20971520)
	@Lob
	@NotNull
	private byte[] documentBlob;
	@Column(name = "DOCUMENT_STATE")
	@NotNull
	private String documentState;
	@OneToMany(cascade = CascadeType.MERGE, targetEntity = DocumentSignage.class, mappedBy="documentSignageId.documentId")
	private Set<DocumentSignage> documentSignages;
	
	public Document() {

	}
		
	public Long getDocumentId() {
		return documentId;
	}
	
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	public Long getArrangementId() {
		return arrangementId;
	}
	public void setArrangementId(Long arrangementId) {
		this.arrangementId = arrangementId;
	}
	public byte[] getDocumentBlob() {
		return documentBlob;
	}
	public void setDocumentBlob(byte[] documentBlob) {
		this.documentBlob = documentBlob;
	}
	public String getDocumentState() {
		return documentState;
	}
	public void setDocumentState(String documentState) {
		this.documentState = documentState;
	}

	public Set<DocumentSignage> getDocumentSignages() {
		return documentSignages;
	}

	public void setDocumentSignages(Set<DocumentSignage> documentSignages) {
		this.documentSignages = documentSignages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrangementId == null) ? 0 : arrangementId.hashCode());
		result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());
		result = prime * result + ((documentState == null) ? 0 : documentState.hashCode());
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
		Document other = (Document) obj;
		if (arrangementId == null) {
			if (other.arrangementId != null)
				return false;
		} else if (!arrangementId.equals(other.arrangementId))
			return false;
		if (documentId == null) {
			if (other.documentId != null)
				return false;
		} else if (!documentId.equals(other.documentId))
			return false;
		if (documentState == null) {
			if (other.documentState != null)
				return false;
		} else if (!documentState.equals(other.documentState))
			return false;
		return true;
	}

}
