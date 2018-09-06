package com.com.custom.sonar.java.rules;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.java.model.ModifiersUtils;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.Modifier;
import org.sonar.plugins.java.api.tree.ModifiersTree;
import org.sonar.plugins.java.api.tree.VariableTree;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

@Rule(
	key = "AvoidVariableNameWithoutm", 
	name = "Avoid variable name withou m", 
	description = "This checks a variable name without m.", 
	tags = {"coding-guideline"}, 
	priority = Priority.MINOR)
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.ARCHITECTURE_CHANGEABILITY)
@SqaleConstantRemediation("10min")
public class AvoidVariableNameWithoutm extends BaseTreeVisitor implements JavaFileScanner {

	private static final String DEFAULT_VALUE = "VariableNameWithout-m";

	private JavaFileScannerContext context;

	/**
	 * Avoid usage of the smaller length in local variable name in Quality
	 * profiles. The key
	 */
	@RuleProperty(defaultValue = DEFAULT_VALUE, description = "Use m in as first character of private variable")
	protected String name;

	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
		scan(context.getTree());

	}

	@Override
	public void visitVariable(VariableTree tree) {

		String variableName = tree.simpleName().name();
		
		 ModifiersTree modifiers = tree.modifiers();
		 
		if (ModifiersUtils.hasModifier(modifiers, Modifier.PRIVATE) && variableName.charAt(0) != 'm') {
			context.reportIssue(this, tree, "Variable name must starts with m");

		}

		super.visitVariable(tree);
	}
}
