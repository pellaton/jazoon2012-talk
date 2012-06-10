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

import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import junit.framework.AssertionFailedError;

/**
 * Set of assert methods used on the {@link Diagnostic}s emitted by the Java {@link Compiler}.
 *
 * @author Michael Pellaton
 */
public final class DiagnosticsAssert {

  /**
   * Avoid instantiation.
   */
  private DiagnosticsAssert() {
    throw new AssertionError("Not instantiable.");
  }


  /**
   * Asserts that the list of {@link Diagnostic}s passed is null or empty (meaning the compiler has not emitted any
   * messages).
   *
   * @param diagnostics the diagnostics to assert
   */
  public static void assertNoCompilerMessage(List<Diagnostic<? extends JavaFileObject>> diagnostics) {
    if (diagnostics != null && !diagnostics.isEmpty()) {
      throw new AssertionFailedError();
    }
  }

  /**
   * Asserts that the diagnostics passed contain exactly the single expected message on the expected line.
   *
   * @param expectedKind the expected message
   * @param expectedLineNumber the expected line number
   * @param diagnostics the diagnostics to assert
   */
  public static void assertContainsSingleMessage(Kind expectedKind, long expectedLineNumber,
      List<Diagnostic<? extends JavaFileObject>> diagnostics) {

    if (diagnostics.size() != 1) {
      throw new AssertionFailedError("Number of diagnostic messages expected <1> but was <" + diagnostics.size() + ">");
    }

    Diagnostic<? extends JavaFileObject> diagnostic = diagnostics.get(0);
    if (!(expectedKind  == diagnostic.getKind() && expectedLineNumber == diagnostic.getLineNumber())) {
      throw new AssertionFailedError("Diagnostic message expected <" + expectedKind + ", on line " + expectedLineNumber 
          + "> but was <" + diagnostic.getKind() + " on line " + diagnostic.getLineNumber() + ">");
    }
  }
}