
package com.com.custom.sonar.java.rules;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.VariableTree;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

@Rule(key = "AvoidSmallerLengthLocalVariableName", name = "Avoid usage of the smaller length in local variable name", description = "This rule detects usage of smaller length local variable name. Variable name should not be smaller than 4 characters.", tags = {
		"coding-guideline" }, priority = Priority.MINOR)
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.ARCHITECTURE_CHANGEABILITY)
@SqaleConstantRemediation("10min")
public class AvoidSmallerLengthVariableNameRule extends BaseTreeVisitor implements JavaFileScanner {
	private static final String DEFAULT_VALUE = "SmallerLengthLocalVariable";
	private JavaFileScannerContext context;
	@RuleProperty(defaultValue = DEFAULT_VALUE, description = "Avoid usage of the smaller length in local variable name")
	protected String name;

	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
		scan(context.getTree());

	}

	@Override
	public void visitVariable(VariableTree tree) {

		String variableName = tree.simpleName().name();
		System.out.println("Scanning the variable : " + variableName);

		if (variableName.length() < 4) {
			context.reportIssue(this, tree, "Variable length is less than 4 characters");
		}

		super.visitVariable(tree);
	}
}