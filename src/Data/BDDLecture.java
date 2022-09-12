package Data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class BDDLecture {
	FileInputStream inputStream;
	private String host;
	private String port;
	private String dbName;
	private String login;
	private String password;
	private static String chemin = "src/ressources/config.properties";
	final static String secretKey = "cleeTresSecrete";
	AESEncryptionDecryption AESEncryptionDecryption = new AESEncryptionDecryption();
	
	
	public void getAppConfig() throws IOException {
		try {
	            Properties prop = new Properties();
	            inputStream = new FileInputStream("src/ressources/config.properties");
	            if (inputStream != null) {
	                prop.load(inputStream);
	            } else {
	                throw new FileNotFoundException("Property file not found in the classpath");
	            }
	            // get the property value and print it out
	            setHost(AESEncryptionDecryption.decrypt(prop.getProperty("host"),secretKey));
	            setPort(AESEncryptionDecryption.decrypt(prop.getProperty("port"),secretKey));
	            setDbName(AESEncryptionDecryption.decrypt(prop.getProperty("dbName"),secretKey));
	            setLogin(AESEncryptionDecryption.decrypt(prop.getProperty("username"),secretKey));
	            setPassword(AESEncryptionDecryption.decrypt(prop.getProperty("password"),secretKey));
	        } catch (Exception e) {
	            System.out.println("Exception: " + e);
	        } finally {
	            inputStream.close();
	        }
	}
	/**
	 * Définit les propriétes de l'app
	 * 
	 * @throws IOException
	 */
	public static void setAppConfig(String dbName, String host, String port, String login, String password) throws IOException {
				Properties prop = new Properties();

				AESEncryptionDecryption AESEncryptionDecryption = new AESEncryptionDecryption();
		        dbName = AESEncryptionDecryption.encrypt(dbName, secretKey);
		        host = AESEncryptionDecryption.encrypt(host, secretKey);
		        port = AESEncryptionDecryption.encrypt(port, secretKey);
		        login = AESEncryptionDecryption.encrypt(login, secretKey);
		        password = AESEncryptionDecryption.encrypt(password, secretKey);
		        
		        
				prop.setProperty("dbName", dbName);
				prop.setProperty("host", host);
				prop.setProperty("port", port);
				prop.setProperty("username", login);
				prop.setProperty("password", password);
				prop.setProperty("key", secretKey);
				try {
					// On stocke le fichier sur le disque
					OutputStream out = new FileOutputStream(chemin);
					prop.store(out, "log BDD");
					out.flush();
					out.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
	/**---------------------Methodes Get---------------------**/
	
	
	public String getHost() {
		return this.host;
	}
	
	public String getPort() {
		return this.port;
	}
	
	public String getDbName() {
		return this.dbName;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	/**---------------------Methodes Set---------------------**/
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setPort(String port) {
		this.port = port;
	}
	
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}