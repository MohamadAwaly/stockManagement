/**
 *  PART USER
 */
//RegExp
//Minimum eight characters, at least one upper case French letter, one lower case French letter, one number and one special character
var regPass = new RegExp("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");
var regEmail = new RegExp(/[a-zA-Z0-9\.]{1,}[@][a-zA-Z0-9\.]{1,}[\.][a-zA-Z0-9\.]{1,}$/);
var noNumber = new RegExp("^[a-zA-Z]+$", "i");
var regVat = new RegExp("^(BE){0,1}[0]{0,1}[0-9]{9}$");

$(document).ready(function () {
    //Var add User
    var login = $('#login'),
        lastName = $('#lastName'),
        firstName = $('#firstName'),
        dayOfBirth = $('.dayOfBirthClass'),
        vat = $('#vat'),
        mail = $('.emailClass'),
        password = $('#password'),
        rpassword = $('#rpPassword');

    // var update user
    var lastNameUpdate = $('#lastNameUpdate'),
        firstNameUpdate = $('#firstNameUpdate'),
        vatUpdate = $('#vatUpdate'),
        passwordUpdate = $('#passwordUpdate'),
        rpasswordUpdate = $('#rpPasswordUpdate');

    // Error add new user
    var errorLoginValide = "true",
        errorLastNameValide = "true",
        errorFirstNameValide = "true",
        errorDayOfBirthValide = "true",
        errorVatValide = "true",
        errorMailValide = "true",
        errorPasswordValide = "true",
        errorRpPAsswordValide = "true";

    var errorVatValideUpdate = "true";

    //Form addUser
    login.keyup(function () {
        var errorVal = $(this).val();
        var errorLogin = document.getElementById("errorLogin");
        var errorUserExist = document.getElementById("errorUserExist");
        if (errorVal.length < 4) {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorLogin.hidden = false;
            errorLoginValide = "true";
            $('#btn-addUser').prop('disabled', true);
        } else {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorLogin.hidden = true;
            errorLoginValide = "false";
            $('#btn-addUser').prop('disabled', false);
        }
        //test ajax
        $.ajax({
            type: "POST",
            url: 'UserAjaxCheckUserExist',
            data: {
                login: login.val(),
            },
            // dataType : 'json',
        }).done(function (data) {
            $('#error').attr('value', data);
            if (data == "error") {
                login.removeClass("is-valid");
                login.addClass("is-invalid");
                $('#errorUserExist').html("l'utilisateur " + login.val() + " existe deja");
                errorLoginValide = "true";
                $('#btn-addUser').prop('disabled', true);
                errorUserExist.hidden = false;
            } else {
                login.removeClass("is-invalid");
                login.addClass("is-valid");
                errorUserExist.hidden = true;
                errorLoginValide = "false";
                $('#btn-addUser').prop('disabled', false);
            }
        });
    });
    lastName.keyup(function () {
        var errorValLastName = $(this).val();
        var errorlastName = document.getElementById("errorlastName");
        var btn = $('#btn-addUser').attr('id');
        errorLastName(lastName, errorValLastName, errorlastName, btn);
    })
    firstName.keyup(function () {
        var errorVal = $(this).val();
        var errorfirstName = document.getElementById("errorfirstName");
        var btn = $('#btn-addUser').attr('id');
        errorFirstName(firstName, errorVal, errorfirstName, btn);
    })
    dayOfBirth.keyup(function () {
        const months = [
            'January',
            'February',
            'March',
            'April',
            'May',
            'June',
            'July',
            'August',
            'September',
            'October',
            'November',
            'December'
        ]
        var errordate = document.getElementById("errodayOfBirth");
        var errordate17ans = document.getElementById("errodayOfBirth17ans");
        var today = new Date();
        var dateFormLenght = $('.dayOfBirthClass').val();
        var dateForm = new Date($('.dayOfBirthClass').val());
        if (dateFormLenght.length == 10) {
            var dateOfBirth = new Date(dateForm.getDate() + " " + months[dateForm.getMonth()] + " " + dateForm.getUTCFullYear());
            var resultDay = dateDiffInDays(dateOfBirth, today);
            var resultMons = Math.round(resultDay / 30);
            var resultYears = Math.round(resultMons / 12);
            console.log("age: " + resultYears);
            if (resultYears > 17 && resultYears <= 101) {
                console.log("17 ans");
                $(this).removeClass("is-invalid");
                $(this).addClass("is-valid");
                errordate.hidden = true;
                errordate17ans.hidden = true;
                errorDayOfBirthValide = "false";
                $('#btn-addUser').prop('disabled', false);
            } else {
                console.log("dans le else");
                errordate17ans.hidden = false;
                errorDayOfBirthValide = "true";
                $('#btn-addUser').prop('disabled', true);
            }

        } else {
            console.log("le grand else");
            $(this).removeClass("is-valid");
            $(this).removeClass("is-invalid");
            errordate.hidden = true;
            errorDayOfBirthValide = "false";
            $('#btn-addUser').prop('disabled', false);
        }
    })
    vat.keyup(function () {
        var vatVal = $(this).val();
        var errorTva = document.getElementById("errorTva");
        var errorTvaExist = document.getElementById("errorTvaExist");
        var btn = $('#btn-addUser').attr('id');
        errorVat(vat, vatVal, errorTva, errorTvaExist, errorVatValide, btn);

    })
    mail.keyup(function () {
        var email = $(this).val();
        var errorEmail = document.getElementById("errorEmail");
        console.log("mail");
        if (email.length > 0) {
            if (regEmail.test(email)) {
                $(this).removeClass("is-invalid");
                $(this).addClass("is-valid");
                errorEmail.hidden = true;
                errorMailValide = "false";
                $('#btn-addUser').prop('disabled', false);
            } else {
                $(this).removeClass("is-valid");
                $(this).addClass("is-invalid");
                errorEmail.hidden = false;
                errorMailValide = "true";
                $('#btn-addUser').prop('disabled', true);
            }
        }
    })
    password.keyup(function () {
        var errorPass = document.getElementById("erroPassword");
        var btn = $('#btn-addUser').attr('id');
        errorPassword(password, errorPass, btn, rpassword);
    })
    rpassword.keyup(function () {
        var errorRpass = document.getElementById("errorRPassword");
        var btn = $('#btn-addUser').attr('id');
        erroRpassword(rpassword, password, errorRpass, btn);
    })

    //Form update user
    lastNameUpdate.keyup(function () {
        var errorValLastName = $(this).val();
        var errorlastName = document.getElementById("errorlastNameUpdate");
        var btn = $('#id-valider-updateUser-btn').attr('id');
        errorLastName(lastNameUpdate, errorValLastName, errorlastName, btn);
    })
    firstNameUpdate.keyup(function () {
        var errorVal = $(this).val();
        var errorfirstName = document.getElementById("errorfirstNameUpdate");
        var btn = $('#id-valider-updateUser-btn').attr('id');
        errorFirstName(firstNameUpdate, errorVal, errorfirstName, btn);
    })
    vatUpdate.keyup(function () {
        console.log("vatUpdate");
        var vatVal = $(this).val();
        var errorTva = document.getElementById("errorTvaUpdate");
        var errorTvaExist = document.getElementById("errorTvaExistUpdate");
        var btn = $('#id-valider-updateUser-btn').attr('id');
        errorVat(vatUpdate, vatVal, errorTva, errorTvaExist, errorVatValideUpdate, btn);
    })
    passwordUpdate.keyup(function () {
        var errorPass = document.getElementById("erroPasswordUpdate");
        var btn = $('#id-valider-updateUser-btn').attr('id');
        errorPassword(passwordUpdate, errorPass, btn, rpasswordUpdate);
    })
    rpasswordUpdate.keyup(function () {
        var errorRpass = document.getElementById("errorRPasswordUpdate");
        var btn = $('#id-valider-updateUser-btn').attr('id');
        erroRpassword(rpasswordUpdate, passwordUpdate, errorRpass, btn);
    })

    /**
     * disabled btn add new user if there is any error
     */
    $('#btn-addUser').click(function () {
        if (errorLoginValide == "true" && errorLastNameValide == "true" && errorFirstNameValide == "true" && errorDayOfBirthValide == "true" && errorVatValide == "true" && errorMailValide == "true" && errorPasswordValide == "true" && errorRpPAsswordValide == "true") {
            $(this).prop('disabled', true);
        } else {
            $(this).prop('disabled', false);
        }
    });

    /*Color lign on double click*/
    $(document).on('dblclick', '.usersList', function () {
        console.log("userList . dblclick");
        $('#updateUserbtn').removeAttr('disabled');
        $('.usersList').removeAttr('style');
        $(this).css('background', '#31B0D5');
        $('#selectedUserid').attr('value', $(this).find("td").eq(0).html());
        $('#selectedUserLogin').attr('value', $(this).find("td").eq(1).html());
    })
    // $('.usersList').dblclick(function () {
    //     console.log("userList . dblclick");
    //     $('#updateUserbtn').removeAttr('disabled');
    //     $('.usersList').removeAttr('style');
    //     $(this).css('background', '#31B0D5');
    //     $('#selectedUserid').attr('value', $(this).find("td").eq(0).html());
    //     $('#selectedUserLogin').attr('value', $(this).find("td").eq(1).html());
    // });
    //add the information in the field to add an address
    $('#idAddAdress').click(function () {
        $('#selectedUseridUpdate').attr('value', $('#iduserUpdate').val());
        $('#selectedUserLoginUpdate').attr('value', $('#loginUpdate').val());
    });
    //add id-adress in the field to update adress selected
    $('.adressList').dblclick(function () {
        $('#selected-IdAdress').attr('value', $(this).find("td").eq(0).html());
        $('#id-UpdateAdress').prop('disabled', false);
        $('#user-id').attr('value', $('#iduserUpdate').val());
    });
    // retrieve id and login user to update from session
    $('#id-btn-profile').on('click', function () {
        var login = $('#logUserProfile').text();
        var id = $('#idUserprofile').text();

        console.log("user: " + login);
        console.log("id: " + id);
    });
    /**
     * Users search bar
     *by login, lastName, firstName, mail, roles")
     */
    $('#usersSearchBar').keyup(function () {
        $.ajax({
            url: "UsersSearchAjax",
            method: "POST",
            data: {
                searchUserBar: $('#usersSearchBar').val()
            }
        }).done(function (data) {
            $('.usersList').remove();
            data.forEach(res => {
                let tva = res[7] == null ? "" : res[7];
                let mail = res[8] == null ? "" : res[8];
                let dayofbirth = res[5] == null ? "" : res[5];
                $('#tableListUser').append(
                    "<tr class='usersList' id='" + res[0] + "'>" +
                    "<td hidden>" + res[0] + "</td>" +
                    "<td>" + res[1] + "</td>" +
                    "<td>" + res[2] + "</td>" +
                    "<td>" + res[3] + "</td>" +
                    "<td>" + res[4] + "</td>" +
                    "<td>" + dayofbirth + "</td>" +
                    "<td>" + res[6] + "</td>" +
                    "<td>" + tva + "</td>" +
                    "<td>" + mail + "</td>" +
                    "<td>" + res[9] + "</td>" +
                    "</tr>");
            })
        });
    });
});

