package za.co.sbsa.docusign.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import za.co.sbsa.docusign.services.SBSACryptoService;
import za.co.sbsa.docusign.services.VendorCryptoService;
import za.co.sbsa.dto.SBSACryptoDto;
import za.co.sbsa.dto.VendorCryptoDto;

@RestController
@RequestMapping("/crypto")
@Api(value = "sbsa-crypto-services")
public class CryptoController {
	
	@Autowired
	private SBSACryptoService sBSACryptoService;
	@Autowired
	private VendorCryptoService vendorCryptoService;
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved the resorce"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ApiOperation(value = "Search a vendor crypto with a Key Id", response = VendorCryptoDto.class)
	@RequestMapping(value = "/vendor-crypto/{keyId}", method= RequestMethod.GET, produces = "application/json")
	public ResponseEntity<VendorCryptoDto> getVendorCrypto(@PathVariable Long keyId) {
		VendorCryptoDto vendorCryptoDto = null;
		try{
			vendorCryptoDto = vendorCryptoService.getVendorCrypto(keyId);
		}catch(Exception ex){
			//TODO - Log the exception;
			return new ResponseEntity<VendorCryptoDto>(vendorCryptoDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<VendorCryptoDto>(vendorCryptoDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Search a SBSA Crypto with a BPID", response = SBSACryptoDto.class)
	@RequestMapping(value = "/sbsa-crypto/{bpId}", method= RequestMethod.GET, produces = "application/json")
	public ResponseEntity<SBSACryptoDto> getSbsaCrypto(@PathVariable String bpId){
		SBSACryptoDto sbsaCryptoDto = null;
		try{
			sbsaCryptoDto = sBSACryptoService.retrieveSbsaCrypto(bpId);
		}catch(Exception ex){
			//TODO - Log the exception
			return new ResponseEntity<SBSACryptoDto>(sbsaCryptoDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SBSACryptoDto>(sbsaCryptoDto, HttpStatus.OK);
	}
}
