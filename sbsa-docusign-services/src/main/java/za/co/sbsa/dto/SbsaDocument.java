package za.co.sbsa.dto;

import javax.xml.bind.DatatypeConverter;

public class SbsaDocument {

	private long documentID;
	private long arrangementID;
	private String docState;
	private byte[] binaryDoc;
	
	public byte[] getBinaryDoc() {
		return binaryDoc;
	}
	public void setBinaryDoc(String binaryDoc) {
//		this.binaryDoc = binaryDoc;
		this.binaryDoc = DatatypeConverter.parseBase64Binary(binaryDoc);
	}
	public long getDocumentID() {
		return documentID;
	}
	public void setDocumentID(long documentID) {
		this.documentID = documentID;
	}
	public long getArrangementID() {
		return arrangementID;
	}
	public void setArrangementID(long arrangementID) {
		this.arrangementID = arrangementID;
	}
	public String getDocState() {
		return docState;
	}
	public void setDocState(String docState) {
		this.docState = docState;
	}

}
