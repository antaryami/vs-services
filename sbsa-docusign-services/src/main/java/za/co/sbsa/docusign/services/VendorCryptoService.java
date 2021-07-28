package za.co.sbsa.docusign.services;

import za.co.sbsa.dto.VendorCryptoDto;

public interface VendorCryptoService {

	public VendorCryptoDto getVendorCrypto(Long keyId) throws Exception;
}
