/*
 * *****************************************************************************************************************
 * Copyright 2012 Michael Pellaton
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the License.
 *
 * Contributors:
 *   Michael Pellaton
 *   
 *   
 * This code is derived from the following project published under the same license by the same author:
 * - http://code.google.com/p/spring-configuration-validation-processor
 * 
 * The purpose of this slimmed-down version is to serve as example for a conference presentation.
 * *****************************************************************************************************************
 */
package com.github.pellaton.jazoon2012;

import java.io.IOException;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import org.junit.Test;

public class JazoonProcessorTest {

  /**
   * Tests the processor with a perfectly valid Spring application context configuration class.
   */
  @Test
  public void validConfigurationClassWithoutErrors() throws IOException {
    compileAndAssertNoMessage("ValidTestConfiguration");
  }

  /**
   * Tests the processor's detection of final classes annotated with @Configuration.
   */
  @Test
  public void finalConfigurationClass() throws IOException {
    compileAndAssert("FinalClassTestConfiguration", Kind.ERROR, 32);
  }

  /**
   * Tests the processor's detection of private methods annotated with @Bean.
   */
  @Test
  public void privateBeanMethod() throws IOException {
    compileAndAssert("PrivateBeanMethodTestConfiguration", Kind.ERROR, 36);
  }


  
  private void compileAndAssert(String configurationClass, Kind expectedKind, long expectedLineNumber) throws IOException {
    List<Diagnostic<? extends JavaFileObject>> diagnostics = compileClass(configurationClass);
    DiagnosticsAssert.assertContainsSingleMessage(expectedKind, expectedLineNumber, diagnostics);
  }

  private void compileAndAssertNoMessage(String configurationClass) throws IOException {
    List<Diagnostic<? extends JavaFileObject>> diagnostics = compileClass(configurationClass);
    DiagnosticsAssert.assertNoCompilerMessage(diagnostics);
  }

  private List<Diagnostic<? extends JavaFileObject>> compileClass(String configurationClass) throws IOException {
    return AnnotationProcessorTestCompiler.compileClass("/com/github/pellaton/jazoon2012/" + configurationClass, 
        new JazoonProcessor());
  }
}
