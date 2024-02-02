// const chatbotToggler = document.querySelector(".chatbot-toggler");
// const closeBtn = document.querySelector(".close-btn");
// const chatbox = document.querySelector(".chatbox");
// const chatInput = document.querySelector(".chat-input textarea");
// const sendChatBtn = document.querySelector(".chat-input span");
//
// let userMessage = null; // Variable to store user's message
// const inputInitHeight = chatInput.scrollHeight;
//
//
//
//
//
//
//
//
// const createChatLi = (message, className) => {
//     // Create a chat <li> element with passed message and className
//     const chatLi = document.createElement("li");
//     chatLi.classList.add("chat", `${className}`);
//     let chatContent = className === "outgoing" ? `<p></p>` : `<span class="material-symbols-outlined">smart_toy</span><p></p>`;
//     chatLi.innerHTML = chatContent;
//     chatLi.querySelector("p").textContent = message;
//     return chatLi; // return chat <li> element
// }
//
// const generateResponse = (userMessage) => {
//     const API_URL = "http://localhost:80/chat/send-message";
//     const requestOptions = {
//         method: "POST",
//         headers: {
//             "Content-Type": "application/json"
//         },
//         body: JSON.stringify({ message: userMessage })
//     };
//
//     fetch(API_URL, requestOptions)
//         .then(res => {
//             if (!res.ok) {
//                 throw new Error("Network response was not ok");
//             }
//             return res.json();
//         })
//         .then(response => {
//             console.log("Server Response:", response);
//
//             // Agregar el primer mensaje del servidor Rasa al chatbox
//             const firstMessage = response[0].text;
//
//             const firstChatLi = createChatLi(firstMessage, "incoming");
//             chatbox.appendChild(firstChatLi);
//
//             // // Si hay más mensajes en la respuesta, agregarlos al chatbox
//             // if (response.length > 1) {
//             //     response.slice(1).forEach(message => {
//             //         const newChatLi = createChatLi(message.text, "incoming");
//             //         chatbox.appendChild(newChatLi);
//             //     });
//             // }
//
//             // if we get response from Rasa
//             for (let i = 0; i < response.length; i += 1) {
//
//                 // check if the response contains "buttons"
//                 if (Object.hasOwnProperty.call(response[i], "buttons")) {
//                     if (response[i].buttons.length > 0) {
//                         addSuggestion(response[i].buttons);
//                     }
//                 }
//             }
//
//
//
//             // Agregar sonido de notificación
//             const notificationSound = new Audio('sounds/notification.mp3');
//             notificationSound.play();
//
//
//             // Si hay más mensajes en la respuesta, agregarlos al chatbox con un retraso de 1 segundo cada uno
//             if (response.length > 1) {
//                 response.slice(1).forEach((message, index) => {
//                     setTimeout(() => {
//
//
//                         const newChatLi = createChatLi(message.text, "incoming");
//                         chatbox.appendChild(newChatLi);
//                         chatbox.scrollTo(0, chatbox.scrollHeight);
//                     },  600);
//                 });
//
//
//             }
//
//
//             // Scroll hasta el final del chatbox
//             chatbox.scrollTo(0, chatbox.scrollHeight);
//         })
//         .catch(error => {
//             console.error("Error:", error);
//         });
// }
//
//
// // const handleChat = () => {
// //     userMessage = chatInput.value.trim(); // Obtener el mensaje ingresado por el usuario y eliminar espacios en blanco adicionales
// //     if(!userMessage) return;
// //
// //     // Limpiar el área de entrada y establecer su altura en la predeterminada
// //     chatInput.value = "";
// //     chatInput.style.height = `${inputInitHeight}px`;
// //
// //     // Agregar el mensaje del usuario al chatbox
// //     chatbox.appendChild(createChatLi(userMessage, "outgoing"));
// //     chatbox.scrollTo(0, chatbox.scrollHeight);
// //
// //     setTimeout(() => {
// //         // Mostrar el mensaje "Pensando..." mientras se espera la respuesta
// //         const incomingChatLi = createChatLi("Thinking...", "incoming");
// //         chatbox.appendChild(incomingChatLi);
// //         chatbox.scrollTo(0, chatbox.scrollHeight);
// //         generateResponse(userMessage, incomingChatLi); // Envía el mensaje del usuario al servidor
// //     }, 600);
// // }
//
// const handleChat = () => {
//     userMessage = chatInput.value.trim(); // Obtener el mensaje ingresado por el usuario y eliminar espacios en blanco adicionales
//     if (!userMessage) return;
//
//     // Limpiar el área de entrada y establecer su altura en la predeterminada
//     chatInput.value = "";
//     chatInput.style.height = `${inputInitHeight}px`;
//
//     // Agregar el mensaje del usuario al chatbox
//     chatbox.appendChild(createChatLi(userMessage, "outgoing"));
//     chatbox.scrollTo(0, chatbox.scrollHeight);
//
//     // Enviar el mensaje del usuario al servidor y obtener la respuesta
//     generateResponse(userMessage);
//
//
// }
//
//
//
//
// chatInput.addEventListener("input", () => {
//     // Adjust the height of the input textarea based on its content
//     chatInput.style.height = `${inputInitHeight}px`;
//     chatInput.style.height = `${chatInput.scrollHeight}px`;
// });
//
// chatInput.addEventListener("keydown", (e) => {
//     // If Enter key is pressed without Shift key and the window
//     // width is greater than 800px, handle the chat
//     if(e.key === "Enter" && !e.shiftKey && window.innerWidth > 800) {
//         e.preventDefault();
//         handleChat();
//     }
// });
//
// sendChatBtn.addEventListener("click", handleChat);
// closeBtn.addEventListener("click", () => document.body.classList.remove("show-chatbot"));
// chatbotToggler.addEventListener("click", () => document.body.classList.toggle("show-chatbot"));
//
//
//
//


