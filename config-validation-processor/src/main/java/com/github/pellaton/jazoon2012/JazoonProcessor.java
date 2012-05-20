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
 * *****************************************************************************************************************
 */
package com.github.pellaton.jazoon2012;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * Java 6 annotation processor that processes classes annotated with
 * {@code @Configuration}. This processor is meant for source validation during
 * compilation only and does not write any output besides compiler messages.
 * 
 * <p>
 * This example processor checks for two conditions:
 * </p>
 * <ul>
 *   <li>@Configuration annotated classes must not be final</li>
 *   <li>@Bean annotated methods must not be private</li>
 * </ul>
 */
public class JazoonProcessor extends AbstractProcessor {

  /**
   * Mandatory no-argument constructor.
   */
  public JazoonProcessor() {
    super();
  }

  
  /**
   * @see AbstractProcessor#init(ProcessingEnvironment)
   */
  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
  }

  
  /**
   * @see AbstractProcessor#process(Set, RoundEnvironment)
   */
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    return false;
  }

}
