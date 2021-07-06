$(document).ready(function () {
    //Var add User
    var login = $('#login'),
        lastName = $('#lastName'),
        firstName = $('#firstName'),
        dayOfBoirth = $('#dayOfBirth'),
        vat = $('#vat'),
        mail = $('#email'),
        password = $('#password'),
        rpassword = $('#rpPassword');
    //Minimum eight characters, at least one upper case English letter, one lower case English letter, one number and one special character
    var regPass = new RegExp("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");
    var regEmail = new RegExp(/[a-zA-Z0-9\.]{1,}[@][a-zA-Z0-9\.]{1,}[\.][a-zA-Z0-9\.]{1,}$/);
    var regVat = new RegExp("^(BE){0,1}[0]{0,1}[0-9]{9}$");

    //Form addUser
    login.keyup(function () {
        var errorVal = $(this).val();
        var errorLogin = document.getElementById("errorLogin");
        if (errorVal.length < 4) {
            console.log("login");
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorLogin.hidden = false;
        } else {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorLogin.hidden = true;
        }
    });
    //form add User
    lastName.keyup(function () {
        var errorValLastName = $(this).val();
        var errorlastName = document.getElementById("errorlastName");
        if (errorValLastName.length < 3) {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorlastName.hidden = false;
        } else {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorlastName.hidden = true;
        }
    })
    //form add User
    firstName.keyup(function () {
        var errorVal = $(this).val();
        var errorfirstName = document.getElementById("errorfirstName");
        if (errorVal.length < 3) {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorfirstName.hidden = false;
        } else {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorfirstName.hidden = true;
        }
    })
    dayOfBoirth.keyup(function () {
        //form add User
    })
    //form add User
    vat.keyup(function () {
        var vatVal = $(this).val();
        var errorTva = document.getElementById("errorTva");
        if (regVat.test(vatVal)) {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorTva.hidden = true;
        } else {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorTva.hidden = false;
        }
    })
    //form add User
    mail.keyup(function () {
        var email = $(this).val();
        var errorEmail = document.getElementById("errorEmail");
        console.log("mail");
        if (regEmail.test(email)) {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorEmail.hidden = true;
        } else {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorEmail.hidden = false;
        }
    })
    password.keyup(function () {
        var errorPass = document.getElementById("erroPassword");
        var errorRP = document.getElementById("errorRP");
        var pass = $(this).val();
        var rpass = $(this).val();
        if (regPass.test(pass)) {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorPass.hidden = true;
        } else {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorPass.hidden = false;
        }
    })
    rpassword.keyup(function () {
        var errorRpass = document.getElementById("errorRPassword");
        var rpass = $(this).val();
        var pass = password.val();
        if (rpass == pass) {
            console.log("les mot de passe correpond");
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorRpass.hidden = true;
        } else {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorRpass.hidden = false;
            console.log("les mot de passe ne correponde pas")
        }

    })
});


