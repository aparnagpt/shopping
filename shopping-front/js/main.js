$(() => {
    setItem('cart', []);
    loadProducts();
    $('#products').on('click', 'li span', toggleProductDetails);
    $('#products').on('click', 'button', addToCart);
    $('#cart').on('click', checkout);
    $('#createOrder input[type=submit]').on('click', createOrder);
});

let loadProducts = () => {
    $.getJSON("http://localhost:8080/products", data => {
        setItem('products', data);
        data.forEach(product => $('#products').append('<li id="' + product.id + '"><span>' + product.name + '</span></li>'));
    });
};

let toggleProductDetails = event => {
    let productElem = $(event.target).parent();
    if (productElem.find('.productDetails').length) {
        productElem.find('.productDetails').remove();
    } else {
        getAndDisplayProductDetails(productElem.attr('id'));
    }
};

let getAndDisplayProductDetails = id => {
    $.getJSON("http://localhost:8080/product/" + id, product => {
        $('#' + product.id).append('<div class="productDetails">' +
            '<strong>Price: </strong>' + product.amount + '€<br>' +
            product.description + '<br>' +
            '<button>Add to Cart</button></div>');
    });
};

let setItem = (key, value) => {
    localStorage.setItem(key, JSON.stringify(value));
};

let getItem = key => {
    return JSON.parse(localStorage.getItem(key));
};

let addToCart = event => {
    let productId = $(event.target).closest('li').attr('id');
    let cart = getItem('cart');
    if (!cart.includes(productId)) {
        cart.push(productId);
    }
    setItem('cart', cart);
    $('#cart span').text(cart.length);
};

let checkout = () => {
    if ($('#checkout').is(":visible")) {
        $('#checkout').toggle();
        $('#products').toggle();
    } else {
        const cart = getItem('cart');
        if (cart.length) {
            $('#checkout').toggle();
            $('#products').toggle();
            const allProducts = getItem('products');
            const cartProducts = allProducts.filter(product => cart.includes(product.id.toString()));
            $('#cartProducts').empty();
            cartProducts.forEach(product => {
                $('#cartProducts').append('<li><div>' + product.name + '</div><div>' + product.amount + '€</div></li>')
            });
            const total = cartProducts.map(product => parseInt(product.amount)).reduce((total, num) => total + num);
            $('#cartTotal').html(total + '€')

        }
    }
};

let createOrder = event => {
    event.preventDefault();
    let cart = getItem('cart');
    let custName = $('#name').val();
    let custAddress = $('#address').val();
    let date = new Date();
    const order = {productId: cart, customerName: custName, address: custAddress, date: date};
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/order/',
        data: JSON.stringify(order),
        success: showCheckoutMessage,
        error: showCheckoutMessage,
        contentType: "application/json"
    });
};

const showCheckoutMessage = orderId => {
  if (Number.isInteger(orderId)) {
      $('#checkoutMessage').addClass('success').removeClass('fail').html('Order submitted. You order id is ' + orderId);
  } else {
      $('#checkoutMessage').addClass('fail').removeClass('success').html('Order creation failed. Please retry or contact support');
  }
  resetCart();
};

const resetCart = () => {
    setItem('cart', []);
    $('#cart span').text(0);
    $('#createOrder input[type=submit]').attr("disabled", "disabled");
};