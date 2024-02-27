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
    else if(isNaN(price)){
        alert("El precio deber ser un real.");
        let input = document.getElementById("price");
        input.setAttribute("placeholder", "Debe ser un real");
        input.setAttribute("value", "");
        return false;
    }
    return true;
}
function checkIngredientForm(){
    let name = document.forms["dishForm"]["name"].value;
    let category = document.forms["dishForm"]["category"].value;
    if (name === "" || category === "" || price === "") {
        alert("Todos los campos son obligatorios.");
        document.getElementById("name").setAttribute("placeholder", "Este campo es obligatorio.");
        document.getElementById("category").setAttribute("placeholder", "Este campo es obligatorio.");
        return false;
    }
    return true;
}

function checkTableForm(){
    let calories = document.forms["tableForm"]["calories"].value
    let protein = document.forms["tableForm"]["calories"].value
    let carbohydrates = document.forms["tableForm"]["carbohydrates"].value
    let fats = document.forms["tableForm"]["fats"].value

    if(calories === "" || protein === "" || carbohydrates === "" || fats === ""){
        alert("Todos los campos son obligatorios.");
        document.getElementById("calories").setAttribute("placeholder", "Este campo es obligatorio.");
        document.getElementById("protein").setAttribute("placeholder", "Este campo es obligatorio.");
        document.getElementById("carbohydrates").setAttribute("placeholder", "Este campo es obligatorio.");
        document.getElementById("fats").setAttribute("placeholder", "Este campo es obligatorio.");
        return false;
    }
    return true;
}