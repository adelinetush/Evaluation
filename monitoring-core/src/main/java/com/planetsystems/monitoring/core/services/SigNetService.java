/**
 * 
 */
package com.planetsystems.monitoring.core.services;

import java.io.IOException;
import java.util.List;

import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.esignature.Signature;
import com.planetsystems.monitoring.model.exception.OperationFailedException;
import com.planetsystems.monitoring.model.exception.ValidationFailedException;


/**
 * @author Personal
 *
 */
public interface SigNetService { 

	/**
	 * @param signatureStrings
	 * @return
	 * @throws IOException 
	 * @throws OperationFailedException 
	 */
	boolean createSignature(List<String> signatureStrings, User user) throws ValidationFailedException, IOException, OperationFailedException;
	
	boolean validateSignature( String sinatureString, User user ) throws ValidationFailedException, IOException, OperationFailedException;
	
	Signature saveSignature( Signature signature ) throws ValidationFailedException, IOException, OperationFailedException; 
	
	Signature editSignature( Signature signature ) throws ValidationFailedException, IOException, OperationFailedException;
	
	boolean deleteSignature( Signature signature ) throws ValidationFailedException, IOException, OperationFailedException;
	
	Signature getSignatureById( String id ) throws ValidationFailedException, OperationFailedException;
	

}
