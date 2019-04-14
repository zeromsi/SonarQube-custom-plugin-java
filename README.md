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
					<pluginClass>com.msi.custom.sonar.java.plugin.CustomJavaRulesEntry</pluginClass>
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
#### Note: If package hierarchy is changed, the follwoing plugin will be changed as shown,
```xml
			<plugin>
				<groupId>org.sonarsource.sonar-packaging-maven-plugin</groupId>
				<artifactId>sonar-packaging-maven-plugin</artifactId>
				<version>1.15</version>
				<extensions>true</extensions>
				<configuration>
					<pluginClass>location of ruleEntry class in src folder</pluginClass>
				</configuration>
			</plugin>
```
#### Rule-Definition class
Rule-Definition class(here, CustomRulesDefinition.java)  implements RulesDefinition interface.By implementing define  method this class become a server extension. When SonarQube server gets started this class gets instantiated. 
```xml
@Override
public void define(Context context) {


}
```
The repository name and supported language name gets recognized here.

```xml
public class CustomRulesDefinition implements RulesDefinition {

  public static final String REPOSITORY_KEY = "customRepo";

  @Override
  public void define (Context context) {
    NewRepository repository = context.createRepository(REPOSITORY_KEY, Java.KEY);
    repository.setName("CustomRepo");

    AnnotationBasedRulesDefinition.load(repository, "java", RulesList.getChecks());
    repository.done();
  }
}	

```

Here, ``` REPOSITORY_KEY ``` will be shown on ``` Repository ``` server dashboard. 


#### Class that scans tree and reports issue

This class contains the logic of your rule. To enable scanner and issue reporting, this class must extend BaseTreeVisitor class,  implement JavaFileScanner interface and override scanFile and visitVariable or other kind method. 

```xml
@Rule
(
key = "AvoidVariableNameWithoutm", 
name = "Avoid variable name withou m", 
description = "This checks a variable name without m.", 
tags = {"coding-guideline"}, 
priority = Priority.MINOR
)
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.ARCHITECTURE_CHANGEABILITY)
@SqaleConstantRemediation("10min")
public class AvoidVariableNameWithoutm extends BaseTreeVisitor implements JavaFileScanner {

	private static final String DEFAULT_VALUE = "VariableNameWithout-m";

	private JavaFileScannerContext context;

	@RuleProperty(
defaultValue = DEFAULT_VALUE, 
description = "Use m in as first character of private variable")
	protected String name;

	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
		scan(context.getTree());

	}

	@Override
	public void visitVariable(VariableTree tree) {

		String variableName = tree.simpleName().name();
		if (variableName.charAt(0) != 'm') {
			context.reportIssue(this, tree, "Variable name must starts with m");

		}

		super.visitVariable(tree);
	}
}

```

#### List of kinds that sonarQube can process,
#

