/*
 * Copyright (c) 2006-2007 Massachusetts General Hospital 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the i2b2 Software License v1.0 
 * which accompanies this distribution. 
 * 
 * Contributors:
 *     Mike Mendis - initial API and implementation
 */

package edu.harvard.i2b2.pm.ws;

import edu.harvard.i2b2.common.exception.I2B2Exception;
import edu.harvard.i2b2.common.util.jaxb.JAXBUnWrapHelper;
import edu.harvard.i2b2.common.util.jaxb.JAXBUtil;
import edu.harvard.i2b2.common.util.jaxb.JAXBUtilException;
import edu.harvard.i2b2.pm.datavo.i2b2message.BodyType;
import edu.harvard.i2b2.pm.datavo.i2b2message.RequestMessageType;
import edu.harvard.i2b2.pm.datavo.pm.GetUserConfigurationType;
import edu.harvard.i2b2.pm.util.JAXBConstant;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBElement;


/**
 * The PatientDataMessage class is a helper class to build PFT messages in the
 * i2b2 format
 */
public class ServicesMessage {
    private static Log log = LogFactory.getLog(ServicesMessage.class);
  //  private JAXBUtil jaxbUtil = null;
    RequestMessageType reqMessageType = null;

    /**
     * The constructor
     */
    public ServicesMessage(String requestPdo) throws I2B2Exception {
    	JAXBUtil jaxbUtil = MessageFactory.getJAXBUtil();
        		//new JAXBUtil(JAXBConstant.DEFAULT_PACKAGE_NAME);

        try {
        	System.out.println("Begin unmarshall of XML");
            log.debug("Begin unmarshall of XML");
            JAXBElement jaxbElement = jaxbUtil.unMashallFromString(requestPdo);

            if (jaxbElement == null) {
                throw new I2B2Exception(
                    "Null value from unmashall for PDO xml : " + requestPdo);
            }

            log.debug("Finished unmarshall of XML");
            this.reqMessageType = (RequestMessageType) jaxbElement.getValue();
            System.out.println("++reqMessageType++"+reqMessageType);
        } catch (JAXBUtilException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new I2B2Exception("Umashaller error: " + e.getMessage() +
                requestPdo, e);
        }
    }

    /**
     * Function to get RequestData object from i2b2 request message type
     * @return
     * @throws JAXBUtilException
     */
    
    public BodyType getRequestType() throws JAXBUtilException {
        BodyType bodyType = reqMessageType.getMessageBody();
     //   JAXBUnWrapHelper helper = new JAXBUnWrapHelper();
      //  Object requestType =  helper.getObjectByClass(bodyType.getAny(),
//        		GetUserConfigurationType.class);

        return bodyType;
    }
    

    /*
    public GetUserConfigurationType getRequestType() throws JAXBUtilException {
        BodyType bodyType = reqMessageType.getMessageBody();
        JAXBUnWrapHelper helper = new JAXBUnWrapHelper();
        GetUserConfigurationType requestType = (GetUserConfigurationType) helper.getObjectByClass(bodyType.getAny(),
        		GetUserConfigurationType.class);

        return requestType;
    }
    */
	
    
    public RequestMessageType getRequestMessageType() { 
    	return reqMessageType;
    }
}