/**
 * Return the difference between the two date in day
 * @param a Date of birth
 * @param b Today
 * @returns {number}
 */
function dateDiffInDays(a, b) {
    const _MS_PER_DAY = 1000 * 60 * 60 * 24;

    // Discard the time and time-zone information.
    const utc1 = Date.UTC(a.getFullYear(), a.getMonth(), a.getDate());
    const utc2 = Date.UTC(b.getFullYear(), b.getMonth(), b.getDate());

    return Math.floor((utc2 - utc1) / _MS_PER_DAY);
}

/**
 * check last name : update & add new user
 * @param lastName
 * @param errorValLastName
 * @param errorlastName
 * @param btn
 */
function errorLastName(lastName, errorValLastName, errorlastName, btn) {
    var btnvalide = $('#' + btn);
    if (noNumber.test(errorValLastName) === false || errorValLastName.length < 3) {
        lastName.removeClass("is-valid");
        lastName.addClass("is-invalid");
        errorlastName.hidden = false;
        errorLastNameValide = "true";
        btnvalide.prop('disabled', true);
    } else {
        lastName.removeClass("is-invalid");
        lastName.addClass("is-valid");
        errorlastName.hidden = true;
        errorLastNameValide = "false"
        btnvalide.prop('disabled', false);
    }
}

