function selecionaCategoria (categoria) {
    $ ("#menu-categoria").text (categoria)
}

function buscaProductos () {
    solicitud ("post", "/busqueda", {busqueda : $ ("#entrada-busqueda").val (), categoria : $ ("#menu-categoria").text ()})
}

function consultaProducto (id) {
    solicitud ("get", "/producto", {idproducto : id})
}

function crearProducto () {
    solicitud ("get", "/crear", null)
}

function actualizaImagen () {
    let img = $ ("#imagen").val ()
    if (img != "") {
        $ ("#muestra-previa").attr ("src", img)
    }
}

function actualizaNombre () {
    let nm = $ ("#nombre").val ()
    if (nm != "") {
        $ ("#titulo").text (nm)
    }
}

function actualizarProducto (id) {
    solicitud ("post", "/actualizar", {idproducto : id})
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

// ************************************************
// Carrito de compras
// ************************************************

var shoppingCart = (function() {

    cart = [];
    
    // Constructor
    function Item(name, product_id, price, count, imagen) {
      this.name = name;
      this.product_id = product_id;
      this.price = price;
      this.count = count;
      this.imagen  = imagen;
    }
    
    // Guarda el carrito en session storage
    function saveCart() {
      sessionStorage.setItem('shoppingCart', JSON.stringify(cart));
    }
    
    // Carga el carrito
    function loadCart() {
      cart = JSON.parse(sessionStorage.getItem('shoppingCart'));
    }
    if (sessionStorage.getItem("shoppingCart") != null) {
      loadCart();
    }
    
  
    var obj = {};
    
    // Agrega al carrito
    obj.addItemToCart = function(name, product_id, price, count, imagen) {
      for(var item in cart) {
        if(cart[item].product_id === product_id) {
          cart[item].count ++;
          saveCart();
          return;
        }
      }
      var item = new Item(name, product_id, price, count, imagen);
      cart.push(item);
      saveCart();
    }

    // Cuenta la cantidad del producto en el carrito
    obj.setCountForItem = function(product_id, count) {
      for(var i in cart) {
        if (cart[i].product_id === product_id) {
          cart[i].count = count;
          break;
        }
      }
    };

    // Quita un producto del carrito
    obj.removeItemFromCart = function(product_id) {
        for(var item in cart) {
          if(cart[item].product_id === product_id) {
            cart[item].count --;
            if(cart[item].count === 0) {
              cart.splice(item, 1);
            }
            break;
          }
      }
      saveCart();
    }
  
    // Quita todo el producto del carrito
    obj.removeItemFromCartAll = function(product_id) {
      for(var item in cart) {
        if(cart[item].product_id === product_id) {
          cart.splice(item, 1);
          break;
        }
      }
      saveCart();
    }
  
    // Vacia el carrito
    obj.clearCart = function() {
      cart = [];
      saveCart();
    }
  
    // Cuenta los productos totales en el carrito
    obj.totalCount = function() {
      var totalCount = 0;
      for(var item in cart) {
        totalCount += cart[item].count;
      }
      return totalCount;
    }
  
    // Calcula el costo total del carrito
    obj.totalCart = function() {
      var totalCart = 0;
      for(var item in cart) {
        totalCart += cart[item].price * cart[item].count;
      }
      return Number(totalCart.toFixed(2));
    }
  
    // Copia el carrito
    obj.listCart = function() {
      var cartCopy = [];
      for(i in cart) {
        item = cart[i];
        itemCopy = {};
        for(p in item) {
          itemCopy[p] = item[p];
  
        }
        itemCopy.total = Number(item.price * item.count).toFixed(2);
        cartCopy.push(itemCopy)
      }
      return cartCopy;
    }

    return obj;
  })();
  
  
  // *****************************************
  // Eventos / Disparadores
  // ***************************************** 

  $('#add-to-cart').click(function(event) {
    event.preventDefault();
    console.log("Agregar al carrito");
    var name = $(this).data('name');
    var price = Number($(this).data('price'));
    var product_id = $(this).data('idproducto');
    var imagen = $(this).data('imagen');
    shoppingCart.addItemToCart(name, product_id, price, 1, imagen);
    displayCart();
  });

  $('.clear-cart').click(function() {
    shoppingCart.clearCart();
    displayCart();
  });
  
  
  function displayCart() {
    var cartArray = shoppingCart.listCart();
    var output = "";
    for(var i in cartArray) {
      output += "<tr>"
        + "<td><img widht='200' height='200' alt='Imagen del producto' src='"+ cartArray[i].imagen + "'</img></td>"
        + "<td>" + cartArray[i].name + "</td>" 
        + "<td>$" + Number(cartArray[i].price).toFixed(2) + "</td>"
        + "<td><div class='input-group'><button class='minus-item input-group-addon btn btn-claro-negro' data-idproducto=" + cartArray[i].product_id + ">-</button>"
        + "<input type='number' id='number-input' class='item-count form-control' data-idproducto='" + cartArray[i].product_id + "' value='" + cartArray[i].count + "'>"
        + "<button class='plus-item btn btn-claro-negro input-group-addon' data-idproducto=" + cartArray[i].product_id + ">+</button></div></td>"
        + "<td><button type='button' class='delete-item btn btn-danger' data-idproducto=" + cartArray[i].product_id + ">x</button></td>"
        + " = " 
        + "<td>$" + cartArray[i].total + "</td>"
        +  "</tr>";
    }
    $('.show-cart').html(output);
    $('.total-cart').html(shoppingCart.totalCart());
    $('.total-count').html(shoppingCart.totalCount());
  }

  
  $('.show-cart').on("click", ".delete-item", function(event) {
    var product_id = $(this).data('idproducto')
    shoppingCart.removeItemFromCartAll(product_id);
    displayCart();
  })
  

  $('.show-cart').on("click", ".minus-item", function(event) {
    var product_id = $(this).data('idproducto')
    shoppingCart.removeItemFromCart(product_id);
    displayCart();
  })


  $('.show-cart').on("click", ".plus-item", function(event) {
    var product_id = $(this).data('idproducto')
    shoppingCart.addItemToCart(null, product_id, null, 1, null);
    displayCart();
  })
  

  $('.show-cart').on("change", ".item-count", function(event) {
     var product_id = $(this).data('idproducto');
     var count = Number($(this).val());
    shoppingCart.setCountForItem(product_id, count);
    displayCart();
  });
  
  displayCart();
  