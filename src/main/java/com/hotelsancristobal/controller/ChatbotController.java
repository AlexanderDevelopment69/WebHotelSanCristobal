package com.hotelsancristobal.controller;//package com.hotelsancristobal.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//public class ChatbotController {
//    @Value("${rasa.server.url}")
//    private String rasaServerUrl; // Configura la URL del servidor Rasa en tu archivo application.properties
//
//    @PostMapping("/chatbot")
//    public String sendMessageToChatbot(@RequestBody String message) {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = rasaServerUrl + "/webhooks/rest/webhook";
//        // Envía la solicitud a Rasa para obtener la respuesta del chatbot
//        String[] response = restTemplate.postForObject(url, new ChatbotRequest(message), String[].class);
//        return response != null && response.length > 0 ? response[0] : "No se pudo obtener una respuesta del chatbot";
//    }
//
//    // Clase de solicitud para el chatbot
//    private static class ChatbotRequest {
//        private String message;
//
//        public ChatbotRequest(String message) {
//            this.message = message;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//    }
//}
