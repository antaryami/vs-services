package za.co.sbsa.dto;

public class DocumentSigningDto {

	private Integer documentId;
	private String signature;
	private String signingBPid;
	
	public Integer getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getSigningBPid() {
		return signingBPid;
	}
	public void setSigningBPid(String signingBPid) {
		this.signingBPid = signingBPid;
	}
	
}
