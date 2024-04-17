function checkDishForm() {
    console.log("CHECKING DISH")
    let name = document.forms["dishForm"]["name"];
    let category = document.forms["dishForm"]["category"];
    let price = document.forms["dishForm"]["price"];
    if (name.value === "" || category.value === "" || price.value === "") {
        alert("Todos los campos son obligatorios.");
        name.setAttribute("placeholder", "Este campo es obligatorio.");
        category.setAttribute("placeholder", "Este campo es obligatorio.");
        price.setAttribute("placeholder", "Este campo es obligatorio.");
        return false;
    }
    const number = parseFloat(price.value);
    if (isNaN(number) || number <= 0) {
        alert("El precio deber ser un real positivo.");
        let input = document.getElementById("price");
        input.setAttribute("placeholder", "Debe ser un real");
        input.value = "";
        return false;
    }
    return true;
}

function checkIngredientForm() {
    let name = document.forms["ingredientForm"]["name"];
    let category = document.forms["ingredientForm"]["category"];
    let origin = document.forms["ingredientForm"]["origin"];

    if (name.value === "" || category.value === "" || origin.value === "") {
        alert("Todos los campos son obligatorios.");
        name.setAttribute("placeholder", "Este campo es obligatorio.");
        category.setAttribute("placeholder", "Este campo es obligatorio.");
        origin.setAttribute("placeholder", "Este campo es obligatorio.");
        return false;
    }
    return true;
}


function checkRestaurantForm() {
    let name = document.forms["restaurantForm"]["name"];
    let style = document.forms["restaurantForm"]["style"];
    let quality = document.forms["restaurantForm"]["quality"];
    let location = document.forms["restaurantForm"]["location"];

    if (name.value === "" || style.value === "" || quality.value === "" || location.value === "") {
        alert("Todos los campos son obligatorios.");
        name.setAttribute("placeholder", "Este campo es obligatorio.");
        style.setAttribute("placeholder", "Este campo es obligatorio.");
        quality.setAttribute("placeholder", "Este campo es obligatorio.");
        location.setAttribute("placeholder", "Este campo es obligatorio.");
        return false;
    }
    const number = parseInt(quality.value);
    if (number < 0 || number > 10) {
        alert("La calidad debe ser entre 0 y 10");
        quality.setAttribute("placeholder", "La calidad debe ser entre 0 y 10 ");
        quality.value = "";
        return false;
    }
    return true;
}

function searchBarFunction() {
    console.log("PROCESANDO DATOS")
    let input, filter, ul, li, la, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    ul = document.getElementById("ingredientsList");
    li = ul.getElementsByTagName("li");


    for (i = 0; i < li.length; i++) {
        la = li[i].getElementsByTagName("label")[0];
        txtValue = la.textContent || la.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}
