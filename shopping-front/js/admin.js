$(() => {
    loadOrders();
});

let loadOrders = function () {
    $.getJSON("http://localhost:8080/orders", data => {
        data.forEach(order => $('#orders table').append('<tr><td>' + order.orderId + '</td><td>' + order.customerName + '</td><td>' + getProductNames(order.products) + '</td></tr>'));
    });

    const getProductNames = products => {
        return products.map(product => product.name).reduce((names, name) => names + ', ' + name);
    }
};