package za.co.sbsa.docusign.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="SBSA_CRYPTO")
public class SbsaCrypto implements Serializable {

	private static final long serialVersionUID = -559071914933287916L;
	@Id
	@Column(name="KEY_ID")
	@NotNull
	private Long keyId;
	@Column(name="PUBLIC_KEY_BLOB")
	@Lob
	@NotNull
	private byte[] publicKeyBlob;
	@Column(name="HALFKEY_BLOB",length=20971520)
	@Lob
	@NotNull
	private byte[] halfKeyBlob;
	@Column(name="SIGNING_BPID")
	private String signingBPId;
	@Column(name="PKI_PROTOCOL")
	private String pkiProtocol;
	@Column(name="HASHING_ALGORITHM")
	private String hashingAlgorithm;
	@Column(name="KEY_LENGTH")
	private Integer keyLength;
	@Column(name="ACTIVE_FROM")
	private Timestamp activeFrom;
	@Column(name="ACTIVE_TO")
	private Timestamp activeTo;
	@Column(name="STATE")
	@NotNull
	private String state;
	
	public Long getKeyId() {
		return keyId;
	}
	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}
	public byte[] getPublicKeyBlob() {
		return publicKeyBlob;
	}
	public void setPublicKeyBlob(byte[] publicKeyBlob) {
		this.publicKeyBlob = publicKeyBlob;
	}
	public byte[] getHalfKeyBlob() {
		return halfKeyBlob;
	}
	public void setHalfKeyBlob(byte[] halfKeyBlob) {
		this.halfKeyBlob = halfKeyBlob;
	}
	public String getSigningBPId() {
		return signingBPId;
	}
	public void setSigningBPId(String signingBPId) {
		this.signingBPId = signingBPId;
	}
	public String getPkiProtocol() {
		return pkiProtocol;
	}
	public void setPkiProtocol(String pkiProtocol) {
		this.pkiProtocol = pkiProtocol;
	}
	public String getHashingAlgorithm() {
		return hashingAlgorithm;
	}
	public void setHashingAlgorithm(String hashingAlgorithm) {
		this.hashingAlgorithm = hashingAlgorithm;
	}
	public Integer getKeyLength() {
		return keyLength;
	}
	public void setKeyLength(Integer keyLength) {
		this.keyLength = keyLength;
	}
	public Timestamp getActiveFrom() {
		return activeFrom;
	}
	public void setActiveFrom(Timestamp activeFrom) {
		this.activeFrom = activeFrom;
	}
	public Timestamp getActiveTo() {
		return activeTo;
	}
	public void setActiveTo(Timestamp activeTo) {
		this.activeTo = activeTo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activeFrom == null) ? 0 : activeFrom.hashCode());
		result = prime * result + ((activeTo == null) ? 0 : activeTo.hashCode());
		result = prime * result + ((hashingAlgorithm == null) ? 0 : hashingAlgorithm.hashCode());
		result = prime * result + ((keyId == null) ? 0 : keyId.hashCode());
		result = prime * result + ((keyLength == null) ? 0 : keyLength.hashCode());
		result = prime * result + ((pkiProtocol == null) ? 0 : pkiProtocol.hashCode());
		result = prime * result + ((signingBPId == null) ? 0 : signingBPId.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		SbsaCrypto other = (SbsaCrypto) obj;
		if (activeFrom == null) {
			if (other.activeFrom != null)
				return false;
		} else if (!activeFrom.equals(other.activeFrom))
			return false;
		if (activeTo == null) {
			if (other.activeTo != null)
				return false;
		} else if (!activeTo.equals(other.activeTo))
			return false;
		if (hashingAlgorithm == null) {
			if (other.hashingAlgorithm != null)
				return false;
		} else if (!hashingAlgorithm.equals(other.hashingAlgorithm))
			return false;
		if (keyId == null) {
			if (other.keyId != null)
				return false;
		} else if (!keyId.equals(other.keyId))
			return false;
		if (keyLength == null) {
			if (other.keyLength != null)
				return false;
		} else if (!keyLength.equals(other.keyLength))
			return false;
		if (pkiProtocol == null) {
			if (other.pkiProtocol != null)
				return false;
		} else if (!pkiProtocol.equals(other.pkiProtocol))
			return false;
		if (signingBPId == null) {
			if (other.signingBPId != null)
				return false;
		} else if (!signingBPId.equals(other.signingBPId))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
}