/**
 * check first name : update & add new user
 * @param firstName
 * @param errorVal
 * @param errorfirstName
 * @param btn
 */
function errorFirstName(firstName, errorVal, errorfirstName, btn) {
    var btnvalide = $('#' + btn);
    if (noNumber.test(errorVal) === false || errorVal.length < 3) {
        firstName.removeClass("is-valid");
        firstName.addClass("is-invalid");
        errorfirstName.hidden = false;
        errorFirstNameValide = "true";
        btnvalide.prop('disabled', true);
    } else {
        firstName.removeClass("is-invalid");
        firstName.addClass("is-valid");
        errorfirstName.hidden = true;
        errorFirstNameValide = "false"
        btnvalide.prop('disabled', false);
    }
}

/**
 * check if vat exist & if it start by BE and has 9 chiffres
 * for add new user & update user
 * @param vat
 * @param vatVal
 * @param errorTva
 * @param errorTvaExist
 * @param errorVatValide
 * @param btn
 */
function errorVat(vat, vatVal, errorTva, errorTvaExist, errorVatValide, btn) {
    var btnvalide = $('#' + btn);
    if (!vat.val() == "") {
        if (regVat.test(vatVal) && vatVal.length == 11) {
            $.ajax({
                type: "POST",
                url: 'UserAjaxCheckVatExist',
                data: {
                    vat: vat.val(),
                },
                // dataType : 'json',
            }).done(function (data) {
                if (data === "ok") {
                    vat.removeClass("is-invalid");
                    vat.addClass("is-valid");
                    errorTva.hidden = true;
                    errorTvaExist.hidden = true;
                    errorVatValide = "false";
                    btnvalide.prop('disabled', false);
                } else if (data === "error") {
                    vat.removeClass("is-valid");
                    vat.addClass("is-invalid");
                    errorTvaExist.hidden = false;
                    errorVatValide = "true";
                    btnvalide.prop('disabled', true);
                } else {
                    vat.removeClass("is-valid");
                    vat.addClass("is-invalid");
                    errorTvaExist.hidden = true;
                    errorTva.hidden = false;
                    errorVatValide = "true";
                    btnvalide.prop('disabled', true);
                }
            });

        } else {
            vat.removeClass("is-valid");
            vat.addClass("is-invalid");
            errorTva.hidden = false;
            errorTvaExist.hidden = true;
            errorVatValide = "true";
            btnvalide.prop('disabled', true);
        }
    } else {
        vat.removeClass("is-invalid");
        vat.removeClass("is-valid");
        errorTva.hidden = true;
        errorTvaExist.hidden = true;
        errorVatValide = "false";
        btnvalide.prop('disabled', false);
    }
}

