package com.example.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient; 
import org.eclipse.paho.client.mqttv3.MqttConnectOptions; 
import org.eclipse.paho.client.mqttv3.MqttException; 
import org.eclipse.paho.client.mqttv3.MqttMessage; 
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence; 



public class MqttPublish { 

    public static void main(String[] args) { 


        String topic        = "test"; 

        String content      = "Temp:20, Humi:70"; 

        int qos             = 0; 

        String broker       = "tcp://localhost:1883"; 

        String clientId     = "Hello"; 

        MemoryPersistence persistence = new MemoryPersistence(); 



        try { 


            MqttClient sampleClient = new MqttClient(broker,clientId, persistence); 

            MqttConnectOptions connOpts = new MqttConnectOptions(); 

            connOpts.setCleanSession(true); 

            System.out.println("Connecting to broker: "+broker); 

            sampleClient.connect(connOpts); 

            System.out.println("Connected to broker"); 

            System.out.println("Publishing message:"+content); 

             

            //An MQTT message holds the application payload and options specifying how the message is to be  

            //delivered The message includes a "payload" (the body of the message) represented as a byte[].	 

             

            //MqttMessage(byte[] payload) 

            //Constructs a message with the specified byte array as a payload, and all other values set to defaults. 

             

            MqttMessage message = new MqttMessage(content.getBytes()); 

             

            //setQos(int qos) 

            //Sets the quality of service for this message. 

            message.setQos(qos); 

             

            //publish (java.lang.String topic, MqttMessage message) 

            //Publishes a message to a topic on the server. 

             

            sampleClient.publish(topic, message); 

            System.out.println("Message published"); 

             

            //disconnect() 

            //Disconnects from the server. 

            sampleClient.disconnect(); 

             

            //close() 

            //Close the client Releases all resource associated with the client. 

            sampleClient.close(); 

            System.exit(0);



        } catch(MqttException me) { 

            System.out.println("reason "+me.getReasonCode()); 

            System.out.println("msg "+me.getMessage()); 

            System.out.println("loc "+me.getLocalizedMessage()); 

            System.out.println("cause "+me.getCause()); 

            System.out.println("excep "+me); 
  

            me.printStackTrace();


        } 



    } 

  

} 
