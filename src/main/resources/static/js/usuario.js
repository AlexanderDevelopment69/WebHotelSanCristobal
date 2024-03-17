// Función para asignar automáticamente un usuario_id al visitar por primera vez
function asignarUsuarioIdAutomaticamente() {
    // Verificar si el usuario ya tiene un usuario_id asignado en sessionStorage
    let usuarioId = sessionStorage.getItem('usuario_id');
    if (!usuarioId) {
        // Si no tiene un usuario_id asignado, generar uno nuevo y almacenarlo en sessionStorage
        usuarioId = generarUsuarioId();
        sessionStorage.setItem('usuario_id', usuarioId);
    }
    return usuarioId;
}

// Función para generar un usuario_id único
function generarUsuarioId() {
    // Implementa la lógica para generar un usuario_id único aquí
    return 'id_' + Math.random().toString(36).substr(2, 9); // Ejemplo de generación aleatoria
}

// Llamar a la función para asignar automáticamente un usuario_id al visitar por primera vez
asignarUsuarioIdAutomaticamente();



// function verificarInicioSesion() {
//     $.ajax({
//         type: 'GET',
//         url: '/clientes/verificarSesion',
//         success: function(response) {
//
//             if (response) {
//                 console.log(response)
//                 console.log("hola")
//                 // Si el cliente no ha iniciado sesión, redirigir al formulario de inicio de sesión
//                 window.location.href = '/clientes/login';
//
//             }
//         },
//         error: function(xhr, status, error) {
//             console.error('Error al verificar sesión:', error);
//         }
//     });
// }

// function verificarInicioSesion() {
//     $.ajax({
//         type: 'GET',
//         url: '/clientes/verificarSesion',
//         success: function(response) {
//
//             if (response === true) {
//                 console.log('El usuario ha iniciado sesión.');
//             }
//             else {
//                 console.log('El usuario no ha iniciado sesión.');
//                 // window.location.href = '/clientes/login';
//             }
//         },
//         error: function(xhr, status, error) {
//             console.error('Error al verificar la sesión:', error);
//         }
//     });
// }
