# ADFS adapter module for cms-server

Adapter in charge of integrating ADFS authentication capabilities
with the current cms-server

Responsibilities:

- Configure a SAML integration with the external IDP service
- Validate configuration properties in order to provide a consistent integration
- Log error messages during the authentication process
- Create cms-server users on the fly using SAML authentication metadata

### Pre-requisites

#### Building
- JDK 8
- Maven 2 or greater
- Build command
```
$> mvn clean package
```
- Output: WAR file with all the required components packaged

#### Execution

- Apache Tomcat 7.0.56 or newer
- Deployment as a standar WAR file
- Tomcat installation with HTTPS configured and a valid keystore (see [Keystore creation guide](#keystore-guide))
- CATALINA_HOME/properties/sf.properties file with the following mandatory settings
```
host:                            host-name, used as service provider name
port:                            SSL port, used for secure communication
adfs.idpName:                    string, identity provider host name
adfs.keystorePath:               string, full path of keystore
adfs.keystoreAlias:              string, alias name for domain
adfs.keystorePassword:           string, keystore password
adfs.keystorePrivatePassword:    string, keystore private-key password
```

## Provided services

- /login    : REST service that triggers the authentication process against the configured IDP service
- /logout   : REST service that triggers the logout process against the configured IDP service


# Keystore guide [keystore-guide]

## Create a proper self-signed keystore

-  Generate key with AES256:
```
openssl genrsa -aes256 -out server.key 1024
```

-  Generate cert request for CA:
```
openssl req -x509 -sha256 -new -key server.key -out server.csr
```

- Generate self signed expiry-time 10 years:
```
openssl x509 -sha256 -days 3652 -in server.csr -signkey server.key -out selfsigned.crt
```

-  Create PKCS12 keystore from private key and public certificate:
```
openssl pkcs12 -export -name myservercert -in selfsigned.crt -inkey server.key -out keystore.p12
```

-  Convert PKCS12 keystore into a JKS keystore:
```
keytool -importkeystore -destkeystore mykeystore.jks -srckeystore keystore.p12 -srcstoretype pkcs12 -alias myservercert
```

## Import an existing site certificate

- Export the certificate from the browser
- Import it to your keystore:
```
keytool -importcert -file idp-adfs-server.cer -keystore keystore.jks -alias idp-adfs-server
```

*Note:* The alias names and the CN names should match the site name, sometimes this is required for the system to work


