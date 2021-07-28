package za.co.sbsa.docusign.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.sbsa.docusign.domain.VendorCrypto;
import za.co.sbsa.docusign.repositories.VendorCryptoRepository;
import za.co.sbsa.dto.VendorCryptoDto;

@Service
public class VendorCryptoServiceImpl implements VendorCryptoService {
	
	@Autowired
	private VendorCryptoRepository vendorCryptoRepository;
	
	@Override
	public VendorCryptoDto getVendorCrypto(Long keyId) throws Exception {
		try{
			VendorCrypto vendorCrypto = vendorCryptoRepository.findOne(keyId);
			VendorCryptoDto cryptoDto = new VendorCryptoDto(); 
			if(vendorCrypto!=null){
				cryptoDto.setHalfKey(vendorCrypto.getHalfKeyBlob());
				cryptoDto.setKeyId(vendorCrypto.getKeyId());
			}
			return cryptoDto;
		}catch(Exception ex){
			//TODO - Add the debug log
			throw ex;
		}
	}

}
