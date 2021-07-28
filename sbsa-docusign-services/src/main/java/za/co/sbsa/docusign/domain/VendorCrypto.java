package za.co.sbsa.docusign.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="VENDOR_CRYPTO")
public class VendorCrypto implements Serializable {

	private static final long serialVersionUID = -2470371487792869870L;
	@Id
	@Column(name="KEY_ID")
	@NotNull
	private Long keyId;
	@Column(name="PUBLIC_KEY_BLOB",length=20971520)
	@Lob
	@NotNull
	private byte[] publicKeyBlob;
	@Column(name="HALF_KEY_BLOB",length=20971520)
	@Lob
	@NotNull
	private byte[] halfKeyBlob;
	@Column(name="STATE")
	@NotNull
	private String state;
	@Column(name="PASSWORD")
	private byte[] password;
	
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public byte[] getPassword() {
		return password;
	}
	public void setPassword(byte[] password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keyId == null) ? 0 : keyId.hashCode());
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
		VendorCrypto other = (VendorCrypto) obj;
		if (keyId == null) {
			if (other.keyId != null)
				return false;
		} else if (!keyId.equals(other.keyId))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
}
