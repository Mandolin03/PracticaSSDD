function checkDishForm() {
    let name = document.forms["dishForm"]["name"];
    let category = document.forms["dishForm"]["category"];
    let price = document.forms["dishForm"]["price"];
    let checklist = document.getElementsByName("ingredients");
    let count = 0;
    for(let i = 0; i < checklist.length; ++i){
        if(checklist[i].checked)count++;
    }
    if(count === 0){
        alert("Debes seleccionar al menos un ingrediente.");
        return false;
    }
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
    let input, filter, list;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();

    list = document.getElementsByName("ingredients");
    for(let i = 0; i < list.length; ++i){
        let label = list[i].parentNode.querySelector("label[for='" + list[i].id + "']");
        let txtValue = label.innerText || label.textContent;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            list[i].style.display = "";
            label.style.display = "";

        } else {
            list[i].style.display = "none";
            label.style.display = "none";
        }
    }

}