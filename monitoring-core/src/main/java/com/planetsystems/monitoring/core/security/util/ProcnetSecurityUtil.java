package com.planetsystems.monitoring.core.security.util;

import java.io.IOException;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import com.planetsystems.monitoring.core.security.ProcnetUserDetails;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.exception.SessionExpiredException;
import com.planetsystems.monitoring.model.exception.UnexpectedException;
import com.thoughtworks.xstream.core.util.Base64Encoder;


public final class ProcnetSecurityUtil {

	/** random number generator algorithm */
	/*
	 * private static final String RNG_ALGORITHM = "SHA1PRNG";
	 */

	/**
	 * message digest algorithm (must be sufficiently long to provide the key
	 * and initialization vector)
	 */
	private static final String DIGEST_ALGORITHM = "SHA-256";

	/** key algorithm (must be compatible with CIPHER_ALGORITHM) */
	private static final String KEY_ALGORITHM = "AES";

	/**
	 * key algorith (must be compatible with CIPHER_ALGORITHM
	 */
	@SuppressWarnings("unused")
	private static final String KEY_ALGORITHM_2 = "DES";

	
	/** cipher algorithm (must be compatible with KEY_ALGORITHM) */
	// private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final String CIPHER_ALGORITHM = "AES/CTR/NoPadding";

	/** cipher algorithm (must be compatible with KEY_ALGORITHM) */
	@SuppressWarnings("unused")
	private static final String CIPHER_ALGORITHM_2 = "DES/CBC/PKCS5Padding";

	/** The logger. */
	private static Logger log = LoggerFactory
			.getLogger(ProcnetSecurityUtil.class);

	/**
	 * private default constructor
	 */
	private ProcnetSecurityUtil() {

	}

	/**
	 * This method will hash <code>strToEncode</code> using the preferred
	 * algorithm. Currently, Mohr's preferred algorithm is hard coded to be
	 * SHA-1.
	 * 
	 * @param strToEncode
	 *            string to encode
	 * @return the SHA-1 encryption of a given string
	 */
	public static String encodeString(String strToEncode) {
		try {
			String algorithm = "SHA1";
			MessageDigest md = MessageDigest.getInstance(algorithm);
			/*
			 * pick a character encoding that doesn't rely on the platform
			 * default
			 */
			byte[] input = strToEncode.getBytes();
			return hexString(md.digest(input));
		} catch (NoSuchAlgorithmException ex) {
			throw new UnexpectedException(ex);
		}
	}

	/**
	 * encodes a string using the platform default character encoding
	 * <blockquote>This method is here only for backward compatibility because
	 * it calls the buggy hexString2</blockquote>
	 */
	public static String encodeString2(String strToEncode) {
		try {
			String algorithm = "SHA1";
			MessageDigest md = MessageDigest.getInstance(algorithm);

			/*
			 * we use platform default character encoding
			 */
			byte[] input = strToEncode.getBytes();
			return hexString2(md.digest(input));
		} catch (NoSuchAlgorithmException ex) {
			throw new UnexpectedException(ex);
		}
	}

	/**
	 * Convenience method to convert a byte array to a string <br/>
	 * <strong>This method is here only for backward compatibility because its
	 * buggy.</strong>
	 * 
	 * @param b
	 *            Byte array to convert to HexString
	 * @return Hexidecimal based string
	 */
	private static String hexString2(byte[] b) {
		if (b == null || b.length < 1) {
			return "";
		}
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			s.append(Integer.toHexString(b[i] & 0xFF));
		}

