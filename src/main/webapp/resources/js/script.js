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
    var regPass = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$";
    var regEmail = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";
    var regVat = "(BE)?0[0-9]{9} |";

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
    //errorlastName
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
        var errorVal = $(this).val();
        var errorTva = document.getElementById("errorTva");
        if (errorVal.length < 3) {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorTva.hidden = false;
        } else {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorTva.hidden = true;
        }
    })
    mail.keyup(function () {
        //form add User
    })
    password.keyup(function () {
        //form add User
    })
    rpassword.keyup(function () {

    })
});


