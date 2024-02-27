function checkForm() {
    var name = document.forms["dishForm"]["name"].value;
    var category = document.forms["dishForm"]["category"].value;
    var price = document.forms["dishForm"]["price"].value;
    if (name === "") {
        alert("Todos los campos son obligatorios.");
        var input = document.getElementById("name");
        input.setAttribute("placeholder", "Este campo es obligatorio.");
        return false;
    }
    if (category === "") {
        alert("Todos los campos son obligatorios.");
        var input = document.getElementById("category");
        input.setAttribute("placeholder", "Este campo es obligatorio.");
        return false;
    }
    if (price === "") {
        alert("Todos los campos son obligatorios.");
        var input = document.getElementById("price");
        input.setAttribute("placeholder", "Este campo es obligatorio.");
        return false;
    }
    if(isNaN(price)){
        alert("El precio deber ser un número.");
        var input = document.getElementById("price");
        input.setAttribute("placeholder", "Debe ser un número");
        input.setAttribute("value", "");
        return false;
    }
    return true;
}
