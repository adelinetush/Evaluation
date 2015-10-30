/**
 * 
 */
package com.planetsystems.monitoring.model.esignature;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.planetsystems.monitoring.model.ParentEntity;
import com.planetsystems.monitoring.model.Role;

/**
 * @author Personal
 * 
 */

@Entity
@Table(name = "Signature")
public class Signature extends ParentEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8754360905488744135L;

	private String signature;

	private byte[] signatureBytes;

	private Role role;

	public Signature() {

		super();

	}

	public Signature(String signature) {

		this.signature = signature;
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature
	 *            the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the signatureBytes
	 */
	@Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(name = "image_content", nullable = true)
	public byte[] getSignatureBytes() {
		return signatureBytes;
	}

	/**
	 * @param signatureBytes
	 *            the signatureBytes to set
	 */
	public void setSignatureBytes(byte[] signatureBytes) {
		this.signatureBytes = signatureBytes;
	}

}
