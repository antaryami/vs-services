package za.co.sbsa.docusign.services;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.sbsa.docusign.crypto.Crypto;
import za.co.sbsa.docusign.domain.Document;
import za.co.sbsa.docusign.domain.DocumentSignage;
import za.co.sbsa.docusign.domain.DocumentSignageId;
import za.co.sbsa.docusign.domain.SignatoryArrangementBPPartaking;
import za.co.sbsa.docusign.repositories.DocumentRepository;
import za.co.sbsa.docusign.repositories.DocumentSignageRepository;
import za.co.sbsa.docusign.repositories.SBSACryptoRepository;
import za.co.sbsa.docusign.repositories.SignatureArrangementMasterRepository;
import za.co.sbsa.dto.SBSACryptoDto;
import za.co.sbsa.dto.SbsaDocument;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	DocumentSignageRepository documentSignageRepository;

	@Autowired
	SignatureArrangementMasterRepository samRepository;

	@Autowired
	SBSACryptoRepository sbsaCryptoRepository;
	
	@Autowired
	private ArrangementService arrangementService;
	
	@Autowired
	SBSACryptoService sBSACryptoService;

	@Override
	public boolean signDocument(long documentId, String signature, String signingBPid) throws Exception {
		SBSACryptoDto sbsaCryptoDto = sBSACryptoService.retrieveSbsaCrypto(signingBPid);

		byte[] publicKeyBlob = sbsaCryptoDto.getPublicKey();
		KeyFactory kf = KeyFactory.getInstance("DSA", "BC"); // or "EC" or wha
//		PublicKey publicKey = kf.generatePublic(new PKCS8EncodedKeySpec(publicKeyBlob));
		// from https://docs.oracle.com/javase/tutorial/security/apisign/vstep2.html
		PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(publicKeyBlob));

		Document documentRepositoryOne = documentRepository.findOne(documentId);
		byte[] electronicSignatureBytes = DatatypeConverter.parseBase64Binary(signature);
		boolean validSignature = Crypto.verify(publicKey, documentRepositoryOne.getDocumentBlob(), electronicSignatureBytes);
		if (! validSignature) {
			return false;
		}

		DocumentSignageId documentSignageId = new DocumentSignageId();
		documentSignageId.setDocumentId(documentId);
		documentSignageId.setSingingBPId(signingBPid);
		DocumentSignage documentSignage = new DocumentSignage();
		documentSignage.setDocumentSignageId(documentSignageId);
		documentSignage.setElectronicSignature(electronicSignatureBytes);

		documentSignageRepository.save(documentSignage);

		return  true;
	}

	public SbsaDocument getDocumentByID(long id) {
		Document document = documentRepository.findOne(id);
		SbsaDocument doc = new SbsaDocument();
		if(document!=null){
			doc.setDocumentID(document.getDocumentId());
			doc.setArrangementID(document.getArrangementId());
			doc.setDocState(document.getDocumentState());
			doc.setBinaryDoc(DatatypeConverter.printBase64Binary(document.getDocumentBlob()));
		}
		return doc;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveDocument(SbsaDocument doc) {
		Document document = getDocument(doc);
		if(document!=null){
			documentRepository.save(document);
		}
	}
	
	private Document getDocument(SbsaDocument doc){
		Document document = new Document();
		if(doc!=null){
			document = new Document();
			document.setArrangementId(doc.getArrangementID());
			document.setDocumentId(doc.getDocumentID());
			document.setDocumentBlob(doc.getBinaryDoc());
			document.setDocumentState(doc.getDocState());
		}
		return document;
	}
	
	@Override
	public List<Long> getDocIdsByBpId(String bpId) {
		List<SignatoryArrangementBPPartaking> arrangementBPPartakings = arrangementService.getArrangementByBpID(bpId);
		List<Long> docIds = new ArrayList<Long>();
		if(arrangementBPPartakings!= null && arrangementBPPartakings.size()>0){
			List<Long> arrangementIds = new ArrayList<Long>();
			for(SignatoryArrangementBPPartaking arrangementBPPartaking : arrangementBPPartakings){
				arrangementIds.add(arrangementBPPartaking.getSignatoryArrangementBPPartakingID().getArrangementId());
			}
			List<Document> documents = documentRepository.findAllByArrangementId(arrangementIds);
			if(documents!=null && documents.size()>0){
				for(Document document: documents){
					docIds.add(document.getDocumentId());
				}
			}
		}
		return docIds;
	}
}