| Method name | Parameter | What we can process and analyze |
| --- | --- | --- |
| visitCompilationUnit | CompilationUnitTree tree |  tree.packageDeclaration() tree.imports() tree.types() tree.moduleDeclaration()  |
| visitImport | ImportTree tree | tree.qualifiedIdentifier()  |
| visitClass | ClassTree tree |  tree.modifiers() tree.simpleName() tree.typeParameters() tree.superClass() tree.superInterfaces() tree.members() |
| visitMethod | MethodTree tree |   tree.modifiers()  tree.typeParameters()  tree.returnType()  tree.simpleName()  tree.parameters()  tree.defaultValue()  tree.throwsClauses()  tree.block()  |
| visitBlock | BlockTree tree | tree.body() |
| visitEmptyStatement | EmptyStatementTree tree |   |
| visitLabeledStatement | LabeledStatementTree tree | tree.label() tree.statement() |
| visitExpressionStatement | ExpressionStatementTree tree | tree.expression() |
| visitIfStatement | IfStatementTree tree | tree.condition()tree.thenStatement()tree.elseStatement()  |
| visitAssertStatement | AssertStatementTree tree | tree.condition()tree.detail()  |
| visitSwitchStatement | SwitchStatementTree tree | tree.expression()tree.cases()  |
| visitCaseGroup | CaseGroupTree tree | tree.labels()tree.body()  |
| visitCaseLabel | CaseLabelTree tree | tree.expression() |
| visitWhileStatement | WhileStatementTree tree | tree.condition()tree.statement() |
| visitDoWhileStatement | DoWhileStatementTree tree | tree.statement()tree.condition()  |
| visitForStatement | ForStatementTree tree | tree.initializer()tree.condition()tree.update()tree.statement() |
| visitForEachStatement | ForEachStatement tree | tree.variable()tree.expression()    tree.statement() |
| visitBreakStatement | BreakStatementTree tree | tree.label() |
| visitContinueStatement | ContinueStatementTree tree | tree.label() |
| visitReturnStatement | ReturnStatementTree tree | tree.expression() |
| visitThrowStatement | ThrowStatementTree tree | tree.expression() |
| visitSynchronizedStatement | SynchronizedStatementTree tree | tree.expression()tree.block()  |
| visitTryStatement | TryStatementTree tree | tree.resourceList()tree.block()tree.catches()tree.finallyBlock() |
| visitCatch | CatchTree tree | tree.parameter()tree.block()  |
| visitUnaryExpression | UnaryExpressionTree tree | tree.expression() |
| visitBinaryExpression | BinaryExpressionTree tree |    tree.leftOperand().accept(this);   tree.rightOperand().accept(this); |
| visitConditionalExpression | ConditionalExpressionTree tree | tree.condition()tree.trueExpression() tree.falseExpression()  |
| visitArrayAccessExpression | ArrayAccessExpressionTree tree | tree.expression()tree.dimension()  |
| visitMemberSelectExpression | MemberSelectExpressionTree tree | tree.annotations()tree.expression()tree.identifier()  |
| visitNewClass | NewClassTree tree |    tree.enclosingExpression() tree.identifier()tree.typeArguments()tree.arguments()tree.classBody()  |
| visitNewArray | NewArrayTree tree | tree.type()tree.dimensions()tree.initializers()  |
| visitMethodInvocation | MethodInvocationTree tree | tree.methodSelect()tree.typeArguments()tree.arguments()  |
| visitTypeCast | TypeCastTree tree | tree.type() tree.expression() |
| visitInstanceOf | InstanceOfTree tree | tree.expression()tree.type() |
| visitParenthesized | ParenthesizedTree tree | tree.expression() |
| visitAssignmentExpression | AssignmentExpressionTree tree | tree.variable()tree.expression() |
| visitLiteral | LiteralTree |   |
| visitIdentifier | IdentifierTree tree | tree.annotations() |
| visitVarType | VarTypeTree tree | tree.annotations() |
| visitVariable | VariableTree tree | tree.modifiers()tree.type()tree.simpleName()tree.initializer() |
| visitPrimitiveType | PrimitiveTypeTree tree | tree.annotations() |
| visitArrayType | ArrayTypeTree tree | tree.type() |
| visitEnumConstant | EnumConstantTree tree | tree.modifiers()tree.simpleName()tree.initializer() |
| visitParameterizedType | ParameterizedTypeTree tree | tree.annotations()tree.type() tree.typeArguments()  |
| visitWildcard | WildcardTree tree | tree.annotations()tree.bound()  |
| visitUnionType | UnionTypeTree tree | tree.typeAlternatives() |
| visitModifier | ModifiersTree tree | tree.annotations() |
| visitAnnotation | AnnotationTree tree | tree..annotationType()tree.arguments() |
| visitLambdaExpression | LambdaExpressionTree tree | tree.parameters()tree.body() |
| visitTypeParameter | TypeParameterTree tree | tree.identifier()tree.bounds() |
| visitTypeArguments | TypeArguments | (List\&lt;Tree\&gt;)trees |
| visitTypeParameters | TypeParameters | (List\&lt;TypeParameterTree\&gt;)trees |
| visitOther | Tree |   |
| visitMethodReference | MethodReferenceTree tree | tree.expression()tree.typeArguments()tree.method() |
| visitPackage | PackageDeclarationTree tree | tree.annotations()tree.packageName() |
| visitModule | ModuleDeclarationTree  tree | tree.annotations()tree.moduleName()tree.moduleDirectives() |
| visitRequiresDirectiveTree | RequiresDirectiveTree tree | tree.modifiers()tree.moduleName() |
| visitExportsDirectiveTree | ExportsDirectiveTree tree | tree.packageName()tree.moduleNames()  |
| visitOpensDirective | OpensDirectiveTree | tree.packageName()tree.moduleNames() |
| visitUsesDirective | UsesDirectiveTree | tree.typeName()) |
| visitProvidesDirective | ProvidesDirectiveTree | tree.typeName()tree.typeNames() |
| visitArrayDimension | ArrayDimensionTree | tree.annotations()tree.expression()  |

