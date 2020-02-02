package com.example.sportgym.modelos;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MosquittoCallBack implements MqttCallback {
    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Perda de ligação ao mosquitto");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("Mensagem recebida:\n\t"+ new String(message.getPayload()) + "topico:" + topic);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
