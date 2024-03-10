package com.example.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken; 
import org.eclipse.paho.client.mqttv3.MqttCallback; 
import org.eclipse.paho.client.mqttv3.MqttClient; 
import org.eclipse.paho.client.mqttv3.MqttConnectOptions; 
import org.eclipse.paho.client.mqttv3.MqttException; 
import org.eclipse.paho.client.mqttv3.MqttMessage; 
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence; 


public class MqttSubscriber implements MqttCallback  { 


    private static final String brokerUrl ="tcp://localhost:1883"; 

    private static final String clientId = "clientId"; 

    private static final String topic = "test"; 


    public static void main (String [] args) { 

        System.out.println("Subscriber running"); 

        // 

        MqttSubscriber mqttsubscribe =new MqttSubscriber(); 

        //subscribe(java.lang.String topicFilter) 

        //Subscribe to a topic, which may include wildcards using a QoS of 1. 

        mqttsubscribe.subscribe(topic); 

    } 



    public void subscribe(String topic) { 


        try { 
        
            //Persistence that uses memory in cases where reliability is not required across client or device restarts memory this memory peristence can be used.  

            //In cases where reliability is required like when clean session is set to false then a non-volatile form of persistence should be used. 

            MemoryPersistence persistence = new MemoryPersistence(); 

            MqttClient sampleClient = new MqttClient(brokerUrl, clientId,persistence); 

        

            //Holds the set of options that control how the client connects to a server. 

            MqttConnectOptions connOpts = new MqttConnectOptions(); 

    

            //Sets whether the client and server should remember state across restarts and reconnects. 

            

            //If connecting with MqttConnectOptions.setCleanSession(boolean) set to true it is safe to  

            //use memory persistence  

            //as all state it cleared when a client disconnects.  

            //If connecting with cleanSession set to false, to provide reliable message  

            //delivery then a persistent message store should be used such as the default one. 

            connOpts.setCleanSession(true); 

    

            System.out.println("checking"); 

            System.out.println("Mqtt Connecting to broker: " + brokerUrl); 

    

            //Connects to an MQTT server using the specified options. 

            sampleClient.connect(connOpts); 

            System.out.println("Mqtt Connected"); 

            

            //setCallback(MqttCallback callback)--method 

            //Sets the callback listener to use for events that happen asynchronously. 

            sampleClient.setCallback(this); 

            //Subscribe to a topic, which may include wildcards using a QoS of 1. 

            sampleClient.subscribe(topic); 

    

            System.out.println("Subscribed"); 

            System.out.println("Listening"); 

    

        } catch (MqttException e) { 

            e.printStackTrace(); 

        } 
        

    } 

    

    

    //	messageArrived(java.lang.String topic, MqttMessage message) 

    //	This method is called when a message arrives from the server. 

    

    /** 

    * This method is called when a message arrives from the server. 

    This method is invoked synchronously by the MQTT client. An acknowledgment  

    is not sent back to the server until this method returns cleanly. 

    * If an implementation of this method throws an Exception, then the client will be shut down. When the client is next re-connected, any QoS 1 or 2 messages will be redelivered by the server. 

    Any additional messages which arrive while an implementation of this method is running, will build up in memory, and will then back up on the network. 

    If an application needs to persist data, then it should ensure the data is persisted prior to returning from this method, as after returning from this method, the message is considered to have been delivered, and will not be reproducible. 

    It is possible to send a new message within an implementation of this callback (for example, a response to this message), but the implementation must not disconnect the client, as it will be impossible to send an acknowledgment for the message being processed, and a deadlock will occur. 

    *  

    */ 

    

    public void messageArrived(String topic, MqttMessage message) throws Exception { 

        System.out.println("| Topic:" + topic); 

        System.out.println("| Message: " +message.toString()); 

        System.out.println("-------------------------------------------------"); 

    } 



    //This method is called when the connection to the server is lost. 

    public void connectionLost(Throwable arg0) { 

    } 



    //Called when delivery for a message has been completed, and all acknowledgments have been received. 

    public void deliveryComplete(IMqttDeliveryToken arg0) { 

    }	
    
    


} 
