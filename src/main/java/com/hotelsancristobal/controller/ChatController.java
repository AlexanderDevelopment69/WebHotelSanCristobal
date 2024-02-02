package com.hotelsancristobal.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/send-message")
    public String sendMessage(@RequestBody String message) {
        // Enviar solicitud al servidor RASA y obtener la respuesta
        String rasaUrl = "http://localhost:5005/webhooks/rest/webhook";
        String response = restTemplate.postForObject(rasaUrl, message, String.class);

        // Procesar la respuesta del servidor RASA y realizar acciones necesarias
        String processedResponse = processRasaResponse(response);

        // Devolver la respuesta procesada al cliente
        return processedResponse;
    }

    private String processRasaResponse(String response) {
        // Analizar la respuesta del servidor RASA y realizar acciones necesarias
        // Por ejemplo, puedes extraer y formatear la respuesta del chatbot
        // y devolverla en un formato adecuado para tu cliente (por ejemplo, un mensaje de texto).
        return response;
    }

//    private String processRasaResponse(String response) {
//        try {
//            // Validar y analizar la cadena JSON
//            JSONArray jsonArray = new JSONArray(response);
//
//            // Inicializar un StringBuilder para concatenar todos los textos de mensaje
//            StringBuilder formattedResponse = new StringBuilder();
//
//            // Iterar a través de cada objeto de mensaje en el array
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject messageObject = jsonArray.getJSONObject(i);
//
//                // Extraer el texto del objeto de mensaje y agregarlo a la respuesta formateada
//                String messageText = messageObject.getString("text");
//                formattedResponse.append(messageText);
//
//                // Agregar un carácter de nueva línea después de cada mensaje (opcional)
//                formattedResponse.append("\n");
//            }
//
//            // Devolver la respuesta formateada como una sola cadena
//            return formattedResponse.toString();
//        } catch (JSONException e) {
//            // Manejar excepciones de análisis JSON
//            e.printStackTrace(); // O manejar de acuerdo a tus necesidades
//            return "Error al procesar la respuesta del servidor Rasa";
//        }
//    }
//
//

}
