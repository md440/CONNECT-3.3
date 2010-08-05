package gov.hhs.fha.nhinc.docretrievedeferred.adapter.proxy;

import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;
import gov.hhs.fha.nhinc.common.nhinccommonadapter.RespondingGatewayCrossGatewayRetrieveSecuredRequestType;
import gov.hhs.fha.nhinc.docretrievedeferred.adapter.proxy.request.AdapterDocRetrieveDeferredReqNoOpImpl;
import gov.hhs.healthit.nhin.DocRetrieveAcknowledgementType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.*;

import static org.junit.Assert.assertNotNull;

/**
 * Created by
 * User: ralph
 * Date: Jul 29, 2010
 * Time: 3:39:22 PM
 */
public class AdapterDocRetrieveDeferredReqNoOpImplTest {
    private Mockery context;

    public AdapterDocRetrieveDeferredReqNoOpImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        context = new Mockery() {
            {
                setImposteriser(ClassImposteriser.INSTANCE);
            }
        };
    }

    @After
    public void tearDown() {
    }

     @Test
     public void testSendToAdapter() {
         //
         // Define mock objects
         //
         final AdapterDocRetrieveDeferredReqNoOpImpl                    mockAdapterDocRetrieveDeferredReqNoOpImpl;
         final RespondingGatewayCrossGatewayRetrieveSecuredRequestType  mockRespondingGatewayCrossGatewayRetrieveSecuredRequestType;
         final AssertionType                                            mockAssertionType;
         final DocRetrieveAcknowledgementType                           mockAck;

         //
         // Define the class to be tested and it's inputs and outputs.
         //
         AdapterDocRetrieveDeferredReqNoOpImpl retrieveRequest;
         RespondingGatewayCrossGatewayRetrieveSecuredRequestType            req;
         AssertionType                                                      assertionType;
         RetrieveDocumentSetRequestType                                     retrieveDocumentSetRequestType;
         DocRetrieveAcknowledgementType                                     ack;

         //
         // Instantiate the mock objects.
         //
         mockAdapterDocRetrieveDeferredReqNoOpImpl = context.mock(AdapterDocRetrieveDeferredReqNoOpImpl.class);
         mockRespondingGatewayCrossGatewayRetrieveSecuredRequestType = context.mock(RespondingGatewayCrossGatewayRetrieveSecuredRequestType.class);
         mockAssertionType = context.mock(AssertionType.class);
         mockAck = context.mock(DocRetrieveAcknowledgementType.class);

         //
         // Set up the expectations using the instantiated mock objects.
         //
         context.checking(new Expectations() {
             {
                 allowing(mockAdapterDocRetrieveDeferredReqNoOpImpl).sendToAdapter(mockRespondingGatewayCrossGatewayRetrieveSecuredRequestType,
                                                                             mockAssertionType);
                 will(returnValue(mockAck));
             }
         });

         //
         // Instantiate the object to be tested and it's inputs.
         //
         retrieveRequest = new AdapterDocRetrieveDeferredReqNoOpImpl();
         assertionType = new AssertionType();
         retrieveDocumentSetRequestType = new RetrieveDocumentSetRequestType();
         req = new RespondingGatewayCrossGatewayRetrieveSecuredRequestType();
         req.setRetrieveDocumentSetRequest(retrieveDocumentSetRequestType);

         //
         // Run the test.
         //
         ack = retrieveRequest.sendToAdapter(req, assertionType);

         //
         // Check the results.
         //
         assertNotNull("Ack was null", ack);
         context.assertIsSatisfied();
     }
}
