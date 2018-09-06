
package com.msi.custom.sonar.java.plugin;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.Plugin;
import org.sonar.api.SonarPlugin;

/*********************************
 * Entry point of the sonar plugin
 ********************************/
public class CustomJavaRulesEntry  extends SonarPlugin{

  @Override
  public List getExtensions() {
    return Arrays.asList(
      // server extensions -> objects are instantiated during sonarqube startup
      CustomRulesDefinition.class,

      // batch extensions -> objects are instantiated during the code analysis
      CustomJavaFileCheckRegistrar.class);
  }

}