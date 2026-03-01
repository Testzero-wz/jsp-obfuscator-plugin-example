package com.example.plugin.extensions;

import com.t3stzer0.jspobfuscator.api.plugin.context.ExtensionContext;
import com.t3stzer0.jspobfuscator.api.plugin.gadget.GadgetExtension;
import org.pf4j.Extension;

import java.util.List;

@Extension
public class GadgetExtensionExample implements GadgetExtension {

  private final String StringParam = "StringParam";
  private final String FloatParam = "FloatParam";
  private final String SelectParam = "SelectParam";
  private final List<String> SelectOptions = List.of("Option1", "Option2", "Option3");

  @Override
  public List<String> getDeclaration(ExtensionContext extensionContext) {
    return List.of("your declaration", "another declaration section");
  }

  @Override
  public List<String> getImports(ExtensionContext extensionContext) {
    return List.of("java.io.*", "java.util.*");
  }

  @Override
  public List<String> getCode(ExtensionContext extensionContext) {
    String stringInput = extensionContext.getStringInput(StringParam);
    float floatInput = extensionContext.getFloatInput(FloatParam);
    String selectInput = SelectOptions.get(extensionContext.getSelectInput(SelectParam));
    return List.of(
        "String stringInput=" + stringInput + ";",
        "float floatInput=" + floatInput + ";",
        "String selectInput=" + selectInput + ";",
        "other code here");
  }

  @Override
  public void initExtension(ExtensionContext ctx) {
    // register your extension params
    // required string input
    ctx.registerStringInput(StringParam, true, "default value");

    // optional number input
    ctx.registerFloatInput(FloatParam, false, 3.14f);

    // select input with default value
    ctx.registerSelectInput(SelectParam, SelectOptions, true, 0);
  }

  @Override
  public String getHelp() {
    return "this is a example extension";
  }
}
