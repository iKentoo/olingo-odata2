/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.olingo.odata2.api.exception;

import org.apache.olingo.odata2.api.commons.HttpStatusCodes;

/**
 * {@link ODataMessageException} with a HTTP status code.
 * @author
 */
public abstract class ODataHttpException extends ODataMessageException {

  private static final long serialVersionUID = 1L;

  protected final HttpStatusCodes httpStatus;

  public static final MessageReference COMMON = createMessageReference(ODataHttpException.class, "COMMON");

  public ODataHttpException(final MessageReference messageReference, final HttpStatusCodes httpStatus) {
    this(messageReference, null, httpStatus);
  }

  public ODataHttpException(final MessageReference messageReference, final HttpStatusCodes httpStatus, final String errorCode) {
    this(messageReference, null, httpStatus, errorCode);
  }

  public ODataHttpException(final MessageReference messageReference, final Throwable cause, final HttpStatusCodes httpStatus) {
    this(messageReference, cause, httpStatus, null);
  }

  public ODataHttpException(final MessageReference messageReference, final Throwable cause, final HttpStatusCodes httpStatus, final String errorCode) {
    super(messageReference, cause, errorCode);
    this.httpStatus = httpStatus;
  }

  public HttpStatusCodes getHttpStatus() {
    return httpStatus;
  }
}
