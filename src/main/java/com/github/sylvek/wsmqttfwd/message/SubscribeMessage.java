/*
 * Copyright (c) 2012-2015 The original author or authors
 * ------------------------------------------------------
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 *
 * The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * The Apache License v2.0 is available at
 * http://www.opensource.org/licenses/apache2.0.php
 *
 * You may elect to redistribute this code under either of these licenses.
 */
package com.github.sylvek.wsmqttfwd.message;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author andrea
 * @author Sylvain Maucourt
 */
public class SubscribeMessage extends AbstractMessage {

    protected Integer m_messageID; //could be null if Qos is == 0

    public Integer getMessageID()
    {
        return m_messageID;
    }

    public void setMessageID(Integer messageID)
    {
        this.m_messageID = messageID;
    }

    public static class Couple {

        public final byte qos;
        public final String topicFilter;

        public Couple(byte qos, String topic)
        {
            this.qos = qos;
            this.topicFilter = topic;
        }

    }

    private List<Couple> m_subscriptions = new ArrayList<>();

    public SubscribeMessage()
    {
        //Subscribe has always QoS 1
        m_messageType = SUBSCRIBE;
        m_qos = AbstractMessage.QOSType.LEAST_ONE;
    }

    public List<Couple> subscriptions()
    {
        return m_subscriptions;
    }

    public List<String> topics()
    {
        return m_subscriptions.stream().map(couple -> couple.topicFilter).collect(Collectors.toList());
    }

    public void addSubscription(Couple subscription)
    {
        m_subscriptions.add(subscription);
    }
}
