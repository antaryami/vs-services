package za.co.sbsa.docusign.services;

import za.co.sbsa.dto.SbsaDocument;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface DocumentService {

	public boolean signDocument(long documentId, String signature, String signingBPid) throws Exception;
	public SbsaDocument getDocumentByID(long id);
	public void saveDocument(SbsaDocument doc);
	public List<Long> getDocIdsByBpId(String bpId);
}
