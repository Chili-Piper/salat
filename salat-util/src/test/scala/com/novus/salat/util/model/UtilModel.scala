/*
 * Copyright (c) 2010 - 2012 Novus Partners, Inc. <http://novus.com>
 *
 * Module:        salat-util
 * Class:         UtilModel.scala
 * Last modified: 2012-04-28 20:34:21 EDT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Project:      http://github.com/novus/salat
 * Wiki:         http://github.com/novus/salat/wiki
 * Mailing list: http://groups.google.com/group/scala-salat
 */
package com.novus.salat.util.model

case class OneConstructorWithArgs(x: String)

case class OneConstructorWithArgsOneEmpty(x: String) {
  def this() = this("")
}

case class TwoConstructorsWithArgs(x: String, y: Boolean) {
  def this() = this("", false)
  def this(y: Boolean) = this("", y)
}

case class OnlyEmptyConstructor()

abstract class AbstractCaseObject
case object ConcreteCaseObject extends AbstractCaseObject

case class ἀρετή(πρᾶξις: Boolean = false)