const chatbotToggler = document.querySelector(".chatbot-toggler");
const closeBtn = document.querySelector(".close-btn");
const chatbox = document.querySelector(".chatbox");
const chatInput = document.querySelector(".chat-input textarea");
const sendChatBtn = document.querySelector(".chat-input span");

let userMessage = null; // Variable to store user's message
const inputInitHeight = chatInput.scrollHeight;


const createChatLi = (message, className) => {
    const chatLi = document.createElement("li");
    chatLi.classList.add("chat", `${className}`);

    // Crear el contenedor del contenido del mensaje
    const chatContent = document.createElement("div");
    chatContent.classList.add("chat-content");

    // Crear el elemento para el texto del mensaje
    if (message.text) {
        const chatContent = className === "outgoing" ? `<p>${message.text}</p>` : `<span class="material-symbols-outlined">smart_toy</span><p>${message.text}</p>`;
        chatLi.innerHTML = chatContent;
    }


    // Si hay botones, crear y añadir botones
    if (message.buttons && message.buttons.length > 0) {
        const buttonsContainer = document.createElement("div");
        buttonsContainer.classList.add("buttons-container");

        message.buttons.forEach(button => {
            const buttonElement = document.createElement("button");
            buttonElement.textContent = button.title;
            buttonElement.classList.add("btn", "btn-primary");
            buttonElement.addEventListener("click", () => {
                console.log(`Botón "${button.title}" clickeado`);
                console.log(button.payload); // Enviar payload al servidor Rasa al hacer clic en el botón

                // Crear y añadir un nuevo mensaje al chat con el contenido del payload del botón
                const newMessage = {text: button.payload}; // Crear un nuevo mensaje con el contenido del payload
                const newChatLi = createChatLi(newMessage, "outgoing"); // Crear un nuevo elemento de chat <li>
                chatbox.appendChild(newChatLi); // Añadir el nuevo mensaje al chat
                generateResponse(button.payload)

            });
            buttonsContainer.appendChild(buttonElement);
        });

        chatContent.appendChild(buttonsContainer); // Añadir el contenedor de botones al chatContent
    }


    chatLi.appendChild(chatContent); // Añadir el contenido del chat al elemento <li>
    return chatLi;
}


const generateResponse = (userMessage) => {
    const API_URL = "http://localhost:80/chat/send-message";
    const requestOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({message: userMessage})
    };

    fetch(API_URL, requestOptions)
        .then(res => {
            if (!res.ok) {
                throw new Error("Network response was not ok");
            }
            return res.json();
        })
        .then(response => {
            console.log("Server Response:", response);

            // Agregar mensajes del servidor Rasa al chatbox
            response.forEach(message => {
                const chatLi = createChatLi(message, "incoming");
                chatbox.appendChild(chatLi);
            });

            // Scroll hasta el final del chatbox
            chatbox.scrollTo(0, chatbox.scrollHeight);
        })
        .catch(error => {
            console.error("Error:", error);
        });
}

const handleChat = () => {
    userMessage = chatInput.value.trim();
    if (!userMessage) return;

    chatInput.value = "";
    chatInput.style.height = `${inputInitHeight}px`;

    chatbox.appendChild(createChatLi({text: userMessage}, "outgoing"));
    chatbox.scrollTo(0, chatbox.scrollHeight);

    generateResponse(userMessage);
}

chatInput.addEventListener("input", () => {
    chatInput.style.height = `${inputInitHeight}px`;
    chatInput.style.height = `${chatInput.scrollHeight}px`;
});

chatInput.addEventListener("keydown", (e) => {
    if (e.key === "Enter" && !e.shiftKey && window.innerWidth > 800) {
        e.preventDefault();
        handleChat();
    }
});

sendChatBtn.addEventListener("click", handleChat);
closeBtn.addEventListener("click", () => document.body.classList.remove("show-chatbot"));
chatbotToggler.addEventListener("click", () => document.body.classList.toggle("show-chatbot"));