/**
 * check if password has minimum eight characters, at least one upper case French letter, one lower case French letter, one number and one special character
 * @param password
 * @param errorPass
 * @param btn
 * @param rpassword
 */
function errorPassword(password, errorPass, btn, rpassword) {
    var btnvalide = $('#' + btn);
    var pass = password.val();
    if (regPass.test(pass)) {
        password.removeClass("is-invalid");
        password.addClass("is-valid");
        errorPass.hidden = true;
        rpassword.prop("disabled", false);
        errorPasswordValide = "false";
        btnvalide.prop('disabled', false);
    } else {
        password.removeClass("is-valid");
        password.addClass("is-invalid");
        rpassword.prop("disabled", true);
        errorPass.hidden = false;
        errorPasswordValide = "true";
        btnvalide.prop('disabled', true);
    }
}

/**
 * check if rp == password
 * @param rpassword
 * @param password
 * @param errorRpass
 * @param btn
 */
function erroRpassword(rpassword, password, errorRpass, btn) {
    var btnvalide = $('#' + btn);
    var rpass = rpassword.val();
    var pass = password.val();
    if (rpass == pass) {
        console.log("les mot de passe correpond");
        rpassword.removeClass("is-invalid");
        rpassword.addClass("is-valid");
        errorRpass.hidden = true;
        errorRpPAsswordValide = "false";
        btnvalide.prop('disabled', false);
    } else {
        rpassword.removeClass("is-valid");
        rpassword.addClass("is-invalid");
        errorRpass.hidden = false;
        console.log("Password does not match")
        errorRpPAsswordValide = "true";
        btnvalide.prop('disabled', true);
    }
}

