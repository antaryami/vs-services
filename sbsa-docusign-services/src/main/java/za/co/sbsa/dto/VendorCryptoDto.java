package za.co.sbsa.dto;

public class VendorCryptoDto {

	private Long keyId;
	private byte[] halfKey;
	
	public Long getKeyId() {
		return keyId;
	}
	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}
	public byte[] getHalfKey() {
		return halfKey;
	}
	public void setHalfKey(byte[] halfKey) {
		this.halfKey = halfKey;
	}
}
