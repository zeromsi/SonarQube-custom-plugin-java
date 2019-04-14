# SonarQube-custom-plugin-java
## What are issues
SonarQube raises an issue every time a piece of code breaks a coding rule. There are five different kind of issues,
### BLOCKER
Bug with a high probability to impact the behavior of the application in production: memory leak, unclosed JDBC connection, .... The code MUST be immediately fixed.

### CRITICAL
Either a bug with a low probability to impact the behavior of the application in production or an issue which represents a security flaw: empty catch block, SQL injection, ... The code MUST be immediately reviewed. 

### MAJOR
Quality flaw which can highly impact the developer productivity: uncovered piece of code, duplicated blocks, unused parameters, ...

### MINOR
Quality flaw which can slightly impact the developer productivity: lines should not be too long, "switch" statements should have at least 3 cases, ...
INFO
Neither a bug nor a quality flaw, just a finding.

## What are plugins
Plugins are set of rules in sonarQube. In case of java, plugins get activated in the form of jar file that is placed inside ‘/extensions/plugins’ directory.


## What sonarQube can do
* SonarQube Java Analyzer can parse java code and can make syntax tree, where each and every syntax is a specific kind of tree. Tree List that sonarQube can process. 
* We can check our code against our own set of rules as well as against a customised version which is a combination of our custom rules and rules of other plugins from marketplace. 
* With the help of Quality gate, we can enforce coding policies. For example, 3 Vulnerabilities can be accepted.  

## Normal Installation
* Download [sonarQube](https://www.sonarqube.org/downloads/).
* Extract in a directory.
* Go to extract directory/sonarqube/bin/[OS]
* Open terminal and type ./sonar.sh console

#### By default sonarQube server starts on 9000 port. For more info,visit doc, youtube .

## Custom Plugin development
### Required software
* Maven 3.x or above
* JDK 1.8

### Pom.xml

#### Properties
```xml 
<properties>
          <java.plugin.version>3.13.1</java.plugin.version>
          <sslr.version>1.21</sslr.version>
          <gson.version>2.6.2</gson.version>
          <sonar.version>6.7.4</sonar.version>
          <sonarjava.version>5.5.0.14655</sonarjava.version>
</properties>
```
#### Dependencies

```xml
<dependencies>
		<dependency>
			<groupId>org.codehaus.sonar</groupId>
			<artifactId>sonar-plugin-api</artifactId>
			<version>4.5.6</version>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>org.sonarsource.java</groupId>
			<artifactId>sonar-java-plugin</artifactId>
			<type>sonar-plugin</type>
			<version>${java.plugin.version}</version>
			<scope>provided</scope>
		</dependency>
		
		<dependency>
			<groupId>org.sonarsource.java</groupId>
			<artifactId>java-frontend</artifactId>
			<version>${java.plugin.version}</version>
		</dependency>

		<dependency>
			<groupId>org.sonarsource.sslr-squid-bridge</groupId>
			<artifactId>sslr-squid-bridge</artifactId>
			<version>2.6.1</version>
			<exclusions>
				<exclusion>
					<groupId>org.codehaus.sonar.sslr</groupId>
					<artifactId>sslr-core</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

		<dependency>
			<groupId>org.sonarsource.java</groupId>
			<artifactId>java-checks-testkit</artifactId>
			<version>${java.plugin.version}</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.codehaus.sonar.sslr</groupId>
			<artifactId>sslr-testing-harness</artifactId>
			<version>1.19.2</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.11</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.easytesting</groupId>
			<artifactId>fest-assert</artifactId>
			<version>1.4</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-classic</artifactId>
			<version>0.9.30</version>
			<scope>test</scope>
		</dependency>
		
		<dependency>
			<groupId>org.sonarsource.sonarqube</groupId>
			<artifactId>sonar-plugin-api</artifactId>
			<version>${sonar.version}</version>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>org.sonarsource.java</groupId>
			<artifactId>sonar-java-plugin</artifactId>
			<type>sonar-plugin</type>
			<version>${sonarjava.version}</version>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>org.sonarsource.sslr-squid-bridge</groupId>
			<artifactId>sslr-squid-bridge</artifactId>
			<version>2.6.1</version>
			<exclusions>
				<exclusion>
					<groupId>org.codehaus.sonar.sslr</groupId>
					<artifactId>sslr-core</artifactId>
				</exclusion>
				<exclusion>
					<groupId>org.codehaus.sonar</groupId>
					<artifactId>sonar-plugin-api</artifactId>
				</exclusion>
				<exclusion>
					<groupId>org.codehaus.sonar.sslr</groupId>
					<artifactId>sslr-xpath</artifactId>
				</exclusion>
				<exclusion>
					<groupId>org.slf4j</groupId>
					<artifactId>jcl-over-slf4j</artifactId>
				</exclusion>
				<exclusion>
					<groupId>org.slf4j</groupId>
					<artifactId>slf4j-api</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

		<dependency>
			<groupId>org.sonarsource.java</groupId>
			<artifactId>java-checks-testkit</artifactId>
			<version>${sonarjava.version}</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
			<version>${gson.version}</version>
		</dependency>

		<dependency>
			<groupId>org.sonarsource.sslr</groupId>
			<artifactId>sslr-testing-harness</artifactId>
			<version>${sslr.version}</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>commons-lang</groupId>
			<artifactId>commons-lang</artifactId>
			<version>2.6</version>
		</dependency>

		<dependency>
			<groupId>com.google.guava</groupId>
			<artifactId>guava</artifactId>
			<version>19.0</version>
		</dependency>

		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>1.6.2</version>
		</dependency>

		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.11</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.assertj</groupId>
			<artifactId>assertj-core</artifactId>
			<version>3.6.1</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-classic</artifactId>
			<version>0.9.30</version>
			<scope>test</scope>
		</dependency>
	</dependencies>

```


#### Plugins
```xml
<build>
		<plugins>
			<plugin>
				<groupId>org.sonarsource.sonar-packaging-maven-plugin</groupId>
				<artifactId>sonar-packaging-maven-plugin</artifactId>
				<version>1.15</version>
				<extensions>true</extensions>
				<configuration>
					<pluginClass>com.ashish.custom.sonar.java.plugin.CustomJavaRulesEntry</pluginClass>
				</configuration>
			</plugin>

			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.1</version>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>

			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-dependency-plugin</artifactId>
				<version>2.10</version>
				<executions>
					<execution>
						<id>copy</id>
						<phase>test-compile</phase>
						<goals>
							<goal>copy</goal>
						</goals>
						<configuration>
							<artifactItems>
								<artifactItem>
									<groupId>org.apache.commons</groupId>
									<artifactId>commons-collections4</artifactId>
									<version>4.0</version>
									<type>jar</type>
								</artifactItem>
							</artifactItems>
							<outputDirectory>${project.build.directory}/test-jars</outputDirectory>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
```