#### Java file check registrar class
This class(here,CustomJavaFileCheckRegistrar) is the batch extension by implementing CheckRegistrar which gets instantiated during the code analysis. This class registers all custom rule classes as a list of a batch.
```xml
public class CustomJavaFileCheckRegistrar implements CheckRegistrar {
  @Override
  public void register(RegistrarContext registrarContext) {

    // Call to registerClassesForRepository to associate the classes with the correct repository key

   registrarContext.registerClassesForRepository(CustomRulesDefinition.REPOSITORY_KEY, 
Arrays.asList(checkClasses()), Arrays.asList(testCheckClasses()));
  }

  /**
   * Lists all the checks provided by the plugin
   */
  public static Class<? extends JavaCheck>[] checkClasses() {
    return new Class[] { // List of rules to be included here
      AvoidVariableNameWithoutm.class
    };
  }

  /**
   * Lists all the test checks provided by the plugin
   */
  public static Class<? extends JavaCheck>[] testCheckClasses() {
    return new Class[] {};
  }
}

```
#### A class that list all rules classes
This class (here, RulesList) lists all custom rules and provides the list to the CustomRulesDefinition  class to register them with sonarqube.
```xml
public final class RulesList {

  private RulesList() {
  }

  public static List<Class> getChecks() {
    return ImmutableList.<Class>builder().addAll(getJavaChecks()).addAll(getJavaTestChecks()).build();
  }

  //add rule classes
  public static List<Class<? extends JavaCheck>> getJavaChecks() {
    return ImmutableList.<Class<? extends JavaCheck>>builder()
      .add(AvoidVariableNameWithoutm.class)
      .build();
  }

  public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
    return ImmutableList.<Class<? extends JavaCheck>>builder()
      .build();
  }
}
```

#### A class that enables plugin
This class is the entry point for the SONAR plugin. This class is extended from SonarPlugin class. This class includes server extension which gets instantiated during sonarqube startup and batch extensions which gets instantiated during the code analysis.

```xml
public class CustomJavaRulesEntry extends SonarPlugin {

  @Override
  public List getExtensions() {
    return Arrays.asList(
      // server extensions -> objects are instantiated during sonarqube startup
      CustomRulesDefinition.class,

      // batch extensions -> objects are instantiated during the code analysis
      CustomJavaFileCheckRegistrar.class);
  }
}
```
#### Enable plugin
* Create jar file.
* Command: Mvn install
* This command will create a jar file inside target folder. Just copy this .jar file and place this inside   	sonarQube_installed_directory‘/extensions/plugin’. 
* Restart sonarQube server
* Open  SonarQube application in browser
* Go to rules
* Select CustomRepo (name of the plugin)
You would be able to see the rule that has been written in this application as shown below.

