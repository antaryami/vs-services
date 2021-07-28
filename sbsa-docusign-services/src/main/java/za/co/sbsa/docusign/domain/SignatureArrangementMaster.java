package za.co.sbsa.docusign.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="SIGNATURE_ARRANGEMENT_MASTER")
public class SignatureArrangementMaster implements Serializable {

	private static final long serialVersionUID = -4091471646225623248L;
	@Id
	@Column(name="ARRANGEMENT_ID")
	@NotNull
	private Long arrangementId;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="BP_ID")
	@NotNull
	private String bpID;
	@OneToMany(cascade = CascadeType.MERGE, targetEntity = SignatoryArrangementBPPartaking.class, mappedBy="signatoryArrangementBPPartakingID.arrangementId")
	private Set<SignatoryArrangementBPPartaking> arrangementBPPartakings;
	
	public Long getArrangementId() {
		return arrangementId;
	}
	public void setArrangementId(Long arrangementId) {
		this.arrangementId = arrangementId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBpID() {
		return bpID;
	}
	public void setBpID(String bpID) {
		this.bpID = bpID;
	}
	public Set<SignatoryArrangementBPPartaking> getArrangementBPPartakings() {
		return arrangementBPPartakings;
	}
	public void setArrangementBPPartakings(Set<SignatoryArrangementBPPartaking> arrangementBPPartakings) {
		this.arrangementBPPartakings = arrangementBPPartakings;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrangementId == null) ? 0 : arrangementId.hashCode());
		result = prime * result + ((bpID == null) ? 0 : bpID.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		SignatureArrangementMaster other = (SignatureArrangementMaster) obj;
		if (arrangementId == null) {
			if (other.arrangementId != null)
				return false;
		} else if (!arrangementId.equals(other.arrangementId))
			return false;
		if (bpID == null) {
			if (other.bpID != null)
				return false;
		} else if (!bpID.equals(other.bpID))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}
}
