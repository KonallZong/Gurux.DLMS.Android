//
// --------------------------------------------------------------------------
//  Gurux Ltd
// 
//
//
// Filename:        $HeadURL$
//
// Version:         $Revision$,
//                  $Date$
//                  $Author$
//
// Copyright (c) Gurux Ltd
//
//---------------------------------------------------------------------------
//
//  DESCRIPTION
//
// This file is a part of Gurux Device Framework.
//
// Gurux Device Framework is Open Source software; you can redistribute it
// and/or modify it under the terms of the GNU General Public License 
// as published by the Free Software Foundation; version 2 of the License.
// Gurux Device Framework is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of 
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
// See the GNU General Public License for more details.
//
// More information of Gurux products: https://www.gurux.org
//
// This code is licensed under the GNU General Public License v2. 
// Full text may be retrieved at http://www.gnu.org/licenses/gpl-2.0.txt
//---------------------------------------------------------------------------

package gurux.dlms.secure;

import java.math.BigInteger;

import gurux.dlms.GXDLMSSettings;
import gurux.dlms.GXDLMSTranslatorStructure;
import gurux.dlms.enums.Security;
import gurux.dlms.internal.GXCommon;
import gurux.dlms.objects.enums.SecuritySuite;

public class AesGcmParameter {

    private int tag;

    /*
     * DLMS settings.
     */
    private GXDLMSSettings settings;

    private Security security;

    /**
     * Invocation counter.
     */
    private long invocationCounter;

    /**
     * System title.
     */
    private byte[] systemTitle;
    /**
     * Block cipher key.
     */
    private byte[] blockCipherKey;
    /**
     * Authentication key.
     */
    private byte[] authenticationKey;
    /**
     * Count type.
     */
    private int type;

    /**
     * Recipient system title.
     */
    private byte[] recipientSystemTitle;
    /**
     * Date time.
     */
    private byte[] dateTime;
    /**
     * Other information.
     */
    private byte[] otherInformation;

    /**
     * Key parameters.
     */
    private int keyParameters;

    /**
     * Key ciphered data.
     */
    private byte[] keyCipheredData;

    /**
     * Ciphered content.
     */
    private byte[] cipheredContent;

    /**
     * Signature.
     */
    private byte[] signature;

    /*
     * Used security suite.
     */
    private SecuritySuite securitySuite = SecuritySuite.SUITE_0;

    /*
     * System title is not send on pre-established connections.
     */
    private boolean ignoreSystemTitle;

    private GXDLMSTranslatorStructure xml;

    /**
     * Constructor.
     *
     * @param forTag               Tag.
     * @param forSecurity          Security level.
     * @param forSecuritySuite     Security suite.
     * @param forInvocationCounter Invocation counter.
     * @param forSystemTitle       System title.
     * @param forBlockCipherKey    Block cipher key. A.k.a EK.
     * @param forAuthenticationKey Authentication key.
     */
    public AesGcmParameter(final GXDLMSSettings s, final int forTag, final Security forSecurity,
                           final SecuritySuite forSecuritySuite, final long forInvocationCounter,
                           final byte[] forSystemTitle, final byte[] forBlockCipherKey,
                           final byte[] forAuthenticationKey) {
        settings = s;
        tag = forTag;
        security = forSecurity;
        securitySuite = forSecuritySuite;
        invocationCounter = forInvocationCounter;
        systemTitle = forSystemTitle;
        blockCipherKey = forBlockCipherKey;
        authenticationKey = forAuthenticationKey;
        type = CountType.PACKET;
    }

    /**
     * Constructor.
     *
     * @param forTag                   Tag.
     * @param forSettings              DLMS settings.
     * @param forSecurity              Security level.
     * @param forSecuritySuite         Security suite.
     * @param forInvocationCounter     Invocation counter.
     * @param kdf                      KDF.
     * @param forAuthenticationKey     Authentication key.
     * @param forOriginatorSystemTitle Originator system title.
     * @param forRecipientSystemTitle  Recipient system title.
     * @param forDateTime              Date and time.
     * @param forOtherInformation      Other information.
     */
    public AesGcmParameter(final int forTag, GXDLMSSettings forSettings, final Security forSecurity,
                           final SecuritySuite forSecuritySuite, final long forInvocationCounter, final byte[] kdf,
                           final byte[] forAuthenticationKey, final byte[] forOriginatorSystemTitle,
                           final byte[] forRecipientSystemTitle, final byte[] forDateTime,
                           final byte[] forOtherInformation) {
        settings = forSettings;
        tag = forTag;
        security = forSecurity;
        invocationCounter = forInvocationCounter;
        securitySuite = forSecuritySuite;
        blockCipherKey = kdf;
        authenticationKey = forAuthenticationKey;
        systemTitle = forOriginatorSystemTitle;
        recipientSystemTitle = forRecipientSystemTitle;
        type = CountType.PACKET;
        dateTime = forDateTime;
        otherInformation = forOtherInformation;
    }