### SonarQube Openshift DeployementConfig
#### SonarQube PostGresql Template
```xml

apiVersion: v1
kind: Template
metadata:
  name: "sonarqube"
objects:
- apiVersion: v1
  kind: Secret
  stringData:
    database-name: ${POSTGRES_DATABASE_NAME}
    database-password: ${POSTGRES_PASSWORD}
    database-user: ${POSTGRES_USERNAME}
  metadata:
    labels:
      app: sonarqube
      template: postgresql-persistent-template
    name: sonardb
  type: Opaque
- apiVersion: v1
  stringData:
    password: ${SONAR_LDAP_BIND_PASSWORD}
    username: ${SONAR_LDAP_BIND_DN}
  kind: Secret
  metadata:
    name: sonar-ldap-bind-dn
  type: kubernetes.io/basic-auth
- apiVersion: v1
  kind: ImageStream
  metadata:
    generation: 1
    labels:
      app: sonarqube
    name: sonarqube
  spec:
    lookupPolicy:
      local: false
    tags:
    - annotations: null
      from:
        kind: DockerImage
        name: sonarqube:latest
      generation: null
      importPolicy: {}
      name: latest
      referencePolicy:
        type: ""
  status:
    dockerImageRepository: ""
- apiVersion: v1
  kind: BuildConfig
  metadata:
    labels:
      app: sonarqube
      appName: sonarqube
      name: sonarqube
      type: image
    name: sonarqube
  spec:
    nodeSelector: null
    output:
      to:
        kind: ImageStreamTag
        name: sonarqube:latest
    postCommit: {}
    resources: {}
    runPolicy: Serial
    source:
      contextDir: "${SOURCE_REPOSITORY_CONTEXT_DIR}"
      git:
        ref: ${SOURCE_REPOSITORY_REF}
        uri: ${SOURCE_REPOSITORY_URL}
      type: Git
    strategy:
      dockerStrategy:
        from:
          kind: DockerImage
          name: 'sonarqube:latest'
        noCache: true
      type: Docker
    triggers:
    - type: ConfigChange
  status:
    lastVersion: 0
- apiVersion: v1
  kind: PersistentVolumeClaim
  metadata:
    name: sonarqube-data
  spec:
    accessModes:
    - ReadWriteOnce
    - ReadWriteMany
    resources:
      requests:
        storage: ${SONARQUBE_PERSISTENT_VOLUME_SIZE}
  status: {}
- apiVersion: v1
  kind: DeploymentConfig
  metadata:
    generation: 1
    labels:
      app: sonarqube
      template: postgresql-persistent-template
    name: sonardb
  spec:
    replicas: 1
    selector:
      name: sonardb
    strategy:
      activeDeadlineSeconds: 21600
      recreateParams:
        timeoutSeconds: 600
      resources: {}
      type: Recreate
    template:
      metadata:
        labels:
          name: sonardb
      spec:
        containers:
        - env:
          - name: POSTGRESQL_USER
            valueFrom:
              secretKeyRef:
                key: database-user
                name: sonardb
          - name: POSTGRESQL_PASSWORD
            valueFrom:
              secretKeyRef:
                key: database-password
                name: sonardb
          - name: POSTGRESQL_DATABASE
            valueFrom:
              secretKeyRef:
                key: database-name
                name: sonardb
          imagePullPolicy: IfNotPresent
          livenessProbe:
            failureThreshold: 3
            initialDelaySeconds: 30
            periodSeconds: 10
            successThreshold: 1
            tcpSocket:
              port: 5432
            timeoutSeconds: 1
          name: postgresql
          ports:
          - containerPort: 5432
            protocol: TCP
          readinessProbe:
            exec:
              command:
              - /bin/sh
              - -i
              - -c
              - psql -h 127.0.0.1 -U $POSTGRESQL_USER -q -d $POSTGRESQL_DATABASE -c
                'SELECT 1'
            failureThreshold: 3
            initialDelaySeconds: 5
            periodSeconds: 10
            successThreshold: 1
            timeoutSeconds: 1
          resources:
            limits:
              memory: ${POSTGRES_CONTAINER_MEMORY_SIZE_LIMIT}
              cpu: ${POSTGRES_CONTAINER_CPU_LIMIT}
            requests:
              memory: 1Gi
          securityContext:
            capabilities: {}
            privileged: false
          terminationMessagePath: /dev/termination-log
          terminationMessagePolicy: File
          volumeMounts:
          - mountPath: /var/lib/pgsql/data
            name: sonardb-data
        dnsPolicy: ClusterFirst
        restartPolicy: Always
        schedulerName: default-scheduler
        securityContext: {}
        terminationGracePeriodSeconds: 30
        volumes:
        - name: sonardb-data
          persistentVolumeClaim:
            claimName: sonardb
    test: false
    triggers:
    - imageChangeParams:
        automatic: true
        containerNames:
          - postgresql
        from:
          kind: ImageStreamTag
          name: 'postgresql:9.6'
          namespace: openshift
        lastTriggeredImage: >-
          registry.access.redhat.com/rhscl/postgresql-96-rhel7@sha256:2fea3ba5978862cc04ce5921462e9ca9caaec03f095c9b5c7be074358861b5c7
      type: ImageChange
    - type: ConfigChange
- apiVersion: v1
  kind: DeploymentConfig
  metadata:
    generation: 1
    labels:
      app: sonarqube
    name: sonarqube
  spec:
    replicas: 1
    selector:
      app: sonarqube
      deploymentconfig: sonarqube
    strategy:
      activeDeadlineSeconds: 21600
      recreateParams:
        timeoutSeconds: 600
      resources:
        limits:
          cpu: "1"
          memory: 2Gi
      type: Recreate
    template:
      metadata:
        annotations:
          openshift.io/generated-by: OpenShiftWebConsole
        labels:
          app: sonarqube
          deploymentconfig: sonarqube
      spec:
        containers:
        - env:
          - name: JDBC_URL
            value: jdbc:postgresql://sonardb:5432/sonar
          - name: JDBC_USERNAME
            valueFrom:
              secretKeyRef:
                key: database-user
                name: sonardb
          - name: JDBC_PASSWORD
            valueFrom:
              secretKeyRef:
                key: database-password
                name: sonardb
          - name: FORCE_AUTHENTICATION
            value: "true"
          - name: PROXY_HOST
            value: ${PROXY_HOST}
          - name: PROXY_PORT
            value: ${PROXY_PORT}
          - name: PROXY_USER
            value: ${PROXY_USER}
          - name: PROXY_PASSWORD
            value: ${PROXY_PASSWORD}
          - name: LDAP_URL
            value: ${SONAR_LDAP_URL}
          - name: LDAP_REALM
            value: ${SONAR_AUTH_REALM}
          - name: LDAP_AUTHENTICATION
            value: ${SONAR_LDAP_BIND_METHOD}
          - name: LDAP_USER_BASEDN
            value: ${SONAR_BASE_DN}
          - name: LDAP_USER_REAL_NAME_ATTR
            value: ${SONAR_LDAP_USER_REAL_NAME_ATTR}
          - name: LDAP_USER_EMAIL_ATTR
            value: ${SONAR_LDAP_USER_EMAIL_ATTR}
          - name: LDAP_USER_REQUEST
            value: ${SONAR_LDAP_USER_REQUEST}
          - name: LDAP_GROUP_BASEDN
            value: ${SONAR_LDAP_GROUP_BASEDN}
          - name: LDAP_GROUP_REQUEST
            value: ${SONAR_LDAP_GROUP_REQUEST}
          - name: LDAP_GROUP_ID_ATTR
            value: ${SONAR_LDAP_GROUP_ID_ATTR}
          - name: LDAP_CONTEXTFACTORY
            value: ${SONAR_LDAP_CONTEXTFACTORY}
          - name: SONAR_AUTOCREATE_USERS
            value: ${SONAR_AUTOCREATE_USERS}
          - name: LDAP_BINDDN
            valueFrom:
              secretKeyRef:
                key: username
                name: sonar-ldap-bind-dn
          - name: LDAP_BINDPASSWD
            valueFrom:
              secretKeyRef:
                key: password
                name: sonar-ldap-bind-dn
          imagePullPolicy: Always
          livenessProbe:
            failureThreshold: 3
            httpGet:
              path: /
              port: 9000
              scheme: HTTP
            initialDelaySeconds: 45
            periodSeconds: 10
            successThreshold: 1
            timeoutSeconds: 1
          name: sonarqube
          ports:
          - containerPort: 9000
            protocol: TCP
          readinessProbe:
            failureThreshold: 3
            httpGet:
              path: /
              port: 9000
              scheme: HTTP
            initialDelaySeconds: 10
            periodSeconds: 10
            successThreshold: 1
            timeoutSeconds: 1
          resources: {}
          terminationMessagePath: /dev/termination-log
          terminationMessagePolicy: File
          volumeMounts:
          - mountPath: /opt/sonarqube/data
            name: sonar-data
        dnsPolicy: ClusterFirst
        restartPolicy: Always
        schedulerName: default-scheduler
        securityContext: {}
        terminationGracePeriodSeconds: 30
        volumes:
        - name: sonar-data
          persistentVolumeClaim:
            claimName: sonarqube-data
    test: false
    triggers:
    - imageChangeParams:
        automatic: true
        containerNames:
        - sonarqube
        from:
          kind: ImageStreamTag
          name: sonarqube:latest
      type: ImageChange
    - type: ConfigChange
- apiVersion: v1
  kind: Route
  metadata:
    labels:
      app: sonarqube
    name: sonarqube
  spec:
    port:
      targetPort: 9000-tcp
    tls:
      termination: edge
    to:
      kind: Service
      name: sonarqube
      weight: 100
    wildcardPolicy: None
- apiVersion: v1
  kind: Service
  metadata:
    annotations:
      template.openshift.io/expose-uri: postgres://{.spec.clusterIP}:{.spec.ports[?(.name=="postgresql")].port}
    labels:
      app: sonarqube
      template: postgresql-persistent-template
    name: sonardb
  spec:
    ports:
    - name: postgresql
      port: 5432
      protocol: TCP
      targetPort: 5432
    selector:
      name: sonardb
    sessionAffinity: None
    type: ClusterIP
  status:
    loadBalancer: {}
- apiVersion: v1
  kind: Service
  metadata:
    labels:
      app: sonarqube
    name: sonarqube
  spec:
    ports:
    - name: 9000-tcp
      port: 9000
      protocol: TCP
      targetPort: 9000
    selector:
      deploymentconfig: sonarqube
    sessionAffinity: None
    type: ClusterIP
  status:
    loadBalancer: {}
- apiVersion: v1
  kind: PersistentVolumeClaim
  metadata:
    labels:
      app: sonarqube
      template: postgresql-persistent-template
    name: sonardb
  spec:
    accessModes:
    - ReadWriteOnce
    resources:
      requests:
        storage: ${POSTGRES_PERSISTENT_VOLUME_CLAIM_SIZE}
  status: {}
parameters:
  - description: Password for the Posgres Database to be used by Sonarqube
    displayName: Postgres password
    name: POSTGRES_PASSWORD
    generate: expression
    from: '[a-zA-Z0-9]{16}'
    required: true
  - description: Username for the Posgres Database to be used by Sonarqube
    displayName: Postgres username
    name: POSTGRES_USERNAME
    generate: expression
    from: 'user[a-z0-9]{8}'
    required: true
  - description: Database name for the Posgres Database to be used by Sonarqube
    displayName: Postgres database name
    name: POSTGRES_DATABASE_NAME
    value: sonar
    required: true
  - description: Postgres Persistent volume claim size
    displayName: Postgres Persistent volume claim size
    name: POSTGRES_PERSISTENT_VOLUME_CLAIM_SIZE
    value: 5Gi
    required: true
  - description: Postgres Container Memory size limit
    displayName: Postgres Container Memory size limit
    name: POSTGRES_CONTAINER_MEMORY_SIZE_LIMIT
    value: 1Gi
  - description: Postgres Container CPU limit
    displayName: Postgres Container CPU limit
    name: POSTGRES_CONTAINER_CPU_LIMIT
    value: 2000m
  - name: SONARQUBE_MEMORY
    description: SonarQube memory
    displayName: SonarQube memory
    value: 2Gi
  - name: SONARQUBE_CPU
    description: SonarQube Container CPU limit
    displayName: SonarQube Container CPU limit
    value: 2000m
  - name: SONARQUBE_PERSISTENT_VOLUME_SIZE
    description: The persistent storage volume for SonarQube to use for plugins/config/logs/etc...
    displayName: SonarQube Storage Space Size
    required: true
    value: 5Gi
  - name: SONAR_AUTH_REALM
    value: ''
    description: The type of authentication that SonarQube should be using (None or LDAP) (Ref - https://docs.sonarqube.org/display/PLUG/LDAP+Plugin)
    displayName: SonarQube Authentication Realm
  - name: SONAR_AUTOCREATE_USERS
    value: 'false'
    description: When using an external authentication system, should SonarQube automatically create accounts for users?
    displayName: Enable auto-creation of users from external authentication systems?
    required: true
  - name: PROXY_HOST
    description: Hostname of proxy server the SonarQube application should use to access the Internet
    displayName: Proxy server hostname/IP
  - name: PROXY_PORT
    description: TCP port of proxy server the SonarQube application should use to access the Internet
    displayName: Proxy server port
  - name: PROXY_USER
    description: Username credential when the Proxy Server requires authentication
    displayName: Proxy server username
  - name: PROXY_PASSWORD
    description: Password credential when the Proxy Server requires authentication
    displayName: Proxy server password
  - name: SONAR_LDAP_BIND_DN
    description: When using LDAP authentication, this is the Distinguished Name used for binding to the LDAP server
    displayName: LDAP Bind DN
  - name: SONAR_LDAP_BIND_PASSWORD
    description: When using LDAP for authentication, this is the password with which to bind to the LDAP server
    displayName: LDAP Bind Password
  - name: SONAR_LDAP_URL
    description: When using LDAP for authentication, this is the URL of the LDAP server in the form of ldap(s)://<hostname>:<port>
    displayName: LDAP Server URL
  - name: SONAR_LDAP_REALM
    description: When using LDAP, this allows for specifying a Realm within the directory server (Usually not used)
    displayName: LDAP Realm
  - name: SONAR_LDAP_AUTHENTICATION
    description: When using LDAP, this is the bind method (simple, GSSAPI, kerberos, CRAM-MD5, DIGEST-MD5)
    displayName: LDAP Bind Mode
  - name: SONAR_LDAP_USER_BASEDN
    description: The Base DN under which SonarQube should search for user accounts in the LDAP directory
    displayName: LDAP User Base DN
  - name: SONAR_LDAP_USER_REAL_NAME_ATTR
    description: The LDAP attribute which should be referenced to get a user's full name
    displayName: LDAP Real Name Attribute
  - name: SONAR_LDAP_USER_EMAIL_ATTR
    description: The LDAP attribute which should be referenced to get a user's e-mail address
    displayName: LDAP User E-Mail Attribute
  - name: SONAR_LDAP_USER_REQUEST
    description: An LDAP filter to be used to search for user objects in the LDAP directory
    displayName: LDAP User Request Filter
  - name: SONAR_LDAP_GROUP_BASEDN
    description: The Base DN under which SonarQube should search for groups in the LDAP directory
    displayName: LDAP Group Base DN
  - name: SONAR_LDAP_GROUP_REQUEST
    description: An LDAP filter to be used to search for group objects in the LDAP directory
    displayName: LDAP Group Request Filter
  - name: SONAR_LDAP_GROUP_ID_ATTR
    description: The LDAP attribute which should be referenced to get a group's ID
    displayName: LDAP Group Name Attribute
  - name: SONAR_LDAP_CONTEXTFACTORY
    description: The ContextFactory implementation to be used when communicating with the LDAP server
    displayName: LDAP Context Factory
    value: com.sun.jndi.ldap.LdapCtxFactory
  - name: SOURCE_REPOSITORY_URL
    description: The Git repository where the Docker build resources can be found
    displayName: Repository URL
    value: https://github.com/rht-labs/labs-ci-cd
  - name: SOURCE_REPOSITORY_REF
    value: master
    description: Branch from which to get docker build resources
    displayName: Repository branch
  - name: SOURCE_REPOSITORY_CONTEXT_DIR
    value: docker/sonarqube
    description: Directory path within the repository where the docker build resources can be found
    displayName: Context directory
```
#### SonarQube H2 template
```xml

kind: Template
apiVersion: v1
metadata:
  annotations:
    description: The SonarQube OpenShift template
    tags: instant-app,sonarqube
  name: sonarqube
message: "Login to SonarQube with the default admin user: admin/admin"
objects:
- apiVersion: v1
  kind: Service
  metadata:
    name: sonarqube
    labels:
      app: sonarqube
  spec:
    ports:
    - name: sonarqube
      port: 9000
      protocol: TCP
      targetPort: 9000
    selector:
      app: sonarqube
      deploymentconfig: sonarqube
    sessionAffinity: None
    type: ClusterIP
- apiVersion: v1
  kind: Route
  metadata:
    annotations:
      description: Route for SonarQube's http service.
    name: sonarqube
    labels:
      app: sonarqube
  spec:
    to:
      kind: Service
      name: sonarqube
- apiVersion: v1
  kind: ImageStream
  metadata:
    labels:
      app: sonarqube
    name: sonarqube
  spec:
    tags:
    - annotations:
        description: The SonarQube Docker image
        tags: sonarqube
      from:
        kind: DockerImage
        name: openshiftdemos/sonarqube:${SONARQUBE_VERSION}
      importPolicy: {}
      name: ${SONARQUBE_VERSION}
- apiVersion: v1
  kind: DeploymentConfig
  metadata:
    labels:
      app: sonarqube
      deploymentconfig: sonarqube
    name: sonarqube
  spec:
    replicas: 1
    selector:
      app: sonarqube
      deploymentconfig: sonarqube
    strategy:
      resources: {}
      rollingParams:
        intervalSeconds: 1
        maxSurge: 25%
        maxUnavailable: 25%
        timeoutSeconds: 600
        updatePeriodSeconds: 1
      type: Recreate
    template:
      metadata:
        annotations:
          openshift.io/container.sonarqube.image.entrypoint: '["./bin/run.sh"]'
        creationTimestamp: null
        labels:
          app: sonarqube
          deploymentconfig: sonarqube
      spec:
        containers:
        - image: ' '
          imagePullPolicy: IfNotPresent
          name: sonarqube
          ports:
          - containerPort: 9000
            protocol: TCP
          livenessProbe:
            failureThreshold: 5
            initialDelaySeconds: 180
            periodSeconds: 20
            successThreshold: 1
            httpGet:
              port: 9000
              path: /
            timeoutSeconds: 5
          readinessProbe:
            failureThreshold: 5
            initialDelaySeconds: 60
            periodSeconds: 20
            successThreshold: 1
            httpGet:
              port: 9000
              path: /
            timeoutSeconds: 5
          resources:
            requests:
              cpu: 200m
              memory: 1Gi
            limits:
              cpu: 1
              memory: ${SONAR_MAX_MEMORY}
          terminationMessagePath: /dev/termination-log
          volumeMounts:
          - mountPath: /opt/sonarqube/data
            name: sonarqube-data
        dnsPolicy: ClusterFirst
        restartPolicy: Always
        securityContext: {}
        terminationGracePeriodSeconds: 30
        volumes:
        - name: sonarqube-data
          persistentVolumeClaim:
            claimName: sonarqube-data
    triggers:
    - type: ConfigChange
    - imageChangeParams:
        automatic: true
        containerNames:
        - sonarqube
        from:
          kind: ImageStreamTag
          name: sonarqube:${SONARQUBE_VERSION}
      type: ImageChange
- apiVersion: v1
  kind: PersistentVolumeClaim
  metadata:
    name: sonarqube-data
  spec:
    accessModes:
    - ReadWriteOnce
    - ReadWriteMany
    resources:
      requests:
        storage: ${SONAR_VOLUME_CAPACITY}
parameters:
- displayName: SonarQube version
  value: "6.7"
  name: SONARQUBE_VERSION
  required: true
- description: Volume space available for SonarQube
  displayName: SonarQube Volume Capacity
  name: SONAR_VOLUME_CAPACITY
  required: true
  value: 1Gi
- displayName: SonarQube Max Memory
  name: SONAR_MAX_MEMORY
  required: true
  value: 2Gi
```
