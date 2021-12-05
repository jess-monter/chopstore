function selecionaCategoria (categoria) {
    $ ("#menu-categoria").text (categoria)
}

function buscaProductos () {
    solicitud ("post", "/busqueda", {busqueda : $ ("#entrada-busqueda").val (), categoria : $ ("#menu-categoria").text ()})
}

function consultaProducto (id) {
    solicitud ("get", "/producto", {idproducto : id})
}

function eliminarProducto (id) {
    solicitud ("post", "/elimina", {idproducto : id})
}

function solicitud (metodo, direccion, parametros) {
    const formulario = document.createElement ("form")
    formulario.method = metodo
    formulario.action = direccion
    for (const llave in parametros) {
        if (parametros.hasOwnProperty (llave)) {
            const campo = document.createElement ("input")
            campo.type = "hidden"
            campo.name = llave
            campo.value = parametros [llave]
            formulario.appendChild (campo)
        }
    }
    document.body.appendChild (formulario)
    formulario.submit ()
}