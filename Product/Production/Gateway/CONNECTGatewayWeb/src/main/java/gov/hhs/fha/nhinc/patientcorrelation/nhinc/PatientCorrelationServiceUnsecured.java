/*
 * Copyright (c) 2012, United States Government, as represented by the Secretary of Health and Human Services. 
 * All rights reserved. 
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met: 
 *     * Redistributions of source code must retain the above 
 *       copyright notice, this list of conditions and the following disclaimer. 
 *     * Redistributions in binary form must reproduce the above copyright 
 *       notice, this list of conditions and the following disclaimer in the documentation 
 *       and/or other materials provided with the distribution. 
 *     * Neither the name of the United States Government nor the 
 *       names of its contributors may be used to endorse or promote products 
 *       derived from this software without specific prior written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE UNITED STATES GOVERNMENT BE LIABLE FOR ANY 
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND 
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */
package gov.hhs.fha.nhinc.patientcorrelation.nhinc;

import gov.hhs.fha.nhinc.async.AsyncMessageIdExtractor;
import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;
import gov.hhs.fha.nhinc.saml.extraction.SamlTokenExtractor;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.WebServiceContext;

import org.hl7.v3.AddPatientCorrelationRequestType;
import org.hl7.v3.AddPatientCorrelationResponseType;
import org.hl7.v3.RetrievePatientCorrelationsRequestType;
import org.hl7.v3.RetrievePatientCorrelationsResponseType;

/**
 * 
 * @author jhoppesc
 */
@WebService(serviceName = "PatientCorrelationService", portName = "PatientCorrelationPort", endpointInterface = "gov.hhs.fha.nhinc.nhinccomponentpatientcorrelation.PatientCorrelationPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:nhinccomponentpatientcorrelation", wsdlLocation = "WEB-INF/wsdl/PatientCorrelationServiceUnsecured/NhincComponentPatientCorrelation.wsdl")
@BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class PatientCorrelationServiceUnsecured {
    @Resource
    private WebServiceContext context;

    private PatientCorrelationService<RetrievePatientCorrelationsRequestType, RetrievePatientCorrelationsResponseType, AddPatientCorrelationRequestType, AddPatientCorrelationResponseType> service;

    public PatientCorrelationServiceUnsecured() {
        this(PatientCorrelationServiceUnsecuredFactory.getInstance());

    }

    public PatientCorrelationServiceUnsecured(
            PatientCorrelationServiceFactory<RetrievePatientCorrelationsRequestType, RetrievePatientCorrelationsResponseType, AddPatientCorrelationRequestType, AddPatientCorrelationResponseType> factory) {
        service = factory.createPatientCorrelationService();
    }

    public RetrievePatientCorrelationsResponseType retrievePatientCorrelations(
            RetrievePatientCorrelationsRequestType retrievePatientCorrelationsRequest) {
        AssertionType assertionType = createAssertion(context);

        if (retrievePatientCorrelationsRequest != null && retrievePatientCorrelationsRequest.getAssertion() != null) {
            retrievePatientCorrelationsRequest.getAssertion().setMessageId(createMessageId(context));
        }
        return service.retrievePatientCorrelations(retrievePatientCorrelationsRequest, assertionType);
    }

    public AddPatientCorrelationResponseType addPatientCorrelation(
            AddPatientCorrelationRequestType addPatientCorrelationRequest) {
        AssertionType assertionType = createAssertion(context);

        if (addPatientCorrelationRequest != null && addPatientCorrelationRequest.getAssertion() != null) {
            addPatientCorrelationRequest.getAssertion().setMessageId(createMessageId(context));
        }

        return service.addPatientCorrelation(addPatientCorrelationRequest, assertionType);
    }

    private AssertionType createAssertion(WebServiceContext context) {
        AssertionType assertion = SamlTokenExtractor.GetAssertion(context);

        // Extract the message id value from the WS-Addressing Header and place
        // it in the Assertion Class
        if (assertion != null) {
            assertion.setMessageId(AsyncMessageIdExtractor.GetAsyncMessageId(context));
        }

        return assertion;
    }

    private String createMessageId(WebServiceContext context) {
        return AsyncMessageIdExtractor.GetAsyncMessageId(context);
    }

}
