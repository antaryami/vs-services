package za.co.sbsa.dto;

public class SBSACryptoDto {

	private String bpId;
	private Long keyId;
	private byte[] halfKey1;
	private byte[] publicKey;
	
	public String getBpId() {
		return bpId;
	}
	public void setBpId(String bpId) {
		this.bpId = bpId;
	}
	public Long getKeyId() {
		return keyId;
	}
	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}
	public byte[] getHalfKey1() {
		return halfKey1;
	}
	public void setHalfKey1(byte[] halfKey1) {
		this.halfKey1 = halfKey1;
	}
	public byte[] getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(byte[] publicKey) {
		this.publicKey = publicKey;
	}
	
}
