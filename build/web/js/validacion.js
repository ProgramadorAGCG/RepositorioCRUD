window.addEventListener("load", (e)=>{
    validacion();
});

function validacion(){
    const inputEmail = document.getElementById("inputEmail");
    const inputPassword = document.getElementById("inputPassword");
    const messageEmail = document.getElementById("messageEmail");
    const messagePassword = document.getElementById("messagePassword");
    inputEmail.addEventListener("keydown", (e)=>{
        if(/^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$/.test(inputEmail)){
            messagePassword.innerHTML = "Email correcto";
            console.log("Correcto");
        }else{
            messagePassword.innerHTML = "Error en el email";
            console.log("Incorrecto");
        }
    });
}