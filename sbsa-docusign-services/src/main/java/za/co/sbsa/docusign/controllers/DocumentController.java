package za.co.sbsa.docusign.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sbsa.docusign.services.DocumentService;
import za.co.sbsa.dto.DocumentSigningDto;
import za.co.sbsa.dto.SbsaDocument;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/document")
@Api(value = "sbsa-document-services")
public class DocumentController {

	@Autowired
	private DocumentService documentService;


	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved the resource"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ApiOperation(value = "Search a document with an ID", response = SbsaDocument.class)
	@RequestMapping(value = "/sbsa-doc/{id}", method= RequestMethod.GET, produces = "application/json")
	public SbsaDocument getDOcument(@PathVariable Integer id) {
		return documentService.getDocumentByID(id);
	}


	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully persisted the resource"),
			@ApiResponse(code = 401, message = "You are not authorized to persist the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ApiOperation(value = "Saves a document to be signed by corporate/individual with bpId")
	@RequestMapping (value = "/save", method= RequestMethod.POST, produces = "application/json")
	public void saveDocument(@RequestBody SbsaDocument sbsaDocument) {
		sbsaDocument.setDocState("Unsigned");
		documentService.saveDocument(sbsaDocument);
	}

	@ApiOperation(value = "Search a document with an ID", response = List.class)
	@RequestMapping(value = "/unsigned-doc/{bpId}", method= RequestMethod.GET, produces = "application/json")
	public List<Long> getDOcumentIdByBPID(@PathVariable String bpId) {
		return documentService.getDocIdsByBpId(bpId);
	}


	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully persisted the resource"),
			@ApiResponse(code = 401, message = "You are not authorized to persist the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ApiOperation(value = "Saves a document, its signing BP and signature to database")
	@RequestMapping (value = "/sign", method= RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> signDocument(@RequestBody DocumentSigningDto documentSigningDto) {
		Security.addProvider(new BouncyCastleProvider());
		Boolean response = false;
		try {
			response = documentService.signDocument(documentSigningDto.getDocumentId(), documentSigningDto.getSignature(), documentSigningDto.getSigningBPid());
		} catch(Exception ex){
			ex.printStackTrace();
				//TODO - Log the exception
				return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Boolean>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Search a document with an ID", response = SbsaDocument.class)
	@RequestMapping(value = "/unsigned-dummy-doc/{bpId}", method= RequestMethod.GET, produces = "application/json")
	public List<Long> getDummyDocumentByBPID(@PathVariable String bpId) {		
		List<Long> ids = new ArrayList<Long>();
		ids.add(1l);
		ids.add(2l);
		return ids;
	}
	
	@ApiOperation(value = "Search a document with an ID")
	@RequestMapping(value = "/dummy-doc-image/{docId}", method= RequestMethod.GET)
	public String getDocImageByID(@PathVariable String docId){
//		return MyViewModel.getPdfByte();
		return null;
	}

}
