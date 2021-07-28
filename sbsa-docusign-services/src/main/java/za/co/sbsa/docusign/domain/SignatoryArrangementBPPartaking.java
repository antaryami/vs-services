package za.co.sbsa.docusign.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="SIGNATORY_ARRANGEMENT_BP_PARTAKING")
public class SignatoryArrangementBPPartaking implements Serializable {

	private static final long serialVersionUID = 8942977941201389546L;
	@EmbeddedId
	private SignatoryArrangementBPPartakingID signatoryArrangementBPPartakingID;

	public SignatoryArrangementBPPartakingID getSignatoryArrangementBPPartakingID() {
		return signatoryArrangementBPPartakingID;
	}

	public void setSignatoryArrangementBPPartakingID(SignatoryArrangementBPPartakingID signatoryArrangementBPPartakingID) {
		this.signatoryArrangementBPPartakingID = signatoryArrangementBPPartakingID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((signatoryArrangementBPPartakingID == null) ? 0 : signatoryArrangementBPPartakingID.hashCode());
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
		SignatoryArrangementBPPartaking other = (SignatoryArrangementBPPartaking) obj;
		if (signatoryArrangementBPPartakingID == null) {
			if (other.signatoryArrangementBPPartakingID != null)
				return false;
		} else if (!signatoryArrangementBPPartakingID.equals(other.signatoryArrangementBPPartakingID))
			return false;
		return true;
	}
	
}
