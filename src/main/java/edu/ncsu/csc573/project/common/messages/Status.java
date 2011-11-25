/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

import java.math.BigInteger;

/**
 *
 * @author krishna
 */
public class Status implements IStatus{
    private BigInteger errorid;
    
    public Status(BigInteger id) {
        errorid = id;
    }
    
    public BigInteger getErrorId() {
            return errorid;
    }
}