    /**
     * Constructor.
     *
     * @param forSettings          DLMS settings.
     * @param forsystemTitle       System title.
     * @param forblockCipherKey    Block cipher key.
     * @param forauthenticationKey Authentication key.
     */
    public AesGcmParameter(final GXDLMSSettings forSettings, final byte[] forsystemTitle,
                           final byte[] forblockCipherKey, final byte[] forauthenticationKey) {
        systemTitle = forsystemTitle;
        blockCipherKey = forblockCipherKey;
        authenticationKey = forauthenticationKey;
        type = CountType.PACKET;
        settings = forSettings;
    }

    public final int getTag() {
        return tag;
    }

    /**
     * Used transaction ID.
     */
    BigInteger transactionId;

    /**
     * @return Used transaction ID.
     */
    public final BigInteger getTransactionId() {
        return transactionId;
    }

    /**
     * @param value Used transaction ID.
     */
    public final void setTransactionId(final BigInteger value) {
        transactionId = value;
    }

    public final Security getSecurity() {
        return security;
    }

    public final void setSecurity(final Security value) {
        security = value;
    }

    /**
     * @return Invocation counter.
     */
    public final long getInvocationCounter() {
        return invocationCounter;
    }

    /**
     * @param value Invocation counter.
     */
    public final void setInvocationCounter(final long value) {
        invocationCounter = value;
    }

    public final byte[] getSystemTitle() {
        return systemTitle;
    }

    public final void setSystemTitle(final byte[] value) {
        systemTitle = value;
    }

    public final byte[] getBlockCipherKey() {
        return blockCipherKey;
    }

    public final void setBlockCipherKey(final byte[] value) {
        blockCipherKey = value;
    }

    public final byte[] getAuthenticationKey() {
        return authenticationKey;
    }

    public final void setAuthenticationKey(final byte[] value) {
        authenticationKey = value;
    }

    public final int getType() {
        return type;
    }

    public final void setType(final int value) {
        type = value;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Security: ");
        sb.append(getSecurity());
        sb.append(" InvocationCounter: ");
        sb.append(getInvocationCounter());
        sb.append(" SystemTitle: ");
        sb.append(GXCommon.toHex(systemTitle, true));
        sb.append(" AuthenticationKey: ");
        sb.append(GXCommon.toHex(authenticationKey, true));
        sb.append(" BlockCipherKey: ");
        sb.append(GXCommon.toHex(blockCipherKey, true));
        return sb.toString();
    }

    /**
     * @return Optional Date time.
     */
    public byte[] getDateTime() {
        return dateTime;
    }

    /**
     * @param value Date time.
     */
    public final void setDateTime(final byte[] value) {
        dateTime = value;
    }

    /**
     * @return Optional other information.
     */
    public byte[] getOtherInformation() {
        return otherInformation;
    }

    /**
     * @param value Other information.
     */
    public final void setOtherInformation(final byte[] value) {
        otherInformation = value;
    }

    /**
     * @return Recipient system title.
     */
    public byte[] getRecipientSystemTitle() {
        return recipientSystemTitle;
    }

    /**
     * @param value Recipient system title.
     */
    public final void setRecipientSystemTitle(final byte[] value) {
        recipientSystemTitle = value;
    }

    /**
     * @return Used security suite.
     */
    public SecuritySuite getSecuritySuite() {
        return securitySuite;
    }

    /**
     * @param value Used security suite.
     */
    public void setSecuritySuite(final SecuritySuite value) {
        securitySuite = value;
    }

    /**
     * @return System title is not send on pre-established connections.
     */
    public boolean getIgnoreSystemTitle() {
        return ignoreSystemTitle;
    }

    /**
     * @param value System title is not send on pre-established connections.
     */
    public void setIgnoreSystemTitle(final boolean value) {
        ignoreSystemTitle = value;
    }

    /**
     * @return Key parameters.
     */
    public int getKeyParameters() {
        return keyParameters;
    }

    /**
     * @param value Key parameters.
     */
    public void setKeyParameters(final int value) {
        keyParameters = value;
    }

    /**
     * @return Key ciphered data.
     */
    public byte[] getKeyCipheredData() {
        return keyCipheredData;
    }

    /**
     * @param value Key ciphered data.
     */
    public void setKeyCipheredData(final byte[] value) {
        keyCipheredData = value;
    }

    /**
     * @return Ciphered content.
     */
    public byte[] getCipheredContent() {
        return cipheredContent;
    }

    /**
     * @param value Ciphered content.
     */
    public void setCipheredContent(final byte[] value) {
        cipheredContent = value;
    }

    public final void setXml(final GXDLMSTranslatorStructure value) {
        xml = value;
    }

    public final GXDLMSTranslatorStructure getXml() {
        return xml;
    }

    /**
     * @return DLMS settings.
     */
    public GXDLMSSettings getSettings() {
        return settings;
    }

    /**
     * @return Signature.
     */
    public byte[] getSignature() {
        return signature;
    }

    /**
     * @param value Signature.
     */
    public void setSignature(final byte[] value) {
        signature = value;
    }
}
