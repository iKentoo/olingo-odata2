/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 *        or more contributor license agreements.  See the NOTICE file
 *        distributed with this work for additional information
 *        regarding copyright ownership.  The ASF licenses this file
 *        to you under the Apache License, Version 2.0 (the
 *        "License"); you may not use this file except in compliance
 *        with the License.  You may obtain a copy of the License at
 * 
 *          http://www.apache.org/licenses/LICENSE-2.0
 * 
 *        Unless required by applicable law or agreed to in writing,
 *        software distributed under the License is distributed on an
 *        "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *        KIND, either express or implied.  See the License for the
 *        specific language governing permissions and limitations
 *        under the License.
 ******************************************************************************/
package org.apache.olingo.odata2.core.uri.expression;

import org.apache.olingo.odata2.api.edm.EdmType;
import org.apache.olingo.odata2.api.exception.ODataApplicationException;
import org.apache.olingo.odata2.api.uri.expression.BinaryExpression;
import org.apache.olingo.odata2.api.uri.expression.BinaryOperator;
import org.apache.olingo.odata2.api.uri.expression.CommonExpression;
import org.apache.olingo.odata2.api.uri.expression.ExceptionVisitExpression;
import org.apache.olingo.odata2.api.uri.expression.ExpressionKind;
import org.apache.olingo.odata2.api.uri.expression.ExpressionVisitor;

/**
 * @author
 */
public class BinaryExpressionImpl implements BinaryExpression {
  final protected InfoBinaryOperator operatorInfo;
  final protected CommonExpression leftSide;
  final protected CommonExpression rightSide;
  final protected Token token;
  protected EdmType edmType;

  public BinaryExpressionImpl(final InfoBinaryOperator operatorInfo, final CommonExpression leftSide, final CommonExpression rightSide, final Token token) {
    this.operatorInfo = operatorInfo;
    this.leftSide = leftSide;
    this.rightSide = rightSide;
    this.token = token;
    edmType = null;
  }

  @Override
  public BinaryOperator getOperator() {
    return operatorInfo.getOperator();
  }

  @Override
  public CommonExpression getLeftOperand() {
    return leftSide;
  }

  @Override
  public CommonExpression getRightOperand() {
    return rightSide;
  }

  @Override
  public EdmType getEdmType() {
    return edmType;
  }

  @Override
  public CommonExpression setEdmType(final EdmType edmType) {
    this.edmType = edmType;
    return this;
  }

  @Override
  public ExpressionKind getKind() {
    return ExpressionKind.BINARY;
  }

  @Override
  public String getUriLiteral() {
    return operatorInfo.getSyntax();
  }

  @Override
  public Object accept(final ExpressionVisitor visitor) throws ExceptionVisitExpression, ODataApplicationException {
    Object retLeftSide = leftSide.accept(visitor);
    Object retRightSide = rightSide.accept(visitor);

    return visitor.visitBinary(this, operatorInfo.getOperator(), retLeftSide, retRightSide);
  }

  public Token getToken() {
    return token;
  }

}