/**
 * ********************************************* *
 * ********************************************* *
 * ********************************************* *
 * ********************************************* *
 */

/**
 *  PARTIE FOURNISSEUR
 */

/**
 * Page : supplierCreate
 * Contrôle fournisseur si Existe
 * Retourne 1 = existe ou 0 = n'existe pas
 * @author Jiwaii
 */
$(document).on('keyup', '#formNewSupplierName', function () {
    let inputNewSupplierName = $(this).val().toString().trim();
    $.ajax({
        url: "SupplierExist",
        method: "POST",
        data: {
            name: inputNewSupplierName
        }
    }).done(function (data) {
            // console.log('Response Data : '+data);
            let supplierExist = parseInt(data.toString().trim());
            let inputName = $('#formNewSupplierName').val().trim();
            if (supplierExist == 1 || inputName == '') {
                $('#formNewSupplierName').attr('class', 'form-control is-invalid');
                $('#submitNewSupplier').attr('disabled', 'true');
            } else {
                $('#formNewSupplierName').attr('class', 'form-control is-valid');
                $('#submitNewSupplier').removeAttr('disabled');
            }
        }
    )
});
/**
 * Page : CommandSupplierCreate.jsp
 * Quantity control
 */
$(document).on('keyup', '.inputQuantity', function () {
    let qte = $(this).val();
    console.log('is a number : ' + !isNaN(qte));
    if (isNaN(qte)) {
        $(this).val('');//$('.inputQuantity').val('');
    }
})
/**
 * Page : CommandSupplierCreate.jsp
 * Btn Add new row product to the command
 */
$(document).on('click', '#CmdSuppAddProductToCmd', function () {
    let nbProduct = $('#tableProducts tr').length;
    $('#product1').clone().appendTo('#tableProducts');
    $('#tableProducts tr:last').attr('id', 'product' + nbProduct);
    $('#tableProducts tr:last td:first select').attr('name', 'Product' + nbProduct);
    $('#tableProducts tr:last td:last input').attr('name', 'Quantity' + nbProduct);
    $('#nbRowProduct').attr('value', nbProduct);
    console.log('bouton ajouter produit , nb produits : ' + nbProduct);

})


/**
 * Page : CommandSupplierShowAll.jsp
 * search Bar and AJAX
 */
$(document).on('keyup', '#CmdSupBchSearchBar', function () {
    $.ajax({
        url: "CommandSuppliersSearch",
        method: "POST",
        data: {
            searchBar: $('#CmdSupBchSearchBar').val()
        }
    }).done(function (data) {
        let json = data; // pas de parse Json, déjà fait dans la servlet
        console.log(json);
        $('#CmdSupListContent').empty();
        json.forEach(obj => {
            //cs.idCommandSuppliers ,s.name, cs.orderDate, csb.lotQuantity, b.idBatch, p.designation,cs.users.lastName
            $('#CmdSupListContent').append("" +
                "<tr id='" + obj[0] + "'>" +
                "<td>" + obj[0] + "</td>" +
                "<td>" + obj[1] + "</td>" +
                "<td>" + obj[2] + "</td>" +
                "<td>" + obj[3] + "</td>" +
                "</tr>");
        })
    });
});
/**
 * Page : CommandSupplierShowAll.jsp
 * Get PDF Button
 */
$(document).on('click', '#GetCmdSuppPdf', function () {
    $.ajax({
        url: "GetPdfCmdSupp",
        method: "POST",
        data: {
            searchBar: $('#CmdSupBchSearchBar').val()
        }
    }).done(function (data) {
        alert('Enregistré dans C:\\StockManagement\\ ');
    })
});

$(document).on('dblclick', '.rowCommand', function () {
    let idCmdSupp = (this).getAttribute('id');
    console.log(idCmdSupp);
    window.location.href = 'CommandSupplierBatchShowAll?idCmdSupp=' + idCmdSupp;
})




