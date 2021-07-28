package za.co.sbsa.docusign.domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Access (AccessType.FIELD)
public class SignatoryArrangementBPPartakingID implements Serializable {

	private static final long serialVersionUID = -766239380832969855L;
	@Column(name="ARRANGEMENT_ID")
	@NotNull
	private Long arrangementId;
	@Column(name="SIGNINGBP_ID")
	@NotNull
	private String signingBPId;
	public Long getArrangementId() {
		return arrangementId;
	}
	public void setArrangementId(Long arrangementId) {
		this.arrangementId = arrangementId;
	}
	public String getSigningBPId() {
		return signingBPId;
	}
	public void setSigningBPId(String signingBPId) {
		this.signingBPId = signingBPId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrangementId == null) ? 0 : arrangementId.hashCode());
		result = prime * result + ((signingBPId == null) ? 0 : signingBPId.hashCode());
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
		SignatoryArrangementBPPartakingID other = (SignatoryArrangementBPPartakingID) obj;
		if (arrangementId == null) {
			if (other.arrangementId != null)
				return false;
		} else if (!arrangementId.equals(other.arrangementId))
			return false;
		if (signingBPId == null) {
			if (other.signingBPId != null)
				return false;
		} else if (!signingBPId.equals(other.signingBPId))
			return false;
		return true;
	}
}
