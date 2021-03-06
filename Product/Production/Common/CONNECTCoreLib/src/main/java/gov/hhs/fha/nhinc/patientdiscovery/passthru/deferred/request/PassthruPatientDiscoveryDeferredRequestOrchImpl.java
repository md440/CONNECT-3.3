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
package gov.hhs.fha.nhinc.patientdiscovery.passthru.deferred.request;

import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;
import gov.hhs.fha.nhinc.common.nhinccommon.NhinTargetSystemType;
import gov.hhs.fha.nhinc.nhinclib.NhincConstants;
import gov.hhs.fha.nhinc.patientdiscovery.PatientDiscoveryAuditLogger;
import gov.hhs.fha.nhinc.patientdiscovery.PatientDiscoveryAuditor;
import gov.hhs.fha.nhinc.patientdiscovery.entity.deferred.request.OutboundPatientDiscoveryDeferredRequestDelegate;
import gov.hhs.fha.nhinc.patientdiscovery.entity.deferred.request.OutboundPatientDiscoveryDeferredRequestOrchestratable;
import gov.hhs.fha.nhinc.patientdiscovery.nhin.deferred.request.proxy.NhinPatientDiscoveryDeferredReqProxy;
import gov.hhs.fha.nhinc.patientdiscovery.nhin.deferred.request.proxy.NhinPatientDiscoveryDeferredReqProxyObjectFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hl7.v3.MCCIIN000002UV01;
import org.hl7.v3.PRPAIN201305UV02;
import org.hl7.v3.RespondingGatewayPRPAIN201305UV02RequestType;

public class PassthruPatientDiscoveryDeferredRequestOrchImpl {

    private Log log = null;

    public PassthruPatientDiscoveryDeferredRequestOrchImpl() {
        log = createLogger();
    }

    protected Log createLogger() {
        return ((log != null) ? log : LogFactory.getLog(getClass()));
    }

    protected PatientDiscoveryAuditor createAuditLogger() {
        return new PatientDiscoveryAuditLogger();
    }

    public MCCIIN000002UV01 processPatientDiscoveryAsyncReq(PRPAIN201305UV02 message, AssertionType assertion,
            NhinTargetSystemType targets) {
        log.debug("Entering PassthruPatientDiscoveryDeferredRequestOrchImpl.processPatientDiscoveryAsyncReq with message: "
                + message + " assertion: " + assertion + " targets: " + targets);
                
        auditRequestToNhin(message, assertion);
        
        OutboundPatientDiscoveryDeferredRequestDelegate pdReqDelegate = new OutboundPatientDiscoveryDeferredRequestDelegate();
        OutboundPatientDiscoveryDeferredRequestOrchestratable pdReqOrchestratable = new OutboundPatientDiscoveryDeferredRequestOrchestratable(
                pdReqDelegate);
        pdReqOrchestratable.setAssertion(assertion);
        pdReqOrchestratable.setRequest(message);
        pdReqOrchestratable.setTarget(targets);
        MCCIIN000002UV01 response = ((OutboundPatientDiscoveryDeferredRequestOrchestratable) pdReqDelegate
                .process(pdReqOrchestratable)).getResponse();
        
        auditResponseFromNhin(response, assertion);
        
        log.debug("Exiting PassthruPatientDiscoveryDeferredRequestOrchImpl.processPatientDiscoveryAsyncReq with response: "
                + response);
        return response;
    }
    
    protected void auditRequestToNhin(PRPAIN201305UV02 request, AssertionType assertion) {
        PatientDiscoveryAuditor auditLog = createAuditLogger();
        auditLog.auditNhinDeferred201305(request, assertion, NhincConstants.AUDIT_LOG_OUTBOUND_DIRECTION);
    }

    protected void auditResponseFromNhin(MCCIIN000002UV01 resp, AssertionType assertion) {
        PatientDiscoveryAuditor auditLog = new PatientDiscoveryAuditLogger();
        auditLog.auditAck(resp, assertion, NhincConstants.AUDIT_LOG_INBOUND_DIRECTION,
                NhincConstants.AUDIT_LOG_NHIN_INTERFACE);
    }
}
