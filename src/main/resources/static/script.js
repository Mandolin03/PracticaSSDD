function checkDishForm() {
    let name = document.forms["dishForm"]["name"].value;
    let category = document.forms["dishForm"]["category"].value;
    let price = document.forms["dishForm"]["price"].value;
    if (name === "" || category === "" || price === "") {
        alert("Todos los campos son obligatorios.");
        document.getElementById("name").setAttribute("placeholder", "Este campo es obligatorio.");
        document.getElementById("category").setAttribute("placeholder", "Este campo es obligatorio.");
        document.getElementById("price").setAttribute("placeholder", "Este campo es obligatorio.");

        return false;
    }
    else if(isNaN(price) || price <= 0){
        alert("El precio deber ser un real positivo.");
        let input = document.getElementById("price");
        input.setAttribute("placeholder", "Debe ser un real");
        input.setAttribute("value", "");
        return false;
    }
    return true;
}

function checkIngredientForm(){
    let name = document.forms["ingredientForm"]["name"].value;
    let category = document.forms["ingredientForm"]["category"].value;
    let origin = document.forms["ingredientForm"]["origin"].value;

    if (name === "" || category === "" || origin === "") {
        alert("Todos los campos son obligatorios.");
        document.getElementById("name").setAttribute("placeholder", "Este campo es obligatorio.");
        document.getElementById("category").setAttribute("placeholder", "Este campo es obligatorio.");
        document.getElementById("origin").setAttribute("placeholder", "Este campo es obligatorio.");
        return false;
    }
    return true;
}


function checkRestaurantForm() {
    let name = document.forms["restaurantForm"]["name"].value;
    let style = document.forms["restaurantForm"]["style"].value;
    let quality = document.forms["restaurantForm"]["quality"].value;
    let location = document.forms["restaurantForm"]["location"].value;

    if (name === "" || style === "" || quality === "" || location === "") {
        alert("Todos los campos son obligatorios.");
        document.getElementById("name").setAttribute("placeholder", "Este campo es obligatorio.");
        document.getElementById("style").setAttribute("placeholder", "Este campo es obligatorio.");
        document.getElementById("quality").setAttribute("placeholder", "Este campo es obligatorio.");
        document.getElementById("location").setAttribute("placeholder", "Este campo es obligatorio.");
        return false;
    }
    return true;
}