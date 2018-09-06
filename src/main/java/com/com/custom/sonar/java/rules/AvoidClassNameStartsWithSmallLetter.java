package com.com.custom.sonar.java.rules;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

@Rule(key = "AvoidClassNameStartsWithSmallLetter", name = "Avoid Class Name Starts With Small Letter", description = "This rule detects usage of small letter as the first character of a class.", tags = {
		"coding-guideline" }, priority = Priority.MINOR)
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.ARCHITECTURE_CHANGEABILITY)
@SqaleConstantRemediation("10min")
public class AvoidClassNameStartsWithSmallLetter extends BaseTreeVisitor implements JavaFileScanner {
	private static final String DEFAULT_VALUE = "ClassNameStartsWithSmallLetter";
	private JavaFileScannerContext context;
	@RuleProperty(defaultValue = DEFAULT_VALUE, description = "Avoid Class Name Starts With Small Letter")
	protected String name;

	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
		scan(context.getTree());

	}

	@Override
	public void visitClass(ClassTree tree) {
		String className = tree.simpleName().name();
		System.out.println("Scanning the variable : " + className);
		if (Character.isLowerCase(className.charAt(0))) {
			context.reportIssue(this, tree, "Class name started with lower case character!");
		}

		super.visitClass(tree);
	}
}