		return new String(s);
	}

	/**
	 * Convenience method to convert a byte array to a string. This solves a bug
	 * in the above method.
	 * 
	 * @param b
	 * @return
	 */
	private static String hexString(byte[] b) {
		StringBuffer buf = new StringBuffer();
		char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		int len = b.length;
		int high = 0;
		int low = 0;
		for (int i = 0; i < len; i++) {
			high = ((b[i] & 0xf0) >> 4);
			low = (b[i] & 0x0f);
			buf.append(hexChars[high]);
			buf.append(hexChars[low]);
		}

		return buf.toString();
	}

	/**
	 * Sets the <code>Spring security context</code> with the current
	 * <code>User</code> authentication details.
	 * 
	 * @param userDetails
	 *            - User to place in security context.
	 */
	public static void setSecurityContext(ProcnetUserDetails userDetails) {
		User user = userDetails.getSystemUser();

		// Proceed to put the User in the spring security Context.
		SecurityContext sc = new SecurityContextImpl();
		Authentication auth = new UsernamePasswordAuthenticationToken(user,
				user.getPassword(), userDetails.getAuthorities());
		sc.setAuthentication(auth);
		SecurityContextHolder.setContext(sc);

		log.info("Successfully logged in User: << " + user.getUsername()
				+ " >> ");
		log.info("<< " + "Setting User:" + user.getUsername() + " in Context"
				+ ">> ");
	}

	/**
	 * gets the logged in user
	 * 
	 * @return
	 * @throws SessionExpiredException
	 *             thrown of the session of the logged in user is expired
	 */
	public static User getLoggedInUser() throws SessionExpiredException {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			Authentication auth = context.getAuthentication();
			if (auth != null) {
				User user = null;
				if (auth.getPrincipal() instanceof ProcnetUserDetails) {
					ProcnetUserDetails userDetails = (ProcnetUserDetails) auth
							.getPrincipal();
					user = userDetails.getSystemUser();
				} else if (auth.getPrincipal() instanceof User) {
					user = (User) auth.getPrincipal();
				} else {
					log.debug("Auth not an instance of MohrUserDetails - i.e. no logged in user. Auth="
							+ auth);
					throw new SessionExpiredException(
							"Could not find logged in user");
				}
				return user;
			}
		}

		log.debug("No Spring SecurityContext or Authentication - i.e. no logged in user");
		throw new SessionExpiredException("Could not find logged in user");
	}

	/**
	 * This method will generate a random string
	 * 
	 * @return a secure random token.
	 */
	public static String getRandomToken() {
		Random rng = new Random();
		return encodeString(Long.toString(System.currentTimeMillis())
				+ Long.toString(rng.nextLong()));
	}

	/**
	 * check the given user if they have a clear text password and then creates
	 * a hashed password for them to use and assign them a salt as well
	 * 
	 * @param user
	 */
	public static void prepUserCredentials(User user) {
		if (user != null) {
			if (StringUtils.isNotBlank(user.getClearTextPassword())
					&& StringUtils.isNotEmpty(user.getClearTextPassword())) {

				if (StringUtils.isBlank(user.getSalt())
						&& StringUtils.isEmpty(user.getSalt())) {
					user.setSalt(ProcnetSecurityUtil.getRandomToken());
				}

				String hashedPassword = ProcnetSecurityUtil.encodeString(user
						.getClearTextPassword() + user.getSalt());
				user.setPassword(hashedPassword);
				user.setDateOfLastPasswordChange(Calendar.getInstance()
						.getTime());
			}
		}
	}

	/**
	 * generates a random password with a given minimum length
	 * 
	 * @param minLength
	 *            minimun length of the password
	 * @return
	 */
	public static String generatePassword(int minLength) {
		Random ran = new Random();
		char[] passCharacters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j',
				'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
				'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
				'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
				'Z', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '@', };

		StringBuffer sb = new StringBuffer();
		for (int index = 0; index < minLength; index++) {
			sb.append(passCharacters[ran.nextInt(passCharacters.length)]);
		}

		return sb.toString();
	}

	/**
	 * Encrypt the specified cleartext using the given password. With the
	 * correct salt, number of iterations, and password, the decrypt() method
	 * reverses the effect of this method. This uses a given salt, and the
	 * user-specified number of iterations and password to create a 16-byte
	 * secret key and 16-byte initialization vector. The secret key and
	 * initialization vector are then used in the AES-128 cipher to encrypt the
	 * given cleartext.
	 * 
	 * @param salt
	 *            salt to use in the encryption
	 * @param iterations
	 *            number of iterations to use in salting
	 * @param password
	 *            password to use for encryption
	 * @param clearText
	 *            clear text to be encrypted
	 * @param characterSet
	 *            the name of the character set to use for the conversion
	 *            between characters and bytes
	 * @return cipherText
	 * @throws Exception
	 *             on any error encountered in the encryption
	 */
	public static byte[] encrypt(final byte[] salt, final int iterations,
			final String password, final byte[] clearText, String characterSet)
			throws Exception {

		/*
		 * computer key and initialization vector
		 */
		final MessageDigest shaDigest = MessageDigest
				.getInstance(DIGEST_ALGORITHM);
		byte[] pw = password.getBytes(Charset.forName(characterSet));

		for (int i = 0; i < iterations; i++) {
			/*
			 * add salt
			 */
			final byte[] salted = new byte[pw.length + salt.length];
			System.arraycopy(pw, 0, salted, 0, pw.length);
			System.arraycopy(salt, 0, salted, pw.length, salt.length);
			Arrays.fill(pw, (byte) 0x00);

			/*
			 * compute SHA-256 digest
			 */
			shaDigest.reset();
			pw = shaDigest.digest(salted);
			Arrays.fill(salted, (byte) 0x00);
		}

		/*
		 * extract the 16-byte key and initialization vector from the SHA-256
		 * digest
		 */
		final byte[] key = new byte[16];
		final byte[] iv = new byte[16];
		System.arraycopy(pw, 0, key, 0, 16);
		System.arraycopy(pw, 16, iv, 0, 16);
		Arrays.fill(pw, (byte) 0x00);

		/*
		 * perform AES-128 encryption
		 */
		final Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, KEY_ALGORITHM),
				new IvParameterSpec(iv));

		Arrays.fill(key, (byte) 0x00);
		Arrays.fill(iv, (byte) 0x00);

		return cipher.doFinal(clearText);
	}

	/**
	 * Decrypt the specified ciphertext using the given password. With the
	 * correct salt, number of iterations, and password, this method reverses
	 * the effect of the encrypt() method. This method uses the user-specified
	 * salt, number of iterations, and password to recreate the 16-byte secret
	 * key and 16-byte initialization vector. The secret key and initialization
	 * vector are then used in the AES-128 cipher to decrypt the given
	 * ciphertext.
	 * 
	 * @param salt
	 *            salt to be used in the decryption
	 * @param iterations
	 *            number of iterations to use in salting
	 * @param password
	 *            password to be used in decrypting
	 * @param cipherText
	 *            cipher text to decrypt.
	 * @param characterSet
	 *            the name of the character set to use for the conversion
	 *            between characters and bytes
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(final byte[] salt, final int iterations,
			final String password, final byte[] cipherText, String characterSet)
			throws Exception {

		/*
		 * compute key and initialization vector
		 */
		final MessageDigest shaDigest = MessageDigest
				.getInstance(DIGEST_ALGORITHM);
		byte[] pw = password.getBytes(Charset.forName(characterSet));

		for (int i = 0; i < iterations; i++) {
			/*
			 * add salt
			 */
			final byte[] salted = new byte[pw.length + salt.length];
			System.arraycopy(pw, 0, salted, 0, pw.length);
			System.arraycopy(salt, 0, salted, pw.length, salt.length);
			Arrays.fill(pw, (byte) 0x00);

			/*
			 * compute SHA-256 digest
			 */
			shaDigest.reset();
			pw = shaDigest.digest(salted);
			Arrays.fill(salted, (byte) 0x00);
		}

		/*
		 * extract the 16-byte key and initialization vector from the SHA-256
		 * digest
		 */
		final byte[] key = new byte[16];
		final byte[] iv = new byte[16];
		System.arraycopy(pw, 0, key, 0, 16);
		System.arraycopy(pw, 16, iv, 0, 16);
		Arrays.fill(pw, (byte) 0x00);

		/*
		 * perform AES-128 decryption
		 */
		final Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, KEY_ALGORITHM),
				new IvParameterSpec(iv));

		Arrays.fill(key, (byte) 0x00);
		Arrays.fill(iv, (byte) 0x00);

		return cipher.doFinal(cipherText);
	}

	/**
	 * From a base 64 representation, returns the corresponding byte[]
	 * 
	 * @param data
	 *            String The base64 representation
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] base64ToByte(String data) throws IOException {
		Base64Encoder decoder = new Base64Encoder();
		return decoder.decode(data);
	}

	/**
	 * From a byte[] returns a base 64 representation
	 * 
	 * @param data
	 *            byte[]
	 * @return String
	 * @throws IOException
	 */
	public static String byteToBase64(byte[] data) {
		Base64Encoder encoder = new Base64Encoder();
		return encoder.encode(data);
	}
}
