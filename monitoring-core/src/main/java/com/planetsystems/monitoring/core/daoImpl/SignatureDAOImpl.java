/**
 * 
 */
package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.SignatureDAO;
import com.planetsystems.monitoring.model.esignature.Signature;


/**
 * @author Personal
 *
 */
@Repository("SignatureDAO")
public class SignatureDAOImpl extends BaseDaoImpl<Signature> implements SignatureDAO{

}
